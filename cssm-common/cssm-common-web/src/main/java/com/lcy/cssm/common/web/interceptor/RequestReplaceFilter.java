package com.lcy.cssm.common.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : 赵天增
 * @create : 2017-10-25 17:19
 * 描述 ：
 */
@Component
public class RequestReplaceFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestReplaceFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!(request instanceof CustomServletRequestWrapper)) {
            request = new CustomServletRequestWrapper(request);
        }
        filterChain.doFilter(request, response);
    }
}
