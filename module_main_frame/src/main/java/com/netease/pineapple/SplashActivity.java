package com.netease.pineapple;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.netease.pineapple.utils.IntentUtils;


@Route(path = "/main_frame/splash")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.startHomeActivity(SplashActivity.this);
                finish();
            }
        },500);
    }

    @Override
    public void onBackPressed() {
        // 防止退出
    }
}
