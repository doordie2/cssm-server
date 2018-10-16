package com.lcy.cssm.common.web.annotation;

/**
 * 返回数据
 *
 * @author lcy
 * @create 2017-12-10 23:15
 **/
public @interface ReturnVO {
    ReturnVOField[] value();

    Class<?> targetClass() default Void.class;

    Class<?> srcClass() default Void.class;

    String parent() default "";

}
