package com.yyq.backgroud.mapper;

import com.yyq.backgroud.bean.Role;
import com.yyq.backgroud.bean.User;

import java.util.List;

public interface UserMapper {

    User getUserByName(String name);
    void insertUser(User user);
    void updateUser(User user);
    void deleteUser(String userName);
    List<User> queryAllUser(User user);
    Role findRoleByuserName(String name);
    void insertUserAndRoleByName(String name);
    void updateRoleByUserName(String username,int roleId);
    List<User> findAllUserByPage(User user);


}
