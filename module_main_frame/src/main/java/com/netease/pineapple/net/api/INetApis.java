package com.netease.pineapple.net.api;


import com.netease.pineapple.bean.CategoryListBean;
import com.netease.pineapple.bean.HomeListBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhaonan on 2018/2/16.
 */

public interface INetApis {

    public static final String HOST = "https://boluo.m.163.com/";


    /**
     * 获取首页推荐列表的接口
     */
//    @GET("https://boluo.m.163.com/recommend/getChanListNews")
//    Observable<ResponseBody> getHomeRecommendList(@QueryMap Map<String, String> map);
    /**
     * 获取首页推荐列表的接口
     */
    @GET("recommend/getChanListNews")
    Observable<HomeListBean> getHomeRecommendList(@QueryMap Map<String, String> map);

    /**
     * 获取首页推荐栏目
     */
    @GET("recommend/video/tablist")
    Observable<CategoryListBean> getHomeCatergoryList();

}
