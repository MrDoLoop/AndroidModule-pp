package com.netease.pineapple.bean;

import com.netease.pineapple.common.http.BaseEntity;
import com.netease.pineapple.common.http.JsonBase;

import java.util.List;

/**
 * Created by zhaonan on 17/9/20.
 */

public class HomeListBean extends BaseEntity<HomeListBean.HomeListDataBean> {

    // 首页数据datalist里面type对应的bean
    // 头部视频
    public static final int HOME_LIST_ITEM_BEAN_TYPE_HEADER = 1;
    // 一般的video
    public static final int HOME_LIST_ITEM_BEAN_TYPE_VIDEO = 2;
    // 推荐类型
    public static final int HOME_LIST_ITEM_BEAN_TYPE_RECOMMEND = 3;
    // 推荐类型V2
    public static final int HOME_LIST_ITEM_BEAN_TYPE_RECOMMEND_V2 = 13;

    public static final int HOME_LIST_ITEM_REFRESH_BTN = -100;



    public List<HomeListDataListItemBean> getDatalist() {
        return data != null ? data.getDatalist() : null;
    }

    public NextpageParamBean getNextpageParam() {
        return data != null ? data.getNextpageParam() : null;
    }

    public HomeListDataBean getData() {
        return this.data;
    }

    public void setData(HomeListDataBean data) {
        this.data = data;
    }


    /////////内部类定义///////////////////
    public static class HomeListDataListItemBean extends BaseEntity {
        private int type;
        private Object content;
        private String dataRespTime;//请求到数据的时间,曝光埋点用到
        public boolean insertRefresh = false;//是假数据,代表刷新条. 对应需求:首页推荐,第一次上拉加载更多的时候,在第五条数据显示的下一项加一个自动刷新的按钮.
        public int staticCount = -1;//视频曝光 需要原始数据的位置,记在这里

        public HomeListDataListItemBean(int type, Object content) {
            this.type = type;
            this.content = content;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public int getType() {
            return this.type;
        }

        public String getDataRespTime() {
            return dataRespTime;
        }

        public void setDataRespTime(String dataRespTime) {
            this.dataRespTime = dataRespTime;
        }
    }

    /**
     * 首页整体数据的bean里面的data
     */
    public static class HomeListDataBean extends JsonBase {

        private List<HomeListDataListItemBean> dataList;

        private NextpageParamBean nextPageParam;

        public List<HomeListDataListItemBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<HomeListDataListItemBean> dataList) {
            this.dataList = dataList;
        }

        public NextpageParamBean getNextPageParam() {
            return nextPageParam;
        }

        public void setNextPageParam(NextpageParamBean nextPageParam) {
            this.nextPageParam = nextPageParam;
        }

        public List<HomeListDataListItemBean> getDatalist() {
            return dataList;
        }


        public NextpageParamBean getNextpageParam() {
            return nextPageParam;
        }
    }
}
