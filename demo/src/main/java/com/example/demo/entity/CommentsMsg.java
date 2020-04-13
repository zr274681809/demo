package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/** @author lyn
 * @description //TODO 评论实体类
 * @date 2020/4/13 15:04 
*/
public class CommentsMsg {
    //评论主键
    private Integer id ;
    //动态id/评论id
    private  Integer dynamicId;
    //评论人id
    private Integer userId;
    //评论人昵称
    private  String userNickName;
    //被评论人id
    private Integer friendId;
    //被评论人昵称
    private String  friendNickName;
    //消息内容
    private  String Msg;
    //评论时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date times;
    //0动态评论1评论评论
    private Integer stat;
    //评论点赞数
    private BigDecimal likesCount;
    //评论评论数
    private Integer comCount;

    @Override
    public String toString() {
        return "CommentsMsg{" +
                "id=" + id +
                ", dynamicId=" + dynamicId +
                ", userId=" + userId +
                ", userNickName='" + userNickName + '\'' +
                ", friendId=" + friendId +
                ", friendNickName='" + friendNickName + '\'' +
                ", Msg='" + Msg + '\'' +
                ", times=" + times +
                ", stat=" + stat +
                ", likesCount=" + likesCount +
                ", comCount=" + comCount +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getFriendNickName() {
        return friendNickName;
    }

    public void setFriendNickName(String friendNickName) {
        this.friendNickName = friendNickName;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Date getTimes() {
        return times;
    }

    public void setTimes(Date times) {
        this.times = times;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public BigDecimal getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(BigDecimal likesCount) {
        this.likesCount = likesCount;
    }

    public Integer getComCount() {
        return comCount;
    }

    public void setComCount(Integer comCount) {
        this.comCount = comCount;
    }
}
