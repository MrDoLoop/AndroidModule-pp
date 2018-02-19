package com.netease.pineapple.common.bean;

import com.netease.pineapple.common.http.JsonBase;

/**
 * Created by zhaonan on 17/9/20.
 */

public class NextpageParamBean extends JsonBase {


    private String tag;

    private int start;
    private int size;

    private String lastId;

    public NextpageParamBean(String tag, int start, int size, String lastId) {
        this.tag = tag;
        this.start = start;
        this.size = size;
        this.lastId = lastId;
    }

    public NextpageParamBean() {
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getStart() {
        return start;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public int getSize() {
        return size;
    }

    public void setLastId(String lastId) {
        this.lastId = lastId;
    }
    public String getLastId() {
        return lastId;
    }

    public boolean hasMore() {
        if(start < 0) { // 没有下一页的数据了
            return false;
        }
        return true;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
}
