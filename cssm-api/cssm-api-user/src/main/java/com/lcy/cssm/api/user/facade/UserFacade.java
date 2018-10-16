package com.lcy.cssm.api.user.facade;

import com.lcy.cssm.api.user.configuration.UserConfiguration;
import com.lcy.cssm.api.user.fallback.UserFacadeFallbackFactory;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import com.lcy.cssm.support.user.form.BackUsersForm;
import com.lcy.cssm.support.user.po.TbUserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


/**
 * 用户接口
 *
 * @author 王培
 * @create 2017-05-19 13:33
 **/
@FeignClient(name = "cssm-provider-user", configuration = UserConfiguration.class, fallbackFactory = UserFacadeFallbackFactory.class)
@RequestMapping("/provider/user")
public interface UserFacade {

    /**
     * 获取用户信息
     * @return
     */
    @RequestMapping(value = "getUser",method = RequestMethod.GET)
    TbUserInfo getUserInfo();

    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserInfoByToken", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByToken(@RequestParam(value = "token") String token);

}
