package com.netease.pineapple.home;

import android.os.Bundle;
import android.view.View;

import com.netease.pineapple.common.base.BaseListFragment;
import com.netease.pineapple.utils.AdapterRegisterUtils;

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
    protected boolean enableLazyLoad() {
        return true;
    }

    @Override
    protected void initData() {
        mEname = getArguments().getString(TAG);
        presenter.setEname(mEname);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        AdapterRegisterUtils.registerHomeListItem(adapter, loadMoreErrorClickListener);
    }

    @Override
    public void fetchData() {
        super.fetchData();
//        if(NetworkUtils.isConnected()) {
//            onShowLoading();
//            presenter.doInitLoadData();
//        } else {
//            onHideLoading();
//            presenter.doShowError(getString(R.string.network_error));
//        }
        onShowLoading();
        presenter.doInitLoadData();
    }

    @Override
    public void setPresenter(IHomeCategory.Presenter preter) {
        if (null == preter) {
            this.presenter = new HomeCategoryPresenter(this);
        }
    }
}
