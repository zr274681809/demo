package com.example.demo.serviceImpl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User login(String account, String password) {
        return userMapper.selectUserBynameAndPassword(account, password);
    }

    @Override
    public User selectByUserName(String username) {
        return null;
    }
}
