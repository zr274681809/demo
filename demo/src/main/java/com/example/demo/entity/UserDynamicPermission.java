package com.example.demo.entity;
/** @author lyn 
 * @description //TODO 用户动态权限实体类
 * @date 2020/4/13 17:22 
*/
public class UserDynamicPermission {
    //动态发布人Id
    private  Integer userid;
    //动态Id
    private  Integer dynamicId;
    //申请查看动态的朋友Id
    private  Integer friendId;
    //权限ID
    private Integer permission;

    @Override
    public String toString() {
        return "UserDynamicPermission{" +
                "userid=" + userid +
                ", dynamicId=" + dynamicId +
                ", friendId=" + friendId +
                ", permission=" + permission +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}
