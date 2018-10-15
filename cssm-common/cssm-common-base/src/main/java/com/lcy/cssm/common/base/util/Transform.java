package com.lcy.cssm.common.base.util;

import java.text.ParseException;

/**
 * @author: 赵天增
 * @create : 2017/11/13
 * 描述：转换
 */
public interface Transform<T, O> {
    /**
     * @param srcClass 原类
     * @param targetClass 要拷贝的目标类
     */
    void transform(T srcClass, O targetClass) throws ParseException;
}
