package com.lcy.cssm.support.user.exceptions;


import com.lcy.cssm.common.base.exceptions.ResultCode;
import com.lcy.cssm.common.base.exceptions.ResultCodeType;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户模块错误码返回类
 * @author lcy
 * @create 2016-12-01 18:45
 **/
public enum AuthResultCode implements ResultCode {

    USER_SMS_SEND_ERROR("2051", "一分钟内只能发送一次验证码"),
    USER_EXIST_ERROR("2052", "用户不存在"),
    USER_ACCOUNT_ERROR("2053", "用户已注册"),
    USER_PASSWORD_ERROR("2054", "密码错误"),
    USER_UPDATE_PASSWORD_ERROR("2056", "旧密码输入有误"),
    USER_UPDATE_PASSWORD_BLANK("2057", "请设置密码"),
    USER_SMS_ERROR("2006", "验证码错误"),
    USER_MOBILE_ERROR("2055", "该手机号已绑定其他账户"),

    SUCCESS("1", "处理成功");

    /** 错误码 */
    private String code;

    /** 描述 */
    private String desc;

    /**
     * 构造函数
     *
     * @param code  错误码
     * @param desc  描述
     */
    private AuthResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过name获取结果码
     *
     * @param code  错误码
     * @return      返回业务结果码
     */
    public static AuthResultCode getResultEnum(String code) {
        for (AuthResultCode result : values()) {
            if (StringUtils.equals(result.getCode(), code)) {
                return result;
            }
        }
        return null;
    }

    @Override
    public ResultCodeType getType() {
        return ResultCodeType.USER;
    }
    @Override
    public String getCode() {
        return  this.code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
