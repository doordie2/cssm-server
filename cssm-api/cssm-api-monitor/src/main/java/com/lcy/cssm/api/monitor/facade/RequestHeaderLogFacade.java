package com.lcy.cssm.api.monitor.facade;

import com.lcy.cssm.api.monitor.configuration.MonitorConfiguration;
import com.lcy.cssm.api.monitor.fallback.RequestHeaderLogFacadeFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 请求头日志信息接口
 * @auther lcy
 * @create 2017-09-30 12:05
 */
@FeignClient(name = "cssm-provider-monitor",configuration = MonitorConfiguration.class,fallbackFactory = RequestHeaderLogFacadeFallbackFactory.class)
public interface RequestHeaderLogFacade {

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
    @RequestMapping(value = "/provider/log/insertRequestHeaderLog", method = RequestMethod.POST)
    void insertRequestHeaderLog(@RequestParam(value = "userOs", required = false) String userOs, @RequestParam(value = "osVersion", required = false) String osVersion,
                                @RequestParam(value = "appVersion", required = false) String appVersion, @RequestParam(value = "agentModel", required = false) String agentModel,
                                @RequestParam(value = "userAgent", required = false) String userAgent, @RequestParam(value = "agentNum", required = false) String agentNum,
                                @RequestParam(value = "interfaceName", required = false) String interfaceName, @RequestParam(value = "ipAddress", required = false) String ipAddress,
                                @RequestParam(value = "ipCountry", required = false) String ipCountry, @RequestParam(value = "ipArea", required = false) String ipArea,
                                @RequestParam(value = "ipProvince", required = false) String ipProvince, @RequestParam(value = "ipCity", required = false) String ipCity,
                                @RequestParam(value = "uuid", required = false) String uuid, @RequestParam(value = "operateId", required = false) String operateId,
                                @RequestParam(value = "costTime", required = false) String costTime, @RequestParam(value = "appId", required = false) String appId);
}
