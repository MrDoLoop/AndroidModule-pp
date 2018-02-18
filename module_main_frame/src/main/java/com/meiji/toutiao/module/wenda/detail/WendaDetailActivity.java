package com.meiji.toutiao.module.wenda.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.netease.pineapple.common.utils.PPUtils;

import com.netease.pineapple.module.main.frame.R;
import com.meiji.toutiao.bean.wenda.WendaContentBean;
import com.netease.pineapple.base.BaseActivity;

/**
 * Created by Meiji on 2017/5/23.
 */

public class WendaDetailActivity extends BaseActivity {

    private static final String TAG = "WendaDetailActivity";

    public static void launch(WendaContentBean.AnsListBean bean) {
        PPUtils.getAppContext().startActivity(new Intent(PPUtils.getAppContext(), WendaDetailActivity.class)
                .putExtra(TAG, bean)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.container);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, WendaDetailFragment.newInstance(getIntent().getParcelableExtra(TAG)))
                .commit();
    }
}
