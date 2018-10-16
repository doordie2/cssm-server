package com.lcy.cssm.common.base.exceptions;

import com.lcy.cssm.common.base.constant.CommonConstant;
import org.apache.commons.lang3.StringUtils;

/**
 * 通用错误码返回类
 *
 * @author zxq
 * @create 2017-12-22
 **/
public enum CommonResultCode implements ResultCode {

    AUTH_NOT_ALLOW("5000", "没有权限访问"),
    USER_REQUEST_EXPIRED("5001", "用户请求时间超期"),
    USER_REUSED_NONCE("5002", "用户重复请求"),
    USER_SIGN_DIFF("5003", "签名不一致"),
    USER_NOT_EXIST("5004", "您还没有登入，请登入"),
    DOCTOR_NOT_EXIST("5005", "医生不存在或者没有登入"),
    ADMIN_NOT_EXIST("5006", "管理员不存在或者没有登入"),
    WECHAT_NOT_EXIST("5007", "用户wechat_id为空"),
    RONGYUN_ERROR("5008", "注册融云用户失败"),
    SMS_ERROR("5009", "验证码错误"),
    USER_SMS_SEND_ERROR ("5010", "一分钟内只能发送一次验证码"),
    USER_SMS_ERROR("5011", "验证码错误"),

    USER_LIKE_REPEAT("5012","用户已点赞"),

    USER_ACTIVITYSIGN_REPEAT("5013","用户活动已报名"),
    VOTE_NUM_OVER("5014","今日投票数已用完"),
    USER_MUST_MOBILE("5015","请绑定手机号"),
    APP_MUST_UPDATE("5016","必须更新app版本才能使用该功能"),

    COMMON_FAIL_ERROR("-1", "处理失败"),
    PARAMETER_FAIL_ERROR("-2", "传入参数异常"),

    USER_LIKE_COLLECT("5017","用户已收藏"),
    USER_LIKE_ATTENTION("5019","用户已关注"),


    BLIND_DATE_LOCATION_CANT_EMPTY("2000","所在城市-必填"),
    BLIND_DATE_GENDER_CANT_EMPTY("2001","性别-必填"),
    BLIND_DATE_PHOTOS_CANT_EMPTY("2002","照片-必填"),
    BLIND_DATE_AGE_CANT_EMPTY("2003","年龄-必填"),
    BLIND_DATE_HEIGHT_CANT_EMPTY("2004","身高-必填"),
    BLIND_DATE_WEIGHT_CANT_EMPTY("2005","体重-必填"),
    BLIND_DATE_MARRIAGE_CANT_EMPTY("2006","婚姻情况-必填"),
    BLIND_DATE_INCOME_CANT_EMPTY("2007","月收入-必填"),
    BLIND_DATE_CHILD_CANT_EMPTY("2008","孩子情况-必填"),
    BLIND_DATE_NOT_BELONG_YOU("2012","这不是您的相亲资料"),
    BLIND_DATE_EXIST("2009","您已发布相亲资料！"),
    BLIND_DATE_NOT_EXIST("2010","该相亲资料已下架!"),
    BLIND_DATE_YOU_SENT("2011","您已送花成功!"),


    MOMENT_CONTENT_CANT_EMPTY("5020","老友圈内容不能为空"),
    APP_ALMOST_NEW("5021","APP已经是最新版本"),
    MOMENT_COMMENT_CANT_EMPTY("5022","老友圈评论内容不能为空"),
    WECHAT_TEMPLATE_ERROR("5023","获取微信模板id失败"),
    WECHAT_SEND_TEMPLATE_ERROR("5024","发送微信模板失败"),
    BUSINESS_BUCKET_ERROR("5025","获取业务的bucket失败"),
    OTHER_USER_NOT_EXIST("5026","对方用户不存在"),
    IS_NO_CAPTAIN("5027","不是队长"),
    TOKEN_IS_INVALID("5028","token已经失效"),
    MOMENT_HAS_DELETE("5029","老友圈已经被删除"),
    MOMENT_CANT_COMMENT("5030","老友圈已经被删除或举报，不能评论"),
    FILE_NOT_FOUND("5031","文件不存在"),
    ADD_ALBUM_FAIL("5032","添加专题失败"),
    USER_NOT_LIKE("5033","用户未点赞"),
    USER_NOT_ATTENTION("5034","用户未关注"),
    USER_NOT_COLLECTION("5035","用户未收藏"),
    RESOURCE_TYPE_ERROR("5036","传入了错误的资源类型"),
    YOU_ARE_FORBIDDEN("5037","抱歉，账号已被禁用!"),
    LIKE_NOT_IN_USE("5038","系统升级中，点赞功能暂时关闭，下周将开放!"),
    THIRD_NOT_RIGHT("5039","查不到对应的第三方!"),
    SYSTEM_BUSY("5040","系统繁忙，请稍后重试!"),

    BLIND_DATE_STOP("6000","该用户已关闭相亲"),
    SUCCESS("1", "处理成功");

    /**
     * 错误码
     */
    private String code;

    /**
     * 描述
     */
    private String desc;

    /**
     * 构造函数
     *
     * @param code 错误码
     * @param desc 描述
     */
    CommonResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过name获取结果码
     *
     * @param code 错误码
     * @return 返回业务结果码
     */
    public static CommonResultCode getResultEnum(String code) {
        for (CommonResultCode result : values()) {
            if (StringUtils.equals(result.getCode(), code)) {
                return result;
            }
        }
        return null;
    }

    public ResultCodeType getSuccessType() {
        return ResultCodeType.SYSTEM_SUCCESS;
    }

    @Override
    public ResultCodeType getType() {
        return ResultCodeType.SYSTEM_ERROR;
    }

    @Override
    public String getCode() {
        if (StringUtils.equals(this.code, CommonConstant.TYPE_1)) {
            return this.getSuccessType().getCode() + this.code;
        }
        return this.code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

}
