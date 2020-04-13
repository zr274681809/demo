package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
/** @author lyn
 * @description //TODO 用户信息实体类
 * @date 2020/4/7 14:37
*/
public class User {
    //id
    private Integer userId;
    //用户名
    private String account;
    //昵称
    private String nickName;
    //年龄
    private Integer age;
    //出生日期
    private Date birthday;
    //头像图片地址
    private String headPortraitUrl;
    //手机号
    private String phoneNumber;
    // 密码
    private String password;
    //身份证号
    private String identifyNumber;
    //性别
    private String sex;
    //兴趣爱好
    private String hobby;
    //自我介绍
    private String introduce;
    //状态
    private Integer stat;
    //最后登录时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date lastLoginTime;
    //普通设置为0
    //达人设置为1
    private Integer isMember;
    //隐私模式
    //0表示开放，只要有人申请关注，就可以通过
    //1表示私密，需要申请并由用户本人确认后，才可以通过
    private Integer privateModel;


    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Integer getPrivateModel() {
        return privateModel;
    }

    public void setPrivateModel(Integer privateModel) {
        this.privateModel = privateModel;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", headPortraitUrl='" + headPortraitUrl + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", identifyNumber='" + identifyNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby='" + hobby + '\'' +
                ", introduce='" + introduce + '\'' +
                ", stat=" + stat +
                ", lastLoginTime=" + lastLoginTime +
                ", isMember=" + isMember +
                ", privateModel=" + privateModel +
                '}';
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadPortraitUrl() {
        return headPortraitUrl;
    }

    public void setHeadPortraitUrl(String headPortraitUrl) {
        this.headPortraitUrl = headPortraitUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
