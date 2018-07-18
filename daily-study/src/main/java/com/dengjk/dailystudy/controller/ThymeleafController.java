package com.dengjk.dailystudy.controller;

import com.dengjk.dailystudy.dao.UserRepository;
import com.dengjk.dailystudy.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ThymeleafController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user")
    public String forwardFirstIndex(ModelMap modelMap){
        UserEntity user = userRepository.findOne(1);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("name", "Dear");
        return "index";
    }
}
