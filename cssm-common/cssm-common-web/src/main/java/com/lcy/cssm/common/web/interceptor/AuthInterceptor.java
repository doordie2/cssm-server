package com.lcy.cssm.common.web.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.mcilife.zlnsh.api.base.facade.DictionaryFacade;
import com.mcilife.zlnsh.api.third.facade.v3.CompanyFacade;
import com.mcilife.zlnsh.common.base.constant.CommonConstant;
import com.mcilife.zlnsh.common.base.constant.HttpConstant;
import com.mcilife.zlnsh.common.base.constant.UserOsConstant;
import com.mcilife.zlnsh.common.base.exceptions.CommonResultCode;
import com.mcilife.zlnsh.common.base.exceptions.McException;
import com.mcilife.zlnsh.common.base.util.AssertUtil;
import com.mcilife.zlnsh.common.base.util.EmojiUtils;
import com.mcilife.zlnsh.common.base.util.Md5Util;
import com.mcilife.zlnsh.common.core.redis.RedisClient;
import com.mcilife.zlnsh.common.web.annotation.FileUploadCheck;
import com.mcilife.zlnsh.common.web.annotation.NoAuthCheck;
import com.mcilife.zlnsh.common.web.annotation.PageSessionCheck;
import com.mcilife.zlnsh.support.third.dto.v3.BusinessCrmCompanyDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 权限interceptor，每个请求都会调用
 *
 * @author 王培
 * @create 2017-05-03 14:26
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private DictionaryFacade dictionaryFacade;


    @Autowired
    private CompanyFacade companyFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //如果是页面跳转的请求，什么都不处理
        PageSessionCheck pageSessionCheck = ((HandlerMethod) handler).getMethodAnnotation(PageSessionCheck.class);
        FileUploadCheck fileUploadCheck = ((HandlerMethod) handler).getMethodAnnotation(FileUploadCheck.class);
        NoAuthCheck noAuthCheck = ((HandlerMethod) handler).getMethodAnnotation(NoAuthCheck.class);
        if (pageSessionCheck != null||fileUploadCheck != null||noAuthCheck != null) {
            return true;
        }
        Map<String, String> headerInfo = (Map<String, String>) request.getAttribute("headerInfo");
        // Feature.OrderedField 这里必须顺序解析，不加这个Feature的话多层json只有第一层是正常的，后面的还是会变成hashmap
        LinkedHashMap<String, Object> objectMap = !request.getParameterMap().isEmpty() || StringUtils.equals(request.getMethod(), HttpConstant.GET_METHOD) ? getParameterMap(request) : JSON.parseObject(request.getInputStream(), Charset.forName("UTF-8"), LinkedHashMap.class, Feature.AutoCloseSource, Feature.OrderedField);
        Long requestTimeStamp = Long.parseLong((String) objectMap.get("auth_time_stamp"));//请求里面的时间戳
        String requesyAuthNonce = (String) objectMap.get("auth_nonce"); //请求里面的随机字符串
        String requestSign = (String) objectMap.get("auth_sign");//请求里面的签名
        Long nowTimeStamp = System.currentTimeMillis();
        Long difference = (nowTimeStamp - requestTimeStamp) / 1000;//请求和系统时间差
        String  isCheck=dictionaryFacade.getDictionary("iosCheck","inCheck").getValue();
        if (isCheck.equals("1")){
            if (difference > 5 * 60) {
                throw new McException(CommonResultCode.USER_REQUEST_EXPIRED);
            }
        }
        AssertUtil.isBlank(redisClient.get("user_auth_nonce_" + requesyAuthNonce), CommonResultCode.USER_REUSED_NONCE);
        redisClient.setEx("user_auth_nonce_" + requesyAuthNonce, LocalDateTime.now().getSecond() + 60 * 5, requesyAuthNonce);//过期时间5分钟
        objectMap.remove("auth_time_stamp");
        objectMap.remove("auth_nonce");
        objectMap.remove("auth_sign");
        String requestParam = "";

        ArrayList<String> list = new ArrayList<String>();
        for (String key : objectMap.keySet()) {
            if(CommonConstant.SPECIAL_SYMBOL.indexOf(key)==-1){
                list.add(key + "=" + objectMap.get(key));
            }
        }

        if (!list.isEmpty()) {
            Collections.sort(list);
            requestParam = StringUtils.join(list.toArray(), "&") + "&";//参数字符串
        }

        String secretKetParam = "auth_secret_key="+CommonConstant.AUTH_COMSUMER_SECRET_KEY;


        //请求头里面传了app-id就是第三方
        String appId = headerInfo.get("app-id");
        if(StringUtils.isNotBlank(appId)){
            BusinessCrmCompanyDTO businessCrmCompanyDTO = companyFacade.getBusinessCrmCompanyByAppId(appId);
            AssertUtil.isNotNull(businessCrmCompanyDTO,CommonResultCode.THIRD_NOT_RIGHT);
            secretKetParam = "app_secret="+businessCrmCompanyDTO.getAppSecret();
        }


        if(!StringUtils.equals(UserOsConstant.ANDROID,headerInfo.get("user-os"))&&!StringUtils.equals(UserOsConstant.IOS,headerInfo.get("user-os"))){
            requestParam = EmojiUtils.emojiChange(requestParam);
        }


        String baseSignUri = requestParam + "auth_time_stamp=" + String.valueOf(requestTimeStamp) + "&auth_nonce=" + requesyAuthNonce + "&" + secretKetParam;
        logger.info("签名字符串={}", StringUtils.lowerCase(baseSignUri));
        logger.info("签名={},客户端传入的签名={}", StringUtils.lowerCase(Md5Util.getMD5Str(StringUtils.lowerCase(baseSignUri))), requestSign);
        AssertUtil.isEqual(StringUtils.lowerCase(Md5Util.getMD5Str(StringUtils.lowerCase(baseSignUri))), requestSign, CommonResultCode.USER_SIGN_DIFF);

        request.setAttribute("requestParam",objectMap);
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }


    public static LinkedHashMap<String, Object> getParameterMap(HttpServletRequest request) throws Exception {
        // 参数Map
        Map properties = request.getParameterMap();
        // 返回值Map
        LinkedHashMap<String, Object> returnMap = new LinkedHashMap<String, Object>();
        Iterator entries = properties.entrySet().iterator();
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

}
