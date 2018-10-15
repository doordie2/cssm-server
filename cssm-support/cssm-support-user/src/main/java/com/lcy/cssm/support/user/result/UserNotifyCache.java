package com.lcy.cssm.support.user.result;

import java.io.Serializable;
import java.util.Date;

public class UserNotifyCache implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer userNotReadAttentionCount;

    private Date attentionCountUpdateTime;

    private Integer userNotReadCommentCount;

    private Date commentCountUpdateTime;

    private Integer userNotReadReplyCount;

    private Date replyCountUpdateTime;

    private Integer userNotReadMomentLikeCount;

    private Date momentLikeCountUpdateTime;

    private Integer userNotReadSystemCount;

    private Date systemCountUpdateTime;

    private Integer userNotReadTotalCount;

    public Integer getUserNotReadTotalCount() {
        return userNotReadTotalCount;
    }

    public void setUserNotReadTotalCount(Integer userNotReadTotalCount) {
        this.userNotReadTotalCount = userNotReadTotalCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Date getAttentionCountUpdateTime() {
        return attentionCountUpdateTime;
    }

    public void setAttentionCountUpdateTime(Date attentionCountUpdateTime) {
        this.attentionCountUpdateTime = attentionCountUpdateTime;
    }

    public Date getCommentCountUpdateTime() {
        return commentCountUpdateTime;
    }

    public void setCommentCountUpdateTime(Date commentCountUpdateTime) {
        this.commentCountUpdateTime = commentCountUpdateTime;
    }


    public Date getReplyCountUpdateTime() {
        return replyCountUpdateTime;
    }

    public void setReplyCountUpdateTime(Date replyCountUpdateTime) {
        this.replyCountUpdateTime = replyCountUpdateTime;
    }



    public Date getMomentLikeCountUpdateTime() {
        return momentLikeCountUpdateTime;
    }

    public void setMomentLikeCountUpdateTime(Date momentLikeCountUpdateTime) {
        this.momentLikeCountUpdateTime = momentLikeCountUpdateTime;
    }

    public Date getSystemCountUpdateTime() {
        return systemCountUpdateTime;
    }

    public void setSystemCountUpdateTime(Date systemCountUpdateTime) {
        this.systemCountUpdateTime = systemCountUpdateTime;
    }

    public Integer getUserNotReadAttentionCount() {
        return userNotReadAttentionCount;
    }

    public void setUserNotReadAttentionCount(Integer userNotReadAttentionCount) {
        this.userNotReadAttentionCount = userNotReadAttentionCount;
    }

    public Integer getUserNotReadCommentCount() {
        return userNotReadCommentCount;
    }

    public void setUserNotReadCommentCount(Integer userNotReadCommentCount) {
        this.userNotReadCommentCount = userNotReadCommentCount;
    }

    public Integer getUserNotReadReplyCount() {
        return userNotReadReplyCount;
    }

    public void setUserNotReadReplyCount(Integer userNotReadReplyCount) {
        this.userNotReadReplyCount = userNotReadReplyCount;
    }

    public Integer getUserNotReadMomentLikeCount() {
        return userNotReadMomentLikeCount;
    }

    public void setUserNotReadMomentLikeCount(Integer userNotReadMomentLikeCount) {
        this.userNotReadMomentLikeCount = userNotReadMomentLikeCount;
    }

    public Integer getUserNotReadSystemCount() {
        return userNotReadSystemCount;
    }

    public void setUserNotReadSystemCount(Integer userNotReadSystemCount) {
        this.userNotReadSystemCount = userNotReadSystemCount;
    }
}
