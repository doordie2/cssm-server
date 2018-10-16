package com.lcy.cssm.common.web.wechat;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.common.base.exceptions.CommonResultCode;
import com.lcy.cssm.common.base.exceptions.McException;
import com.lcy.cssm.common.base.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangpei on 2017/03/21.
 */
public class AdvancedUtil {
    private static final Logger logger = LoggerFactory.getLogger(AdvancedUtil.class);


    /**
     * 获取网页授权凭证
     *
     * @param appId     公众账号的唯一标识
     * @param appSecret 公众账号的密钥
     * @param code
     * @return WeixinAouth2Token
     */
    public static WeixinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) throws IOException {
        WeixinOauth2Token wat = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("SECRET", appSecret);
        requestUrl = requestUrl.replace("CODE", code);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        logger.info("Oauth2 = {}", jsonObject.toJSONString());
        if (null != jsonObject) {
            try {
                wat = new WeixinOauth2Token();
                logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));
                wat.setAccessToken(jsonObject.getString("access_token"));
                wat.setExpiresIn(jsonObject.getInteger("expires_in"));
                wat.setRefreshToken(jsonObject.getString("refresh_token"));
                wat.setOpenId(jsonObject.getString("openid"));
                wat.setScope(jsonObject.getString("scope"));
            } catch (Exception e) {
                wat = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return wat;
    }


    public static String appAccessToken(String appId, String appSecret) throws IOException {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        requestUrl = requestUrl.replace("APPID", appId);
        requestUrl = requestUrl.replace("APPSECRET", appSecret);
        String result = "";
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                logger.debug("app的token返回的数据:{} ", JSONObject.toJSONString(jsonObject));
                result = jsonObject.getString("access_token");
            } catch (Exception e) {
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取app的token失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }

    public static String getJsTicket(String accessToken) throws IOException {
        String result = "";
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        logger.debug("请求是accessToken:{} ", accessToken);
        // 获取网页授权凭证
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
            try {
                logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));

                result = jsonObject.getString("ticket");

            } catch (Exception e) {
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取jsticket失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }


    public static String getTemplateId(String accessToken,String templateIdShort){
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        logger.debug("请求是accessToken:{} ", accessToken);
        // 获取网页授权凭证
        Map<String,String> param = new HashMap<>();
        param.put("template_id_short",templateIdShort);
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", JSON.toJSONString(param));
        if (null != jsonObject) {
            logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));
            if(jsonObject.getInteger("errcode")==0){
                return jsonObject.getString("template_id");
            }
        }
        throw new McException(CommonResultCode.WECHAT_TEMPLATE_ERROR);
    }

    public static void sendTemplate(String accessToken,String templateJsonString) {
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        logger.debug("请求是accessToken:{} ", accessToken);
        logger.debug("请求的模板字符串:{} ", templateJsonString);
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", templateJsonString);
        if (null != jsonObject) {
            logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));
            if(jsonObject.getInteger("errcode")==0){
                logger.debug("发送微信模板消息成功");
            }
        }
    }

    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) throws IOException {
        /**
         * 通过网页授权获取用户信息
         *
         * @param accessToken 网页授权接口调用凭证
         * @param openId 用户标识
         * @return SNSUserInfo
         */
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        logger.info("snsUser = {}", jsonObject.toJSONString());

        if (null != jsonObject) {
            try {
                logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInteger("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));

                snsUserInfo.setUnionid(jsonObject.getString("unionid"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }


    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId      用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings({"deprecation", "unchecked"})
    public static SNSUserInfo getSNSUserInfoByOpenId(String accessToken, String openId) throws IOException {
        /**
         * 通过网页授权获取用户信息
         *
         * @param accessToken 网页授权接口调用凭证
         * @param openId 用户标识
         * @return SNSUserInfo
         */
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        logger.info("用openId获取snsUser = {}", jsonObject.toJSONString());
        if (null != jsonObject) {
            try {
                logger.debug("返回的数据 errcode:{} ", JSONObject.toJSONString(jsonObject));
                snsUserInfo = new SNSUserInfo();
                snsUserInfo.setSubscribe(jsonObject.getInteger("subscribe"));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInteger("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("用openId获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }


    public static JsParam getJsSign(String wechatAppid,String jsTicket, String url) {
        String noncestr = CommonUtils.randomLetterAndNumber(16);
        String timeStamp = String.valueOf(System.currentTimeMillis());
        String signUrl = "jsapi_ticket=" + jsTicket + "&noncestr=" + noncestr + "&timestamp=" + timeStamp + "&url=" + url;
        String sign = Sha1Util.getSha1(signUrl);
        JsParam jsParam = new JsParam();
        jsParam.setAppId(wechatAppid);
        jsParam.setTimestamp(timeStamp);
        jsParam.setNonceStr(noncestr);
        jsParam.setSignature(sign);
        return jsParam;
    }


}
