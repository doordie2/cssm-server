package com.lcy.cssm.common.base.po;


/**
 * @author: 赵天增
 * @date: 2018 -02-06
 * @描述: 基础状态
 */
public class BaseDictionaryPO {
    private String key;

    private String value;

    private String reviewReason;

    public BaseDictionaryPO() {
    }

    public BaseDictionaryPO(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
