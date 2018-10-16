package com.lcy.cssm.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 返回数据
 *
 * @author lcy
 * @create 2017-12-10 23:15
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnVOCheck {
    ReturnVO[] value() default @ReturnVO({@ReturnVOField(
            name = "",
            alias = "",
            isConversion = false
    )});
}
