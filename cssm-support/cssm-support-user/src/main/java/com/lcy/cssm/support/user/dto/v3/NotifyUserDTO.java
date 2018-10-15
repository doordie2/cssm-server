package com.lcy.cssm.support.user.dto.v3;

import java.util.Date;

public class NotifyUserDTO {

    private Integer notifyUserId;

    private Integer userId;

    private Date updateTime;

    private String type;

    public Integer getNotifyUserId() {
        return notifyUserId;
    }

    public void setNotifyUserId(Integer notifyUserId) {
        this.notifyUserId = notifyUserId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
