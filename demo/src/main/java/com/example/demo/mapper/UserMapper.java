package com.example.demo.mapper;

import com.example.demo.entity.User;

public interface UserMapper {
   User selectUserBynameAndPassword(String username, String password);
}
