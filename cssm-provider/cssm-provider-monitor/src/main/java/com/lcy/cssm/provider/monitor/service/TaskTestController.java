package com.lcy.cssm.provider.monitor.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider/monitor")
public class TaskTestController {

    @RequestMapping(value = "/checkAlive", method = RequestMethod.GET)
    public Boolean checkAlive(){
        return true;
    }
}
