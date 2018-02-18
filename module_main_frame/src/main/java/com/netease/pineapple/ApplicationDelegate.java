package com.netease.pineapple;

import android.support.annotation.Keep;

import com.netease.pineapple.common.base.IApplicationDelegate;
import com.netease.pineapple.common.http.RetrofitFactory;
import com.netease.pineapple.net.api.INetApis;

// 防止打包的时候被资源优化掉
@Keep
public class ApplicationDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        RetrofitFactory.init(INetApis.HOST);
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
