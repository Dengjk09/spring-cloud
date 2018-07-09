package com.dengjk.clouduserprovid.controller;

import com.dengjk.clouduserprovid.entity.UserEntity;
import com.dengjk.clouduserprovid.servier.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getUser/{id}")
    @ApiOperation("获取用户信息")
    public UserEntity getUserById(@PathVariable(value = "id") Integer id) {
        return userService.getUserById(id);
    }
}
