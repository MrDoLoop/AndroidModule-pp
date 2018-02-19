package com.netease.pineapple.viewbinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.pineapple.common.bean.HomeListBean;
import com.netease.pineapple.common.bean.ListMultiTypeBean;
import com.netease.pineapple.common.bean.VideoItemBean;
import com.netease.pineapple.common.glide.ImageLoader;
import com.netease.pineapple.common.widget.CircleImageView;
import com.netease.pineapple.module.common.R;
import com.netease.pineapple.utils.IntentUtils;

import me.drakeet.multitype.ItemViewBinder;


public class HomeListADBigImgViewBinder extends ItemViewBinder<ListMultiTypeBean, HomeListADBigImgViewBinder.ViewHolder> implements View.OnClickListener{

    private VideoItemBean dataBean;
    @NonNull
    @Override
    protected HomeListADBigImgViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.module_main_frame_home_list_item_video, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final HomeListADBigImgViewBinder.ViewHolder holder, @NonNull final ListMultiTypeBean item) {
        try {
            final Context context = holder.itemView.getContext();
            if(item.getDataObj() instanceof HomeListBean.HomeListDataListItemBean) {
                HomeListBean.HomeListDataListItemBean tmpbean = (HomeListBean.HomeListDataListItemBean) item.getDataObj();
                if(tmpbean.getContent() instanceof VideoItemBean) {
                    dataBean = (VideoItemBean) tmpbean.getContent();
                    holder.tv_title.setText(dataBean.getTitle());
                    holder.tv_topic.setText(dataBean.getTname());
                    ImageLoader.loadCenterCrop(context, dataBean.getCover(), holder.iv_cover, R.color.viewBackground);
                    ImageLoader.loadCenterCrop(context, dataBean.getVideoTopic().getTopic_icons(), holder.iv_icon, R.color.viewBackground);
                    holder.iv_share.setOnClickListener(this);
                    holder.iv_like.setOnClickListener(this);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.video_player_favor) {

        }else if(v.getId() == R.id.video_player_share) {
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
