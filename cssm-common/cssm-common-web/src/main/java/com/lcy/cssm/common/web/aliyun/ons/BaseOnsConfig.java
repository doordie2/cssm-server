package com.lcy.cssm.common.web.aliyun.ons;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: 赵天增
 * @date: 2018 -04-09
 * @描述: 基础ONS配置
 */
@Configuration
public class BaseOnsConfig {
    @Autowired
    private OnsProperties onsProperties;

    @Bean
    public Map<String, Producer> producerBean() {
        Map<String, Producer> producerMap = new HashMap<>();
        onsProperties.getModel().forEach((topic, model) -> {
            ProducerBean producerBean = new ProducerBean();
            Properties properties = new Properties();
            properties.put(PropertyKeyConst.ProducerId, model.get("pid"));
            properties.put(PropertyKeyConst.AccessKey, onsProperties.getAccessKey());
            properties.put(PropertyKeyConst.SecretKey, onsProperties.getAccessKeySecret());
            properties.put(PropertyKeyConst.ONSAddr, onsProperties.getOnsAddr());
            // 循环两个Map 把对象注入到数组中
            producerBean.setProperties(properties);
            producerBean.start();
            producerMap.put(topic, producerBean);
        });
        return producerMap;
    }

    @Bean
    public Map<String, Message> messageMap() {
        Map<String, Message> message = new HashMap<>();
        onsProperties.getModel().forEach((topic, model) -> {
            Message ms = new Message();
            ms.setTopic(model.get("topic"));
            message.put(topic, ms);
        });
        return message;
    }
}
