package com.lcy.cssm.application.task.controller;/**
 * @Title: RequestLogCleanComponent
 * @ProjectName cssm-server
 * @Description: TODO
 * @author Administrator
 * @date 2018/10/1617:53
 */

import com.lcy.cssm.api.monitor.facade.RequestHeaderLogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title: RequestLogCleanComponent
 * @ProjectName cssm-server
 * @Description: TODO
 * @author lcy
 * @date 2018/10/16 17:53
 */
@Component
public class RequestLogCleanComponent {

    @Autowired
    private RequestHeaderLogFacade requestHeaderLogFacade;
    /**
     * 每天十二点清除七天前请求日志
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void requestLogClean(){
        System.out.print("定时任务开始");
        requestHeaderLogFacade.requestLogClean();
    }
}
