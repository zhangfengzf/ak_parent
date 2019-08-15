package com.yyq.backgroud.service;

import com.yyq.backgroud.bean.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> findAllMenu();

    // 查询所有不需要登录的路径
    List<String> findAllMenuIsNotLogin();

}
