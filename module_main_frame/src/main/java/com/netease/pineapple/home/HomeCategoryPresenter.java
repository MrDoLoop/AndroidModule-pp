package com.netease.pineapple.home;

import com.google.gson.Gson;
import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.bean.HomeListResDeserializer;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.common.utils.GsonUtil;
import com.netease.pineapple.net.api.INetApis;
import com.netease.pineapple.net.api.NetApiParamsBuilder;
import com.netease.pineapple.utils.ErrorActionUtils;
import com.netease.pineapple.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public class HomeCategoryPresenter implements IHomeCategory.Presenter {

    private static final String TAG = "HomeCategoryPresenter";
    private IHomeCategory.View view;
    private List<HomeListBean.HomeListDataListItemBean> mDataList = new ArrayList<>();
    private String mEname;
    private Gson mHomeGson;
    int fn = 1;
    int offset = 0;

    public HomeCategoryPresenter(IHomeCategory.View view, String ename) {
        this.view = view;
        this.mEname = ename;
        this.mHomeGson = GsonUtil.getGsonWithDeserializer(HomeListBean.class, new HomeListResDeserializer());
    }

    @Override
    public void doLoadData() {
        BaseEntityObserver observer = new BaseEntityObserver<HomeListBean.HomeListDataBean>() {
            @Override
            public void onRequestSuccess(HomeListBean.HomeListDataBean bean) {
                doSetAdapter(bean.getDatalist());
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                doShowNetError();
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, fn, offset, mEname);
    }

    @Override
    public void doLoadMoreData() {
        doLoadData();
    }

    @Override
    public void doSetAdapter(List<HomeListBean.HomeListDataListItemBean> list) {
        fn++;
        mDataList.addAll(list);
        view.onSetAdapter(mDataList);
        offset += list.size();
        view.onHideLoading();
    }

    @Override
    public void doRefresh() {
        offset = 0;
        mDataList.clear();
        view.onShowLoading();
        doLoadData();
    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doShowNoMore() {
        view.onHideLoading();
        view.onShowNoMore();
    }
}
