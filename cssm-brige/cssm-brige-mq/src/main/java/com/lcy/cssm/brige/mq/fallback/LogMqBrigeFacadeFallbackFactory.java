package com.lcy.cssm.brige.mq.fallback;

import com.lcy.cssm.brige.mq.facade.LogMqBrigeFacade;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lcy
 */
@Component
public class LogMqBrigeFacadeFallbackFactory implements FallbackFactory<LogMqBrigeFacade> {
    private static final Logger logger = LoggerFactory.getLogger(LogMqBrigeFacade.class);

    @Override
    public LogMqBrigeFacade create(Throwable w){
        return new LogMqBrigeFacade(){
            @Override
            public void saveRequestHeaderLog(Map<String, String> headerInfo) {
            }
        };
    }

}
