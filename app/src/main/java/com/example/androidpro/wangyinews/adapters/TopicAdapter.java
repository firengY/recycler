package com.example.androidpro.wangyinews.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.example.androidpro.wangyinews.LoginActivity;
import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.bean.TopicEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by firengy
 * on 16-1-11.
 * Email: 18811372352@163.com
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.MyViewHolder> implements View.OnClickListener {

    private Context context;
    private List<TopicEntity.DataEntity.ExpertListEntity> list;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, TopicEntity.DataEntity.ExpertListEntity itemData);
    }


    public TopicAdapter(Context context) {
        this.context = context;
        list = new ArrayList<TopicEntity.DataEntity.ExpertListEntity>();
        List<TopicEntity.DataEntity.ExpertListEntity> listDB = getAll(10);

        for (TopicEntity.DataEntity.ExpertListEntity expert : listDB) {
            TopicEntity.DataEntity.ExpertListEntity entity = new TopicEntity.DataEntity.ExpertListEntity();
            entity.setName(expert.name);
            entity.setHeadpicurl(expert.headpicurl);
            entity.setPicurl(expert.picurl);
            entity.setAlias(expert.alias);
            entity.setConcernCount(expert.concernCount);
            entity.setClassification(expert.classification);

            list.add(entity);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_topic_topicfragment, parent, false);

        MyViewHolder ret = new MyViewHolder(view);

        view.setOnClickListener(this);
        return ret;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TopicEntity.DataEntity.ExpertListEntity item = list.get(position);

        //将数据保存在itemView的Tag中，以便点击时获取
        holder.itemView.setTag(item);

        //控件的设置
        holder.userIcon.setImageURI(Uri.parse(item.getHeadpicurl()));
        holder.userName.setText(item.getName());
        holder.pic.setImageURI(Uri.parse(item.getPicurl()));

        holder.alias.setText(item.getAlias());
        holder.concernCount.setText(item.getConcernCount() + "关注");
        holder.classfication.setText(item.getClassification());

        //关注的点击事件
        holder.concern.setOnClickListener(new OnClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //Toast.makeText(context, "数据"+v.getTag(), Toast.LENGTH_SHORT).show();
            //这里getTag()获取数据
            mOnItemClickListener.onItemClick(v, (TopicEntity.DataEntity.ExpertListEntity) v.getTag());
        }

    }

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    class OnClick implements View.OnClickListener {
        private int position;

        public OnClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(context, "你点击了关注",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, LoginActivity.class);
            context.startActivity(intent);
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView userIcon;
        private TextView userName;
        private TextView alias;
        private TextView concernCount;
        private TextView classfication;
        private SimpleDraweeView pic;
        private ImageView concern;

        public MyViewHolder(View itemView) {
            super(itemView);
            userIcon = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_user_icon));
            pic = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_pic));
            userName = ((TextView) itemView.findViewById(R.id.textview_topic_user_name));
            alias = ((TextView) itemView.findViewById(R.id.textview_topic_alias));
            concernCount = ((TextView) itemView.findViewById(R.id.textview_topic_concern_count));
            classfication = ((TextView) itemView.findViewById(R.id.textview_topic_classfication));
            //cardView = ((CardView) itemView.findViewById(R.id.cardview_topic_click));
            concern = ((ImageView) itemView.findViewById(R.id.imageview_topic_concern));
        }
    }

    public static List<TopicEntity.DataEntity.ExpertListEntity> getAll(int num) {
        return new Select()
                .from(TopicEntity.DataEntity.ExpertListEntity.class)
                .limit(num)
                .execute();
    }

    public void addAll(Collection<? extends TopicEntity.DataEntity.ExpertListEntity> col) {
        //Log.d(TAG, "addAll: list"+list);

        if (col != null) {
            list.addAll(col);
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "无数据", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearAll() {
        list.clear();
    }
}



/*public class TopicAdapter extends BaseAdapter {
    private static final String TAG = "TopicAdapter";

    private Context context;
    private List<TopicEntity.DataEntity.ExpertListEntity> list;

    public TopicAdapter(Context context) {
        this.context = context;
        list = new ArrayList<TopicEntity.DataEntity.ExpertListEntity>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View ret = null;

        if (convertView != null) {
            ret = convertView;
        }else{
            ret = LayoutInflater.from(context)
                    .inflate(R.layout.item_topic_topicfragment,parent,false);
        }
        ret.setTag(new ViewHolder(ret));

        TopicEntity.DataEntity.ExpertListEntity item = list.get(position);

        ViewHolder holder = (ViewHolder) ret.getTag();

        holder.userIcon.setImageURI(Uri.parse(item.getHeadpicurl()));
        holder.userName.setText(item.getName());
        holder.pic.setImageURI(Uri.parse(item.getPicurl()));
        holder.alias.setText(item.getAlias());
        holder.concernCount.setText(item.getConcernCount()+"关注");
        holder.classfication.setText(item.getClassification());

        return ret;
    }


    public void addAll(Collection<? extends TopicEntity.DataEntity.ExpertListEntity> col){
        //Log.d(TAG, "addAll: list"+list);

        if (col != null) {
            list.addAll(col);
            notifyDataSetChanged();
        }else{
            Toast.makeText(context, "无数据", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ViewHolder{
        private SimpleDraweeView userIcon;
        private TextView userName;
        private TextView alias;
        private TextView concernCount;
        private TextView classfication;
        private SimpleDraweeView pic;

        public ViewHolder(View itemView){
            userIcon = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_user_icon));
            pic = ((SimpleDraweeView) itemView.findViewById(R.id.imageview_topic_pic));
            userName = ((TextView) itemView.findViewById(R.id.textview_topic_user_name));
            alias = ((TextView) itemView.findViewById(R.id.textview_topic_alias));
            concernCount = ((TextView) itemView.findViewById(R.id.textview_topic_concern_count));
            classfication = ((TextView) itemView.findViewById(R.id.textview_topic_classfication));
        }
    }
}*/
