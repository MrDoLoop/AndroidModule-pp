package com.netease.pineapple;

import android.support.annotation.Keep;

import com.meiji.toutiao.api.INewsApi;
import com.netease.pineapple.common.base.IApplicationDelegate;
import com.netease.pineapple.common.http.RetrofitFactory;

// 防止打包的时候被资源优化掉
@Keep
public class ApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        RetrofitFactory.init(INewsApi.HOST);
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
