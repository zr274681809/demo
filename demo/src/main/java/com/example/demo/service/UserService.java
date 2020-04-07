package com.example.demo.service;

import com.example.demo.entity.User;


public interface UserService {
    /** @description //TODO 用户登录
     * @parme [account, password]
     * @return com.example.entity.User
    */
    String login(String account, String password);
    /** @description //TODO 权限验证获取用户
     * @parme [username]
     * @return com.example.demo.entity.User
    */
    User selectByUserName(String username);
    /** @description //TODO 用户注册
     * @parme [user] 
     * @return java.lang.String
    */
    String register(User user);

}