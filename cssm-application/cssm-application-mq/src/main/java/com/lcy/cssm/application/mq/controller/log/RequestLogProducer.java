package com.lcy.cssm.application.mq.controller.log;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 记录请求头信息
 * @auther lcy
 * @create 2018-09-29 11:12
 */
@EnableBinding(RequestLogProcessor.class)
@Service
public class RequestLogProducer {

    @Autowired
    @Qualifier("requestLogOutput")
    MessageChannel requestLogOutput;

    public void saveRequestHeaderLog(Map<String,String> headerInfo) {
        requestLogOutput.send(MessageBuilder.withPayload(JSON.toJSONString(headerInfo)).build());
    }
}
