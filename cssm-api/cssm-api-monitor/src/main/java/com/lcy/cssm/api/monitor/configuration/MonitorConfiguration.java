package com.lcy.cssm.api.monitor.configuration;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 日志模块auth配置
 *
 * @author 王培
 * @create 2017-05-26 09:01
 **/
@Configuration
public class MonitorConfiguration {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("provider-monitor", "35446Q3QJS7UvVdN");
    }


}
