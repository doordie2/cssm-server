package com.lcy.cssm.common.web.annotation;

import com.lcy.cssm.common.base.constant.aliyun.AliyunBucketEnum;

/**
 * 返回数据
 *
 * @author 王培
 * @create 2017-12-10 23:15
 **/
public @interface ReturnVOField {
    String name() default "";

    String alias() default "";

    AliyunBucketEnum bucket() default AliyunBucketEnum.BUCKET_COMMON;

    String prefix() default "";

    /***
     * 索引，用于从  string1,string2,string3  中取出第几个string
     * @return
     */
    String index() default "";

    /**
     * 扩展信息
     *
     * @return
     */
    String extend() default "";

    /**
     * 格式化
     */
    String format() default "";

    /**
     * 默认值
     */
    String defaultValue() default "";

    boolean isConversion() default false;
}
