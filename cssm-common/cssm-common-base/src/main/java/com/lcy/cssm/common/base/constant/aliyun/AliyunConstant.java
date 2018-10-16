package com.lcy.cssm.common.base.constant.aliyun;

/**
 * 阿里云的常量
 *
 * @author lcy
 * @create 2017-09-16 10:47
 **/
public interface AliyunConstant {

    /**
     * oss bucket常量
     **/
    String COMMON_BUCKET = "https://mcresource.mcilife.com/";

    /**
     * 云市场的app code,现在用不到appkey和appsecret
     **/
    String ALIYUN_MARKET_APP_CODE = "c451b1f780214af48c38b1f4ce4013ad";

    /**
     * 云市场域名
     **/
    String ALIYUN_MARKET_ALI_URL = "dm-81.data.aliyun.com";

    String ALIYUN_MARKET_ALI_URL_EXPRESS = "wuliu.market.alicloudapi.com";

    /**
     * 云市场path
     **/
    String ALIYUN_MARKET_IP_PATH = "/rest/160601/ip/getIpInfo.json";
    String ALIYUN_MARKET_EXPRESS_PATH = "/kdi";

    /**
     * 阿里云账号的accessId和accessKey
     **/
    String ALIYUN_YJY_ACCESSID = "LTAIkOKBmr5diGpG";
    String ALIYUN_YJY_ACCESSKEY = "D6114ODVhb0bSTHJWfztHignYIXWr9";


    /**
     * 阿里云推送的regionId
     **/
    String ALIYUN_REGIONID_HANGZHOU = "cn-hangzhou";


    /**
    *产品名称:云通信短信API产品,开发者无需替换
     */
    String SMS_PRODUCT = "Dysmsapi";

    /**
     * 短信回执
     **/
    String SMS_REPORT = "SmsReport";

    // 队列
    String QUEUE_NAME = "Alicom-Queue-1762899749859961-SmsReport";


    //产品域名,开发者无需替换
    String SMS_DOMAIN = "dysmsapi.aliyuncs.com";
}
