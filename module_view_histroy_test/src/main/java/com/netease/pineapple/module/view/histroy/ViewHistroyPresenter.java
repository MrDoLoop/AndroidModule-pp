package com.netease.pineapple.module.view.histroy;

import com.netease.pineapple.common.base.api.NetApiParamsBuilder;
import com.netease.pineapple.common.bean.NextpageParamBean;
import com.netease.pineapple.common.bean.VideoItemBean;
import com.netease.pineapple.common.bean.WatchHistoryResultBean;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.utils.ErrorActionUtils;
import com.netease.pineapple.common.utils.HttpUtils;

import java.util.List;

/**
 * Created by zhaonan on 2018/2/19.
 */

public class ViewHistroyPresenter implements IViewHistroy.Presenter  {

    private IViewHistroy.View view;

    private NextpageParamBean mNextpageParamBean;

    public ViewHistroyPresenter(IViewHistroy.View view) {
        this.view = view;
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowError() {

    }

    @Override
    public void doShowLoadMoreError() {

    }

    @Override
    public void doLoadMoreData() {
        if(mNextpageParamBean == null) return;

        BaseEntityObserver observer = new BaseEntityObserver<WatchHistoryResultBean.WatchHistoryResultBeanData>() {
            @Override
            public void onRequestSuccess(WatchHistoryResultBean.WatchHistoryResultBeanData bean) {
                mNextpageParamBean = bean.nextPageParam;
                doSetAdapter(bean.videoList);
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                doShowError();
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getViewHistroyList(view, observer, mNextpageParamBean.getStart(), mNextpageParamBean.getSize(),mNextpageParamBean.getLastId());
    }

    @Override
    public void doLoadData() {
        BaseEntityObserver observer = new BaseEntityObserver<WatchHistoryResultBean.WatchHistoryResultBeanData>() {
            @Override
            public void onRequestSuccess(WatchHistoryResultBean.WatchHistoryResultBeanData bean) {
                mNextpageParamBean = bean.nextPageParam;
                doSetAdapter(bean.videoList);
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
                doShowError();
                ErrorActionUtils.print(e);
            }
        };
        HttpUtils.getViewHistroyList(view, observer, 0, NetApiParamsBuilder.DEFAULT_REQ_DATA_SIZE,"");
    }

    @Override
    public void doShowNoMore() {

    }

    @Override
    public void doSetAdapter(List<VideoItemBean> list) {

    }

//    @Override
//    public void doGetCatagoryList() {
//        //请求网络数据
//        BaseEntityObserver observer = new BaseEntityObserver<ArrayList<CategoryBean>>() {
//            @Override
//            public void onRequestSuccess(ArrayList<CategoryBean> s) {
//                view.onShowCatagoryList(s);
//            }
//
//            @Override
//            public void onRequestError(String msg, Throwable e) {
//                super.onRequestError(msg, e);
//                view.onShowCatagoryList(readCategorylListFromAsset());
//            }
//        };
//        HttpUtils.getHomeCategroyList(view, observer);
//    }
//
//
//    private ArrayList<CategoryBean> readCategorylListFromAsset() {
//        try {
//            InputStream is = PPUtils.getAppContext().getAssets().open("category.txt");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            String text = new String(buffer, "utf8");
//            CategoryListBean ret = GsonUtil.parse(text, CategoryListBean.class);
//            return ret.getData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}
