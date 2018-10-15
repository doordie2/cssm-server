package com.lcy.cssm.common.web.interceptor;


import com.mcilife.zlnsh.api.auth.facade.AuthAccessFacade;
import com.mcilife.zlnsh.api.auth.facade.AuthAdminFacade;
import com.mcilife.zlnsh.api.user.facade.UserFacade;
import com.mcilife.zlnsh.common.base.constant.CommonConstant;
import com.mcilife.zlnsh.common.base.constant.UserTypeEnum;
import com.mcilife.zlnsh.common.base.constant.aliyun.AliyunConstant;
import com.mcilife.zlnsh.common.base.exceptions.CommonResultCode;
import com.mcilife.zlnsh.common.base.exceptions.McException;
import com.mcilife.zlnsh.common.base.util.AssertUtil;
import com.mcilife.zlnsh.common.web.annotation.SessionCheck;
import com.mcilife.zlnsh.support.auth.dto.AuthAdminDTO;
import com.mcilife.zlnsh.support.user.dto.UserInfoDTO;
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
 * @author 王培
 * @create 2017-05-03 14:26
 **/
public class ConvertIdInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(ConvertIdInterceptor.class);

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private com.mcilife.zlnsh.api.user.facade.v3.UserFacade userFacadeV3;

    @Autowired
    private AuthAdminFacade authAdminFacade;

    @Autowired
    private AuthAccessFacade authAccessFacade;


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
                AuthAdminDTO authAdminDTO = authAdminFacade.getAuthAdminByTokenId(session.getAttribute("adminTokenId").toString());
                if (authAdminDTO == null) {
                    response.sendRedirect("/admin/login");
                    return false;
                }
                authAdminDTO.setProfile(AliyunConstant.COMMON_BUCKET + authAdminDTO.getProfile());
                if (sessionCheck.getMenu()) {
                    request.setAttribute("accessList", authAccessFacade.listAccess(authAdminDTO.getAdminId(), Integer.valueOf(CommonConstant.NUMBER_ZERO), authAdminDTO.getFlag()));
                    request.setAttribute("parentAccessId", StringUtils.isNotBlank(request.getParameter("parentAccessId")) ? Integer.valueOf(request.getParameter("parentAccessId")) : CommonConstant.BLANK);//不转成integer在menu.html里面判断会出错
                    request.setAttribute("accessId", StringUtils.isNotBlank(request.getParameter("accessId")) ? Integer.valueOf(request.getParameter("accessId")) : CommonConstant.BLANK);
                }
                request.setAttribute("authAdminDTO", authAdminDTO);
                request.setAttribute("adminId", authAdminDTO.getAdminId());
                session.setMaxInactiveInterval(28800);
                MDC.put("operationId", String.valueOf(authAdminDTO.getAdminId()));
                MDC.put("operationType", UserTypeEnum.ADMIN.getDesc());
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
