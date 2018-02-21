package com.netease.pineapple.module.view.histroy;

import android.view.View;

import com.netease.pineapple.common.base.BaseListFragment;
import com.netease.pineapple.module.view.histroy.utils.AdapterRegisterUtils;

/**
 * Created by zhaonan on 2018/2/19.
 */

public class ViewHistroyFragment extends BaseListFragment<IViewHistroy.Presenter> implements IViewHistroy.View {

    @Override
    public void setPresenter(IViewHistroy.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new ViewHistroyPresenter(this);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        AdapterRegisterUtils.registerViewHistroyListItem(adapter, loadMoreErrorClickListener);
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onShowLoading();
        presenter.doInitLoadData();
    }
}
