
package com.lcy.cssm.common.base.exceptions;

/**
 * 结果码接口，所有结果码枚举都需要实现该接口
 * @author 王培
 * @create 2017-07-16 17:15
 */
public interface ResultCode {

    /**
     * 获取异常类型
     * 
     * @return 异常类型枚举
     */
    ResultCodeType getType();

    /**
     * 获取结果码
     * 
     * @return 结果码
     */
    String getCode();

    /**
     * 获取结果描述
     * 
     * @return 结果描述
     */
    String getDesc();

}
