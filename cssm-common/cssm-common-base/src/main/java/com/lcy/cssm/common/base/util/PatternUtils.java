package com.lcy.cssm.common.base.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 正则表达式辅助类
 *
 * @author 王培
 * @create 2018-03-26 10:54
 */
public class PatternUtils {

    /** 电话格式验证 **/
    private static final String CHINA_PHONE = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";

    /**
     * 是否国内手机
     * @param str
     * @return
     * @throws PatternSyntaxException
     */
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        Pattern p = Pattern.compile(CHINA_PHONE);
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
