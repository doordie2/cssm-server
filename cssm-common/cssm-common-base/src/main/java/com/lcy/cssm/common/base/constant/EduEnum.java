package com.lcy.cssm.common.base.constant;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

public enum EduEnum {

    UN_LIMIT(-1,"un_limit"),

    PRIMARY_SCHOOL(1,"primary_school"),

    JUNIOR_SCHOOL(2,"junior_school"),

    POLYTECHNIC_SCHOOL(3,"polytechnic_school"),

    SENIOR_SCHOOL(4,"senior_school"),

    JUNIOR_COLLEGE(5,"junior_college"),

    UNDERGRADUATE(6,"undergraduate"),

    MASTER(7,"master"),

    DOCTOR(8,"doctor");




    private Integer value;

    /**
     * 枚举值
     */
    private String name;


    private EduEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }


    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }


    public static EduEnum getEnumByName(String name) {
        EduEnum eduEnum = null;
        EduEnum[] values = EduEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (StringUtils.equals(values[i].getName(), name)) {
                eduEnum = values[i];
                break;
            }
        }
        return eduEnum;
    }


    public static EduEnum getEnumByValue(Integer value) {
        EduEnum eduEnum = null;
        EduEnum[] values = EduEnum.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue().intValue()==value.intValue()) {
                eduEnum = values[i];
                break;
            }
        }
        return eduEnum;
    }

}
