package com.lcy.cssm.common.base.exceptions;


import com.alibaba.fastjson.JSONObject;
import com.lcy.cssm.common.base.filter.ResourceValueFilter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 返回类定义
 *
 * @author lcy
 * @create 2017-07-16 17:15
 **/
public class CommonResult implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -1018655635101946594L;

    /**
     * 成功状态
     */
    private boolean success = false;

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 结果描述
     */
    private String resultDesc;

    private Object content;

    /**
     * 默认构造函数。
     */
    public CommonResult() {
        super();
    }

    /**
     * 全参构造函数。
     *
     * @param success 成功状态
     */
    public CommonResult(boolean success) {
        this.success = success;
    }

    /**
     * 全参构造函数。
     *
     * @param success    成功状态
     * @param resultCode 结果码
     */
    public CommonResult(boolean success, String resultCode) {
        this.success = success;
        this.resultCode = resultCode;
    }

    /**
     * 全参构造函数。
     *
     * @param success    成功状态
     * @param resultCode 结果码
     * @param resultDesc 结果描述
     */
    public CommonResult(boolean success, String resultCode, String resultDesc) {
        this.success = success;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public String toJson() {
        JSONObject js = new JSONObject();
        js.put("success", success);
        js.put("resultCode", resultCode);
        js.put("content", content);
        js.put("resultDesc", resultDesc);
        return JSONObject.toJSONString(js, ResourceValueFilter.getInstance());
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
