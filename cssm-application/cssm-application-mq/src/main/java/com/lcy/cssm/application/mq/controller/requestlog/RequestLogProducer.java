package com.lcy.cssm.application.mq.controller.requestlog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 记录请求头信息
 * @auther lcy
 * @create 2018-09-29 11:12
 */
@Component
public class RequestLogProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void saveRequestHeaderLog(Map<String,String> headerInfo) {
        rabbitTemplate.convertAndSend("requestLog", headerInfo);
    }
}
