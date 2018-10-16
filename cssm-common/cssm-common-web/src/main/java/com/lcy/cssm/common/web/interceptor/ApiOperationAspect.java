package com.lcy.cssm.common.web.interceptor;

import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.web.annotation.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @author : lcy
 * @create : 2017-10-26 9:39
 * 描述 ：
 */
@Aspect
@Component
public class ApiOperationAspect {


    public ApiOperationAspect() {

    }

    @Pointcut("@annotation(com.lcy.cssm.common.web.annotation.ApiOperation)")
    public void controllerInteceptor() {

    }

    @Around("controllerInteceptor()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        // 先放行，在记录日志
        Object res = pjp.proceed();
        //获取注解的方法参数列表
        Object[] args = pjp.getArgs();
        //获取被注解的方法
        MethodInvocationProceedingJoinPoint mjp = (MethodInvocationProceedingJoinPoint) pjp;
        MethodSignature signature = (MethodSignature) mjp.getSignature();
        Method method = signature.getMethod();
        //获取方法上的注解
        ApiOperation operation = method.getAnnotation(ApiOperation.class);
        Logger logger = LoggerFactory.getLogger(method.getClass());
        logger.info(CommonConstant.RECORD + "{}", operation.value());
        return res;
    }
}
