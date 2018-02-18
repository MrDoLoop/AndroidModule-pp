package com.netease.pineapple.base;

import java.util.List;


public interface IBaseListView<T> extends IBaseView<T> {

    /**
     * 刷新
     */
    void onRefresh();

    /**
     * 设置 presenter
     */
    void setPresenter(T presenter);

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list, boolean loadMore);

    /**
     * 加载完毕
     */
    void onShowNoMore();

    /**
     * 加载更多错误
     */
    void onShowLoadMoreError();
}
