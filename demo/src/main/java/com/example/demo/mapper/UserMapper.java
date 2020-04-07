package com.example.demo.mapper;

import com.example.demo.entity.User;
/** @author lyn 
 * @description //TODO 用户数据库查询
 * @date 2020/4/7 15:18 
*/
public interface UserMapper {
    /** @description //TODO 根据用户名密码查找用户
     * @parme [username, password] 
     * @return com.example.demo.entity.User
    */
   User selectUserBynameAndPassword(String username, String password);
    /** @description //TODO 插入新用户
     * @parme [user] 
     * @return boolean
    */
    boolean insertNewOne(User user);
    /** @description //TODO 通过用户名查找用户
     * @parme [username]
     * @return com.example.demo.entity.User
    */
    User selectByUserName(String username);
}
