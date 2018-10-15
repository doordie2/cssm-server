package com.lcy.cssm.common.base.constant;

import java.util.Objects;

/**
 * @author : 赵天增
 * @create : 2017-12-04 10:14
 * 描述 ：
 */
public enum UserTypeEnum {
    // 用户
    USER(1, "user"),
    // 管理员
    ADMIN(0, "admin");
    private String desc;
    private Integer value;

    UserTypeEnum(Integer value, String desc) {
        this.desc = desc;
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getValue() {
        return value;
    }

    public static UserTypeEnum getEnum(Integer value) {
        UserTypeEnum userTypeEnum = null;
        UserTypeEnum[] enumAry = UserTypeEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (Objects.equals(enumAry[i].getValue(), value)) {
                userTypeEnum = enumAry[i];
                break;
            }
        }
        return userTypeEnum;
    }
    public static UserTypeEnum getEnumByDesc(String desc) {
        UserTypeEnum userTypeEnum = null;
        UserTypeEnum[] enumAry = UserTypeEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (Objects.equals(enumAry[i].getDesc(), desc)) {
                userTypeEnum = enumAry[i];
                break;
            }
        }
        return userTypeEnum;
    }
}
