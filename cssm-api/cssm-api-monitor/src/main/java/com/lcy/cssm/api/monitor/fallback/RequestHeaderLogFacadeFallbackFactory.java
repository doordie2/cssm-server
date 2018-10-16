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
        return new RequestHeaderLogFacade() {
            @Override
            public void insertRequestHeaderLog(String userOs, String osVersion, String appVersion, String agentModel, String userAgent, String agentNum, String interfaceName, String ipAddress, String ipCountry, String ipArea, String ipProvince, String ipCity, String uuid, String operateId, String costTime, String appId) {
            }

        };
    }

}
