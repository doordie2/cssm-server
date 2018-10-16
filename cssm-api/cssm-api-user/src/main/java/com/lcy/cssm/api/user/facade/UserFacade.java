package com.lcy.cssm.api.user.facade;

import com.lcy.cssm.api.user.configuration.UserConfiguration;
import com.lcy.cssm.api.user.fallback.UserFacadeFallbackFactory;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import com.lcy.cssm.support.user.form.BackUsersForm;
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


    @RequestMapping(value = "/fixMomentCodeAndUniqueId", method = RequestMethod.POST)
    void fixMomentCodeAndUniqueId();
    /**
     * 根据token获取用户信息
     *
     * @param token token
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserInfoByToken", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByToken(@RequestParam(value = "token") String token);

    @RequestMapping(value = "/getUserInfoByRongyunToken", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByRongyunToken(@RequestParam(value = "rongyunToken") String rongyunToken);

    @RequestMapping(value = "/getUserInfoByUniqueId", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByUniqueId(@RequestParam(value = "uniqueId") String uniqueId);


    @RequestMapping(value = "/getUserInfoList", method = RequestMethod.GET)
    List<UserInfoDTO> getUserInfoList(@RequestParam(value = "userIds") String userIds);


    @RequestMapping(value = "/getUserInfoByMomentCode", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByMomentCode(@RequestParam(value = "momentCode") String momentCode);

    @RequestMapping(value = "/forbiddenUser", method = RequestMethod.POST)
    UserInfoDTO forbiddenUser(@RequestParam(value = "userId") String userId, @RequestParam(value = "value") String value);


    @RequestMapping(value = "/pullBlackUser", method = RequestMethod.POST)
    void pullBlackUser(@RequestBody BackUsersForm backUsersForm);

    @RequestMapping(value = "/recommendUser", method = RequestMethod.POST)
    void recommendUser(@RequestBody BackUsersForm backUsersForm);

    /**
     * 获得推荐用户
     * @return
     */
    /**
     * userId 已关注的用户将不推荐
     * @param offset
     * @param limit
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getRecommendUsers", method = RequestMethod.GET)
    List<UserInfoDTO> getRecommendUsers(@RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "userId") Integer userId, @RequestParam("lastTime") String lastTime);

    /**
     * 微信绑定手机号码
     * @param mobile
     * @param unionId
     * @return
     */
    @RequestMapping(value = "/bindByWechatId", method = RequestMethod.POST)
    UserInfoDTO bindByWechatId(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "unionId") String unionId,
                               @RequestParam(value = "idfa") String idfa, @RequestParam(value = "imei") String imei, @RequestParam(value = "userOs") String userOs);

    /**
     * 使用验证码登录
     * @param mobile
     * @param aliyunPushId
     * @return
     */
    @RequestMapping(value = "/doLoginBySmsCode", method = RequestMethod.POST)
    UserInfoDTO doLoginBySmsCode(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "aliyunPushId") String aliyunPushId,
                                 @RequestParam(value = "userOs") String userOs, @RequestParam(value = "agentModel") String agentModel
            , @RequestParam(value = "idfa") String idfa, @RequestParam(value = "imei") String imei,
                                 @RequestParam(value = "appVersion") String appVersion);


    /**
     * 微信注册
     */
    @RequestMapping(value = "/insertUserInfoFromWechat", method = RequestMethod.POST)
    UserInfoDTO insertUserInfoFromWechat(@RequestBody Map<String, Object> requestMap);

    /**
     * app端用第三方微信登入
     * @param appOpenId
     * @param unionId
     * @param profile
     * @param username
     * @param aliyunPushId
     * @return
     */
    @RequestMapping(value = "/loginByWechat", method = RequestMethod.POST)
    UserInfoDTO loginByWechat(@RequestParam(value = "appOpenId") String appOpenId, @RequestParam(value = "unionId") String unionId,
                              @RequestParam(value = "profile") String profile, @RequestParam(value = "username") String username,
                              @RequestParam(value = "aliyunPushId") String aliyunPushId, @RequestParam(value = "userOs") String userOs,
                              @RequestParam(value = "agentModel") String agentModel, @RequestParam(value = "appVersion") String appVersion);


    @RequestMapping(value = "/cancelOrAttentWechat", method = RequestMethod.POST)
    void cancelOrAttentWechat(@RequestParam("openId") String openId, @RequestParam("wechatAttention") String wechatAttention);


    @RequestMapping(value = "/setAliyunPushId", method = RequestMethod.GET)
    UserInfoDTO setAliyunPushId(@RequestParam(value = "token") String token, @RequestParam(value = "aliyunPushId") String aliyunPushId, @RequestParam(value = "userOs") String userOs);

    @RequestMapping(value = "/setMomentCode", method = RequestMethod.GET)
    UserInfoDTO setMomentCode(@RequestParam(value = "token") String token);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    UserInfoDTO logout(Integer userId);

}
