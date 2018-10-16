package com.lcy.cssm.common.base.constant.aliyun;

import org.apache.commons.lang3.StringUtils;

/**
 * 推送类别
 *
 * @author lcy
 * @create 2017-08-03 10:45
 **/
public enum AliyunBucketEnum {

    BUCKET_WEB_RESOURCE("1", "mcwebresource", "https://mcwebresource.mcilife.com/"),

    BUCKET_COMMON("2", "mcresource", "https://mcresource.mcilife.com/"),

    BUCKET_WEB_IMG("3","mcimgresource","http://appattach.mcilife.com/"),

    INTERACTION_RESOURCE("4","interactionresource","https://interaction.mcilife.com/"),

    ACTIVITY_RESOURCE("5","mcactivityresource","https://mcactivityresource.mcilife.com/"),


    ;




    private String type;

    /**
     * 枚举值
     */
    private String name;

    /**
     * 描述
     */
    private String url;

    private AliyunBucketEnum(String type, String name, String url) {
        this.type = type;
        this.name = name;
        this.url = url;
    }


    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public static AliyunBucketEnum getEnum(String type) {
        AliyunBucketEnum resultEnum = null;
        AliyunBucketEnum[] enumAry = AliyunBucketEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (StringUtils.equals(enumAry[i].getType(), type)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

}
