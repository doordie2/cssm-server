package com.lcy.cssm.common.web.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.mcilife.zlnsh.api.interaction.facade.MomentFacade;
import com.mcilife.zlnsh.api.operation.facade.v2.ActivityFacade;
import com.mcilife.zlnsh.api.operation.facade.v2.ContentInfoFacade;
import com.mcilife.zlnsh.api.user.facade.UserFacade;
import com.mcilife.zlnsh.api.user.facade.v2.UserLikeFacade;
import com.mcilife.zlnsh.brige.mq.facade.LogMqBrigeFacade;
import com.mcilife.zlnsh.common.base.constant.HttpConstant;
import com.mcilife.zlnsh.common.base.util.StopWatchUtil;
import com.mcilife.zlnsh.common.web.annotation.HistoryCheck;
import com.mcilife.zlnsh.support.interaction.dto.MomentDTO;
import com.mcilife.zlnsh.support.operation.dto.v2.ActivityDTO;
import com.mcilife.zlnsh.support.operation.dto.v2.ContentInfoDTO;
import com.mcilife.zlnsh.support.user.dto.UserBrowseHistoryDTO;
import com.mcilife.zlnsh.support.user.dto.UserInfoDTO;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 基础interceptor，每个请求都会调用
 *
 * @author 王培
 * @create 2017-05-03 14:26
 **/
public class BaseInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(BaseInterceptor.class);

    @Autowired
    private LogMqBrigeFacade logMqBrigeFacade;

    @Autowired
    private UserLikeFacade userLikeFacade;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private ActivityFacade activityFacade;

    @Autowired
    private MomentFacade momentFacade;

    @Autowired
    private ContentInfoFacade contentInfoFacade;

    @Value("${environment}")
    private String environment;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        StopWatchUtil.startWatch();
        UUID uuid = UUID.randomUUID();
        MDC.put("threadUuid", uuid);
        Map<String, String> headerInfo = getHeadersInfo(request);
        logger.info("【请求头】 headerInfo = {}", JSON.toJSONString(headerInfo));
        String operator = request.getServletPath();
        String params="";
        if (!request.getParameterMap().isEmpty() || StringUtils.equals(request.getMethod(), HttpConstant.GET_METHOD)) {
            params = JSON.toJSONString(request.getParameterMap());
        } else {
            String contentStr = IOUtils.toString(request.getInputStream(), "utf-8");
            if(StringUtils.isNotBlank(contentStr)){
                if (!contentStr.startsWith("<xml>")) {
                    params = JSON.toJSONString(JSON.parseObject(contentStr, Map.class, Feature.AutoCloseSource));
                } else {
                    params = contentStr;
                }
            }
        }
        logger.info("【请求】methord={} request={}", operator, params);
        headerInfo.put("interFaceName", operator);
        headerInfo.put("ipAddress", getIpAddress(request));
        headerInfo.put("uuid", uuid.toString());
        request.setAttribute("headerInfo", headerInfo);
        request.setAttribute("appVersion", headerInfo.get("app-version"));
        request.setAttribute("appType", headerInfo.getOrDefault("apptype","2.1.0"));
        request.setAttribute("environment", environment);
        // 只有返回true才会继续向下执行，返回false取消当前请求

        try{
            HistoryCheck historyCheck = ((HandlerMethod) handler).getMethodAnnotation(HistoryCheck.class);
            if (historyCheck != null) {
                Integer historyId = Integer.valueOf(operator.substring(operator.lastIndexOf("/")+1));
                String historyType = historyCheck.type();
                UserInfoDTO userInfoDTO = userFacade.getUserInfoByToken(headerInfo.get("operatertoken"));
                Integer userId = null ;
                if(userInfoDTO!=null){
                    userId = userInfoDTO.getUserId();
                }
//                UserBrowseHistoryDTO userBrowseHistoryDTO = userLikeFacade.getUserBrowseHistory(userId, historyId, historyType,headerInfo.get("agent-num"));
//                if (userBrowseHistoryDTO == null) {
                    UserBrowseHistoryDTO userBrowseHistoryDTO2 = new UserBrowseHistoryDTO();
                    //没有记录则增加一条,或者浏览间隔超过一天
                    userBrowseHistoryDTO2.setCreateby(String.valueOf(userId));
                    userBrowseHistoryDTO2.setHistoryId(historyId);
                    userBrowseHistoryDTO2.setHistoryType(historyType);
                    userBrowseHistoryDTO2.setUserId(userId);
                    userBrowseHistoryDTO2.setAgentNum(headerInfo.get("agent-num"));
                    userLikeFacade.insertUserBrowseHistory(userBrowseHistoryDTO2);
//                } else if (!DateUtils.isOverIntervalLimit(new Date(), userBrowseHistoryDTO.getUpdateDate(), CommonConstant.ONE)) {
//                    //如果记录时间间隔在一天之内，修改浏览时间
//                    userLikeFacade.updateUserBrowseHistory(userBrowseHistoryDTO);
//                }

                //修改各个内容的浏览量
                if (historyType.equals("content")) {
                    ContentInfoDTO contentInfoDTO = contentInfoFacade.getContentInfoById(historyId);
                    contentInfoDTO.setViewCount(contentInfoDTO.getViewCount() + 1);
                    contentInfoFacade.updateContentInfo(contentInfoDTO);
                }  else if (historyType.equals("activity")) {
                    ActivityDTO activityDTO = activityFacade.getActivityInfoById(historyId);
                    activityDTO.setViewCount(activityDTO.getViewCount() + 1);

                    activityFacade.updateActivity(activityDTO);
                } else if (historyType.equals("moment")) {
                    MomentDTO momentDTO = momentFacade.getMomentById(historyId);
                    momentDTO.setPageViews(momentDTO.getPageViews() + 1);
                    momentFacade.updateMoment(momentDTO);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        Long totleTime = StopWatchUtil.getTotalTime();
        logger.info("【响应】 耗时={}ms, requestUri = {}", totleTime + "", request.getServletPath());
        Map<String, String> headerInfo = (Map<String, String>) request.getAttribute("headerInfo");
        headerInfo.put("costTime", String.valueOf(totleTime));
        try {
            logMqBrigeFacade.saveRequestHeaderLog(headerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取请求头
     *
     * @param request
     * @return
     */
    public static Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        //"***.***.***.***".length() = 15
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
