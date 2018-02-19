package com.netease.pineapple.module.view.histroy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.netease.pineapple.common.base.BaseActivity;

/**
 * Created by zhaonan on 2018/2/13.
 */
public class ViewHistroyActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_view_histroy_list_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        initToolBar(toolbar, true, getString(R.string.view_histroy));

//        Fragment fragment = Fragment.instantiate(this, fragmentName, args);
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
//        transaction.replace(R.id.container, fragment);
//        transaction.commitAllowingStateLoss();

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        ViewHistroyFragment fragment = (ViewHistroyFragment) fragmentManager.findFragmentByTag(FRAGMENT_TAG);
//        if (fragment == null) {
//            fragment = new ViewHistroyFragment();
//            fragmentManager.beginTransaction().add(R.id.fragment_container, fragment, FRAGMENT_TAG).commit();
//        }


    }
}
