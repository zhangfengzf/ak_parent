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
        return userMapper.queryAllUser(user);
    }

    @Transactional
    @Override
    public ResponseModel addUserMessage(User user) throws Exception {

        User isuser = userMapper.getUserByName(user.getUsername());
        if (isuser != null) {
            return new ResponseModel("", "error", "该用户名已经存在！", false);
        }
        if(user.getTelphone().equals(isuser.getTelphone())){
            return new ResponseModel("", "error", "该手机号已经注册！", false);
        }
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        user.setCreateTime(timeStamp);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        /*
           String randomPassword = PasswordUtil.getRandomPassword();
           String password = encoder.encode(randomPassword);
         */

        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        userMapper.insertUser(user);
        userMapper.insertUserAndRoleByName(user.getUsername());
        return new ResponseModel("", "", "成功添加用户！", true);

    }

    @Override
    public ResponseModel deleteUserMessage(String userName) {
        userMapper.deleteUser(userName);
        return new ResponseModel("", "", "删除成功", true);
    }

    @Override
    public ResponseModel updateUserMessage(User user) {
        userMapper.updateUser(user);
        return new ResponseModel("", "", "修改资料用户成功", true);
    }

    @Override
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public void resetPassword(String name) {
      User user = userMapper.getUserByName(name);
      BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String resetPassword = bCryptPasswordEncoder.encode(password);
      user.setPassword(resetPassword);
      userMapper.updateUser(user);
    }

    @Override
    public ResponseModel resetPassword(String password, String newpassword) {
        if(password.equals(newpassword)){
            return new ResponseModel("","error","旧密码与新密码相同！",false);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String currentUserName = UserUtil.getCurrentUser();
        User user = userMapper.getUserByName(currentUserName);
        // 获取当前用户，存在在数据库中加密的密码
        String bCryptPassword = user.getPassword();
        if(!bCryptPasswordEncoder.matches(password,bCryptPassword)){
            return new ResponseModel("","error","旧密码输入错误！",false);
        }
        String newpasswordEncoder = bCryptPasswordEncoder.encode(newpassword);
        user.setPassword(newpasswordEncoder);
        userMapper.updateUser(user);
        return new ResponseModel("","","密码修改成功！",true);
    }

    @Override
    public void updateUserState(String name,String state) {
        User user = userMapper.getUserByName(name);
        user.setState(state);
        userMapper.updateUser(user);
        //  state :   1.启动     2.停用  ,且需要修改用户权限
        int roleId = "1".equals(state) ? 2 : 3;
        userMapper.updateRoleByUserName(name,roleId);
    }

    @Override
    public PageResult findAllUserByPage(User user, PageRequest pageRequest) {
        return PageUtil.getPageResult(pageRequest,getPageInfo(pageRequest,user));
    }

    public PageInfo getPageInfo(PageRequest pageRequest,User user){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum,pageSize);
        List<User> users = userMapper.findAllUserByPage(user);
        return new PageInfo(users);

    }
}
