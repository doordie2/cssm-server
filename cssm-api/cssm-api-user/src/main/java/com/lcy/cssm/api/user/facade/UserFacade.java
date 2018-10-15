package com.lcy.cssm.api.user.facade;

import com.lcy.cssm.api.user.configuration.UserConfiguration;
import com.lcy.cssm.api.user.fallback.UserFacadeFallbackFactory;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import com.lcy.cssm.support.user.form.BackUsersForm;
import com.lcy.cssm.support.user.po.TbUserInfo;
import com.lcy.cssm.support.user.result.UserAttentionResult;
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
@FeignClient(name = "zlnsh-provider-user", configuration = UserConfiguration.class, fallbackFactory = UserFacadeFallbackFactory.class)
@RequestMapping("/provider/user")
public interface UserFacade {


    @RequestMapping(value = "/fixMomentCodeAndUniqueId", method = RequestMethod.POST)
    void fixMomentCodeAndUniqueId();

    @RequestMapping(value = "/updateUserInfoTask", method = RequestMethod.POST)
    void updateUserInfoTask(@RequestBody TbUserInfo tbUserInfo);
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



    @RequestMapping(value = "/listNoUniqueIdUser", method = RequestMethod.GET)
    List<TbUserInfo> listNoUniqueIdUser();



    @RequestMapping(value = "/recommendUser", method = RequestMethod.POST)
    void recommendUser(@RequestBody BackUsersForm backUsersForm);


    /**
     * 根据微信的unionId获取用户信息
     *
     * @param unionId unionId
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserInfoByUnionId", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByUnionId(@RequestParam(value = "unionId") String unionId);

    /**
     * 根据微信的openId获取用户信息
     *
     * @param openId openId
     * @return 用户信息
     */
    @RequestMapping(value = "/getUserInfoByOpenId", method = RequestMethod.GET)
    UserInfoDTO getUserInfoByOpenId(@RequestParam(value = "openId") String openId);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.GET)
    UserInfoDTO getUserInfoById(@RequestParam(value = "userId") Integer userId);

    /**
     * 更新用户信息
     *
     * @param userInfoDTO 提交的更新信息
     * @return 更新后的用户详情
     */
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    UserInfoDTO updateUserInfo(@RequestBody UserInfoDTO userInfoDTO);

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

    /**
     * 关键字搜索用户信息
     * @param keyWord
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/listUserInfoByKeyWord", method = RequestMethod.GET)
    List<UserAttentionResult> listUserInfoByKeyWord(@RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit, @RequestParam(value = "keyWord") String keyWord);

    @RequestMapping(value = "/cancelOrAttentWechat", method = RequestMethod.POST)
    void cancelOrAttentWechat(@RequestParam("openId") String openId, @RequestParam("wechatAttention") String wechatAttention);


    @RequestMapping(value = "/setAliyunPushId", method = RequestMethod.GET)
    UserInfoDTO setAliyunPushId(@RequestParam(value = "token") String token, @RequestParam(value = "aliyunPushId") String aliyunPushId, @RequestParam(value = "userOs") String userOs);

    @RequestMapping(value = "/setMomentCode", method = RequestMethod.GET)
    UserInfoDTO setMomentCode(@RequestParam(value = "token") String token);

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    UserInfoDTO logout(Integer userId);

    /**
     * 后台查询用户
     * @param nickName
     * @param phone   手机号
     * @param startDate
     * @param endDate
     * @param province
     * @param city
     * @param phoneOs  微信，安卓，iOS
     * @param offset
     * @param limit
     * @return
     */
    @RequestMapping(value = "/listUsersForAdmin", method = RequestMethod.GET)
    List<UserInfoDTO> listUsersForAdmin(@RequestParam(value = "sex", required = false) String sex,
                                        @RequestParam(value = "auth", required = false) String auth,
                                        @RequestParam(value = "nickName", required = false) String nickName,
                                        @RequestParam(value = "momentCode", required = false) String momentCode,
                                        @RequestParam(value = "phone", required = false) String phone,
                                        @RequestParam(value = "recommend", required = false) String recommend,
                                        @RequestParam(value = "startDate", required = false) String startDate,
                                        @RequestParam(value = "endDate", required = false) String endDate,
                                        @RequestParam(value = "province", required = false) String province,
                                        @RequestParam(value = "city", required = false) String city,
                                        @RequestParam(value = "phoneOs", required = false) String phoneOs,
                                        @RequestParam(value = "inblack", required = false) Integer inblack,
                                        @RequestParam(value = "forbidden", required = false) Integer forbidden,
                                        @RequestParam(value = "voType", required = false) String voType,
                                        @RequestParam(value = "offset") Integer offset, @RequestParam(value = "limit") Integer limit);

    /**
     * 后台查询用户总数
     * @param nickName
     * @param phone   手机号
     * @param startDate
     * @param endDate
     * @param province
     * @param city
     * @param phoneOs   微信，安卓，iOS
     * @return
     */
    @RequestMapping(value = "/countUsersForAdmin", method = RequestMethod.GET)
    Integer countUsersForAdmin(@RequestParam(value = "sex", required = false) String sex,
                               @RequestParam(value = "auth", required = false) String auth,
                               @RequestParam(value = "nickName", required = false) String nickName,
                               @RequestParam(value = "momentCode", required = false) String momentCode,
                               @RequestParam(value = "phone", required = false) String phone,
                               @RequestParam(value = "recommend", required = false) String recommend,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "endDate", required = false) String endDate,
                               @RequestParam(value = "province", required = false) String province,
                               @RequestParam(value = "city", required = false) String city,
                               @RequestParam(value = "phoneOs", required = false) String phoneOs,
                               @RequestParam(value = "inblack", required = false) Integer inblack,
                               @RequestParam(value = "forbidden", required = false) Integer forbidden,
                               @RequestParam(value = "voType", required = false) String voType);

}
