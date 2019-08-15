package com.yyq.backgroud.mapper;

import com.yyq.backgroud.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    User getUserByName(String name);
    void insertUser(User user);
    int updateUser(User user);
    int deleteUser(@Param("username") String userName);
    List<User> queryAllUser();
    void insertUserAndRoleByName(@Param("name") String name,@Param("roleid") int roleid);
    void updateRoleByUserName(String username,int roleId);
    List<User> findAllUserByPage(@Param("user") User user,@Param("startT") String startT ,@Param("endT") String endT);
    void updateState(@Param("name") String name,@Param("state") String state);


}
