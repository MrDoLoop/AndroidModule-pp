package com.meiji.toutiao.module.joke.comment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.netease.pineapple.common.utils.PPUtils;

import com.netease.pineapple.module.main.frame.R;;
import com.meiji.toutiao.bean.joke.JokeContentBean;
import com.netease.pineapple.base.BaseActivity;

/**
 * Created by Meiji on 2017/1/1.
 */

public class JokeCommentActivity extends BaseActivity {

    private static final String TAG = "NewsCommentView";

    public static void launch(JokeContentBean.DataBean.GroupBean bean) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), JokeCommentActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, JokeCommentFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }
}
