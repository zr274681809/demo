package com.example.demo.service;

import com.example.demo.entity.User;


public interface UserService {
    /** @description //TODO 用户登录
     * @parme [account, password]
     * @return com.example.entity.User
    */
    User login(String account, String password);

}
