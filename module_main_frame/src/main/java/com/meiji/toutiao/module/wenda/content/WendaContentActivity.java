package com.meiji.toutiao.module.wenda.content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.pineapple.common.utils.PPUtils;

import com.netease.pineapple.module.main.frame.R;
import com.netease.pineapple.base.BaseActivity;

/**
 * Created by Meiji on 2017/5/22.
 */

public class WendaContentActivity extends BaseActivity {

    private static final String TAG = "WendaContentActivity";

    public static void launch(String qid) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), WendaContentActivity.class)
                .putExtra(TAG, qid)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, WendaContentFragment.newInstance(getIntent().getStringExtra(TAG)))
                .commit();
    }
}
