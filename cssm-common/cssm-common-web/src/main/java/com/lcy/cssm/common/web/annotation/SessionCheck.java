package com.lcy.cssm.common.web.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 有此注解的需要校验用户
 * @author 王培
 * @create 2017-04-21 23:36
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SessionCheck {
    //获取菜单
    boolean getMenu() default true;


    boolean needPhone() default false;


    /**
     * 是否强制抛出错误
     * @return
     */
    boolean force() default true;

}
