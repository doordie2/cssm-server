package com.lcy.cssm.support.user.vo.v3;

import com.alibaba.fastjson.annotation.JSONField;
import com.mcilife.zlnsh.common.base.filter.*;

import java.util.Date;
import java.util.List;

/**

 /**
 * @Author: 王培
 * @Date: 2018/5/10 17:22
 * @Description: 用户表
 */
public class UserInfoVO {

    @UrlResource
    private String avatar;

    private String nickname;


    private String userDescription;

    private String gender;

    @JSONField(format = "yyyy-MM-dd")
    private Date birthday;

    private String mobile;

    private String introduction;

    private String openId;

    private String unionId;

    private String token;

    private String secretKey;

    @StringToList
    private List<String> tagList;

    @StringToList
    private List<String> eTagList;

    private String rongyunToken;

    private String aliyunPushId;

    private String uniqueId;

    @NullToZero
    private String isAttention;

    private String isAttentionGzh;

    @CodeResource(root = "user",parent = "level",valueType = "value",type = "dictionary")
    private String levelIcon;

    private Integer provinceCode;

    private String provinceName;

    private Integer cityCode;

    private String cityName;

    private String momentCode;

    @AgeResource
    private String age;

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription;
    }

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMomentCode() {
        return momentCode;
    }

    public void setMomentCode(String momentCode) {
        this.momentCode = momentCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getRongyunToken() {
        return rongyunToken;
    }

    public void setRongyunToken(String rongyunToken) {
        this.rongyunToken = rongyunToken;
    }

    public String getAliyunPushId() {
        return aliyunPushId;
    }

    public void setAliyunPushId(String aliyunPushId) {
        this.aliyunPushId = aliyunPushId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }

    public String getIsAttentionGzh() {
        return isAttentionGzh;
    }

    public void setIsAttentionGzh(String isAttentionGzh) {
        this.isAttentionGzh = isAttentionGzh;
    }

    public String getLevelIcon() {
        return levelIcon;
    }

    public void setLevelIcon(String levelIcon) {
        this.levelIcon = levelIcon;
    }
}
