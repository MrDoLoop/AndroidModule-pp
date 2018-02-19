package com.netease.pineapple.common.bean;

import com.netease.pineapple.common.http.JsonBase;

/**
 * 简易网易号
 * Created by dengxuan on 18-1-23.
 */

public class SimpleNetEaseCode extends JsonBase {
    public String tid; //网易号TID
    public String ename; //网易号ename

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }
}
