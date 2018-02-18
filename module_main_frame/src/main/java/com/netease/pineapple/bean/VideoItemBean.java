package com.netease.pineapple.bean;

import android.text.TextUtils;

import com.netease.pineapple.cache.LocalSavedInfoCacheManager;
import com.netease.pineapple.common.http.JsonBase;
import com.netease.pineapple.common.utils.DateUtil;
import com.netease.pineapple.common.utils.StringUtils;

import java.util.Calendar;

/**
 * Created by dengxuan on 17-9-26.
 */

public class VideoItemBean extends JsonBase {

    // 不是bean里面的数据 本地添加的 当前播放位置
    private long position = -1;
    // 数据在list中的当前位置
    private int posInList = -1;

    private boolean showSubBtn = false;
    //////////////
    private String vid;
    private String title;
    private String cover;
    private String multiVersion;
    private String watchCount;
    private long publishTime;
    private String replyId;
    private int replyCount;
    private long duration;
    private long watchTime;
    private VideoInfoBean mp4;
    private VideoInfoBean m3u8;
    private NetEaseCode netEaseCode;
    private String category;
    private String topicDesc;
    private VideoTopic videoTopic;
    private long publicTime; // 视频详情页面 相关推荐用的这个

    // 本地添加的数据
    private boolean isViewed = false;

//    public String mCurrentCategory;//打点需要用到 当前视频所在的栏目
    private String dataRespTime;//请求到数据的时间,曝光埋点用到
    public String getDataRespTime() {
        return dataRespTime;
    }
    public void setDataRespTime(String dataRespTime) {
        this.dataRespTime = dataRespTime;
    }


    public NetEaseCode getNetEaseCode() {
        return netEaseCode;
    }

    public void setNetEaseCode(NetEaseCode netEaseCode) {
        this.netEaseCode = netEaseCode;
    }

    public String getTid() {
        if(netEaseCode != null) {
            return netEaseCode.getTid();
        }
        if(videoTopic != null) {
            return videoTopic.getTid();
        }
        return "";
    }

    public String getTname() {
        if(netEaseCode != null) {
            return netEaseCode.getTname();
        }
        if(videoTopic != null) {
            return videoTopic.getTname();
        }
        return "";
    }


    public void setViewed(boolean viewed) {
        this.isViewed = viewed;
    }

    public boolean isViewed() {
        return isViewed;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public void setPublicTime(long publicTime) {
        this.publishTime = publicTime;
    }

    public VideoInfoBean getMp4() {
        return mp4;
    }

    public void setMp4(VideoInfoBean mp4) {
        this.mp4 = mp4;
    }

    public VideoInfoBean getM3u8() {
        return m3u8;
    }

    public void setM3u8(VideoInfoBean m3u8) {
        this.m3u8 = m3u8;
    }

    public boolean getLikeFlag() {
        return LocalSavedInfoCacheManager.getInstance().isLiked(vid);
    }

    /**
     * 不要直接更新本地数据
     * @param likeFlag
     */
    @Deprecated
    public void setLikeFlag(boolean likeFlag) {
        //LocalSavedInfoCacheManager.getInstance().putLikedInfo(vid, likeFlag);
    }

    public long getPublicTime() {
        return this.publishTime;
    }

    public String getPublicTimeStr() {
        if(publishTime<=0){
            return DateUtil.getDurationD_M(publicTime);
        }
        return DateUtil.getDurationD_M(publishTime);
    }

    public long getDuration() {
        return this.duration;
    }

    public String getDurationStr() {
        return DateUtil.getTimeFormat(this.duration);
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMp4Url() {
        if(mp4 != null) {
            return mp4.getUrl();
        }
        return "";
    }

    public String getShareNameDes() {
        StringBuilder sb = new StringBuilder("");
        if(videoTopic != null && !TextUtils.isEmpty(videoTopic.getTname())){
            sb.append("来自: ").append(videoTopic.getTname());
        }
        return sb.toString();
    }

    public String getMultiVersion() {
        return multiVersion;
    }

    public void setMultiVersion(String multiVersion) {
        this.multiVersion = multiVersion;
    }

    public String getWatchCount() {
        return watchCount;
    }

    public String getWatchCountStr() {
        return StringUtils.getViewCountStr(watchCount,"观看");
    }

    public void setWatchCount(String watchCount) {
        this.watchCount = watchCount;
    }

    public int getPosInList() {
        return posInList;
    }

    public void setPosInList(int pos) {
        this.posInList = pos;
    }

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public String getVideoUrl() {
        return getMp4Url();
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public long getWatchTime() {
        return watchTime;
    }

    public void setWatchTime(long watchTime) {
        this.watchTime = watchTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public VideoTopic getVideoTopic() {
        if(videoTopic == null){
            videoTopic = new VideoTopic();
        }
        return videoTopic;
    }

    public void setVideoTopic(VideoTopic videoTopic) {
        this.videoTopic = videoTopic;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public boolean getShowSubBtn() {
        return this.showSubBtn;
    }

    public void setShowSunBtn(boolean showSubBtn) {
        this.showSubBtn = showSubBtn;
    }

    public static VideoItemBean buildVideoInfoItem(String vid, String topicId, String videoId, String cover) {
        VideoItemBean infoItem = new VideoItemBean();
        infoItem.setVid(videoId);
        infoItem.setCover(cover);
        infoItem.setVid(vid);
        return infoItem;
    }

    /**
     * 是否是今天观看过的视频，从当天00:00:00开始算起
     * */
    public boolean isTodaysVideo(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long zero = cal.getTimeInMillis();
        return getWatchTime() >= zero;
    }

    public static boolean isVidEq(VideoItemBean v1, VideoItemBean v2) {
        if(v1 != null && v2 != null && v1.getVid() != null && v2.getVid() != null) {
            return v1.getVid().equals(v2.getVid());
        }

        return false;
    }
}
