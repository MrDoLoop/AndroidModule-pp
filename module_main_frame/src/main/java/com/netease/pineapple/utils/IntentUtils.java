package com.netease.pineapple.utils;

import android.content.Context;
import android.content.Intent;

import com.netease.pineapple.MainActivity;

/**
 * Created by zhaonan on 2018/2/17.
 */

public class IntentUtils {
    public final static void startHomeActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }
}
