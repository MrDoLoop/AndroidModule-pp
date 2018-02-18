package com.netease.pineapple.home;

import com.google.gson.Gson;
import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.bean.HomeListResDeserializer;
import com.netease.pineapple.bean.ListMultiTypeBean;
import com.netease.pineapple.bean.VideoItemBean;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.common.utils.DataUtils;
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
    //最终用来显示的总list
    private List<ListMultiTypeBean> mShowList = new ArrayList<>();
    //广告列表
    private List<HomeListBean.HomeListDataListItemBean> mAdList = new ArrayList<>();
    // 数据列表
    private List<HomeListBean.HomeListDataListItemBean> mDataItemList = new ArrayList<>();

    private String mEname;
    private Gson mHomeGson;
    int fn = 1;

    @Override
    public boolean hasAD() {
        if("Video_Recom".equals(mEname)) {
            return true;
        }
        return false;
    }

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
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, fn, 0, mEname);

        if(hasAD()) {
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
                    buildList();
                }

                @Override
                public void onRequestError(String msg, Throwable e) {
                    ErrorActionUtils.print(e);
                }
            };
            HttpUtils.getHomeRecommendList(view, AdObserver, mHomeGson, fn, 0, mEname);
        }
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
                doShowNetError();
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getHomeRecommendList(view, observer, mHomeGson, fn, mDataItemList.size(), mEname);
    }

    @Override
    public void doSetAdapter(List<HomeListBean.HomeListDataListItemBean> list) {
        fn++;
        mDataItemList.clear();
        mShowList.clear();
        mDataItemList.addAll(list);
        buildList();
        view.onSetAdapter(mShowList);
        view.onHideLoading();
    }

    @Override
    public void doAppendMoreData(List<HomeListBean.HomeListDataListItemBean> list) {
        fn++;
        mDataItemList.addAll(list);
        buildList();
        view.onSetAdapter(mShowList);
        view.onHideLoading();
    }

    private synchronized void buildList() {
        // 1. 添加数据
        if(DataUtils.listNotEmpty(mDataItemList)) {
            for(HomeListBean.HomeListDataListItemBean bean : mDataItemList) {
                ListMultiTypeBean multiTypeBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM, bean);
                mShowList.add(multiTypeBean);
            }
            // 2. 添加广告
            for(HomeListBean.HomeListDataListItemBean bean : mAdList) {
                ListMultiTypeBean multiTypeBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM, bean);
                mShowList.add(multiTypeBean);
            }
        }
    }


    @Override
    public void doRefresh() {
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
