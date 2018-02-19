package com.netease.pineapple.common.utils;

import com.google.gson.Gson;
import com.netease.pineapple.common.base.IBaseView;
import com.netease.pineapple.common.bean.CategoryListBean;
import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.common.base.api.INetApis;
import com.netease.pineapple.common.base.api.NetApiParamsBuilder;

import io.reactivex.Observable;

/**
 * Created by zhaonan on 2018/2/18.
 */

public class HttpUtils {

    private static INetApis sNetApis;

    public static void init() {
        RetrofitFactory.init(INetApis.HOST);
        sNetApis = RetrofitFactory.getRetrofit().create(INetApis.class);
    }


    /**
     * 获取首页列表
     * @param view
     * @param observer
     * @param gson
     * @param fn
     * @param offset
     * @param ename
     */
    public static void getHomeRecommendList(IBaseView view, BaseEntityObserver observer, Gson gson, int fn, int offset, String ename) {
        Observable<HomeListBean> observable = RetrofitFactory.getRetrofit(gson).create(INetApis.class)
                .getHomeRecommendList(NetApiParamsBuilder.getHomeRecommendListParams(fn,offset,ename));
        observable.compose(view.compose(view.bindToLife())).subscribe(observer);
    }

    public static void getHomeCategroyList(IBaseView view, BaseEntityObserver observer) {
        Observable<CategoryListBean> observable = sNetApis.getHomeCatergoryList();
        observable.compose(view.compose(view.bindToLife())).subscribe(observer);
    }

    public static void getViewHistroyList(IBaseView view, BaseEntityObserver observer, int start, int size, String lastId) {
        Observable<CategoryListBean> observable = sNetApis.getWatchHistory(NetApiParamsBuilder.getViewHistroyParams(start, size, lastId));
        observable.compose(view.compose(view.bindToLife())).subscribe(observer);
    }

}
