package com.lcy.cssm.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 最新程序返回数据
 *
 * @author 王培
 * @create 2017-12-10 23:15
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnVOCheckV3 {
    ReturnVO[] value() default @ReturnVO({@ReturnVOField(
            name = "",
            alias = "",
            isConversion = false
    )});
}
