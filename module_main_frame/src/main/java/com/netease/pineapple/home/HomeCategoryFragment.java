package com.netease.pineapple.home;

import android.os.Bundle;
import android.view.View;

import com.netease.pineapple.base.BaseListFragment;
import com.netease.pineapple.bean.LoadingBean;
import com.netease.pineapple.utils.DiffCallback;

import java.util.List;

import me.drakeet.multitype.Items;

public class HomeCategoryFragment extends BaseListFragment<IHomeCategory.Presenter> implements IHomeCategory.View {

    private static final String TAG = "HomeCategoryFragment";
    private String mEname;

    public static HomeCategoryFragment newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        HomeCategoryFragment view = new HomeCategoryFragment();
        view.setArguments(bundle);
        return view;
    }

    @Override
    protected void initData() {
        mEname = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void onSetAdapter(final List<?> list) {
        super.onSetAdapter(list);
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        /**
         * https://medium.com/@hanru.yeh/recyclerview-and-appbarlayout-behavior-changed-in-v26-0-x-d9eb4de78fc0
         * support libraries v26 增加了 RV 惯性滑动，当 root layout 使用了 AppBarLayout Behavior 就会自动生效
         * 因此需要手动停止滑动
         */
        recyclerView.stopScroll();
    }

    @Override
    public void setPresenter(IHomeCategory.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new HomeCategoryPresenter(this, mEname);
        }
    }
}
