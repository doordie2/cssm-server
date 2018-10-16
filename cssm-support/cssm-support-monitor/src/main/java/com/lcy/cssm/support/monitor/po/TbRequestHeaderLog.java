package com.lcy.cssm.support.monitor.po;

import com.lcy.cssm.common.base.po.BasePO;

/**
 * 请求头里面的信息
 * @auther lcy
 * @create 2016-12-01 19:02
 */
public class TbRequestHeaderLog extends BasePO {

    /**用户设备的操作系统**/
    private String userOs;

    /**系统版本号**/
    private String osVersion;

    /**app版本号**/
    private String appVersion;

    /**手机型号**/
    private String agentModel;

    /**
     * 第三方appid
     */
    private String appId;

    /**手机名称**/
    private String userAgent;

    /**设备号**/
    private String agentNum;

    /**请求的接口名称**/
    private String interfaceName;

    /**ip地址**/
    private String ipAddress;

    /**ip地址所在国家**/
    private String ipCountry;

    /**ip地址所在区域**/
    private String ipArea;

    /**ip地址所在省**/
    private String ipProvince;

    /**ip地址所在城市**/
    private String ipCity;

    /**操作者id**/
    private String operateId;

    /**请求的uuid，用来对应traceId**/
    private String uuid;

    /**请求花费时间**/
    private String costTime;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getUserOs() {
        return userOs;
    }

    public void setUserOs(String userOs) {
        this.userOs = userOs;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAgentModel() {
        return agentModel;
    }

    public void setAgentModel(String agentModel) {
        this.agentModel = agentModel;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(String agentNum) {
        this.agentNum = agentNum;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpCountry() {
        return ipCountry;
    }

    public void setIpCountry(String ipCountry) {
        this.ipCountry = ipCountry;
    }

    public String getIpArea() {
        return ipArea;
    }

    public void setIpArea(String ipArea) {
        this.ipArea = ipArea;
    }

    public String getIpProvince() {
        return ipProvince;
    }

    public void setIpProvince(String ipProvince) {
        this.ipProvince = ipProvince;
    }

    public String getIpCity() {
        return ipCity;
    }

    public void setIpCity(String ipCity) {
        this.ipCity = ipCity;
    }


    public String getOperateId() {
        return operateId;
    }

    public void setOperateId(String operateId) {
        this.operateId = operateId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCostTime() {
        return costTime;
    }

    public void setCostTime(String costTime) {
        this.costTime = costTime;
    }
}
