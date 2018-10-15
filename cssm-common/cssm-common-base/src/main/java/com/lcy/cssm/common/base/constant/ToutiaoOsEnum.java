package com.lcy.cssm.common.base.constant;

import org.apache.commons.lang3.StringUtils;

public enum ToutiaoOsEnum {

    ANDROID("android",0),

    IOS("iOS",1),

    WP("WP",2),

    OTHERS("Others",3);

    private String desc;
    private Integer value;

    private ToutiaoOsEnum(String desc, Integer value) {
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


    public static ToutiaoOsEnum getEnum(String value) {
        ToutiaoOsEnum resultEnum = null;
        ToutiaoOsEnum[] enumAry = ToutiaoOsEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (StringUtils.equals(String.valueOf(enumAry[i].getValue()),value)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }
}
