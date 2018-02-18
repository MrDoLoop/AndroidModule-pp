package com.meiji.toutiao.module.video.article;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.netease.pineapple.utils.AdapterRegisterUtils;
import com.netease.pineapple.bean.LoadingBean;
import com.netease.pineapple.base.BaseListFragment;
import com.netease.pineapple.home.HomeCategoryPresenter;
import com.netease.pineapple.home.IHomeCategory;
import com.netease.pineapple.utils.DiffCallback;
import com.netease.pineapple.utils.OnLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by Meiji on 2017/3/29.
 */

public class VideoArticleView extends BaseListFragment<IHomeCategory.Presenter> implements IHomeCategory.View, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "VideoArticleView";
    private String categoryId;

    public static VideoArticleView newInstance(String categoryId) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, categoryId);
        VideoArticleView videoArticleView = new VideoArticleView();
        videoArticleView.setArguments(bundle);
        return videoArticleView;
    }

    @Override
    protected void initData() {
        categoryId = getArguments().getString(TAG);
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        adapter = new MultiTypeAdapter(oldItems);
        AdapterRegisterUtils.registerVideoArticleItem(adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (canLoadMore) {
                    canLoadMore = false;
                    presenter.doLoadMoreData();
                }
            }
        });
    }

    @Override
    public void fetchData() {
        super.fetchData();
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        presenter.doLoadData();
    }

    @Override
    public void onSetAdapter(final List<?> list) {
        Items newItems = new Items(list);
        newItems.add(new LoadingBean());
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = true;
        recyclerView.stopScroll();
    }

    /**
     * API 跟新闻的一样 所以采用新闻的 presenter
     *
     * @param presenter
     */
    @Override
    public void setPresenter(IHomeCategory.Presenter presenter) {
        if (null == presenter) {
            this.presenter = new HomeCategoryPresenter(this, categoryId);
        }
    }
}
