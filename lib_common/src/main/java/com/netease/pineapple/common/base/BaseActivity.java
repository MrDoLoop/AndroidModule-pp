package com.netease.pineapple.common.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.netease.pineapple.common.utils.StatusBarUtils;
import com.netease.pineapple.module.common.R;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseActivity extends RxAppCompatActivity {

    private static final String TAG = "BaseActivity";

    public static final String FRAGMENT_TAG = "fragment_tag";

    /**
     * 初始化 Toolbar
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        ((TextView) toolbar.findViewById(R.id.toolbar_title)).setText(title);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void setStatusBarColor(int color) {
        StatusBarUtils.setColor(this, color);
    }
    //https://www.jianshu.com/p/7f5a9969be53

    /**
     * 设置状态栏为文字的颜色为黑色 6.0以后才有效
     * 主要是为了适应白色状态栏栏的时候
     */
    protected void setStatusBarLightMode(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // Fragment 逐个出栈
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * 线程调度
     */
    public <T> ObservableTransformer<T, T> compose(final LifecycleTransformer<T> lifecycle) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                // 可添加网络连接判断等
//                                if (!Utils.isNetworkAvailable(BaseActivity.this)) {
//                                    Toast.makeText(BaseActivity.this, R.string.toast_network_error, Toast.LENGTH_SHORT).show();
//                                }
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(lifecycle);
            }
        };
    }
}
