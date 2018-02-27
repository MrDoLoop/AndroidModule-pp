package com.netease.pineapple.home;

import com.netease.pineapple.common.bean.CategoryBean;
import com.netease.pineapple.common.bean.CategoryListBean;
import com.netease.pineapple.common.cache.CacheHelper;
import com.netease.pineapple.common.http.BaseEntityObserver;
import com.netease.pineapple.common.utils.DataUtils;
import com.netease.pineapple.common.utils.GsonUtils;
import com.netease.pineapple.common.utils.HttpUtils;
import com.netease.pineapple.common.utils.PPUtils;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by zhaonan on 2018/2/19.
 */

public class HomeMainPresenter implements IHomeMain.Presenter {
    private IHomeMain.View view;

    public HomeMainPresenter(IHomeMain.View view) {
        this.view = view;
    }


    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowError(String msg) {

    }

    @Override
    public void doShowLoadMoreError() {

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doInitLoadData() {

        // 直接返回本地缓存的数据
        ArrayList<CategoryBean> list = CacheHelper.getHomeCategoryDataBean();
        if(DataUtils.listNotEmpty(list)) {
            view.onShowCatagoryList(list);
        } else {
            view.onShowCatagoryList(readCategorylListFromAsset());
        }

        //请求网络数据 暂时存储下来 下次在使用
        //view.onShowLoading();
        BaseEntityObserver observer = new BaseEntityObserver<ArrayList<CategoryBean>>() {
            @Override
            public void onRequestSuccess(ArrayList<CategoryBean> list) {

                CacheHelper.saveHomeCategoryDataBean(list);
//                view.onHideLoading();
//                if(DataUtils.listNotEmpty(list)) {
//                    view.onShowCatagoryList(list);
//                } else {
//                    view.onShowCatagoryList(readCategorylListFromAsset());
//                }
            }

            @Override
            public void onRequestError(String msg, Throwable e) {
//                super.onRequestError(msg, e);
//                view.onHideLoading();
//                view.onShowCatagoryList(readCategorylListFromAsset());
            }
        };
        HttpUtils.getHomeCategroyList(view, observer);
    }

    @Override
    public void doShowNoMore() {

    }

    private ArrayList<CategoryBean> readCategorylListFromAsset() {
        try {
            InputStream is = PPUtils.getAppContext().getAssets().open("category.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf8");
            CategoryListBean ret = GsonUtils.parse(text, CategoryListBean.class);
            return ret.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
