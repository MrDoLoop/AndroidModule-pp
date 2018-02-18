package com.meiji.toutiao.module.video.article;

import com.meiji.toutiao.bean.news.MultiNewsArticleDataBean;
import com.netease.pineapple.base.IBaseListView;
import com.netease.pineapple.base.IBasePresenter;

import java.util.List;

/**
 * Created by Meiji on 2017/3/29.
 */

public interface IVideoArticle {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);
    }
}
