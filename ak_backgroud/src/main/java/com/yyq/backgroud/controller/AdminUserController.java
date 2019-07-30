package com.yyq.backgroud.controller;

import com.yyq.backgroud.bean.User;
import com.yyq.backgroud.model.PageRequest;
import com.yyq.backgroud.model.RequestModel;
import com.yyq.backgroud.model.ResponseModel;
import com.yyq.backgroud.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Api(value = "用户管理Api" ,description = "用户管理接口，管理员操作")
@RequestMapping("yyq")
@RestController
public class AdminUserController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "添加用户",notes = "只有管理员有权限添加用户")
    @PostMapping(value="/add",consumes ="application/json")
    public ResponseModel addUser(@ApiParam(name = "user",value = "user用户信息",required = true)
                                     @RequestBody User user){
        try{
         return    (ResponseModel) userService.addUserMessage(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseModel("","error","添加失敗！",false);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/update" ,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@ApiParam(name = "user",value = "user用户信息",required = true)
                                     @RequestBody User user){
        return ResponseEntity.ok( userService.updateUserMessage(user));
    }
    @ApiOperation(value = "删除用户信息",notes = "只有管理员有权限")
    @GetMapping (value = "/delete")
    public ResponseEntity deleteUser(@ApiParam(name = "username",value = "用户名",required = true)
                                     @RequestParam("username") String username){
        return ResponseEntity.ok(userService.deleteUserMessage(username));
    }

    @ApiOperation(value = "查询用户信息",notes = "根据用户名查询用户信息")
    @GetMapping (value = "/queryUser")
    public ResponseEntity query(@ApiParam(name = "username",value = "用户名",required = true)
                                @RequestParam("username") String username){
        return ResponseEntity.ok( new ResponseModel(userService.getUserByName(username),"","",true));
    }

    @ApiOperation(value = "用户列表",notes = "分页查询所有用户，并支持模糊查询")
    @PostMapping(value = "/queryAllUserByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryAllUserByPage(@RequestBody  RequestModel requestModel){
        User user = requestModel.getUser();
        PageRequest pageRequest = requestModel.getPageRequest();
       return ResponseEntity.ok(new ResponseModel( userService.findAllUserByPage(user,pageRequest),"","",true));

    }

    @ApiOperation(value = "重置用户密码",notes = "只有管理员有权限")
    @GetMapping (value = "/resetpassword")
    public ResponseEntity resetPassword(@ApiParam(name = "username",value = "用户名",required = true)
                                     @RequestParam("username") String username){
        userService.resetPassword(username);
        return ResponseEntity.ok( new ResponseModel("","","重置密码成功！",true));
    }
    @ApiOperation(value = "用户状态",notes = "修改用户状态，设置停用或者启用状态")
    @GetMapping (value = "/userState")
    public void updateUserState(@ApiParam(name = "username",value = "用户名",required = true)
                                    @RequestParam("username") String username,
                                @ApiParam(name = "state",value = "用户状态",required = true)
                                @RequestParam("state") String state){
        userService.updateUserState(username,state);

    }

    /**
     *   不支持分页，弃用
     * @param user
     * @return
     */
    @ApiOperation(value = "查询所有用户信息",notes = "只有管理员有权限")
    @PostMapping(value = "/queryAll",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity queryAllUser(
            @ApiParam(name = "user",value = "user")
            @RequestBody(required=false) User user){
        return ResponseEntity.ok(userService.queryAllUser(user));
    }

}
