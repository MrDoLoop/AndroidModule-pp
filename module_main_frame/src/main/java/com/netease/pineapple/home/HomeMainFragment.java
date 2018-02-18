package com.netease.pineapple.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netease.pineapple.bean.CategoryBean;
import com.netease.pineapple.bean.CategoryListBean;
import com.netease.pineapple.common.utils.DataUtils;
import com.netease.pineapple.common.utils.GsonUtil;
import com.netease.pineapple.module.main.frame.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HomeMainFragment extends Fragment {

    public static final String TAG = "HomeMainFragment";
    private static HomeMainFragment instance = null;
    private ViewPager mViewPager;
    private HomeCategoryPagerAdapter mAdapter;
    //private List<Fragment> mFragmentList;
    private List<String> mCTitleList;
    private List<String> mETitleList;
    //private Map<String, Fragment> map = new HashMap<>();

    public static HomeMainFragment getInstance() {
        if (instance == null) {
            instance = new HomeMainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.module_main_frame_home_main_fragment, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mViewPager = view.findViewById(R.id.view_pager);
        TabLayout tab_layout = view.findViewById(R.id.tab_layout_catagory);
        tab_layout.setupWithViewPager(mViewPager);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        ImageView search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getActivity(), NewsChannelActivity.class));
            }
        });
    }

    private void initData() {
        initTabs();
        mAdapter = new HomeCategoryPagerAdapter(getChildFragmentManager(), mCTitleList, mETitleList);
        mViewPager.setAdapter(mAdapter);
    }


    private CategoryListBean readCategorylListFromAsset() {
        try {
            InputStream is = getActivity().getAssets().open("category.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String text = new String(buffer, "utf8");
            CategoryListBean ret = GsonUtil.parse(text, CategoryListBean.class);
            return ret;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void initTabs() {
        CategoryListBean categoryListBean = readCategorylListFromAsset();
        mCTitleList = new ArrayList<>();
        mETitleList = new ArrayList<>();
        if(categoryListBean != null && DataUtils.listNotEmpty(categoryListBean.getData())) {
            for (CategoryBean bean : categoryListBean.getData()) {
                mCTitleList.add(bean.cname);
                mETitleList.add(bean.ename);
            }
        }
    }
}
