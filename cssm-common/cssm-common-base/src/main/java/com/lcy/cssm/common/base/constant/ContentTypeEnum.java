package com.lcy.cssm.common.base.constant;

public enum ContentTypeEnum {

    CONTENT_VIDEO("视频",1),

    CONTENT_SONG("音频",2),

    CONTENT_IMG_TEXT("图文",3),

    CONTENT_ALBUM("专题",4),

    CONTENT_ACTIVITY("活动",5),

    CONTENT_OLD_FRIEND("老友圈",6),

    MOMENT_COMMENT("老友圈评论",8),

    VOTE_NUM("广场舞投票数",1);


    private String desc;
    private Integer value;

    private ContentTypeEnum(String desc, Integer value) {
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
