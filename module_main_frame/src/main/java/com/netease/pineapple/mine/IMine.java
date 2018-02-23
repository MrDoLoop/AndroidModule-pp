package com.netease.pineapple.mine;

import com.netease.pineapple.common.base.IBasePresenter;
import com.netease.pineapple.common.base.IBaseView;


public interface IMine {

    interface View extends IBaseView<Presenter> {
        void showLogedInView();
        void showLogedOutView();
    }

    interface Presenter extends IBasePresenter {

    }
}
