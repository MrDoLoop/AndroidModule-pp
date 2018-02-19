package com.netease.pineapple.home;

import com.netease.pineapple.base.IBasePresenter;
import com.netease.pineapple.base.IBaseView;
import com.netease.pineapple.bean.CategoryBean;

import java.util.ArrayList;


public interface IHomeMain {

    interface View extends IBaseView<Presenter> {
        void onShowCatagoryList(ArrayList<CategoryBean> list);
    }

    interface Presenter extends IBasePresenter {
        /**
         * 获取分类列表
         */
        void doGetCatagoryList();
    }
}
