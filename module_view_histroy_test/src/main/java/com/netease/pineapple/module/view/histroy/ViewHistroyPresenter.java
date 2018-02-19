package com.netease.pineapple.module.view.histroy;

/**
 * Created by zhaonan on 2018/2/19.
 */

public class ViewHistroyPresenter  {
//    private IHomeMain.View view;
//
//
//    public ViewHistroyPresenter(IHomeMain.View view) {
//        this.view = view;
//    }
//
//
//    @Override
//    public void doRefresh() {
//
//    }
//
//    @Override
//    public void doShowError() {
//
//    }
//
//    @Override
//    public void doShowLoadMoreError() {
//
//    }
//
//    @Override
//    public void doLoadMoreData() {
//
//    }
//
//    @Override
//    public void doLoadData() {
//
//    }
//
//    @Override
//    public void doShowNoMore() {
//
//    }
//
//    @Override
//    public void doGetCatagoryList() {
//        //请求网络数据
//        BaseEntityObserver observer = new BaseEntityObserver<ArrayList<CategoryBean>>() {
//            @Override
//            public void onRequestSuccess(ArrayList<CategoryBean> s) {
//                view.onShowCatagoryList(s);
//            }
//
//            @Override
//            public void onRequestError(String msg, Throwable e) {
//                super.onRequestError(msg, e);
//                view.onShowCatagoryList(readCategorylListFromAsset());
//            }
//        };
//        HttpUtils.getHomeCategroyList(view, observer);
//    }
//
//
//    private ArrayList<CategoryBean> readCategorylListFromAsset() {
//        try {
//            InputStream is = PPUtils.getAppContext().getAssets().open("category.txt");
//            int size = is.available();
//            byte[] buffer = new byte[size];
//            is.read(buffer);
//            is.close();
//            String text = new String(buffer, "utf8");
//            CategoryListBean ret = GsonUtil.parse(text, CategoryListBean.class);
//            return ret.getData();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

}
