package com.netease.pineapple.bean;

/**
 * Created by zhaonan on 2018/2/18.
 */

public class ListMultiTypeBean {
    private int type;
    private Object dataObj;

    public ListMultiTypeBean(int type, Object dataObj) {
        this.type = type;
        this.dataObj = dataObj;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDataObj(Object dataObj) {
        this.dataObj = dataObj;
    }

    public int getType() {
        return type;
    }

    public Object getDataObj() {
        return dataObj;
    }

    public interface ListMultiTypeBeanType {

        /** 加载更多 */
        int TYPE_LOAD_MORE = 1;

        /** 手动更多 */
        int TYPE_MANUALLY_LOAD_MORE = 2;

        ///需要保留新添加的
        int TYPE_HOME_LIST_ITEM = 100;

        /** 推荐 */
        int TYPE_VIDEO_LIST_RECOMMEND_ITEM = 101;

        int TYPE_HOME_LIST_HEAD_ITEM = 102;

        /** 上次看到这里 */
        int TYPE_HOME_LIST_LAST_TIME = 103;

        /**发现页列表类型*/
        int TYPE_DISCOVERY_LIST_ITEM = 103;
        /**发现页列表精选类型*/
        int TYPE_DISCOVERY_LIST_FEATURED_ITEM = 104;
        /**主题详情页 详情*/
        int TYPE_TOPIC_DETAIL_TOPIC_ITEM = 105;
        /**主题详情页 视频*/
        int TYPE_TOPIC_DETAIL_VIDEO_ITEM = 106;
        /**视频情页 视频*/
        int TYPE_VIDEO_DETAIL_VIDEO_ITEM = 107;

        /**视频情页 推荐视频 header*/
        int TYPE_VIDEO_DETAIL_RET_HEADER_VIDEO_ITEM = 108;

        /**视频情页 推荐视频 header*/
        int TYPE_VIDEO_DETAIL_RET_HEADER_OPT_VIDEO_ITEM = 109;

        /**搜索结果 类型 主题/视频*/
        int TYPE_SEARCH_RESULT_DATA_TYPE = 110;
        /**搜索结果 主题*/
        int TYPE_SEARCH_RESULT_TOPIC_TYPE = 111;
        /**搜索结果 视频*/
        int TYPE_SEARCH_RESULT_VIDEO_TYPE = 112;
        /**主题列表*/
        int TYPE_TOPIC_LIST = 113;
        /**发现页列表Banner类型*/
        int TYPE_DISCOVERY_BANNER_ITEM = 114;
        /**观看历史 Video item*/
        int TYPE_WATCH_HISTORY_VIDEO_ITEM = 115;
        /**观看历史 分类 item （今天 or 更早）*/
        int TYPE_WATCH_HISTORY_CLASSIC_ITEM_TODAY = 116;//今天
        int TYPE_WATCH_HISTORY_CLASSIC_ITEM_OLDER = 117;//更早
        /**主题详情页面 推荐主题类型*/
        int TYPE_FEATURED_TOPIC_LIST = 118;
        /**搜索结果页,主题为空的UI*/
        int TYPE_SEARCH_RESULT_EMPTY_TOPIC = 119;
        /**发现主题分类 */
        int TYPE_TOPIC_CLASSIFY = 120;
        /**合集列表*/
        int TYPE_ALL_COLLECTIONS_ITEM = 121;
        /**合集列表*/
        int TYPE_TOPIC_CATEGORY_ITEM = 122;
        /**广告 大图*/
        int TYPE_LIST_AD_BIG_IMG_ITEM = 123;
        /**广告*/
        int TYPE_LIST_AD_VIDEO_ITEM = 124;
        /** 刷新按钮 */
        int HOME_LIST_ITEM_REFRESH_BTN = 125;
        /** 网易号详情页没有视频的情况 */
        int TYPE_TOPIC_DETAIL_NO_VIDEO_ITEM = 126;
    }

}
