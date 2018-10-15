package com.lcy.cssm.common.base.constant.aliyun;

public enum ShareTypeEnum {

    CONTENT_VIDEO("视频",1),

    CONTENT_SONG("音频",2),

    CONTENT_IMG_TEXT("图文",3),

    CONTENT_ALBUM("专题",4),

    CONTENT_ACTIVITY("活动",5),

    GCW_TEAM("广场舞队伍",6),

    GCW_TEAM_DETAIL("广场舞详情",7),
    ABOUT_ZLNSH("关于中老年生活",8),
    MOMENT_INDEX("老友圈首页",9),
    MOMENT_DETAIL("老友圈详情",10);


    private String desc;
    private Integer value;

    private ShareTypeEnum(String desc, Integer value) {
        this.value = value;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
