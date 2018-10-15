package com.lcy.cssm.common.web.base;


import com.mcilife.zlnsh.common.base.constant.CommonConstant;
import com.mcilife.zlnsh.common.base.exceptions.CommonResult;
import org.springframework.beans.factory.annotation.Value;

/**
 * 后台基础控制器
 * @author 王培
 * @create 2016-12-03 13:14
 */
public class BaseController {

    @Value("${web.host}")
    protected String webHost;

    @Value("${wechat.appid}")
    protected String wechatAppid;
    @Value("${wechat.secret}")
    protected String wechatSecret;
    @Value("${web.url}")
    protected String webUrl;

    @Value("${environment}")
    protected String environment;

    protected CommonResult SUCCESS_RESULT = new CommonResult(CommonConstant.TRUE, CommonConstant.NUMBER_ONE);
}
