package com.lcy.cssm.application.mq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mq")
public class TaskTestController {

    @RequestMapping(value = "/checkAlive", method = RequestMethod.GET)
    public Boolean checkAlive(){
        return true;
    }
}
