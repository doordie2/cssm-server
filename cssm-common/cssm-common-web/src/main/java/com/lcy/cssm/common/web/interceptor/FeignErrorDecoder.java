package com.lcy.cssm.common.web.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.base.exceptions.CommonResultCode;
import com.lcy.cssm.common.base.exceptions.McFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

/**
 * feign的异常解析
 *
 * @auther lcy
 * @create 2017-08-04 13:51
 */
@Configuration
public class FeignErrorDecoder implements ErrorDecoder{

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() == 401) {
            return new McFeignException(CommonResultCode.AUTH_NOT_ALLOW.getCode(), CommonResultCode.AUTH_NOT_ALLOW.getDesc());
        }
        if (response.status() == 500) {
            JSONObject responseBody = JSONObject.parseObject(response.body().toString());
            String message = responseBody.getString("message");
            if(StringUtils.isNotBlank(message)&&(message.contains("McException"))){
                String[] messageArr = message.split(CommonConstant.SEPARATOR_1);
                return new McFeignException(messageArr[0],messageArr[1]);
            }
        }
        return feign.FeignException.errorStatus(methodKey, response);
    }
}
