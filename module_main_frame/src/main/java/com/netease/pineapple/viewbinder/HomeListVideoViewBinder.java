package com.netease.pineapple.viewbinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jakewharton.rxbinding2.view.RxView;
import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.bean.VideoItemBean;
import com.netease.pineapple.module.main.frame.R;
import com.netease.pineapple.utils.ErrorActionUtils;
import com.netease.pineapple.common.glide.ImageLoader;
import com.netease.pineapple.utils.IntentUtils;
import com.netease.pineapple.view.CircleImageView;

import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;
import me.drakeet.multitype.ItemViewBinder;


public class HomeListVideoViewBinder extends ItemViewBinder<HomeListBean.HomeListDataListItemBean, HomeListVideoViewBinder.ViewHolder> implements View.OnClickListener{

    private VideoItemBean dataBean;
    @NonNull
    @Override
    protected HomeListVideoViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.module_main_frame_home_list_item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final HomeListVideoViewBinder.ViewHolder holder, @NonNull final HomeListBean.HomeListDataListItemBean item) {
        try {
            final Context context = holder.itemView.getContext();
            if(item.getContent() instanceof VideoItemBean) {
                dataBean = (VideoItemBean) item.getContent();
                holder.tv_title.setText(dataBean.getTitle());
                holder.tv_topic.setText(dataBean.getTname());
                ImageLoader.loadCenterCrop(context, dataBean.getCover(), holder.iv_cover, R.color.viewBackground);
                ImageLoader.loadCenterCrop(context, dataBean.getVideoTopic().getTopic_icons(), holder.iv_icon, R.color.viewBackground);

                RxView.clicks(holder.iv_share)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
                            IntentUtils.startVideoDetailActivity();
                        }
                    });
                holder.iv_like.setOnClickListener(this);
            }
        } catch (Exception e) {
            ErrorActionUtils.print(e);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.video_player_favor) {
            IntentUtils.startShareActivity();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_topic;
        public CircleImageView iv_icon;
        public ImageView iv_cover;
        public ImageView iv_share;
        public ImageView iv_like;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_cover = itemView.findViewById(R.id.cover);
            this.tv_title = itemView.findViewById(R.id.title);
            this.tv_topic = itemView.findViewById(R.id.topic);
            this.iv_icon = itemView.findViewById(R.id.icon);
            this.iv_share = itemView.findViewById(R.id.video_player_share);
            this.iv_like = itemView.findViewById(R.id.video_player_favor);
        }
    }
}
