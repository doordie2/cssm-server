package com.lcy.cssm.common.web.interceptor;




import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.base.exceptions.CommonResult;
import com.lcy.cssm.common.web.item.UeditorFileUploadResultItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


@Configuration
public class MappingJsonConverter extends AbstractHttpMessageConverter<Object> {
    Logger logger_digest   = LoggerFactory.getLogger(MappingJsonConverter.class);

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    /**
     * Construct a new {@code BindingJacksonHttpMessageConverter}.
     */
    public MappingJsonConverter() {
        super(new MediaType("application", "json", DEFAULT_CHARSET));
    }

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return (canRead(mediaType));
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        //如果feign用requestbody传的话，每次requestbody注解都会调用这个转换器，在传给probvider的时候不需要包装，只有返回到前台的时候需要包装
        //todo 看有没有更合理的解决方案
        if(mediaType!=null&&StringUtils.equals("json",mediaType.getSubtype())){
            return (canWrite(mediaType));
        }
        return false;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage)
                                                                                throws IOException,
            HttpMessageNotReadableException {
        InputStream in = inputMessage.getBody();
        return JSON.parseObject(in, DEFAULT_CHARSET, clazz, Feature.AutoCloseSource);


    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage)
                                                                                throws IOException,
            HttpMessageNotWritableException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes()).getRequest();
        Object result = object;
        String jsonResponse;
        if (result instanceof CommonResult) {
            jsonResponse = ((CommonResult) result).toJson();
        } else if (result instanceof Model) {
            CommonResult jsonResult = new CommonResult(CommonConstant.TRUE);
            jsonResult.setContent(((Model) result).asMap());
            jsonResponse = jsonResult.toJson();
        } else if(result instanceof UeditorFileUploadResultItem) {
            jsonResponse= JSON.toJSONString(result);
        }else{
            CommonResult jsonResult = new CommonResult(CommonConstant.TRUE);
            jsonResult.setContent(result);
            jsonResponse = jsonResult.toJson();
        }
        logger_digest.info("【响应】 requestUri = {}, response={},uuid={}",request.getServletPath(), jsonResponse,MDC.get("threadUuid"));
        outputMessage.getBody().write(jsonResponse.getBytes("utf-8"));
    }
}
