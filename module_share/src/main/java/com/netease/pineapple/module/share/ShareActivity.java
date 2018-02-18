package com.netease.pineapple.module.share;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.netease.pineapple.common.contants.RouteConstant;

/**
 * Created by zhaonan on 2018/2/13.
 */
@Route(path = RouteConstant.SHARE_MODULE)
public class ShareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_share_activity_share_layout);
    }
}
