package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.FdfsUtil;
import org.apache.ibatis.annotations.Param;
import org.csource.common.MyException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class API_V1 {
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

    @RequestMapping(value = "/api/upload",method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException, MyException {
        return FdfsUtil.upLoad(file);
    }
    @RequestMapping(value = "/api/download",method = RequestMethod.GET)
    public void download(String filepath, HttpServletResponse response) throws IOException, MyException {
        byte[] bytes = FdfsUtil.downLoad(filepath);
        response.getOutputStream().write(bytes);
    }
}
