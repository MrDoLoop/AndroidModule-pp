package com.netease.pineapple.common.bean;

import android.text.TextUtils;
import com.netease.pineapple.common.cache.LocalSavedInfoCacheManager;
import com.netease.pineapple.common.http.JsonBase;

/**
 * Created by md on 2018/1/11.
 */

public class VideoTopic extends JsonBase {

    private String alias;

    private String ename;


    private String tid;

    private String tname;

    private String topic_icons;


    public VideoTopic(String alias, String ename, String tid, String tname,
            String topic_icons) {
        this.alias = alias;
        this.ename = ename;
        this.tid = tid;
        this.tname = tname;
        this.topic_icons = topic_icons;
    }


    public VideoTopic() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTopic_icons() {
        return topic_icons;
    }

    public void setTopic_icons(String topic_icons) {
        this.topic_icons = topic_icons;
    }

    public boolean isSubscribed(){
        if(!TextUtils.isEmpty(tid)) {
            return LocalSavedInfoCacheManager.getInstance().isSubscribed(tid);
        }
        return false;
    }
}
