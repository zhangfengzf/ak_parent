package com.yyq.backgroud.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 *   权限决策 AccessDecisionManager
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        Iterator<ConfigAttribute> iterable =collection.iterator();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(iterable.hasNext()){
            ConfigAttribute configAttribute = iterable.next();
            String config= configAttribute.getAttribute();
            if (authentication instanceof AnonymousAuthenticationToken) {
                throw new BadCredentialsException("当前账号未登录!");
            }
            for(GrantedAuthority grantedAuthority :authorities){
                if(config.equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("当前访问没有权限!");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }
}
