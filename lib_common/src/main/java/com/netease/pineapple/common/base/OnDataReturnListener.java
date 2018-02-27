package com.netease.pineapple.common.base;

/**
 * Created by zhaonan on 2018/2/27.
 */

public interface OnDataReturnListener<T> {
    void onDataReady(T data);
    void onDataError(String msg, Throwable e);
}
