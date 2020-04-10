package com.example.demo.service;
/** @author lyn
 * @description //TODO 添加好友服务接口
 * @date 2020/4/10 15:55
*/
public interface AddFriendService {
    /** @description //TODO 添加好友
     * @parme [friendAccount, groupId, isRequireUser 0 是发起人 1 不是发起人]
     * @return java.lang.String
    */
    String addUserWithBack(String friendAccount, Integer groupId, Integer isRequireUser,String memo);
}
