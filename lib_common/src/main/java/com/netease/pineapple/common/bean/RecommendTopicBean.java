package com.netease.pineapple.common.bean;

import com.netease.pineapple.common.http.JsonBase;
import com.netease.pineapple.common.utils.StringUtils;

/**
 * Created by zhaonan on 17/12/4.
 */

public class RecommendTopicBean extends JsonBase {


    private String id;
    private String name;
    private String icon;
    private String cover;

    private String subscribeCount;
    private String videoCount;
    private String subscribeFlag;


    public RecommendTopicBean(String id, String name, String icon, String cover,
                              String subscribeCount, String videoCount, String subscribeFlag) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.cover = cover;
        this.subscribeCount = subscribeCount;
        this.videoCount = videoCount;
        this.subscribeFlag = subscribeFlag;
    }


    public RecommendTopicBean() {
    }

    public void setSubscribeFlag(boolean flag) {
        this.subscribeFlag = flag ? "1" : "0";
    }

    public String getSubCntStr() {
        return StringUtils.getViewCountStr(subscribeCount,"订阅");
    }

    public boolean isSubscribed() {
        return "1".equals(subscribeFlag) ? true : false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(String subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(String videoCount) {
        this.videoCount = videoCount;
    }

    public String getSubscribeFlag() {
        return subscribeFlag;
    }

    public void setSubscribeFlag(String subscribeFlag) {
        this.subscribeFlag = subscribeFlag;
    }
}
