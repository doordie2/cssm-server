package com.lcy.cssm.common.base.constant;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : 赵天增
 * @create : 2017-10-19 14:58
 * 描述 ：
 */
public enum UrlResourceEnum {

    CATEGORY_INDEX("category/categoryIndex.html?v=1.0.0"),
    CATEGORY_SECOND_LIST("category/secondList.html?v=1.0.0"),
    CATEGORY_THIRD_LIST("category/thirdList.html?v=1.0.0"),
    CATEGORY_ALBUM("category/album.html?v=1.0.0"),
    CATEGORY_VIDEO("category/video.html?v=1.0.0"),
    CATEGORY_MUSIC("category/music.html?v=1.0.0"),
    CATEGORY_ARTICLE("category/article.html?v=1.0.0"),
    CATEGORY_ARTICLE_NO_RECOMMEND("category/articleNoRecommend.html?v=1.0.0"),

    CATEGORY_CONTENT("category/contentDetail.html?v=1.0.0"),


    MOMENT_INDEX("moments/momentsIndex.html?v=1.0.0"),
    MOMENT_DETAIL("moments/momentsDetail.html?v=1.0.0"),
    MOMENT_DETAIL_COMMENTS("moments/detailComments.html?v=1.0.0"),
    MOMENT_EDITOR("moments/editor.html?v=1.0.0"),
    MOMENT_TA("moments/ta.html?v=1.0.0"),
    MOMENT_TA_NEW("moments/momentsUser.html?v=1.0.0"),

    ACTIVITY_INDEX("activity/activityIndex.html?v=1.0.0"),
    ACTIVITY_DETAIL("activity/activityDetail.html?v=1.0.0"),

    GCW_INDEX("dance/danceIndex.html?v=1.0.0"),
    GCW_DETAIL("dance/danceDetail.html?v=1.0.0"),

    VOTE_INDEX("https://album.mcilife.com/vote/?v=1.0.0"),
    VOTE_ITEM("https://album.mcilife.com/vote/item/?v=1.0.0"),
    VOTE_INTRO("https://album.mcilife.com/vote/intro/?v=1.0.0"),
    VOTE_MIJI("https://album.mcilife.com/vote/miji/?v=1.0.0"),
    VOTE_SIGN("https://album.mcilife.com/vote/sign/?v=1.0.0"),

    MALL_INDEX("https://album.mcilife.com/mall/"),
    MALL_ITEM ("https://album.mcilife.com/mall/?c=item&itemid="),
    MALL_MY("https://album.mcilife.com/mall/?c=mytrades"),


    PHP_ALBUM_INDEX("https://album.mcilife.com/photos/"),
    PHP_ALBUM_HOME("https://album.mcilife.com/photos/home/"),
    PHP_ALBUM_SHOW("https://album.mcilife.com/photos/show/"),
    PHP_ALBUM_SQUARE("https://album.mcilife.com/photos/square/"),
    PHP_ALBUM_EDIT("https://album.mcilife.com/photos/edit/"),

    //运营方提出的跳转url
    OPERATION_URL_1("https://w.url.cn/s/AaZ7Kw5"),
    OPERATION_URL_2("https://mp.weixin.qq.com/s/fbMMOxWe7NGinvrTmI9w7A"),
    OPERATION_URL_3("https://w.url.cn/s/AzwnT6t"),
    OPERATION_URL_4("https://w.url.cn/s/ATbRF1j"),
    NOT_ATTENTION_URL("https://mcwebresource.mcilife.com/about/weixin_subscribe.html"),
    NOT_ATTENTION_URL_ONE("https://mcwebresource.mcilife.com/about/weixin_subscribe1.html"),
    NOT_ATTENTION_URL_TWO("https://mcwebresource.mcilife.com/about/weixin_subscribe2.html"),
    NOT_ATTENTION_URL_THREE("https://mcwebresource.mcilife.com/about/weixin_subscribe3.html"),

    WECHAT_JUMP("common/init.html"),

    IMGTEXT_URL_PRFIX("https://mcwebresource.mcilife.com/product/category/contentDetail.html?contentId="),

    DEFAULT("default.html?v=1.0.0");






    /**
     * 枚举值
     */
    private String value;


    UrlResourceEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public static UrlResourceEnum getEnum(String value) {
        UrlResourceEnum resultEnum = null;
        UrlResourceEnum[] enumAry = UrlResourceEnum.values();
        for (int i = 0; i < enumAry.length; i++) {
            if (StringUtils.equals(enumAry[i].toString(), value)) {
                resultEnum = enumAry[i];
                break;
            }
        }
        return resultEnum;
    }
}
