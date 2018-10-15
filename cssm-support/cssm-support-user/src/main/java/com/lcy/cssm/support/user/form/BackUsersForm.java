package com.lcy.cssm.support.user.form;

import java.util.List;

public class BackUsersForm {


    /**
     * 选中的userids
     */
    private List<String> userIds;

    /**
     * 0:解除禁用  1:禁用
     */
    private Integer value;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
