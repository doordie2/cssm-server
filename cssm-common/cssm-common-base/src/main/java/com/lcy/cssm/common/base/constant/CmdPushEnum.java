package com.lcy.cssm.common.base.constant;

import com.alibaba.fastjson.JSONObject;

/**
 * @author: 赵天增
 * @date: 2018 -03-23
 * @描述: 通话推送
 */
public enum CmdPushEnum {
    /**
     * 关注用户
     */
    USER_ATTENTION("cmd.attention"),

    NOTIFY_LIST("cmd.list");


    private String key;

    public String getKey() {
        return key;
    }

    CmdPushEnum(String key) {
        this.key = key;
    }

    public static JSONObject getPushType(CmdPushEnum pushEnum) {
        JSONObject pushMap = new JSONObject();
        pushMap.put("cmdContent", pushEnum.key);
        pushMap.put("title", "新的信息");
        pushMap.put("pushType", "cmd");
        return pushMap;
    }
}
