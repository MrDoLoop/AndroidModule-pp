package com.meiji.toutiao.module.news.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.pineapple.common.utils.PPUtils;

import com.netease.pineapple.module.main.frame.R;
import com.meiji.toutiao.bean.news.MultiNewsArticleDataBean;
import com.netease.pineapple.base.BaseActivity;

/**
 * Created by Meiji on 2017/2/28.
 */

public class NewsContentActivity extends BaseActivity {

    private static final String TAG = "NewsContentActivity";
    private static final String IMG = "img";

    public static void launch(MultiNewsArticleDataBean bean) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), NewsContentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static void launch(MultiNewsArticleDataBean bean, String imgUrl) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), NewsContentActivity.class)
                .putExtra(TAG, bean)
                .putExtra(IMG, imgUrl)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        Intent intent = getIntent();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,
                        NewsContentFragment.newInstance(intent.getParcelableExtra(TAG), intent.getStringExtra(IMG)))
                .commit();
    }
}
