package com.netease.pineapple.viewbinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.netease.pineapple.bean.LoadingErrorBean;
import com.netease.pineapple.common.utils.NetworkUtils;
import com.netease.pineapple.common.utils.ToastUtils;
import com.netease.pineapple.listener.IOnItemClickListener;
import com.netease.pineapple.module.main.frame.R;

import me.drakeet.multitype.ItemViewBinder;

public class LoadingErrorViewBinder extends ItemViewBinder<LoadingErrorBean, LoadingErrorViewBinder.ViewHolder> {


    private IOnItemClickListener mlistener;

    public LoadingErrorViewBinder(IOnItemClickListener listener) {
        this.mlistener = listener;
    }

    @NonNull
    @Override
    protected LoadingErrorViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_loading_error, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull LoadingErrorBean item) {

    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tv_text;
        ViewHolder(View itemView) {
            super(itemView);
            tv_text = itemView.findViewById(R.id.text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(!NetworkUtils.isConnected()){
                ToastUtils.showNetErrorToast();
                return;
            }

            if(mlistener != null) {
                mlistener.onClick(v, getLayoutPosition());
            }
        }
    }
}
