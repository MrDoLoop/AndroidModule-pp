package com.netease.pineapple.utils;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.launcher.ARouter;
import com.netease.pineapple.MainActivity;
import com.netease.pineapple.common.contants.RouteConstant;

/**
 * Created by zhaonan on 2018/2/17.
 */

public class IntentUtils {
    public final static void startHomeActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }

    public final static void startVideoDetailActivity() {
        ARouter.getInstance().build(RouteConstant.VIDEO_DETAIL_MODULE)
//                .withLong("key1", 666L)
//                .withString("key3", "888")
                .navigation();
    }

    public final static void startShareActivity() {
        ARouter.getInstance().build(RouteConstant.SHARE_MODULE)
                .withLong("key1", 666L)
                .withString("key3", "888")
                .navigation();
    }
}
