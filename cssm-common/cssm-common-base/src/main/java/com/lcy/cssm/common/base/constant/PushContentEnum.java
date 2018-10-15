package com.lcy.cssm.common.base.constant;

/**
 * @author: 赵天增
 * @date: 2018 -02-12
 * @描述: 通知记录。。。使用模板去记录各种信息
 */
public enum PushContentEnum {
    /**
     * 用户关注
     */
    USER_ATTENTION(
            "",
            "MessageList",
            "中老年生活",
            "用户{}关注了你",
            "有用户关注了您",
            "user_attention",
            "user_attention_title"
    ),

    USER_LIKE(
        "",
        "MessageList",
        "中老年生活",
        "用户{}给你点了赞",
        "有用户点赞了您",
        "user_like",
        "user_like_title"
    ),


    USER_COMMENT_MOMENT(
        "",
        "MessageList",
        "中老年生活",
        "{}",
        "{}评论了你",
        "user_comment",
        "user_comment_title"
    ),

    SYSTEM(
        CommonConstant.ZLNSH_LOGO,
        "MessageList",
        "中老年生活",
        "{}",
        "[系统消息]",
        "system",
        "system_title"
    ),

    ACTIVITY_END(
        CommonConstant.ACTIVITY_NOTIFY_LOGO,
        "MessageList",
        "中老年生活",
        "{}",
        "[活动通知]",
        "activity_end",
        "activity_end_title"
    ),

    ACTIVITY_START(
        CommonConstant.ACTIVITY_NOTIFY_LOGO,
        "MessageList",
        "中老年生活",
        "{}",
        "[活动通知]",
        "activity_start",
        "activity_start_title"
    ),

    USER_REPLY_MOMENT_COMMENT(
            "",
            "MessageList",
            "中老年生活",
            "{}",
            "{}回复了你",
            "user_comment_reply",
            "user_comment_reply_title"
    );

    private String imgUrl;

    private String jumpUrlType;

    private String title;

    private String pushBody;

    private String pushTitle;

    private String bodyResource;

    private String titleResource;


    PushContentEnum(String imgUrl, String jumpUrlType, String pushTitle, String pushBody, String title, String bodyResource, String titleResource) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.pushBody = pushBody;
        this.pushTitle = pushTitle;
        this.bodyResource = bodyResource;
        this.jumpUrlType = jumpUrlType;
        this.titleResource = titleResource;
    }

    public String getJumpUrlType() {
        return jumpUrlType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getPushBody() {
        return pushBody;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public String getBodyResource() {
        return bodyResource;
    }

    public String getTitleResource() {
        return titleResource;
    }
}
