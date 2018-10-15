package com.lcy.cssm.common.base.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class AsyncUtils {


    @Autowired
    private JavaMailSender mailSender;


    @Async
    public void sendSimpleMail( HashMap<String, String> mailInfo){

    }
}
