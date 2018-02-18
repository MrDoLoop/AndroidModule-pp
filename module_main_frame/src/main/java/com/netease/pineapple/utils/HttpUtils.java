package com.netease.pineapple.utils;

import com.google.gson.Gson;
import com.netease.pineapple.base.IBaseView;
import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.net.api.INetApis;
import com.netease.pineapple.net.api.NetApiParamsBuilder;

import io.reactivex.Observable;

/**
 * Created by zhaonan on 2018/2/18.
 */

public class HttpUtils {
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
}
