package com.lcy.cssm.common.base.filter;


import com.mcilife.zlnsh.common.base.constant.aliyun.AliyunBucketEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 赵天增
 * @create : 2017/11/11
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface UrlResource {
    /**
     * 默认的url地址
     *
     * @return
     */
    AliyunBucketEnum url() default AliyunBucketEnum.BUCKET_COMMON;

    /**
     * 分隔符
     */
    String split() default ",";

    boolean front() default false;

    /**
     * 是否需要分割
     */
    boolean needSplit() default false;

    String prefix() default "";
}
