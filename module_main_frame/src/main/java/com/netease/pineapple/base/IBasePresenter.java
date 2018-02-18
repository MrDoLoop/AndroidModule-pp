package com.netease.pineapple.base;


public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowError();

    void doShowLoadMoreError();

    /**
     * 加载更多
     */
    void doLoadMoreData();

    /**
     * 请求数据
     */
    void doLoadData();

    /**
     * 加载完毕
     */
    void doShowNoMore();
}
