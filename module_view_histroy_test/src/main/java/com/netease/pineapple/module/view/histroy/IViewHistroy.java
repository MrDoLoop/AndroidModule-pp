package com.netease.pineapple.module.view.histroy;

import com.netease.pineapple.common.base.IBaseListView;
import com.netease.pineapple.common.base.IBasePresenter;
import com.netease.pineapple.common.bean.VideoItemBean;

import java.util.List;

public interface IViewHistroy {

    interface View extends IBaseListView<Presenter> {

    }

    interface Presenter extends IBasePresenter {
        /**
         * 设置适配器
         */
        void doSetAdapter(List<VideoItemBean> list);
    }
}
