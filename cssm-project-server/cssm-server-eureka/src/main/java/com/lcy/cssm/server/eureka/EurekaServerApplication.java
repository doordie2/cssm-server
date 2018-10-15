package com.lcy.cssm.server.eureka;/**
 * @Title: EurekaServerApplication
 * @ProjectName cssm-server
 * @Description: TODO
 * @author Administrator
 * @date 2018/10/1515:04
 */

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Title: EurekaServerApplication
 * @ProjectName cssm-server
 * @Description: TODO
 * @author Administrator
 * @date 2018/10/1515:04
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }

    @EnableWebSecurity
    static class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
}
