package com.netease.pineapple.home;

import com.google.gson.Gson;
import com.netease.pineapple.common.base.IBaseView;
import com.netease.pineapple.common.base.OnDataReturnListener;
import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.bean.HomeListResDeserializer;
import com.netease.pineapple.common.cache.CacheHelper;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.utils.ErrorActionUtils;
import com.netease.pineapple.common.utils.GsonUtils;
import com.netease.pineapple.common.utils.HttpUtils;
import com.netease.pineapple.common.utils.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhaonan on 2018/2/27.
 */

public class HomeCategoryModel {
    private String mEname;
    private int mFn = 1;
    private Gson mHomeGson;

    public HomeCategoryModel() {
        this.mHomeGson = GsonUtils.getGsonWithDeserializer(HomeListBean.class, new HomeListResDeserializer());
    }

    public void setEname(String ename) {
        this.mEname = ename;
    }

    public void initLoadLocalData(OnDataReturnListener<HomeListBean.HomeListDataBean> listener) {
        Observable.create(new ObservableOnSubscribe<HomeListBean.HomeListDataBean>() {
            @Override
            public void subscribe(ObservableEmitter<HomeListBean.HomeListDataBean> emitter) throws Exception {
                HomeListBean.HomeListDataBean bean = CacheHelper.getHomeListDataBean(getCacheKey());
                if(bean == null) {
                    if(listener != null) {
                        listener.onDataReady(null);
                    }
                } else {
                    emitter.onNext(bean);
                }

                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeListBean.HomeListDataBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeListBean.HomeListDataBean bean) {
                        if(listener != null) {
                            listener.onDataReady(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void initLoadNetData(IBaseView view, OnDataReturnListener<HomeListBean.HomeListDataBean> listener) {
        mFn = 1;
        BaseEntityObserver observer = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
            @Override
            public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                saveCacheData(bean);
                if(listener != null) {
                    listener.onDataReady(bean);
                }
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                if(listener != null) {
                    listener.onDataError(msg, e);
                }
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, mFn, 0, mEname);
    }

    public void requestAD(IBaseView view, OnDataReturnListener<HomeListBean.HomeListDataBean> listener) {
        if(NetworkUtils.isConnected()) {
            // 请求广告
            // 赵楠的测试
            BaseEntityObserver AdObserver = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
                @Override
                public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                    if(listener != null) {
                        listener.onDataReady(bean);
                    }
                }

                @Override
                public void onRequestError(String msg, Throwable e) {
                    ErrorActionUtils.print(e);
                }
            };
            HttpUtils.getHomeRecommendList(view, AdObserver, mHomeGson, mFn, 40, mEname);
        }
    }

    public void doLoadMoreData(IBaseView view, int offet, OnDataReturnListener<HomeListBean.HomeListDataBean> listener) {
        BaseEntityObserver observer = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
            @Override
            public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                mFn++;
                if(listener != null) {
                    listener.onDataReady(bean);
                }
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                if(listener != null) {
                    listener.onDataError(msg, e);
                }
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, mFn, offet, mEname);
    }

    private void saveCacheData(HomeListBean.HomeListDataBean bean) {
        if(bean == null) return;
        CacheHelper.saveHomeListDataBean(getCacheKey(), bean);
    }

    private String getCacheKey() {
        return "home-"+mEname;
    }

}
