package com.dengjk.dailystudy.controller;

import com.dengjk.dailystudy.dao.UserRepository;
import com.dengjk.dailystudy.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Api(value = "thymeleaf模板",description = "thymeleaf模板测试")
public class ThymeleafController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ApiOperation(value = "跳转到首页")
    public String forwardFirstIndex(ModelMap modelMap){
        UserEntity user = userRepository.findOne(1);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("name", "Dear");
        return "index";
    }
}
