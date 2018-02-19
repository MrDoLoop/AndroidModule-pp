package com.netease.pineapple.common.bean;

import com.netease.pineapple.common.http.BaseEntity;
import com.netease.pineapple.common.http.JsonBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bjdengxuan1 on 2017/11/13.
 */

public class WatchHistoryResultBean extends BaseEntity<WatchHistoryResultBean.WatchHistoryResultBeanData> {

    public static class WatchHistoryResultBeanData extends JsonBase {
        public NextpageParamBean nextPageParam;
        public List<VideoItemBean> videoList;

        public WatchHistoryResultBeanData(){
            nextPageParam = new NextpageParamBean();
            videoList = new ArrayList<VideoItemBean>();
        }
    }
}
