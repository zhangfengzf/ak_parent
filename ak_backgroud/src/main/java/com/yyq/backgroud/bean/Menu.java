package com.yyq.backgroud.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 *  菜单实体类
 */
@Data
@Component
public class Menu implements Serializable {

    private Integer id;
    private String name;                          // 菜单名
    private String url;                           // 路径
    private String isLogin;                        // 是否需要登录
    @JsonIgnore
    private List<Role> roles ;                    // 角色


}
