package com.lcy.cssm.support.user.form;

import java.util.List;

/**
 * 后台"批量导出"表单
 */
public class BatchExportFom {

    /**
     * 选中的userids
     */
    private List<String> userIds;

    /**
     * 需导出的表格字段
     */
    private List<String> fields;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
