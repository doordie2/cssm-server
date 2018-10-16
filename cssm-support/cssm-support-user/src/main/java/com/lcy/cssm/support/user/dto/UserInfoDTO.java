package com.lcy.cssm.support.user.dto;

import java.util.Date;

/**

 /**
 * @Author: lcy
 * @Date: 2018/5/10 17:22
 * @Description: 用户表
 */
public class UserInfoDTO{

    private Integer userId;

    private String avatar;

    private String nickname;

    private String realname;

    private String gender;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 已发布数
     */
    private Integer issueNum;

    private String eTags;

    private String tags;

    public String getETags() {
        return eTags;
    }

    public void setETags(String eTags) {
        this.eTags = eTags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    /**
     * 获赞数
     */
    private Integer praiseNum;


    /**
     * 粉丝数
     */
    private Integer fansNum;

    /**
     * 登录次数
     */
    private Integer loginNum;


    /**
     * 登录天数
     */
    private Integer loginDays;

    private String age;



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(Integer issueNum) {
        this.issueNum = issueNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getFansNum() {
        return fansNum;
    }

    public void setFansNum(Integer fansNum) {
        this.fansNum = fansNum;
    }

    public Integer getLoginNum() {
        return loginNum;
    }

    public void setLoginNum(Integer loginNum) {
        this.loginNum = loginNum;
    }

    public Integer getLoginDays() {
        return loginDays;
    }

    public void setLoginDays(Integer loginDays) {
        this.loginDays = loginDays;
    }

    private Date birthday;

    private String mobile;

    private String password;

    private String introduction;

    private String openId;

    private Date jointime;

    private Date createTime;

    private String unionId;

    private String token;

    private String secretKey;

    private String rongyunToken;

    private String recommendCode;

    private String aliyunPushId;

    private String appOpenId;

    private String uniqueId;

    private String isAttentionGzh;

    private String userOs;

    private String agentModel;

    private Integer inblack;

    private Integer forbidden;

    private Integer recommend;

    private String level;

    private String levelIcon;

    private Integer provinceCode;

    private String provinceName;

    private Integer cityCode;

    private String cityName;

    private String momentCode;

    private String appVersion;

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getAgentModel() {
        return agentModel;
    }

    public void setAgentModel(String agentModel) {
        this.agentModel = agentModel;
    }

    public Integer getInblack() {
        return inblack;
    }

    public void setInblack(Integer inblack) {
        this.inblack = inblack;
    }

    public Integer getForbidden() {
        return forbidden;
    }

    public void setForbidden(Integer forbidden) {
        this.forbidden = forbidden;
    }

    public String getMomentCode() {
        return momentCode;
    }

    public void setMomentCode(String momentCode) {
        this.momentCode = momentCode;
    }

    public Date getJointime() {
        return jointime;
    }

    public void setJointime(Date jointime) {
        this.jointime = jointime;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Integer provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRongyunToken() {
        return rongyunToken;
    }

    public void setRongyunToken(String rongyunToken) {
        this.rongyunToken = rongyunToken;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public String getAliyunPushId() {
        return aliyunPushId;
    }

    public void setAliyunPushId(String aliyunPushId) {
        this.aliyunPushId = aliyunPushId;
    }

    public String getAppOpenId() {
        return appOpenId;
    }

    public void setAppOpenId(String appOpenId) {
        this.appOpenId = appOpenId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIsAttentionGzh() {
        return isAttentionGzh;
    }

    public void setIsAttentionGzh(String isAttentionGzh) {
        this.isAttentionGzh = isAttentionGzh;
    }

    public String getUserOs() {
        return userOs;
    }

    public void setUserOs(String userOs) {
        this.userOs = userOs;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
