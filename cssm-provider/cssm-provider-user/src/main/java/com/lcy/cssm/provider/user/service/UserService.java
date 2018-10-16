package com.lcy.cssm.provider.user.service;


import com.lcy.cssm.provider.user.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王培
 * @Date: 2018/5/10 18:46
 * @Description: 用户服务类
 */
@RestController
@RequestMapping("/provider/user")
public class UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

}