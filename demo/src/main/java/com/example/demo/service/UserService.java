package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/** @author lyn
 * @description //TODO 用户服务接口
 * @date 2020/4/7 15:18
*/
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
    /** @description //TODO 修改用户信息
     * @parme [user]
     * @return java.lang.String
    */
    String updateUser(User user);
    /** @description //TODO 查找用户通过账户或昵称
     * @parme [userInfo]
     * @return java.util.List<com.example.demo.entity.User>
    */
    List<User> selectByUserNameOrNickName(String userInfo);
}
