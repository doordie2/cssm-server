package com.lcy.cssm.common.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * 消息提醒类型
 *
 * @author lcy
 * @create 2018-02-01 13:46
 */
public enum NotifyTypeEnum {

    USER_SYSTEM_NOTIFY("system", "[系统通知]", "SyetemList", "NATIVE"),
    USER_ATTENTION_NOTIFY("attention", "用户关注通知",  "UserFans", "NATIVE"),
    USER_MOMENT_LIKE_NOTIFY("momentLike", "用户老友圈点赞通知",  "CircleContentDetails", "NATIVE"),
    USER_MOMENT_COMMENT_LIKE_NOTIFY("momentCommentLike", "用户老友圈评论点赞通知",  "CircleReplyDetails", "NATIVE"),
    USER_COMMENT_NOTIFY("comment", "用户评论通知",  "CircleContentDetails", "NATIVE"),
    USER_REPLY_COMMENT_NOTIFY("reply", "用户评论通知",  "CircleReplyDetails", "NATIVE"),
    ACTIVITY_NOTIFY("activity", "[活动通知]",  "/dance/danceDetail.html?", "H5"),

   ;

    private String type;

    private String name;

    private String jumpUrl;

    private String urlType;

    NotifyTypeEnum(String type, String name,  String jumpUrl, String urlType) {
        this.type = type;
        this.name = name;
        this.jumpUrl = jumpUrl;
        this.urlType = urlType;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public static NotifyTypeEnum getEnum(String type) {
        NotifyTypeEnum resultEnum = null;
        NotifyTypeEnum[] enumAry = NotifyTypeEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (StringUtils.equals(enumAry[i].getType(), type)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }
}
