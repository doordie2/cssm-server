package com.lcy.cssm.provider.monitor.service;

import com.lcy.cssm.provider.monitor.mapper.RequestHeaderLogMapper;
import com.lcy.cssm.support.monitor.po.TbRequestHeaderLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 请求头信息日志service
 *
 * @auther 王培
 * @create 2017-09-30 12:32
 */
@RestController
@RequestMapping("/provider/log")
public class RequestHeaderLogService {

    @Autowired
    private RequestHeaderLogMapper requestHeaderLogMapper;

    /**
     * 新增请求头日志信息
     * @param userOs 操作系统
     * @param osVersion 操作系统版本
     * @param appVersion app版本
     * @param agentModel 设备类型
     * @param userAgent 手机名称
     * @param agentNum 设备编号
     * @param interfaceName 接口名称
     * @param ipAddress ip地址
     * @param ipCountry IP地址所在国家
     * @param ipArea ip地址所在地区
     * @param ipProvince ip地址所在省份
     * @param ipCity ip地址所在城市
     * @param uuid uuid
     */
    @RequestMapping(value = "/insertRequestHeaderLog", method = RequestMethod.POST)
    public void insertRequestHeaderLog(String userOs,String osVersion,String appVersion,String agentModel,
                                       String userAgent,String agentNum,String interfaceName,String ipAddress,
                                       String ipCountry,String ipArea,String ipProvince,String ipCity,String uuid,
                                       String operateId,String costTime,String appId){
        TbRequestHeaderLog tbRequestHeaderLog = new TbRequestHeaderLog();
        tbRequestHeaderLog.setUserOs(userOs);
        tbRequestHeaderLog.setOsVersion(osVersion);
        tbRequestHeaderLog.setAppVersion(appVersion);
        tbRequestHeaderLog.setAgentModel(agentModel);
        tbRequestHeaderLog.setUserAgent(userAgent);
        tbRequestHeaderLog.setAgentNum(agentNum);
        tbRequestHeaderLog.setInterfaceName(interfaceName);
        tbRequestHeaderLog.setIpAddress(ipAddress);
        tbRequestHeaderLog.setIpCountry(ipCountry);
        tbRequestHeaderLog.setIpArea(ipArea);
        tbRequestHeaderLog.setIpProvince(ipProvince);
        tbRequestHeaderLog.setIpCity(ipCity);
        tbRequestHeaderLog.setUuid(uuid);
        tbRequestHeaderLog.setOperateId(operateId);
        tbRequestHeaderLog.setCostTime(costTime);
        tbRequestHeaderLog.setAppId(appId);
        requestHeaderLogMapper.insertRequestHeaderLog(tbRequestHeaderLog);
    }
}
