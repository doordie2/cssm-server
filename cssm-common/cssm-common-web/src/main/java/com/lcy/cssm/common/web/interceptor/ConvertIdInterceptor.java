package com.lcy.cssm.common.web.interceptor;

import com.lcy.cssm.api.user.facade.UserFacade;
import com.lcy.cssm.common.base.constant.CommonConstant;
import com.lcy.cssm.common.base.constant.UserTypeEnum;
import com.lcy.cssm.common.base.exceptions.CommonResultCode;
import com.lcy.cssm.common.base.exceptions.McException;
import com.lcy.cssm.common.base.util.AssertUtil;
import com.lcy.cssm.common.web.annotation.SessionCheck;
import com.lcy.cssm.support.user.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 转换id的interceptor，用于common模块
 *
 * @author lcy
 * @create 2017-05-03 14:26
 **/
public class ConvertIdInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ConvertIdInterceptor.class);

    @Autowired
    private UserFacade userFacade;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
        SessionCheck sessionCheck = ((HandlerMethod) handler).getMethodAnnotation(SessionCheck.class);
        Map<String, String> headerInfo = (Map<String, String>) request.getAttribute("headerInfo");
        String appType = StringUtils.isNotBlank(headerInfo.get("apptype"))?headerInfo.get("apptype"):"";
        //用户级别校验
        if (sessionCheck != null) {
            if (StringUtils.isBlank(appType)) {
                HttpSession session = request.getSession();
                if (session.getAttribute("adminTokenId") == null || StringUtils.isBlank(session.getAttribute("adminTokenId").toString())) {
                    response.sendRedirect("/admin/login");
                    return false;
                }

            }else {
                LinkedHashMap<String, Object> objectMap = (LinkedHashMap<String, Object>) request.getAttribute("requestParam");
                String token = (String) objectMap.get("token");
                if(StringUtils.isBlank(token)){
                    throw  new McException(CommonResultCode.USER_NOT_EXIST);
                }
                UserInfoDTO userInfoDTO = userFacade.getUserInfoByToken((String) objectMap.get("token"));
                AssertUtil.isNotNull(userInfoDTO, CommonResultCode.USER_NOT_EXIST);
                AssertUtil.isEqual(userInfoDTO.getForbidden(),CommonConstant.ONE,CommonResultCode.YOU_ARE_FORBIDDEN);
                if(sessionCheck.needPhone()){
                    AssertUtil.isNotBlank(userInfoDTO.getMobile(), CommonResultCode.USER_MUST_MOBILE);
                }
                request.setAttribute("userId", userInfoDTO.getUserId());
                MDC.put("operationId", String.valueOf(userInfoDTO.getUserId()));
                MDC.put("operationType", UserTypeEnum.USER.getDesc());
            }
        }
        // 只有返回true才会继续向下执行，返回false取消当前请求
        return true;
    }
}
