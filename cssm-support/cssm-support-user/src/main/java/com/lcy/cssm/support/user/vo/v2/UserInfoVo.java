package com.lcy.cssm.support.user.vo.v2;

import com.lcy.cssm.common.base.filter.StringToList;
import com.lcy.cssm.common.base.filter.UrlResource;

import java.util.List;

public class UserInfoVo {

    private Integer userId;

    private String userNickname;

    @UrlResource
    private String userAvatar;

    @StringToList
    private List<String> tagList;

    @StringToList
    private List<String> eTagList;

    private String userLevel;

    private String userLevelIcon;

    private String userGender;

    private String momentCode;

    private String isAttention;

    private String showTime;

    private String userUniqueId;

    private String userDescription;

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public List<String> getETagList() {
        return eTagList;
    }

    public void setETagList(List<String> eTagList) {
        this.eTagList = eTagList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }

    public String getUserLevelIcon() {
        return userLevelIcon;
    }

    public void setUserLevelIcon(String userLevelIcon) {
        this.userLevelIcon = userLevelIcon;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getMomentCode() {
        return momentCode;
    }

    public void setMomentCode(String momentCode) {
        this.momentCode = momentCode;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }
}
