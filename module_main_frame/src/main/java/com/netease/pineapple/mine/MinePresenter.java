package com.netease.pineapple.mine;

/**
 * Created by zhaonan on 2018/2/19.
 */

public class MinePresenter implements IMine.Presenter {
    private IMine.View view;


    public MinePresenter(IMine.View view) {
        this.view = view;
    }


    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowError(String msg) {

    }

    @Override
    public void doShowLoadMoreError() {

    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public void doInitLoadData() {

    }

    @Override
    public void doShowNoMore() {

    }
}
