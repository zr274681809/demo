package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/** @author lyn
 * @description //TODO 动态信息
 * @date 2020/4/7 14:38 
*/
public class PersonDynamic {
    //动态唯一主键
    private Integer id;
    //用户id
    private Integer user_id;
    //内容
    private String textContent;
    //视频路径
    private String videoUrl;
    //图片路径
    private String imageUrls;
    //点赞数
    private BigDecimal likesCount;
    //评论数
    private BigInteger commentsCount;
    //发布时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GTM+8")
    private Date submitsTime;

    @Override
    public String toString() {
        return "PersonDynamic{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", textContent='" + textContent + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", imageUrls='" + imageUrls + '\'' +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", submitsTime=" + submitsTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public BigDecimal getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(BigDecimal likesCount) {
        this.likesCount = likesCount;
    }

    public BigInteger getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(BigInteger commentsCount) {
        this.commentsCount = commentsCount;
    }

    public Date getSubmitsTime() {
        return submitsTime;
    }

    public void setSubmitsTime(Date submitsTime) {
        this.submitsTime = submitsTime;
    }
}
