package com.lcy.cssm.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 打开页面的校验
 * @author lcy
 * @create 2017-04-21 23:36
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PageSessionCheck {
    //获取菜单
    boolean getMenu() default true;
}
