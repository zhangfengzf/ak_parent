package com.yyq.backgroud.mapper;

import com.yyq.backgroud.bean.Menu;

import java.util.List;

public interface MenuMapper {
    List<Menu> findAllMenu();
    List<Menu> findAllMenuIsNotLogin();
}
