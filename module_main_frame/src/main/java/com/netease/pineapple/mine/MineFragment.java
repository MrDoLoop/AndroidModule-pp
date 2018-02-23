package com.netease.pineapple.mine;

import android.view.View;

import com.netease.pineapple.common.base.BaseFragment;
import com.netease.pineapple.module.main.frame.R;

public class MineFragment extends BaseFragment<IMine.Presenter> implements IMine.View {

    public static final String TAG = "MineFragment";

    private View mLogInLayout;
    private View mLogOutLayout;

    @Override
    protected int attachLayoutId() {
        return R.layout.activity_user_center;
    }

    @Override
    protected void initView(View view) {
        mLogInLayout = view.findViewById(R.id.loged_in_layout);
        mLogOutLayout = view.findViewById(R.id.loged_out_layout);
        // 赵楠的测试
        showLogedOutView();
    }

    @Override
    protected void initData() {
        presenter.doInitLoadData();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowError(String msg) {

    }

    @Override
    public void setPresenter(IMine.Presenter preter) {
        if (null == preter) {
            this.presenter = new MinePresenter(this);
        }
    }

    @Override
    public void showLogedInView() {
        mLogInLayout.setVisibility(View.VISIBLE);
        mLogOutLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLogedOutView() {
        mLogInLayout.setVisibility(View.INVISIBLE);
        mLogOutLayout.setVisibility(View.VISIBLE);
    }
}
