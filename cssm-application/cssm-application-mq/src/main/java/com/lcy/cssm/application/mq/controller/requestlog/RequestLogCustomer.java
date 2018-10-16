package com.lcy.cssm.application.mq.controller.requestlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.api.monitor.facade.RequestHeaderLogFacade;
import com.lcy.cssm.api.user.facade.UserFacade;
import com.lcy.cssm.common.base.constant.HttpConstant;
import com.lcy.cssm.common.base.constant.aliyun.AliyunConstant;
import com.lcy.cssm.common.base.util.HttpUtils;
import com.lcy.cssm.common.base.util.Method;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志customer
 *
 * @auther lcy
 * @create 2017-09-29 11:25
 */
@Component
public class RequestLogCustomer {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogCustomer.class);

    @Autowired
    private RequestHeaderLogFacade requestHeaderLogFacade;

    @Autowired
    private UserFacade userFacade;

    @RabbitListener(queues = "requestLog")
    public void input(Map<String,String> message){
        logger.info("请求日志记录开始");
        JSONObject headerInfo=JSONObject.parseObject(JSON.toJSONString(message));
        //请求的query
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("ip", headerInfo.getString("ipAddress"));
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + AliyunConstant.ALIYUN_MARKET_APP_CODE);

        //获取ip信息失败不影响保存请求信息
        try{
            HttpResponse httpResponse = HttpUtils.doGet(HttpConstant.HTTPS+ AliyunConstant.ALIYUN_MARKET_ALI_URL, AliyunConstant.ALIYUN_MARKET_IP_PATH, Method.GET.toString(),headers,querys);
            String entityString =  EntityUtils.toString(httpResponse.getEntity());
            JSONObject jsonObject = JSONObject.parseObject(entityString);
            if(jsonObject!=null){
                if(jsonObject.getInteger("code")==0){
                    JSONObject dataObject = jsonObject.getJSONObject("data");
                    headerInfo.put("country",dataObject.getString("country"));
                    headerInfo.put("area",dataObject.getString("area"));
                    headerInfo.put("province",dataObject.getString("region"));
                    headerInfo.put("city",dataObject.getString("city"));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer userId = null;
        if(StringUtils.isNotBlank(headerInfo.getString("operatertoken"))){
            UserInfoDTO userInfoDTO = userFacade.getUserInfoByToken(headerInfo.getString("operatertoken"));
            if(userInfoDTO!=null){
                userId = userInfoDTO.getUserId();
            }
        }

        if(headerInfo.getString("interFaceName").startsWith("")){

        }

       requestHeaderLogFacade.insertRequestHeaderLog(
                headerInfo.getString("user-os"),
                headerInfo.getString("os-version"),
                headerInfo.getString("app-version"),
                headerInfo.getString("agent-model"),
                headerInfo.getString("user-agent"),
                headerInfo.getString("agent-num"),
                headerInfo.getString("interFaceName"),
                headerInfo.getString("ipAddress"),
                headerInfo.getString("country"),
                headerInfo.getString("area"),
                headerInfo.getString("province"),
                headerInfo.getString("city"),
                headerInfo.getString("uuid"),
                String.valueOf(userId),
                headerInfo.getString("costTime"),
                headerInfo.getString("app-id"));
    }
}
