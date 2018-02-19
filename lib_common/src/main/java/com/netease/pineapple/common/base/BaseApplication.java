package com.netease.pineapple.common.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.netease.pineapple.common.utils.HttpUtils;
import com.netease.pineapple.common.utils.PPUtils;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.List;

/**
 * 要想使用BaseApplication，必须在组件中实现自己的Application，并且继承BaseApplication；
 * 组件中实现的Application必须在debug包中的AndroidManifest.xml中注册，否则无法使用；
 * 组件的Application需置于java/debug文件夹中，不得放于主代码；
 * 组件中获取Context的方法必须为:PPUtils.getAppContext()，不允许其他写法；
 *
 */
public class BaseApplication extends Application {

    public static final String ROOT_PACKAGE = "com.netease.pineapple";

    private static BaseApplication sInstance;

    private static boolean sInitDone = false;

    private List<IApplicationDelegate> mAppDelegateList = new ArrayList<>();

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        //mAppDelegateList = ClassUtils.getObjectsWithInterface(this, IApplicationDelegate.class, ROOT_PACKAGE);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onCreate();
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    private void init() {
        if(sInitDone) return;
        sInitDone = true;

        // 初始化内存检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        // 实例初始化
        sInstance = this;

        // 工具初始化
        PPUtils.init(this);

        //初始化路由系统
        if (PPUtils.isAppDebug()) {
            //开启InstantRun之后，一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);

        HttpUtils.init();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTerminate();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onLowMemory();
        }
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        for (IApplicationDelegate delegate : mAppDelegateList) {
            delegate.onTrimMemory(level);
        }
    }
}
