package com.yyq.backgroud.service.impl;


import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
   /* @Autowired
    private UserEntityRepository userEntityRepository;*/
   @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user =userMapper.getUserByName(s);
       if(user == null){
           throw  new UsernameNotFoundException("密码或者用户名错误！");
       }
        return user;
    }
}
