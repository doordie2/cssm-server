package com.lcy.cssm.application.mq.controller.requestlog;

import com.lcy.cssm.common.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: lcy
 * @Date: 2018/7/3 14:35
 * @Description:
 */
@RestController
@RequestMapping("/mq/log")
public class RequestLogController extends BaseController {

    @Autowired
    private RequestLogProducer requestLogProducer;

    @RequestMapping(value = "/saveRequestHeaderLog", method = RequestMethod.POST)
    public void insertRequestHeaderLog(@RequestBody Map<String,String> headerInfo) throws Exception{
        requestLogProducer.saveRequestHeaderLog(headerInfo);
    }
}
