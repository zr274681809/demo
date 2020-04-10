package com.example.demo.entity;
/** @author lyn
 * @description //TODO 好友实体类
 * @date 2020/4/10 15:23
*/
public class UserRelation {
    //当前用户user表主键
    private Integer userid;
    //好友user表主键
    private Integer friendid;
    //分组
    private Integer groupId;
    //屏蔽状态默认0未屏蔽1屏蔽
    private Integer stat1;
    //不让看状态默认0可以看1不让看
    private Integer stat2;
    //备注
    private String memo;
    @Override
    public String toString() {
        return "UserRelation{" +
                "userid=" + userid +
                ", friendid=" + friendid +
                ", groupId=" + groupId +
                ", stat1=" + stat1 +
                ", stat2=" + stat2 +
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStat1() {
        return stat1;
    }

    public void setStat1(Integer stat1) {
        this.stat1 = stat1;
    }


    public Integer getStat2() {
        return stat2;
    }

    public void setStat2(Integer stat2) {
        this.stat2 = stat2;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
