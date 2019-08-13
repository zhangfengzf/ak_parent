package com.yyq.backgroud.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.mapper.UserMapper;
import com.yyq.backgroud.model.PageRequest;
import com.yyq.backgroud.model.PageResult;
import com.yyq.backgroud.model.PageUtil;
import com.yyq.backgroud.model.ResponseModel;
import com.yyq.backgroud.service.UserService;
import com.yyq.backgroud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${auto.resetpassword}")
    private String password;
    @Autowired
    UserMapper userMapper;


    @Override
    public List<User> queryAllUser(User user) {
        return userMapper.queryAllUser();
    }

    @Transactional
    @Override
    public ResponseModel addUserMessage(User user) throws Exception {

        // 1.判断用户是否已经存在
        User existUser = userMapper.getUserByName(user.getUsername());
        if (existUser != null) {
            if (user.getUsername().equals(existUser.getUsername())) {
                return new ResponseModel("", "error", "该用户名已经存在！", false);
            }
        }

        // 2.查询手机号码是否已经使用
        List<User> users = userMapper.queryAllUser();
        for (User u : users) {
            if (u.getTelphone().equals(user.getTelphone())) {
                return new ResponseModel("", "error", "该手机号已被使用！", false);
            }
        }
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        user.setCreateTime(timeStamp);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // 3. 此处设置固定密码，后续需要改成随机密码，并通知给用户
        String password = encoder.encode("666666");
        user.setPassword(password);

        // 4.添加用户到用户表中
        userMapper.insertUser(user);

        // 5.添加信息到用户角色表中
        userMapper.insertUserAndRoleByName(user.getUsername());

        return new ResponseModel("", "", "成功添加用户！", true);

    }

    @Override
    public ResponseModel deleteUserMessage(String userName) {

        int i = userMapper.deleteUser(userName);
        if (i == 1) {
            return new ResponseModel("", "", "用户: " + userName + " 删除成功!", true);
        }
        return new ResponseModel("", "error", "删除失败!", false);


    }

    @Override
    public ResponseModel updateUserMessage(User user) throws Exception {
        int num = userMapper.updateUser(user);
        if (num == 1) {
            return new ResponseModel("", "", "修改资料用户成功", true);
        }
        return new ResponseModel("", "", "修改资料失败", false);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void resetPassword(String name) {
        User user = getUserByName(name);
        user.setUserScope(user.getUserScope().equals("内部用户")?"1":"2");
        user.setUserType(user.getUserType().equals("长期用户")? "1":"2");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String resetPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(resetPassword);
        userMapper.updateUser(user);
    }

    @Override
    public ResponseModel resetPassword(String password, String newpassword) {
        if (password.equals(newpassword)) {
            return new ResponseModel("", "error", "旧密码与新密码相同！", false);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String currentUserName = UserUtil.getCurrentUser();
        User user = userMapper.getUserByName(currentUserName);
        user.setUserScope(user.getUserScope().equals("内部用户")?"1":"2");
        user.setUserType(user.getUserType().equals("长期用户")? "1":"2");
        // 1. 获取当前用户，存在在数据库中加密的密码
        String bCryptPassword = user.getPassword();
        if (!bCryptPasswordEncoder.matches(password, bCryptPassword)) {
            return new ResponseModel("", "error", "旧密码输入错误！", false);
        }
        // 2. 后续需要调用短信接口
        String newPasswordEncoder = bCryptPasswordEncoder.encode(newpassword);
        user.setPassword(newPasswordEncoder);
        userMapper.updateUser(user);
        return new ResponseModel("", "", "密码修改成功！", true);
    }

    @Transactional
    @Override
    public void updateUserState(String name, String state) {
        //User user = userMapper.getUserByName(name);
        //user.setState(state);
       // userMapper.updateUser(user);
        userMapper.updateState(name,state);
        //  state :   1.启动     2.停用  ,且需要修改用户权限
        int roleId = "1".equals(state) ? 2 : 3;
        userMapper.updateRoleByUserName(name, roleId);
    }

    @Override
    public PageResult findAllUserByPage(User user, PageRequest pageRequest) {
        return PageUtil.getPageResult(pageRequest, getPageInfo(pageRequest, user));
    }

    public PageInfo getPageInfo(PageRequest pageRequest, User user) {
        String start = "";
        String end = "";
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        if (user != null && user.getStartTime() != null) {
            String[] startTime = user.getStartTime();
            for (int i = 0; i < startTime.length; i++) {
                start = startTime[0];
                end = startTime[1];
            }
        }
        List<User> users = userMapper.findAllUserByPage(user, start, end);
        return new PageInfo(users);

    }
}
