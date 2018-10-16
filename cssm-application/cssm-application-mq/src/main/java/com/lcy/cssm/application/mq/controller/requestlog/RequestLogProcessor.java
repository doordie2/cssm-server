package com.lcy.cssm.application.mq.controller.requestlog;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志peocessor
 * @auther lcy
 * @create 2017-09-29 11:10
 */
@Configuration
public class RequestLogProcessor {

    @Bean
    public Queue requestLogQueue() {
        return new Queue("requestLog");
    }

    /**
     * 交换机
     */
    @Bean
    public TopicExchange requestLogExchange() {
        return new TopicExchange("requestLogExchange");
    }

    /**
     * 绑定队列到交换机上,路由模式，需要完整匹配topic.message
     * @param requestLogQueue
     * @param requestLogExchange
     * @return
     */
    @Bean
    public Binding bindingTopicExchangeSayQueue(Queue requestLogQueue, TopicExchange requestLogExchange) {
        return BindingBuilder.bind(requestLogQueue).to(requestLogExchange).with("topic.requestLog");
    }

}
