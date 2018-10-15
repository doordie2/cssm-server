package com.lcy.cssm.common.base.constant.aliyun;

import org.apache.commons.lang3.StringUtils;

/**
 * 业务关联的bucket
 *
 * @author 王培
 * @create 2017-08-03 10:45
 **/
public enum AliyunBucketBusinessEnum {

    MOMENT_BUCKET("moment",AliyunBucketEnum.INTERACTION_RESOURCE,"moment"),
    USER_AVATAR("userAvatar",AliyunBucketEnum.BUCKET_COMMON,"user/avatar"),
    BLINDDATE_BUCKET("blindDate",AliyunBucketEnum.ACTIVITY_RESOURCE,"blindDate");




    private String type;

    /**
     * 枚举值
     */
    private AliyunBucketEnum bucket;

    /**
     * 描述
     */
    private String folder;

    AliyunBucketBusinessEnum(String type, AliyunBucketEnum bucket, String folder) {
        this.type = type;
        this.bucket = bucket;
        this.folder = folder;
    }


    public String getType() {
        return type;
    }

    public AliyunBucketEnum getBucket() {
        return bucket;
    }

    public String getFolder() {
        return folder;
    }

    public static AliyunBucketBusinessEnum getEnum(String type) {
        AliyunBucketBusinessEnum resultEnum = null;
        AliyunBucketBusinessEnum[] enumAry = AliyunBucketBusinessEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (StringUtils.equals(enumAry[i].getType(), type)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }

}
