package com.lcy.cssm.common.base.constant;

public enum LikeTypeEnum {

    LIKE_ACTIVITY("活动",0),

    LIKE_ALBUM("专题",1),

    LIKE_SONG("音频",2),

    LIKE_VODEO("视频",3),


    LIKE_IMG_TEXT("图文",4),

    LIKE_OLDHOME("养老院",5),

    LIKE_OLDFRIEND("老友圈",6),

    LIKE_MOMENT_COMMENT("老友圈评论",8);


    private String desc;
    private Integer value;

    private LikeTypeEnum(String desc, Integer value) {
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
