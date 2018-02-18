package com.netease.pineapple.bean;

import com.netease.pineapple.common.http.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaonan on 17/9/27.
 */

public class VideoDetailBean extends BaseEntity<VideoDetailBean.VideoDetailDataBean> {

    public VideoItemBean getVideoDetail() {
        if(data != null) {
            return data.getVideoDetail();
        }
        return null;
    }

    public List<VideoDetailListDataListItemBean> getVideoList() {
        if(data != null) {
            return data.getDatalist();
        }
        return null;
    }

    public List<VideoDetailListDataListItemBean> getDatalist() {
        return data != null ? data.getDatalist() : null;
    }

    public NextpageParamBean getNextpageParam() {
        return data != null ? data.getNextpageParam() : null;
    }

    public VideoDetailDataBean getData() {
        return this.data;
    }

    public void setData(VideoDetailDataBean data) {
        this.data = data;
    }


    /////////内部类定义///////////////////
    public static class VideoDetailListDataListItemBean extends BaseEntity {

        public static final int TYPE_VIDEO_DETAIL_MAIN_ITEM = 9; // 视屏详情主视频
        public static final int TYPE_VIDEO_DETAIL_RELATIVE_VIDEO_ITEM = 10;// 视屏详情下面列表里面的视频条目
        public static final int TYPE_VIDEO_DETAIL_RELATIVE_VIDEO_REC_ITEM = 13;// 视屏详情相关推荐item

        private int type;
        private Object content;
        private String dataRespTime;//请求到数据的时间,曝光埋点用到
        public String getDataRespTime() {
            return dataRespTime;
        }

        public void setDataRespTime(String dataRespTime) {
            this.dataRespTime = dataRespTime;
        }
        public VideoDetailListDataListItemBean(int type, Object content) {
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
    }

    /**
     * 整体数据的bean里面的data
     */
    public static class VideoDetailDataBean extends BaseEntity {

        private List<VideoDetailListDataListItemBean> dataList;

        private NextpageParamBean nextPageParam;

        private VideoItemBean videoDetail;

        public VideoItemBean getVideoDetail() {
            return videoDetail;
        }

        public void setVideoDetail(VideoItemBean vItemBean) {
            this.videoDetail = vItemBean;
        }

        public List<VideoDetailListDataListItemBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<VideoDetailListDataListItemBean> dataList) {
            this.dataList = dataList;
        }

        public NextpageParamBean getNextPageParam() {
            return nextPageParam;
        }

        public void setNextPageParam(NextpageParamBean nextPageParam) {
            this.nextPageParam = nextPageParam;
        }

        public List<VideoDetailListDataListItemBean> getDatalist() {
            return dataList;
        }


        public NextpageParamBean getNextpageParam() {
            return nextPageParam;
        }
    }
    /**
     * 推荐视频的bean
     */

    public static class RecommendItemBean extends BaseEntity {
        String title;
        List<RecommendTopicListItemBean> dataList;
        List<RecommendTopicBean> topicList;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<RecommendTopicBean> getTopicList() {
            if(dataList != null && topicList == null) {
                topicList = new ArrayList<>();
                for(RecommendTopicListItemBean bean : dataList) {
                    topicList.add(bean.getContent());
                }
            }
            if(topicList == null) {
                topicList = new ArrayList<>();
            }
            return topicList;
        }

        public List<RecommendTopicListItemBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<RecommendTopicListItemBean> dataList) {
            this.dataList = dataList;
        }
    }

    public static class RecommendTopicListItemBean extends BaseEntity {
        private String type;
        private RecommendTopicBean content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public RecommendTopicBean getContent() {
            return content;
        }

        public void setContent(RecommendTopicBean content) {
            this.content = content;
        }
    }
}



