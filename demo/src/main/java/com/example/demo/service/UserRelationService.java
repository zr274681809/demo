package com.example.demo.service;


import com.example.demo.entity.UserRelation;

import java.util.List;

/** @author lyn
 * @description //TODO 好友服务
 * @date 2020/4/10 15:21
*/
public interface UserRelationService {
    /** @description //TODO 删除好友
     * @parme [friendAccount] 
     * @return java.lang.String
    */
    String deleteFriend(String friendAccount);

    List<UserRelation> getFriends();
}
