package com.lcy.cssm.support.monitor.exceptions;

import com.lcy.cssm.common.base.exceptions.ResultCode;
import com.lcy.cssm.common.base.exceptions.ResultCodeType;
import org.apache.commons.lang3.StringUtils;

/**
 * 日志模块错误码返回类
 * @author lcy
 * @create 2017-08-12 18:45
 **/
public enum MonitorResultCode implements ResultCode {

    SUCCESS("1", "处理成功");

    /** 错误码 */
    private String code;

    /** 描述 */
    private String desc;

    /**
     * 构造函数
     *
     * @param code  错误码
     * @param desc  描述
     */
    private MonitorResultCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 通过name获取结果码
     *
     * @param code  错误码
     * @return      返回业务结果码
     */
    public static MonitorResultCode getResultEnum(String code) {
        for (MonitorResultCode result : values()) {
            if (StringUtils.equals(result.getCode(), code)) {
                return result;
            }
        }
        return null;
    }
    @Override
    public ResultCodeType getType() {
        return ResultCodeType.MONITOR;
    }
    @Override
    public String getCode() {
        return this.code;
    }
    @Override
    public String getDesc() {
        return desc;
    }

}
