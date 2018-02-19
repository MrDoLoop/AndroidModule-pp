package com.netease.pineapple;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.netease.pineapple.common.base.BaseActivity;
import com.netease.pineapple.common.utils.ToastUtils;
import com.netease.pineapple.helper.BottomNavigationViewHelper;
import com.netease.pineapple.home.HomeMainFragment;
import com.netease.pineapple.module.main.frame.R;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final String CUR_FRAGMENT = "CUR_FRAGMENT";
    private static final int FRAGMENT_MAIN = 0;
    private static final int FRAGMENT_PHOTO = 1;
    private static final int FRAGMENT_VIDEO = 2;
    private HomeMainFragment mHomeMainFragment;
//    private PhotoTabLayout photoTabLayout;
//    private VideoTabLayout videoTabLayout;
    private BottomNavigationView mBottomNavView;
    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main_frame_activity_main);
        initView();

        if (savedInstanceState != null) {
            mHomeMainFragment = (HomeMainFragment) getSupportFragmentManager().findFragmentByTag(HomeMainFragment.class.getName());
//            photoTabLayout = (PhotoTabLayout) getSupportFragmentManager().findFragmentByTag(PhotoTabLayout.class.getName());
//            videoTabLayout = (VideoTabLayout) getSupportFragmentManager().findFragmentByTag(VideoTabLayout.class.getName());
            // 恢复 recreate 前的位置
            showFragment(savedInstanceState.getInt(CUR_FRAGMENT));
            mBottomNavView.setSelectedItemId(savedInstanceState.getInt(CUR_FRAGMENT));
        } else {
            showFragment(FRAGMENT_MAIN);
        }
        setStatusBarColor(Color.WHITE);
        setStatusBarLightMode();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // recreate 时记录当前位置 (在 Manifest 已禁止 Activity 旋转,所以旋转屏幕并不会执行以下代码)
        //super.onSaveInstanceState(outState); // 有可能崩溃
        outState.putInt(CUR_FRAGMENT, mBottomNavView.getSelectedItemId());
    }

    private void initView() {
        mBottomNavView = findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(mBottomNavView);

        mBottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.action_news) {
                    showFragment(FRAGMENT_MAIN);
                }
                else if(item.getItemId() == R.id.action_photo) {
                    showFragment(FRAGMENT_PHOTO);
                }
                else if(item.getItemId() == R.id.action_video) {
                    showFragment(FRAGMENT_VIDEO);
                }
                return true;
            }
        });
    }

    private void showFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        switch (index) {
            case FRAGMENT_MAIN:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (mHomeMainFragment == null) {
                    mHomeMainFragment = HomeMainFragment.getInstance();
                    ft.add(R.id.container, mHomeMainFragment, HomeMainFragment.class.getName());
                } else {
                    ft.show(mHomeMainFragment);
                }
                break;

//            case FRAGMENT_PHOTO:
//                if (photoTabLayout == null) {
//                    photoTabLayout = PhotoTabLayout.getInstance();
//                    ft.add(R.id.container, photoTabLayout, PhotoTabLayout.class.getName());
//                } else {
//                    ft.show(photoTabLayout);
//                }
//                break;
//
//            case FRAGMENT_VIDEO:
//                if (videoTabLayout == null) {
//                    videoTabLayout = VideoTabLayout.getInstance();
//                    ft.add(R.id.container, videoTabLayout, VideoTabLayout.class.getName());
//                } else {
//                    ft.show(videoTabLayout);
//                }
//                break;
        }

        ft.commit();
    }

    private void hideFragment(FragmentTransaction ft) {
        // 如果不为空，就先隐藏起来
        if (mHomeMainFragment != null) {
            ft.hide(mHomeMainFragment);
        }
//        if (photoTabLayout != null) {
//            ft.hide(photoTabLayout);
//        }
//        if (videoTabLayout != null) {
//            ft.hide(videoTabLayout);
//        }
    }

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - mExitTime) < 2000) {
            super.onBackPressed();
        } else {
            ToastUtils.showShortToast(R.string.double_click_exit);
            mExitTime = currentTime;
        }
    }
}
