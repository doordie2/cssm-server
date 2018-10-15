package com.lcy.cssm.api.user.configuration;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用户feignclient配置
 *
 * @author lcy
 * @create 2017-05-26 09:01
 **/
@Configuration
public class UserConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("provider-user", "35446Q3QJS7UvVdN");
    }
}
