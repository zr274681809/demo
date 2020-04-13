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
    private Integer canSeeMe;
    //不让看状态默认0可以看1不让看
    private Integer canSeeHe;
    //备注
    private String memo;
    @Override
    public String toString() {
        return "UserRelation{" +
                "userid=" + userid +
                ", friendid=" + friendid +
                ", groupId=" + groupId +
                ", canSeeMe=" + canSeeMe +
                ", canSeeHe=" + canSeeHe +
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

    public Integer getcanSeeMe() {
        return canSeeMe;
    }

    public void setcanSeeMe(Integer canSeeMe) {
        this.canSeeMe = canSeeMe;
    }


    public Integer getcanSeeHe() {
        return canSeeHe;
    }

    public void setcanSeeHe(Integer canSeeHe) {
        this.canSeeHe = canSeeHe;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
