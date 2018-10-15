package com.lcy.cssm.common.base.constant.aliyun;

public enum SearchTypeEnum {

    SEARCH_CONTENT("内容",1),

    SEARCH_OLDFRIEND("老友圈",2),

    SEARCH_USER("用户",3),

    CONTENT_ACTIVITY("活动",4);



    private String desc;
    private Integer value;

    private SearchTypeEnum(String desc, Integer value) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
