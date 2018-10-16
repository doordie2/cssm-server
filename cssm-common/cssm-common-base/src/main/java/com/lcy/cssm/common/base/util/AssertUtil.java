package com.lcy.cssm.common.base.util;

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */

import com.lcy.cssm.common.base.exceptions.McException;
import com.lcy.cssm.common.base.exceptions.ResultCode;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * 参数检查断言工具类，可以减少一些<code>if</code>代码逻辑<br>
 * 当断言不成立时，抛出指定错误代码的XkException异常
 *
 * @auther lcy
 * @create 2016-12-01 09:06
 */
public final class AssertUtil {

    /**
     * 禁用构造函数
     */
    private AssertUtil() {
        // 禁用构造函数
    }

    /**
     * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>McException</code>
     *
     * @param object
     * @param resutlCode
     * @throws McException
     */
    public static void isNotNull(Object object, ResultCode resutlCode) throws McException {
        if (object == null) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待对象为非空，如果检查的对象为<code>null</code>，抛出异常<code>McException</code>
     *
     * @param object
     * @param resutlCode
     * @param message    异常说明
     * @throws McException
     */
    public static void isNotNull(Object object, ResultCode resutlCode, String message) throws McException {
        if (object == null) {
            throw new McException(resutlCode, message);
        }
    }

    /**
     * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常<code>McException</code>
     *
     * @param text       待检查的字符串
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isNotBlank(String text, ResultCode resutlCode) throws McException {
        if (StringUtils.isBlank(text)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待字符串为非空，如果检查字符串是空白：<code>null</code>、空字符串""或只有空白字符，抛出异常<code>McException</code>
     *
     * @param text       待检查的字符串
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isNotBlank(String text, ResultCode resutlCode, String errorMsg) throws McException {
        if (StringUtils.isBlank(text)) {
            throw new McException(resutlCode, errorMsg);
        }
    }

    /**
     * 期待字符串为空，如果检查字符串是非空，抛出异常<code>McException</code>
     *
     * @param text       待检查的字符串
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isBlank(String text, ResultCode resutlCode) throws McException {
        if (StringUtils.isNotBlank(text)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待数字为0以上，如果坚持数字是0或者小于0，抛出异常<code>McException</code>
     *
     * @param num        待坚持数字
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isNotEqualOrLessThanZero(long num, ResultCode resutlCode) throws McException {
        if (0 == num || 0 > num) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期望num>=compare,否则抛异常
     *
     * @param num
     * @param compare
     * @param resultCode
     * @throws McException
     */
    public static void isNotLess(long num, long compare, ResultCode resultCode) throws McException {
        if (num < compare) {
            throw new McException(resultCode);
        }
    }

    /**
     * 期待集合对象为非空，如果检查集合对象是否为null或者空数据，抛出异常<code>McException</code>
     *
     * @param collection 集合对象
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void notEmpty(Collection<?> collection, ResultCode resutlCode) throws McException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待数组对象为非空
     *
     * @param array
     * @param resutlCode
     * @throws McException
     */
    public static void notEmpty(Object[] array, ResultCode resutlCode) throws McException {
        if (ArrayUtils.isEmpty(array)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待的正确值为true，如果实际为false，抛出异常<code>McException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isTrue(Boolean expression, ResultCode resutlCode, Object... params) throws McException {
        if (!BooleanUtils.isTrue(expression)) {
            throw new McException(resutlCode, params);
        }
    }

    /**
     * 如果2个字符串不同，抛出异常<code>McException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isEqual(Object expression, Object real, ResultCode resutlCode, Object... params) throws McException {
        if (!Objects.equals(expression, real)) {
            throw new McException(resutlCode, params);
        }
    }

    /**
     * 如果2个字符串相同，抛出异常<code>McException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isNotEqual(Object expression, Object real, ResultCode resutlCode) throws McException {
        if (Objects.equals(expression, real)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待的正确值为false，如果实际为true，抛出异常<code>McException</code>
     *
     * @param expression
     * @param resutlCode 异常代码
     * @throws McException
     */
    public static void isFalse(boolean expression, ResultCode resutlCode) throws McException {
        if (expression) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待的正确值为true，如果实际为false，抛出异常<code>McException</code>
     *
     * @param expression 表达式
     * @param resutlCode 错误代码
     * @param message    异常说明
     * @throws McException
     */
    public static void isTrue(boolean expression, ResultCode resutlCode, String message) throws McException {
        if (!expression) {
            throw new McException(resutlCode, message);
        }
    }

    /**
     * 期待的正确值为false，如果实际为true，抛出异常<code>McException</code>
     *
     * @param expression 表达式
     * @param resutlCode 错误代码
     * @param message    异常说明
     * @throws McException
     */
    public static void isFalse(boolean expression, ResultCode resutlCode, String message) throws McException {
        if (expression) {
            throw new McException(resutlCode, message);
        }
    }

    /**
     * 期待对象为空，如果检查的对象不为<code>null</code>，抛出异常<code>McException</code>
     *
     * @param object
     * @param resutlCode
     * @throws McException
     */
    public static void isNull(Object object, ResultCode resutlCode, Object... params) throws McException {
        if (object != null) {
            throw new McException(resutlCode, params);
        }
    }

    /**
     * 期待对象被包含，如果检查的对象不被包含，抛出异常<code>McException</code>
     *
     * @param expression
     * @param real
     * @param resutlCode
     * @throws McException
     */
    public static void isContain(String expression, String real, ResultCode resutlCode) throws McException {
        String[] expressions = expression.split(",");
        List<String> list = java.util.Arrays.asList(expressions);
        if (!list.contains(real)) {
            throw new McException(resutlCode);
        }
    }

    /**
     * 期待字符对象不为空，如果检查的对象为空，抛出异常<code>McException</code>
     *
     * @param resutlCode
     * @param parameters
     */
    public static void notEmptyBatch(ResultCode resutlCode, String... parameters) {
        for (String parameter : parameters) {
            if (StringUtils.isBlank(parameter)) {
                throw new McException(resutlCode);
            }
        }
    }

    public static void isEmpty(List list, ResultCode resutlCode, Object... params) {
        if (!CollectionUtils.isEmpty(list)) {
            throw new McException(resutlCode,params);
        }
    }
}
