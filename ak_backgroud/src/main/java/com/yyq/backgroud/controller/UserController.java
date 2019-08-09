package com.yyq.backgroud.controller;

import com.yyq.backgroud.model.ResponseModel;
import com.yyq.backgroud.service.UserService;
import com.yyq.backgroud.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@Api(value = "个人信息", description = "个人信息接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "个人信息", notes = "查询个人信息")
    @PostMapping(value = "/queryUser")
    public ResponseEntity query() {
        String currentUsername = UserUtil.getCurrentUser();
        userService.getUserByName(currentUsername);
        return ResponseEntity.ok(new ResponseModel(userService.getUserByName(currentUsername), "", "", true));
    }

    @ApiOperation(value = "修改密码")
    @GetMapping(value = "/resetPassword")
    public ResponseEntity resetPassword(@RequestParam("password") String password,@RequestParam("newpassword") String newpassword) {

        return ResponseEntity.ok(userService.resetPassword(password, newpassword));
    }

}
