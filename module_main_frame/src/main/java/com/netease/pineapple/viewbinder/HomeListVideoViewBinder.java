package com.netease.pineapple.viewbinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.pineapple.bean.HomeListBean;
import com.netease.pineapple.bean.VideoItemBean;
import com.netease.pineapple.module.main.frame.R;
import com.netease.pineapple.utils.ErrorActionUtils;
import com.netease.pineapple.common.glide.ImageLoader;
import com.netease.pineapple.view.CircleImageView;

import me.drakeet.multitype.ItemViewBinder;


public class HomeListVideoViewBinder extends ItemViewBinder<HomeListBean.HomeListDataListItemBean, HomeListVideoViewBinder.ViewHolder> {

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
            }



//            MediaWendaBean.AnswerQuestionBean.AnswerBean answerBean = item.getAnswer();
//            MediaWendaBean.AnswerQuestionBean.QuestionBean questionBean = item.getQuestion();
//
//            final String title = questionBean.getTitle();
//            String abstractX = answerBean.getContent_abstract().getText();
//            String readCount = answerBean.getBrow_count() + "个回答";
//            String time = answerBean.getShow_time();
//
//            holder.tv_title.setText(title);
//            holder.tv_title.setTextSize(SettingUtil.getInstance().getTextSize());
//            holder.tv_abstract.setText(abstractX);
//            holder.tv_extra.setText(readCount + "  -  " + time);
//
//            RxView.clicks(holder.itemView)
//                    .throttleFirst(1, TimeUnit.SECONDS)
//                    .subscribe(new Consumer<Object>() {
//                        @Override
//                        public void accept(@io.reactivex.annotations.NonNull Object o) throws Exception {
//                            WendaContentBean.AnsListBean ansBean = new WendaContentBean.AnsListBean();
//                            WendaContentBean.AnsListBean.ShareDataBeanX shareBean = new WendaContentBean.AnsListBean.ShareDataBeanX();
//                            WendaContentBean.AnsListBean.UserBeanX userBean = new WendaContentBean.AnsListBean.UserBeanX();
//                            ansBean.setTitle(title);
//                            ansBean.setQid(item.getQuestion().getQid());
//                            ansBean.setAnsid(item.getQuestion().getQid());
//                            shareBean.setShare_url(item.getAnswer().getWap_url());
//                            userBean.setUname(item.getAnswer().getUser().getUname());
//                            userBean.setAvatar_url(item.getAnswer().getUser().getAvatar_url());
//                            ansBean.setShare_data(shareBean);
//                            ansBean.setUser(userBean);
//                            WendaDetailActivity.launch(ansBean);
//                        }
//                    });
//
//            holder.iv_dots.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    PopupMenu popupMenu = new PopupMenu(context,
//                            holder.iv_dots, Gravity.END, 0, R.style.MyPopupMenu);
//                    popupMenu.inflate(R.menu.menu_share);
//                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem menu) {
//                            int itemId = menu.getItemId();
//                            if (itemId == R.id.action_share) {
//                                IntentAction.send(context, title + "\n" + item.getAnswer().getWap_url());
//                            }
//                            return false;
//                        }
//                    });
//                    popupMenu.show();
//                }
//            });
        } catch (Exception e) {
            ErrorActionUtils.print(e);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_topic;
        private CircleImageView iv_icon;
        private ImageView iv_cover;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_cover = itemView.findViewById(R.id.cover);
            this.tv_title = itemView.findViewById(R.id.title);
            this.tv_topic = itemView.findViewById(R.id.topic);
            this.iv_icon = itemView.findViewById(R.id.icon);
        }
    }
}
