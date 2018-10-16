package com.lcy.cssm.common.web.interceptor;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 安全校验
// *
// * @author lcy
// * @create 2017-05-26 15:24
// **/
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    public void setCommonIgnoring(WebSecurity web){
//        web.ignoring().antMatchers("/js*/**");
//        web.ignoring().antMatchers("/css*/**");
//        web.ignoring().antMatchers("/fonts*/**");
//        web.ignoring().antMatchers("/images*/**");
//        web.ignoring().antMatchers("/ueditor*/**");
//        web.ignoring().antMatchers("/login*/**");
//        web.ignoring().antMatchers("/favicon.ico");
//        web.ignoring().antMatchers("/");//默认请求
//
//    }
//
//}
