package com.lcy.cssm.common.base.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author : 赵天增
 * @create : 2017-11-12 14:59
 * 描述 ：代码翻译类
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface CodeResource {
    /**
     * 代码类型--
     * 默认为code ---- DictionaryDTO
     * disease --- 疾病代码 ---  DiseaseItem
     * city --- 城市代码  ---   CommonAreaItem
     *
     */
    String type() default "";

    String root() default  "";

    String parent() default  "";

    String valueType() default "";
}
