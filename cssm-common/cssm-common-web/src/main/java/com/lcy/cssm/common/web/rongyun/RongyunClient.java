package com.lcy.cssm.common.web.rongyun;

import io.rong.RongCloud;
import io.rong.methods.user.User;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: lcy
 * @Date: 2018/5/16 15:17
 * @Description:
 */
@Configuration
public class RongyunClient {

    @Value("${rongyun.appKey}")
    private String appKey;

    @Value("${rongyun.appSecret}")
    private String appSecret;

    public TokenResult getToken(String userId,String username,String profile) throws Exception{

        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        User User = rongCloud.user;

        UserModel user = new UserModel()
                .setId(userId)
                .setName(username)
                .setPortrait(profile);
        TokenResult result = User.register(user);
        return result;
    }
}
