package com.netease.pineapple.utils;

import android.support.annotation.NonNull;

import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.bean.ListMultiTypeBean;
import com.netease.pineapple.bean.LoadingBean;
import com.netease.pineapple.bean.LoadingEndBean;
import com.netease.pineapple.bean.VideoItemBean;
import com.netease.pineapple.viewbinder.HomeListADBigImgViewBinder;
import com.netease.pineapple.viewbinder.HomeListVideoViewBinder;
import com.netease.pineapple.viewbinder.LoadingEndViewBinder;
import com.netease.pineapple.viewbinder.LoadingViewBinder;

import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.MultiTypeAdapter;

public class AdapterRegisterUtils {



    /// 要保留的
    public static void registerHomeListItem(@NonNull MultiTypeAdapter adapter) {
        adapter.register(ListMultiTypeBean.class)
                .to(new HomeListVideoViewBinder(),
                        new HomeListADBigImgViewBinder())
                .withClassLinker(new ClassLinker<ListMultiTypeBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<ListMultiTypeBean, ?>> index(int position, @NonNull ListMultiTypeBean item) {
                        if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM) {
                            if(item.getDataObj() instanceof HomeListBean.HomeListDataListItemBean) {
                                HomeListBean.HomeListDataListItemBean bean = (HomeListBean.HomeListDataListItemBean) item.getDataObj();
                                if(bean.getContent() instanceof VideoItemBean) {
                                    return HomeListVideoViewBinder.class;
                                }
                            }
                        }
                        else if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM) {
                            return HomeListADBigImgViewBinder.class;
                        } else if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_VIDEO_ITEM) {

                        }
                        return HomeListVideoViewBinder.class;
                    }
                });

        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }
}
