package com.netease.pineapple.common.base;


public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowError(String msg);

    void doShowLoadMoreError();

    /**
     * 加载更多
     */
    void doLoadMoreData();

    /**
     * 初始请求数据
     */
    void doInitLoadData();

    /**
     * 加载完毕
     */
    void doShowNoMore();
}
