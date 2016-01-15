package com.example.androidpro.wangyinews.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.bean.TopicExpertEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by firengy
 * on 16-1-14.
 * Email: 18811372352@163.com
 */
public class TopicExpertAdapter extends RecyclerView.Adapter<TopicExpertAdapter.TopicExpertAdapterViewHolder> {
    private Context context;
    private TopicExpertEntity.DataEntity dataEntity;

    private List<TopicExpertEntity.DataEntity.ExpertEntity> expertList;
    private List<TopicExpertEntity.DataEntity.HotListEntity> hotList;
    private List<TopicExpertEntity.DataEntity.LatestListEntity> latestList;

    public static final int DETAIL = 1;
    public static final int QA = 2;

    public TopicExpertAdapter(Context context) {
        this.context = context;
        hotList = new ArrayList<TopicExpertEntity.DataEntity.HotListEntity>();
        latestList = new ArrayList<TopicExpertEntity.DataEntity.LatestListEntity>();
        expertList = new ArrayList<TopicExpertEntity.DataEntity.ExpertEntity>();

//        dataEntity.setHotList(hotList);
//        dataEntity.setLatestList(latestList);
    }

    @Override
    public TopicExpertAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case DETAIL:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_activity_topic_expert_detail,
                                parent,
                                false);
                break;
            case QA:
                view = LayoutInflater.from(context)
                        .inflate(R.layout.item_activity_topic_expert_qa,
                                parent,
                                false);
                break;
        }
        return new TopicExpertAdapterViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TopicExpertAdapterViewHolder holder, int position) {
        switch (holder.viewType) {
            case DETAIL:
                holder.detailUserIcon.setImageURI(Uri.parse(expertList.get(0).getHeadpicurl()));
                holder.detailUserName.setText(expertList.get(0).getName());
                holder.detailDescription.setText(expertList.get(0).getDescription());
                holder.changestateQuestionCount.setText(expertList.get(0).getQuestionCount()+"提问");
                holder.changestateAnswerCount.setText(expertList.get(0).getAnswerCount()+"回复");
                break;
            case QA:
                holder.qaQuestionContent.setText(hotList.get(position-1).getQuestion().getContent());
                holder.qaAnswerContent.setText(hotList.get(position-1).getAnswer().getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return hotList.size() + expertList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return DETAIL;
        } else {
            return QA;
        }
    }

    public void addAll(TopicExpertEntity.DataEntity dataEntity,String type){
        switch (type) {
            case "expert":
                expertList.add(dataEntity.getExpert());
                break;
            case "hot":
                hotList.addAll(dataEntity.getHotList());
                break;
            case "latest":
                latestList.addAll(dataEntity.getLatestList());
                break;
        }
        notifyDataSetChanged();
    }

    public class TopicExpertAdapterViewHolder extends RecyclerView.ViewHolder {
        public int viewType;
        private SimpleDraweeView detailUserIcon;
        private TextView detailUserName;
        private TextView detailTitle;
        private TextView detailDescription;
        private TextView changestateQuestionCount;
        private TextView changestateAnswerCount;
        private Switch switchChangestate;
        private SimpleDraweeView qaQuestionUserIcon;
        private TextView qaQuestionUserName;
        private TextView qaQuestionContent;
        private SimpleDraweeView qaAnswerUserIcon;
        private TextView qaAnswerUserName;
        private TextView qaAnswerContent;

        public TopicExpertAdapterViewHolder(View itemView, int viewType) {
            super(itemView);
            this.viewType = viewType;
            switch (viewType) {
                case DETAIL:
                    detailUserIcon = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_expert_detail_user_icon));
                    detailUserName = ((TextView) itemView.findViewById(R.id.textview_topic_expert_detail_user_name));
                    detailTitle = ((TextView) itemView.findViewById(R.id.textview_topic_expert_detail_title));
                    detailDescription = (TextView)itemView.findViewById(R.id.textview_topic_expert_detail_description);

                    changestateQuestionCount = ((TextView) itemView.findViewById(R.id.textview_topic_expert_changestate_question_count));
                    changestateAnswerCount = ((TextView) itemView.findViewById(R.id.textview_topic_expert_changestate_answer_count));
                    switchChangestate = ((Switch) itemView.findViewById(R.id.switch_topic_expert_changestate));
                    break;
                case QA:
                    qaQuestionUserIcon = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_expert_qa_question_user_icon));
                    qaQuestionUserName = ((TextView) itemView.findViewById(R.id.textview_topic_expert_qa_question_user_name));
                    qaQuestionContent = ((TextView) itemView.findViewById(R.id.textview_topic_expert_qa_question_content));
                    qaAnswerUserIcon = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_expert_qa_answer_user_icon));
                    qaAnswerUserName = ((TextView) itemView.findViewById(R.id.textview_topic_expert_qa_answer_user_name));
                    qaAnswerContent = ((TextView) itemView.findViewById(R.id.textview_topic_expert_qa_answer_content));
                    break;
            }
        }
    }
}
