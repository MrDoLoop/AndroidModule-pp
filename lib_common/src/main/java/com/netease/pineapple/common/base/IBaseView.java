package com.netease.pineapple.common.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import io.reactivex.ObservableTransformer;

public interface IBaseView<T> {

    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 全局错误
     */
    void onShowError(String s);

    /**
     * 设置 presenter
     */
    void setPresenter(T preter);

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 线程转换 绑定生命周期
     */
    <T> ObservableTransformer<T, T> compose(LifecycleTransformer<T> lifecycle);

}
