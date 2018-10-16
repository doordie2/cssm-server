package com.lcy.cssm.provider.user.service;


import com.lcy.cssm.provider.user.mapper.UserInfoMapper;
import com.lcy.cssm.support.user.po.TbUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lcy
 * @Date: 2018/5/10 18:46
 * @Description: 用户服务类
 */
@RestController
@RequestMapping("/provider/user")
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    public TbUserInfo getUserInfo(){
       return userInfoMapper.getUserInfo();
    }

}
