
package com.yyq.backgroud.filter;

import com.yyq.backgroud.bean.Menu;
import com.yyq.backgroud.bean.Role;
import com.yyq.backgroud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;
    private AntPathMatcher antPathMatcher = new AntPathMatcher();
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        List<String> allMenuIsNotLogin = menuService.findAllMenuIsNotLogin();

        String requestUrl = ((FilterInvocation)o).getRequestUrl();
        List<Menu> list = menuService.findAllMenu();
        for(Menu menu :list){
            String url = menu.getUrl();
            if(allMenuIsNotLogin.contains(url)){
                return null;
            }
            if(antPathMatcher.match(url,requestUrl) ){
               List<Role> roles = menu.getRoles();
                int size = roles.size();
                String[] roleArray = new String[size];
                for(int i = 0;i<size; i++){
                    roleArray[i] = roles.get(i).getRoleCode();
                }
               return SecurityConfig.createList(roleArray);

            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}

