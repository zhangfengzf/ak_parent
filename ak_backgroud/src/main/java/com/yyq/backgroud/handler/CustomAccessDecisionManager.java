package com.yyq.backgroud.handler;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) {

    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}