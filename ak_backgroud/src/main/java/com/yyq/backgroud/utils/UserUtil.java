package com.yyq.backgroud.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static String getCurrentUser(){
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }
}
