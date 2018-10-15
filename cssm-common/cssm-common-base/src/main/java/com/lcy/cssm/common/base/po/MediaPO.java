package com.lcy.cssm.common.base.po;

/**
 * @Author: 王培
 * @Date: 2018/5/24 15:01
 * @Description: 多媒体模型，包括图片，视频，音频
 */
public class MediaPO {
    private String thumb;

    private String mediaUrl;

    private String totalTime;

    private String key;

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
