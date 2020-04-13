package com.example.demo.entity;

import java.util.Date;

/** @author lyn
 * @description //TODO 添加好友实体类
 * @date 2020/4/10 15:50
*/
public class AddFriendRecord {
    //发起用户
    private Integer userid;
    //接受用户
    private Integer friendid;
    //默认0请求中1接受2拒绝
    private Integer stat;
    //请求时间
    private Date time;
    //分组id
    private Integer groupid;
    //备注名
    private String memo;

    @Override
    public String toString() {
        return "AddFriend{" +
                "userid=" + userid +
                ", friendid=" + friendid +
                ", stat=" + stat +
                ", time=" + time +
                ", groupid=" + groupid +
                ", memo='" + memo + '\'' +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getFriendid() {
        return friendid;
    }

    public void setFriendid(Integer friendid) {
        this.friendid = friendid;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getGroupid() {
        return groupid;
    }

    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
