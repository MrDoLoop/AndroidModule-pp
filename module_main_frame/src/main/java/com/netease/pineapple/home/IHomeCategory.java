package com.netease.pineapple.home;

import com.netease.pineapple.base.IBaseListView;
import com.netease.pineapple.base.IBasePresenter;
import com.netease.pineapple.bean.HomeListBean;

import java.util.List;


public interface IHomeCategory {

    interface View extends IBaseListView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        /**
         * 设置适配器
         */
        void doSetAdapter(List<HomeListBean.HomeListDataListItemBean> dataBeen);
    }
}
