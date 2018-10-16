package com.lcy.cssm.common.base.exceptions;

/**
 * 要feign处理过的异常，是provider里面抛出来的
 *
 * @auther lcy
 * @create 2017-08-04 14:32
 */
public class McFeignException extends RuntimeException{

    private String errorCode;

    private String errorDesc;

    public McFeignException(){

    }

    public McFeignException(String errorCode, String errorDesc) {
        super(constructMessage(errorCode, errorDesc));
        this.errorCode=errorCode;
        this.errorDesc=errorDesc;
    }

    public String getErrorCode() {
        return errorCode;
    }


    public String getErrorDesc() {
        return errorDesc;
    }

    public static String constructMessage(String resultCode, String message) {
        return resultCode + "," + message + "," + "McFeignException";
    }


}
