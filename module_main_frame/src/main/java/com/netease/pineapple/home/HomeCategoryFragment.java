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
    }

    @Override
    public void setPresenter(IHomeCategory.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new HomeCategoryPresenter(this, mEname);
        }
    }
}
