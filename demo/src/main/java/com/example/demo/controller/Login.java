package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Login {
    @Resource
    UserService userService;

    @RequestMapping(value = "/api/login",method = RequestMethod.GET)
    public String login(String account, String password){
        return  userService.login(account, password);
    }

    @RequestMapping(value = "/api/user",method = RequestMethod.POST)
    public String register(User user){
        return userService.register(user);
    }
}
