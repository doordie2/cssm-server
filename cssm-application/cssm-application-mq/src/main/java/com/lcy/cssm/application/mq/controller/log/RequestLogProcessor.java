package com.lcy.cssm.application.mq.controller.log;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 日志peocessor
 * @auther 王培
 * @create 2017-09-29 11:10
 */
public interface RequestLogProcessor {

    String INPUT = "requestLogInput";
    String OUTPUT = "requestLogOutput";


    @Input(INPUT)
    SubscribableChannel requestLogInput();

    @Output(OUTPUT)
    MessageChannel requestLogOutput();
}
