package com.lcy.cssm.common.web.aliyun.ons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@ConfigurationProperties(prefix = "ons")
@Component
public class OnsProperties {
    private String accessKey;

    private String accessKeySecret;

    private String onsAddr;

    private Map<String, Map<String,String>> model;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getOnsAddr() {
        return onsAddr;
    }

    public void setOnsAddr(String onsAddr) {
        this.onsAddr = onsAddr;
    }

    public Map<String, Map<String,String>> getModel() {
        return model;
    }

    public void setModel(Map<String, Map<String,String>> model) {
        this.model = model;
    }

}
