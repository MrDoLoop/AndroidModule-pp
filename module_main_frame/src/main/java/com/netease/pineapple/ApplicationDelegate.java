package com.netease.pineapple;

import android.support.annotation.Keep;

import com.netease.pineapple.common.base.IApplicationDelegate;

// 防止打包的时候被资源优化掉
@Keep
public class ApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {

    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }

}
