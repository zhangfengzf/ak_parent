package com.yyq.backgroud.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *  用户实体类
 */
@Data
@Component
public class User implements UserDetails {
    private Integer id;
    private String username;                         // 用户名
    private String password;                         // 密码
    private String realName ;                        // 用户姓名
    private String telphone;                         // 电话号码
    private String department;                       // 所属单位
    private String userScope;                        // 用户范围
    private String userType;                         // 用户类型
    private String state;                            // 用户状态
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;                         // 创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private String[] startTime;                        // 开始时间+结束时间
    private String isDelete;                           // 删除标记
    @JsonIgnore
    private List<Role> roles;                         // 角色

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
       List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
       if(roles != null){
           for(Role role : roles){
               grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
           }
       }

        return grantedAuthorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    /**
     * 帐户是否被冻结
      */

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        if(isDelete != null){
            if(isDelete.equals("1")){
                return  false;
            }
        }
        return true;
    }

    /**
     *   帐户是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {

        return true;
    }

    /**
     * 密码是否过期
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 帐号是否可用
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }


}
