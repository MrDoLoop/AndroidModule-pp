package com.netease.pineapple.common.utils;

import android.support.design.widget.TabLayout;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Created by zhaonan on 2018/2/20.
 */

public class ViewUtils {
    public static View getTabViewAtPos(TabLayout tabLayout, int index){
        View tabView = null;
        TabLayout.Tab tab = tabLayout.getTabAt(index);
        Field view = null;
        try {
            view = TabLayout.Tab.class.getDeclaredField("mView");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        view.setAccessible(true);
        try {
            tabView = (View) view.get(tab);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return tabView;
    }
}
