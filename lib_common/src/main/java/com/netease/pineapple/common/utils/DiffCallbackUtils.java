package com.netease.pineapple.common.utils;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;


public class DiffCallbackUtils extends DiffUtil.Callback {

    private final Items mOldItems, mNewItems;

    private DiffCallbackUtils(Items oldItems, Items mNewItems) {
        this.mOldItems = oldItems;
        this.mNewItems = mNewItems;
    }

    public static void create(@NonNull Items oldList, @NonNull Items newList, @NonNull MultiTypeAdapter adapter) {
        DiffCallbackUtils diffCallback = new DiffCallbackUtils(oldList, newList);
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(diffCallback, true);
        result.dispatchUpdatesTo(adapter);
    }

    @Override
    public int getOldListSize() {
        return mOldItems != null ? mOldItems.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewItems != null ? mNewItems.size() : 0;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).equals(mNewItems.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldItems.get(oldItemPosition).hashCode() == mNewItems.get(newItemPosition).hashCode();
    }
}
