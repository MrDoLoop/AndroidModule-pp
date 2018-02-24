package com.netease.pineapple.home;

import com.google.gson.Gson;
import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.bean.HomeListResDeserializer;
import com.netease.pineapple.common.bean.ListMultiTypeBean;
import com.netease.pineapple.common.bean.VideoItemBean;
import com.netease.pineapple.common.cache.CacheHelper;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.utils.DataUtils;
import com.netease.pineapple.common.utils.ErrorActionUtils;
import com.netease.pineapple.common.utils.GsonUtils;
import com.netease.pineapple.common.utils.HttpUtils;
import com.netease.pineapple.common.utils.NetworkUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeCategoryPresenter implements IHomeCategory.Presenter {

    private static final String TAG = "HomeCategoryPresenter";
    private IHomeCategory.View view;
    //最终用来显示的总list
    private List<ListMultiTypeBean> mShowList = new ArrayList<>();
    //广告列表
    private List<HomeListBean.HomeListDataListItemBean> mAdList = new ArrayList<>();
    // 数据列表
    private List<HomeListBean.HomeListDataListItemBean> mDataItemList = new ArrayList<>();

    private String mEname;
    private Gson mHomeGson;
    private int mFn = 1;

    @Override
    public boolean hasAD() {
        if("Video_Recom".equals(mEname)) {
            return true;
        }
        return false;
    }

    public HomeCategoryPresenter(IHomeCategory.View view) {
        this.view = view;
        this.mHomeGson = GsonUtils.getGsonWithDeserializer(HomeListBean.class, new HomeListResDeserializer());
    }

    @Override
    public void doInitLoadData() {
        initLoadLocalData();
        requestAD();
    }

    private void initLoadLocalData() {
        Observable.create(new ObservableOnSubscribe<HomeListBean.HomeListDataBean>() {
            @Override
            public void subscribe(ObservableEmitter<HomeListBean.HomeListDataBean> emitter) throws Exception {
                HomeListBean.HomeListDataBean bean = CacheHelper.getHomeListDataBean(getCacheKey());
                if(bean == null) {
                    initLoadNetData();
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
                if(bean == null) {
                    initLoadNetData();
                } else {
                    doSetAdapter(bean.getDatalist());
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

    private void initLoadNetData() {
        BaseEntityObserver observer = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
            @Override
            public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                saveCacheData(bean);
                doSetAdapter(bean.getDatalist());
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                doShowError(msg);
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, mFn, 0, mEname);

    }


    private void requestAD() {
        if(hasAD() && NetworkUtils.isConnected()) {
            // 请求广告
            // 赵楠的测试
            BaseEntityObserver AdObserver = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
                @Override
                public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                    for(int i = 0; i<bean.getDataList().size() ;i++) {
                        String title = ((VideoItemBean)bean.getDataList().get(i).getContent()).getTitle();
                        ((VideoItemBean)bean.getDataList().get(i).getContent()).setTitle("我是广告---" + title);
                    }
                    mAdList.clear();
                    mAdList.addAll(bean.getDatalist());
                    insertAd();
                    updateList();
                }

                @Override
                public void onRequestError(String msg, Throwable e) {
                    ErrorActionUtils.print(e);
                }
            };
            HttpUtils.getHomeRecommendList(view, AdObserver, mHomeGson, mFn, 40, mEname);
        }
    }

    private void saveCacheData(HomeListBean.HomeListDataBean bean) {
        if(bean == null) return;
        CacheHelper.saveHomeListDataBean(getCacheKey(), bean);
    }

    private String getCacheKey() {
        return "home-"+mEname;
    }


    @Override
    public void doLoadMoreData() {
        BaseEntityObserver observer = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
            @Override
            public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                doAppendMoreData(bean.getDatalist());
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                doShowLoadMoreError();
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, mFn, mDataItemList.size(), mEname);
    }

    @Override
    public void doSetAdapter(List<HomeListBean.HomeListDataListItemBean> list) {
        mDataItemList.clear();
        mShowList.clear();
        doAppendMoreData(list);
    }

    @Override
    public void doAppendMoreData(List<HomeListBean.HomeListDataListItemBean> list) {
        mFn++;
        mDataItemList.addAll(list);
        appendDataToShowList(list);
        updateList();
    }

    private void updateList() {
        view.onSetAdapter(mShowList, true);
        view.onHideLoading();
    }


    private synchronized void appendDataToShowList(List<HomeListBean.HomeListDataListItemBean> list) {
        // 1. 添加数据
        if(DataUtils.listNotEmpty(list)) {
            for(HomeListBean.HomeListDataListItemBean bean : list) {
                ListMultiTypeBean multiTypeBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM, bean);
                mShowList.add(multiTypeBean);
            }
            insertAd();
        }
    }

    private synchronized void insertAd() {
        // 赵楠的测试 4， 11， 16 插入ad
        if(mAdList.size() == 0 || mShowList.size() == 0) return;
        HomeListBean.HomeListDataListItemBean bean = mAdList.get(0);

        ListMultiTypeBean adBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM, bean);

        if(mShowList.size() >= 3) {
            if(isAdAtPos(3)) {
                mShowList.set(3, adBean);
            } else {
                mShowList.add(3, adBean);
            }
        }

        if(mShowList.size() >= 10) {
            if(isAdAtPos(10)) {
                mShowList.set(10, adBean);
            } else {
                mShowList.add(10, adBean);
            }
        }

        if(mShowList.size() >= 16) {
            if(isAdAtPos(15)) {
                mShowList.set(15, adBean);
            } else {
                mShowList.add(15, adBean);
            }
        }
    }


    private boolean isAdAtPos(int pos) {
        if(pos <= mShowList.size()) {
            if(mShowList.get(pos).getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM ||
                    mShowList.get(pos).getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_VIDEO_ITEM) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEname(String ename) {
        this.mEname = ename;
    }

    @Override
    public void doRefresh() {
        initLoadNetData();
        requestAD();
    }

    @Override
    public void doShowError(String msg) {
        view.onHideLoading();
        view.onShowError(msg);
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }

    @Override
    public void doShowLoadMoreError() {
        view.onHideLoading();
        view.onShowLoadMoreError();
    }
}
