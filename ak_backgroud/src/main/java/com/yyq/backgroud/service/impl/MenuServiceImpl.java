package com.yyq.backgroud.service.impl;

import com.yyq.backgroud.bean.Menu;
import com.yyq.backgroud.mapper.MenuMapper;
import com.yyq.backgroud.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
     MenuMapper menuMapper;
    @Override
    @Cacheable(value ="getAllMenu")
    public List<Menu> findAllMenu() {

        return menuMapper.findAllMenu();
    }
}
