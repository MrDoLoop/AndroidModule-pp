package com.netease.pineapple.module.video.detail;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.netease.pineapple.common.contants.RouteConstant;


/**
 * Created by zhaonan on 2018/2/13.
 */
@Route(path = RouteConstant.VIDEO_DETAIL_MODULE)
public class VideoDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_video_detail_activity_video_detail_layout);
    }
}
