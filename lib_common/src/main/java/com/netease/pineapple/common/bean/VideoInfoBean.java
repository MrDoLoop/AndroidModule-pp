package com.netease.pineapple.common.bean;

import com.netease.pineapple.common.http.JsonBase;

/**
 * Created by zhaonan on 17/9/25.
 */


public class VideoInfoBean extends JsonBase {


    private String vid;
    private String url;
    private String size;


    public VideoInfoBean(String vid, String url, String size) {
        this.vid = vid;
        this.url = url;
        this.size = size;
    }


    public VideoInfoBean() {
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
