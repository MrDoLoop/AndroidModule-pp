package com.netease.pineapple.home;

import com.netease.pineapple.common.base.OnDataReturnListener;
import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.bean.ListMultiTypeBean;
import com.netease.pineapple.common.bean.VideoItemBean;
import com.netease.pineapple.common.utils.DataUtils;
import com.netease.pineapple.common.utils.ErrorActionUtils;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryPresenter implements IHomeCategory.Presenter {

    private static final String TAG = "HomeCategoryPresenter";
    private IHomeCategory.View mView;
    private HomeCategoryModel mModel;
    //最终用来显示的总list
    private List<ListMultiTypeBean> mShowList = new ArrayList<>();
    //广告列表
    private List<HomeListBean.HomeListDataListItemBean> mAdList = new ArrayList<>();
    // 数据列表
    private List<HomeListBean.HomeListDataListItemBean> mDataItemList = new ArrayList<>();

    private String mEname;

    @Override
    public boolean hasAD() {
        if("Video_Recom".equals(mEname)) {
            return true;
        }
        return false;
    }

    public HomeCategoryPresenter(IHomeCategory.View view) {
        this.mModel = new HomeCategoryModel();
        this.mView = view;
    }

    @Override
    public void doInitLoadData() {
        initLoadLocalData();
        requestAD();
    }

    private void initLoadLocalData() {
        mModel.initLoadLocalData(new OnDataReturnListener<HomeListBean.HomeListDataBean>() {
            @Override
            public void onDataReady(HomeListBean.HomeListDataBean bean) {
                if(bean == null) {
                    initLoadNetData();
                } else {
                    doSetAdapter(bean.getDatalist());
                }
            }

            @Override
            public void onDataError(String msg, Throwable e) {

            }
        });
    }

    private void initLoadNetData() {
        mModel.initLoadNetData(mView, new OnDataReturnListener<HomeListBean.HomeListDataBean>() {
            @Override
            public void onDataReady(HomeListBean.HomeListDataBean bean) {
                doSetAdapter(bean.getDatalist());
            }

            @Override
            public void onDataError(String msg, Throwable e) {
                doShowError(msg);
                ErrorActionUtils.print(e);
            }
        });
    }

    @Override
    public void requestAD() {
        if(hasAD()) {
            mModel.requestAD(mView, new OnDataReturnListener<HomeListBean.HomeListDataBean>() {
                @Override
                public void onDataReady(HomeListBean.HomeListDataBean bean) {
                    if(bean == null) return;
                    for(int i = 0; i < bean.getDataList().size() ;i++) {
                        String title = ((VideoItemBean)bean.getDataList().get(i).getContent()).getTitle();
                        ((VideoItemBean)bean.getDataList().get(i).getContent()).setTitle("我是广告---" + title);
                    }
                    mAdList.clear();
                    mAdList.addAll(bean.getDatalist());
                    insertAd();
                    updateList();
                }

                @Override
                public void onDataError(String msg, Throwable e) {
                    ErrorActionUtils.print(e);
                }
            });
        }
    }

    @Override
    public void doLoadMoreData() {
        mModel.doLoadMoreData(mView, mDataItemList.size(), new OnDataReturnListener<HomeListBean.HomeListDataBean>() {
            @Override
            public void onDataReady(HomeListBean.HomeListDataBean bean) {
                doAppendMoreData(bean.getDatalist());
            }

            @Override
            public void onDataError(String msg, Throwable e) {
                doShowLoadMoreError();
                ErrorActionUtils.print(e);
            }
        });
    }

    @Override
    public void doSetAdapter(List<HomeListBean.HomeListDataListItemBean> list) {
        mDataItemList.clear();
        mShowList.clear();
        doAppendMoreData(list);
    }

    @Override
    public void doAppendMoreData(List<HomeListBean.HomeListDataListItemBean> list) {
        mDataItemList.addAll(list);
        appendDataToShowList(list);
        updateList();
    }

    private void updateList() {
        mView.onSetAdapter(mShowList, true);
        mView.onHideLoading();
    }


    private synchronized void appendDataToShowList(List<HomeListBean.HomeListDataListItemBean> list) {
        // 1. 添加数据
        if(DataUtils.listNotEmpty(list)) {
            for(HomeListBean.HomeListDataListItemBean bean : list) {
                ListMultiTypeBean multiTypeBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_HOME_LIST_ITEM, bean);
                mShowList.add(multiTypeBean);
            }
            insertAd();
        }
    }

    private synchronized void insertAd() {
        // 赵楠的测试 4， 11， 16 插入ad
        if(mAdList.size() == 0 || mShowList.size() == 0) return;
        HomeListBean.HomeListDataListItemBean bean = mAdList.get(0);

        ListMultiTypeBean adBean = new ListMultiTypeBean(ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM, bean);

        if(mShowList.size() >= 3) {
            if(isAdAtPos(3)) {
                mShowList.set(3, adBean);
            } else {
                mShowList.add(3, adBean);
            }
        }

        if(mShowList.size() >= 10) {
            if(isAdAtPos(10)) {
                mShowList.set(10, adBean);
            } else {
                mShowList.add(10, adBean);
            }
        }

        if(mShowList.size() >= 16) {
            if(isAdAtPos(15)) {
                mShowList.set(15, adBean);
            } else {
                mShowList.add(15, adBean);
            }
        }
    }


    private boolean isAdAtPos(int pos) {
        if(pos <= mShowList.size()) {
            if(mShowList.get(pos).getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_BIG_IMG_ITEM ||
                    mShowList.get(pos).getType() == ListMultiTypeBean.ListMultiTypeBeanType.TYPE_LIST_AD_VIDEO_ITEM) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void setEname(String ename) {
        this.mEname = ename;
        mModel.setEname(ename);
    }

    @Override
    public void doRefresh() {
        initLoadNetData();
        requestAD();
    }

    @Override
    public void doShowError(String msg) {
        mView.onHideLoading();
        mView.onShowError(msg);
    }

    @Override
    public void doShowNoMore() {
        mView.onHideLoading();
        mView.onShowNoMore();
    }

    @Override
    public void doShowLoadMoreError() {
        mView.onHideLoading();
        mView.onShowLoadMoreError();
    }
}
