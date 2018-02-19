package com.netease.pineapple.module.view.histroy.utils;

import android.support.annotation.NonNull;

import com.netease.pineapple.common.bean.LoadingBean;
import com.netease.pineapple.common.bean.LoadingEndBean;
import com.netease.pineapple.common.bean.LoadingErrorBean;
import com.netease.pineapple.common.listener.IOnItemClickListener;
import com.netease.pineapple.common.viewbinder.LoadingEndViewBinder;
import com.netease.pineapple.common.viewbinder.LoadingErrorViewBinder;
import com.netease.pineapple.common.viewbinder.LoadingViewBinder;

import me.drakeet.multitype.MultiTypeAdapter;

public class AdapterRegisterUtils {
    /// 要保留的

    public static void registerViewHistroyListItem(@NonNull MultiTypeAdapter adapter, IOnItemClickListener listener){
        //adapter.register()
    }

//    public static void registerHomeListItem(@NonNull MultiTypeAdapter adapter, IOnItemClickListener listener) {
//        adapter.register(ListMultiTypeBean.class)
//                .to(new HomeListVideoViewBinder(),
//                        new HomeListADBigImgViewBinder())
//                .withClassLinker(new ClassLinker<ListMultiTypeBean>() {
//                    @NonNull
//                    @Override
//                    public Class<? extends ItemViewBinder<ListMultiTypeBean, ?>> index(int position, @NonNull ListMultiTypeBean item) {
//                        if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM) {
//                            if(item.getDataObj() instanceof HomeListBean.HomeListDataListItemBean) {
//                                HomeListBean.HomeListDataListItemBean bean = (HomeListBean.HomeListDataListItemBean) item.getDataObj();
//                                if(bean.getContent() instanceof VideoItemBean) {
//                                    return HomeListVideoViewBinder.class;
//                                }
//                            }
//                        }
//                        else if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM) {
//                            return HomeListADBigImgViewBinder.class;
//                        } else if(item.getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_VIDEO_ITEM) {
//
//                        }
//                        return HomeListVideoViewBinder.class;
//                    }
//                });
//        addLoadingBean(adapter, listener);
//    }

    private static void addLoadingBean(@NonNull MultiTypeAdapter adapter, IOnItemClickListener listener) {
        adapter.register(LoadingErrorBean.class, new LoadingErrorViewBinder(listener));
        adapter.register(LoadingBean.class, new LoadingViewBinder());
        adapter.register(LoadingEndBean.class, new LoadingEndViewBinder());
    }

}
