package com.netease.pineapple.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.netease.pineapple.common.base.BaseFragment;
import com.netease.pineapple.common.bean.CategoryBean;
import com.netease.pineapple.common.utils.DataUtils;
import com.netease.pineapple.common.widget.NetworkStateView;
import com.netease.pineapple.module.main.frame.R;

import java.util.ArrayList;
import java.util.List;

public class HomeMainFragment extends BaseFragment<IHomeMain.Presenter> implements IHomeMain.View {

    public static final String TAG = "HomeMainFragment";

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private NetworkStateView mNetworkStateView;
    private HomeCategoryPagerAdapter mAdapter;
    private List<String> mCTitleList;
    private List<String> mETitleList;

    @Override
    protected int attachLayoutId() {
        return R.layout.module_main_frame_home_main_fragment;
    }

    @Override
    protected void initView(View view) {
        mNetworkStateView = view.findViewById(R.id.state_view);
        mViewPager = view.findViewById(R.id.view_pager);
        mTabLayout = view.findViewById(R.id.tab_layout_catagory);
        mTabLayout.setupWithViewPager(mViewPager);

        ImageView search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), NewsChannelActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        presenter.doInitLoadData();
    }

    private void initTabs(ArrayList<CategoryBean> list) {
        mCTitleList = new ArrayList<>();
        mETitleList = new ArrayList<>();
        for (CategoryBean bean : list) {
            mCTitleList.add(bean.cname);
            mETitleList.add(bean.ename);
        }
    }

    @Override
    public void onShowCatagoryList(ArrayList<CategoryBean> list) {
        if(list != null && DataUtils.listNotEmpty(list)) {
            initTabs(list);
            mAdapter = new HomeCategoryPagerAdapter(getChildFragmentManager(), mCTitleList, mETitleList);
            mViewPager.setAdapter(mAdapter);
        } else {
            onShowError("列表为null");
        }
    }

    @Override
    public void onShowLoading() {
        mNetworkStateView.showLoading();
    }

    @Override
    public void onHideLoading() {
        mNetworkStateView.showSuccess();
    }

    @Override
    public void onShowError(String msg) {

    }

    @Override
    public void setPresenter(IHomeMain.Presenter preter) {
        if (null == preter) {
            this.presenter = new HomeMainPresenter(this);
        }
    }
}
