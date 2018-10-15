package com.lcy.cssm.common.provider.mybatis;

/**
 * @author : 赵天增
 * @create : 2018-01-10 13:43
 * 描述 ：
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BaseInterceptors implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor())
                .excludePathPatterns("/provider/log*/**")
                .excludePathPatterns("/provider/base*/**")
                .addPathPatterns("/**");
    }

    @Bean
    public MyBatisIntercept baseInterceptor() {
        return new MyBatisIntercept();
    }
}
