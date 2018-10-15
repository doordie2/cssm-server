package com.lcy.cssm.common.base.exceptions;


import org.apache.commons.lang3.StringUtils;

/**
 * 结果码类型
 * @author 王培
 * @create 2017-07-16 17:15
 */
public enum ResultCodeType {

    USER("USER_", "USER模块错误码前缀"),

    BASE("BASE_", "BASE模块错误码前缀"),

    AUTH("AUTH_", "AUTH模块错误码前缀"),

    WECHAT("WECHAT_", "WECHAT应用端错误码前缀"),

    MONITOR("MONITOR_", "MONITOR模块错误码前缀"),

    NOTIFY("NOTIFY_", "NOTIFY模块错误码前缀"),

    ZUUL_ERROR("ZUUL_ERROR", "zuul网关错误码前缀"),

    SYSTEM_SUCCESS("SYSTEM_SUCCESS", "系统正常"),

    SYSTEM_ERROR("SYSTEM_ERROR", "系统异常"),
    ;

    /** 结果类型code，将作为结果码的前缀 */
    private String code;

    /** 描述 */
    private String desc;

    /**
     * 构造函数
     *
     * @param code
     *            结果类型code
     * @param desc
     *            描述
     */
    private ResultCodeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过name获取结果码
     * 
     * @param code  错误码
     * @return      返回业务结果码
     */
    public static ResultCodeType getTypeEnum(String code) {
        for (ResultCodeType type : values()) {
            if (StringUtils.equals(type.getCode(), code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
