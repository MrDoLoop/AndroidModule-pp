package com.netease.pineapple.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.netease.pineapple.bean.LoadingBean;
import com.netease.pineapple.bean.LoadingEndBean;
import com.netease.pineapple.bean.LoadingErrorBean;
import com.netease.pineapple.common.utils.NetworkUtils;
import com.netease.pineapple.common.utils.PPUtils;
import com.netease.pineapple.common.utils.ToastUtils;
import com.netease.pineapple.listener.IOnItemClickListener;
import com.netease.pineapple.listener.OnLoadMoreListener;
import com.netease.pineapple.module.main.frame.R;
import com.netease.pineapple.utils.AdapterRegisterUtils;
import com.netease.pineapple.utils.DiffCallback;
import com.netease.pineapple.utils.RxBus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, SwipeRefreshLayout.OnRefreshListener {

    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected boolean canLoadMore = false;
    protected Observable<Integer> observable;

    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(PPUtils.getAppContext().getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(this);

        IOnItemClickListener listener = new IOnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
//                if(!NetworkUtils.isConnected()){
//                    ToastUtils.showNetErrorToast();
//                    return;
//                }

//                Items newItems = new Items(oldItems);
//                newItems.remove(newItems.size() - 1);
//                newItems.add(new LoadingBean());
//                adapter.setItems(newItems);
//                adapter.notifyDataSetChanged();

                presenter.doLoadMoreData();
            }
        };
        adapter = new MultiTypeAdapter(oldItems);
        AdapterRegisterUtils.registerHomeListItem(adapter, listener);
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
    public void onShowError() {
        ToastUtils.showNetErrorToast();
    }

    @Override
    public void onShowLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(true);
            }
        });
    }

    @Override
    public void onHideLoading() {
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void fetchData() {
        observable = RxBus.getInstance().register(BaseListFragment.TAG);
        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSetAdapter(final List<?> list, boolean loadMore) {
        isDataInitiated = true;
        Items newItems = new Items(list);
        if(loadMore) {
            newItems.add(new LoadingBean());
        }
        DiffCallback.create(oldItems, newItems, adapter);
        oldItems.clear();
        oldItems.addAll(newItems);
        canLoadMore = loadMore;
    }

    @Override
    public void onShowLoadMoreError() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {

                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingErrorBean());

                    oldItems.clear();
                    oldItems.addAll(newItems);
                    canLoadMore = true;

                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onShowNoMore() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (oldItems.size() > 0) {
                    Items newItems = new Items(oldItems);
                    newItems.remove(newItems.size() - 1);
                    newItems.add(new LoadingEndBean());
                    adapter.setItems(newItems);
                    adapter.notifyDataSetChanged();
                } else if (oldItems.size() == 0) {
                    oldItems.add(new LoadingEndBean());
                    adapter.setItems(oldItems);
                    adapter.notifyDataSetChanged();
                }
                canLoadMore = false;
            }
        });
    }

    @Override
    public void onRefresh() {
        if(!NetworkUtils.isConnected()) {
            ToastUtils.showShortToast(R.string.network_error);
            onHideLoading();
            return;
        }
        presenter.doRefresh();
    }

    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(BaseListFragment.TAG, observable);
        super.onDestroy();
    }
}
