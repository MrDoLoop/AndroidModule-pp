package com.netease.pineapple.common.cache;

import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.utils.CacheUtils;
import com.netease.pineapple.common.utils.PPUtils;

/**
 * Created by zhaonan on 2018/2/24.
 */

public class CacheHelper {
    public static void saveHomeListDataBean(String key, HomeListBean.HomeListDataBean bean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                CacheUtils.get(PPUtils.getAppContext()).put(key, bean, CacheUtils.TIME_HOUR);
            }
        }).start();
    }

    public static HomeListBean.HomeListDataBean getHomeListDataBean(String key) {
        return (HomeListBean.HomeListDataBean) CacheUtils.get(PPUtils.getAppContext()).getAsObject(key);
    }
}
