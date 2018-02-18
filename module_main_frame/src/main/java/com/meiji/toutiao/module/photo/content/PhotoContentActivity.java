package com.meiji.toutiao.module.photo.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.pineapple.common.utils.PPUtils;

import com.netease.pineapple.module.main.frame.R;
import com.meiji.toutiao.bean.photo.PhotoArticleBean;
import com.netease.pineapple.base.BaseActivity;

/**
 * Created by Meiji on 2017/3/1.
 */

public class PhotoContentActivity extends BaseActivity {

    private static final String TAG = "PhotoContentActivity";

    public static void launch(PhotoArticleBean.DataBean bean) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), PhotoContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, PhotoContentFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }

//    public SlidrInterface getSlidrInterface() {
//        return slidrInterface;
//    }
}
