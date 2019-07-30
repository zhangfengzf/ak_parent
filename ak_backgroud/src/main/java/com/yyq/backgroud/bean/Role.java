package com.yyq.backgroud.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
@Data
@Component
public class Role implements Serializable {

    private int id;
    private String roleCode;                                        //   角色码
    private String roleName;                                        //   角色名称
    private List<User> userEntities ;
    private List<Menu> menuEntities ;

}
