package com.lcy.cssm.common.web.aliyun.oss;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.lcy.cssm.common.base.constant.aliyun.AliyunConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * oss配置
 *
 * @author lcy
 * @create 2017-10-20 12:45
 */
@Configuration
public class BaseOssClient {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Bean
    public OSSClient getOssClient(){
       return new OSSClient(endpoint, new DefaultCredentialProvider( AliyunConstant.ALIYUN_YJY_ACCESSID, AliyunConstant.ALIYUN_YJY_ACCESSKEY),null);
    }
}
