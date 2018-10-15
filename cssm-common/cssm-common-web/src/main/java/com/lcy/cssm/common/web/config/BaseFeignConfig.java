package com.lcy.cssm.common.web.config;


import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author : 赵天增
 * @create : 2018-01-08 20:27
 * 描述 ：基础feign类
 */
@Configuration
public class BaseFeignConfig {
    @Bean
    public RequestInterceptor basicRequestInterceptor() {
        return template -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (Objects.nonNull(attributes)){
                HttpServletRequest request = attributes.getRequest();
                if (request.getAttribute("userId") != null) {
                    template.header("userId", String.valueOf(request.getAttribute("userId")));
                }
            }
        };
    }
}
