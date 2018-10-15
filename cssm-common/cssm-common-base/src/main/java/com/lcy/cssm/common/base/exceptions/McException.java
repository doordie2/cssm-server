package com.lcy.cssm.common.base.exceptions;


/**
 *  业务异常
 * @author 王培
 * @create 2016-12-01 17:15
 */
public class McException extends RuntimeException {

    private ResultCode resultCode;

    private Object[] params;

    public McException(){

    }

    public McException(String message) {
        super(constructMessage(CommonResultCode.COMMON_FAIL_ERROR.getCode(), message));
        resultCode = CommonResultCode.COMMON_FAIL_ERROR;
    }


    public McException(String message, Throwable cause) {
        super(constructMessage(CommonResultCode.COMMON_FAIL_ERROR.getCode(), message), cause);
        resultCode = CommonResultCode.COMMON_FAIL_ERROR;
    }

    public McException(ResultCode resultCode, String message, Throwable cause) {
        super(constructMessage(resultCode.getCode(), message), cause);
        this.resultCode = resultCode;
    }
//
//    public McException(ResultCode resultCode, String message) {
//        super(constructMessage(resultCode.getCode(), message));
//        this.resultCode = resultCode;
//    }

    public McException(ResultCode resultCode) {
        super(constructMessage(resultCode.getCode(),resultCode.getDesc()));
        this.resultCode = resultCode;
    }

    public McException(ResultCode resultCode, Object ...params) {
        super(constructMessage(resultCode.getCode(),resultCode.getDesc()));
        this.resultCode = resultCode;
        this.params = params;
    }
    /**
     * Getter for property 'resultCode'.
     *
     * @return Value for property 'resultCode'.
     */
    public ResultCode getResultCode() {
        return resultCode;
    }

    public Object[] getParams() {
        return params;
    }

    public static String constructMessage(String resultCode, String message) {
        return resultCode + "," + message + "," + "McException";
    }

}
