package com.lcy.cssm.application.task.controller;

import com.google.common.collect.Maps;
import com.lcy.cssm.common.base.util.HttpUtils;
import com.lcy.cssm.common.base.util.Method;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AliveTaskComponent {

    @Autowired
    private DiscoveryClient discoveryClient;


    /**
     * 每隔20秒检查一遍服务是否正常
     */
    //@Scheduled(cron = "0/25 * * * * ? ")
    public void  appsAliveTest() throws Exception {
        HashMap<String, String> maps = Maps.newHashMap();
        maps.put("cssm-application-common","/common/checkAlive");
        maps.put("cssm-application-user","/user/checkAlive");
        maps.put("cssm-application-operation","/operation/checkAlive");
        maps.put("cssm-application-wechat","/wechat/checkAlive");
        maps.put("cssm-application-mq","/mq/checkAlive");
        maps.put("cssm-provider-auth","/provider/auth/checkAlive");
        maps.put("cssm-provider-base","/provider/base/checkAlive");
        maps.put("cssm-provider-interaction","/provider/interaction/checkAlive");
        maps.put("cssm-provider-monitor","/provider/monitor/checkAlive");
        maps.put("cssm-provider-operation","/provider/operation/checkAlive");
        maps.put("cssm-provider-user","/provider/user/checkAlive");

        for(Map.Entry<String, String> entry : maps.entrySet()){
                List<ServiceInstance> instances = discoveryClient.getInstances(entry.getKey());
                try {
                    HttpResponse response = null;
                    for (int i = 0; i < 3; i++) {
                        Thread.sleep(1000);
                        response = HttpUtils.doGet(instances.get(0).getUri().toString(), entry.getValue(), Method.GET.toString(), Maps.newHashMap(), Maps.newHashMap());
                    }
                    if (EntityUtils.toString(response.getEntity()).equals("true")) {
//                        map.put(entry.getKey(), "true");
                    } else {
//                        map.put(entry.getKey(), "false");
                    }
                } catch (Exception e) {
//                    map.put(entry.getKey(), "false");
                }

        }

    }
}
