package com.lcy.cssm.api.monitor.fallback;

import com.lcy.cssm.api.monitor.facade.RequestHeaderLogFacade;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RequestHeaderLogFacadeFallbackFactory implements FallbackFactory<RequestHeaderLogFacade> {
    private static final Logger logger = LoggerFactory.getLogger(RequestHeaderLogFacadeFallbackFactory.class);

    @Override
    public RequestHeaderLogFacade create(Throwable w){
        return null;
    }

}
