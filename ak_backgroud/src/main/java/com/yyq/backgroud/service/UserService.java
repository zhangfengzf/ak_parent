package com.yyq.backgroud.service;

import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.model.PageRequest;
import com.yyq.backgroud.model.PageResult;

import java.util.List;

public interface UserService {

    /**
     *   添加用户
     * @param user
     */
    Object addUserMessage(User user) throws  Exception;

    /**
     * 修改用户信息
     * @param user
     */
    Object updateUserMessage(User user);

    /**
     *   查询用户信息
     * @param
     * @return
     */
    List<User> queryAllUser(User user);

    /**
     *  删除用户信息
     * @param
     */
    Object deleteUserMessage(String userName);

    /**
     *
     * @param name 查询个人用户信息
     * @return
     */
    User getUserByName(String name);

    /**
     *  管理员重置用户密码
     * @param name
     */
    void resetPassword(String name);

    /**
     *  用户重置密码
     * @param password    旧密码
     * @param newpassword   新密码
     */
    Object resetPassword(String password,String newpassword);

    /**
     *   修改用户状态   停用  启用
     */
    void updateUserState(String name,String state);

    /**
     *  分页查询所有用户
     * @param user
     * @return
     */
    PageResult findAllUserByPage(User user, PageRequest pageRequest);
}
