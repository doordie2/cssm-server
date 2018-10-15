package com.lcy.cssm.common.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : 赵天增
 * @create : 2018-01-10 18:13
 * 描述 ：短信代码表
 */
public enum SmsContentEnum {

    SMS_CODE_LOGIN("SMS_77505062", "验证码${code}，您正在登录每次网，若非本人操作，请勿泄露，每次网是中老年生活社交服务平台。"),

    SMS_CODE_CHANGE_MOBILE("SMS_135033904", "你正在更换绑定手机号，验证码${code}。"),

    SMS_ACTIVITY_CHECK_MOBILE("SMS_136171116", "你正在报名中老年活动，验证码${code}。"),

    SMS_BLANK("", "默认记录，空一条。");

    private String smsCode;

    private String content;

    SmsContentEnum(String smsCode, String content) {
        this.smsCode = smsCode;
        this.content = content;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public String getContent() {
        return content;
    }

    public static SmsContentEnum getResultEnum(String value) {
        for (SmsContentEnum result : values()) {
            if (StringUtils.equals(result.getSmsCode(), value)) {
                return result;
            }
        }
        return null;
    }
}
