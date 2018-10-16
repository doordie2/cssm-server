package com.lcy.cssm.application.user.interceptor;

import com.lcy.cssm.common.web.interceptor.AuthInterceptor;
import com.lcy.cssm.common.web.interceptor.BaseInterceptor;
import com.lcy.cssm.common.web.interceptor.ConvertIdInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 基础业务模块适配
 *
 * @author 王培
 * @create 2017-08-02 21:47
 **/
@Configuration
public class UserWebMvcConfigurerAdapter implements WebMvcConfigurer {

    /**必须这么写，否则拦截器里面@Autowired进去的都是null**/
    @Bean
    public BaseInterceptor baseInterceptor() {
        return new BaseInterceptor();
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }

    @Bean
    public ConvertIdInterceptor convertIdInterceptor() {
        return new ConvertIdInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(baseInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(convertIdInterceptor()).addPathPatterns("/**");
    }
}
