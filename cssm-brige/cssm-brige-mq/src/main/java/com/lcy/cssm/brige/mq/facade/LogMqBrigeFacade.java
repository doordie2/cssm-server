package com.lcy.cssm.brige.mq.facade;

import com.lcy.cssm.brige.mq.configuration.MqConfiguration;
import com.lcy.cssm.brige.mq.fallback.LogMqBrigeFacadeFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @Author: lcy
 * @Date: 2018/7/3 10:18
 * @Description:
 */
@FeignClient(name = "logMq",url = "${feign.inside}",configuration = MqConfiguration.class,fallbackFactory = LogMqBrigeFacadeFallbackFactory.class)
public interface LogMqBrigeFacade {

    @RequestMapping(value = "mq/log/saveRequestHeaderLog", method = RequestMethod.POST)
    void saveRequestHeaderLog(@RequestBody Map<String, String> headerInfo);
}
