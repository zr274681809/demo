package com.example.demo.entity;
/** @author lyn
 * @description //TODO 点赞实体类
 * @date 2020/4/13 17:21
*/
public class Like {
    //动态/评论
    private  Integer dynamicId;
    //点赞用户
    private Integer userid;
    //0点赞动态1点赞评论
    private Integer stat;

    @Override
    public String toString() {
        return "Like{" +
                "dynamicId=" + dynamicId +
                ", userid=" + userid +
                ", stat=" + stat +
                '}';
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getStat() {
        return stat;
    }

    public void setStat(Integer stat) {
        this.stat = stat;
    }
}
