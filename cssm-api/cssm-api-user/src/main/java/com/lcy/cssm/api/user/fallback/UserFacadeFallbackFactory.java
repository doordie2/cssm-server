package com.lcy.cssm.api.user.fallback;

import com.lcy.cssm.api.user.facade.UserFacade;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserFacadeFallbackFactory implements FallbackFactory<UserFacade> {
    private static final Logger logger = LoggerFactory.getLogger(UserFacadeFallbackFactory.class);


    @Override
    public UserFacade create(Throwable throwable) {
        logger.info("fallback; reason was: {}", throwable.getMessage());
        return null;
    }
}
