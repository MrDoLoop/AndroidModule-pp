package com.netease.pineapple.listener;

import android.view.View;

public interface IOnItemLongClickListener {

    /**
     * RecyclerView Item长按事件
     */
    void onLongClick(View view, int position);
}
