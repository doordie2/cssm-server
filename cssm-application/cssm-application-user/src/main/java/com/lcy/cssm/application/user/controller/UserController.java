package com.lcy.cssm.application.user.controller;

import com.lcy.cssm.api.user.facade.UserFacade;
import com.lcy.cssm.common.web.base.BaseController;
import com.lcy.cssm.support.user.po.TbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcy
 * @Date: 2018/5/14 10:23
 * @Description: 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserFacade userFacade;

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public TbUserInfo getUserInfo(){
        return userFacade.getUserInfo();
    }
}
