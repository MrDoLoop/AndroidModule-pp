package com.netease.pineapple.common.base.api;


import com.netease.pineapple.common.bean.CategoryListBean;
import com.netease.pineapple.common.bean.HomeListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhaonan on 2018/2/16.
 */

public interface INetApis {

    public static final String HOST = "https://boluo.m.163.com";


    /**
     * 获取首页推荐列表的接口
     */
//    @GET("https://boluo.m.163.com/recommend/getChanListNews")
//    Observable<ResponseBody> getHomeRecommendList(@QueryMap Map<String, String> map);
    /**
     * 获取首页推荐列表的接口
     */
    @GET("/recommend/getChanListNews")
    Observable<HomeListBean> getHomeRecommendList(@QueryMap Map<String, String> map);

    /**
     * 获取首页推荐栏目
     */
    @GET("/recommend/video/tablist")
    Observable<CategoryListBean> getHomeCatergoryList();


    /**
     * 观看历史记录
     */
    @GET("/pa/api/watch/wyh/video/list")
    Observable<CategoryListBean> getWatchHistory(@QueryMap Map<String, String> map);

//    /**
//     * 观看历史记录
//     * */
//    public static final String WATCH_HISTORY = HOST_HTTPS + "/pa/api/watch/wyh/video/list";
//    public static void requestWatchHistory(int start, int size, String lastId,TextResponseCallback callback){
//        RequestParams params = new RequestParams();
//        params.put("start", start);
//        params.put("size", size);
//        params.put("lastId", lastId);
//        OkHttpClientUtils.getAsync(WATCH_HISTORY, params, callback);
//    }



}
