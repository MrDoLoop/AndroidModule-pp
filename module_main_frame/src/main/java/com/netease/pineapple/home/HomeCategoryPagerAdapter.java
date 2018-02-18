package com.netease.pineapple.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import java.util.List;


public class HomeCategoryPagerAdapter extends FragmentStatePagerAdapter {

    private List<String> mTitleEnameList;
    private List<String> mTitleCnameList;

    public HomeCategoryPagerAdapter(FragmentManager fm, List<String> titleCnameList, List<String> titleEnameList) {
        super(fm);
        this.mTitleCnameList = titleCnameList;
        this.mTitleEnameList = titleEnameList;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeCategoryFragment.newInstance(mTitleEnameList.get(position));
    }

    @Override
    public int getCount() {
        return mTitleEnameList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleCnameList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}
