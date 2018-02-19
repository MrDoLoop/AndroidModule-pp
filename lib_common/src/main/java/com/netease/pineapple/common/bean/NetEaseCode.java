package com.netease.pineapple.common.bean;


import com.netease.pineapple.common.cache.LocalSavedInfoCacheManager;
import com.netease.pineapple.common.utils.StringUtils;

/**
 * 网易号
 * Created by dengxuan on 18-1-23.
 */

public class NetEaseCode extends SimpleNetEaseCode {
    public String tname; //网易号TID
    public String alias; //网易号ename
    public String icons;
    public String background;
    public int subscribeCount;
    public long updateTime;

    //
    private boolean isSubed = false; //是否已订阅

    private String dataRespTime;//请求到数据的时间,曝光埋点用到
    public String getDataRespTime() {
        return dataRespTime;
    }
    public void setDataRespTime(String dataRespTime) {
        this.dataRespTime = dataRespTime;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getIcons() {
        return icons;
    }

    public void setIcons(String icons) {
        this.icons = icons;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getSubscribeCount() {
        return subscribeCount;
    }

    public void setSubscribeCount(int subscribeCount) {
        this.subscribeCount = subscribeCount;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isSubed() {
        return LocalSavedInfoCacheManager.getInstance().isSubscribed(tid);
    }

    /**
     * 不要直接更新本地数据
     * @param subed
     */
    @Deprecated
    public void setSubed(boolean subed) {
        //LocalSavedInfoCacheManager.getInstance().putSubscribedInfo(tid,subed);
    }

    public String getSubCountStr() {
        return StringUtils.getViewCountStr(String.valueOf(subscribeCount),"订阅");
    }
}
