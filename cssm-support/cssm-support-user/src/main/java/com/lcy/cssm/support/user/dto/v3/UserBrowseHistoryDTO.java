package com.lcy.cssm.support.user.dto.v3;

import java.util.Date;

public class UserBrowseHistoryDTO {

    private Integer userBrowseHistoryId;

    private Integer userId;

    private Integer historyId;

    private String historyType;

    private String createby;

    private Date updateDate;

    private String agentNum;


    public String getHistoryType() {
        return historyType;
    }

    public void setHistoryType(String historyType) {
        this.historyType = historyType;
    }

    public String getCreateby() {
        return createby;
    }

    public void setCreateby(String createby) {
        this.createby = createby;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserBrowseHistoryId() {
        return userBrowseHistoryId;
    }

    public void setUserBrowseHistoryId(Integer userBrowseHistoryId) {
        this.userBrowseHistoryId = userBrowseHistoryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }
}
