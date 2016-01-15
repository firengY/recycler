package com.example.androidpro.wangyinews.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.NoCopySpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidpro.wangyinews.NewsWebActivity;
import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.bean.News;
import com.example.androidpro.wangyinews.views.NetWorkImageIndicatorView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by lupengcheng
 * 2016/1/11  16:38
 * Email: 18855586117@163.com
 */
public class NewsAdapter extends BaseAdapter {
    //定义三种布局
    public static final int typeCount = 3;
    //广告布局
    public static final int ADS = 0;
    //normal新闻布局
    public static final int NORMAL = 1;
    //无广告布局
    public static final int NOADS = 2;
    //是否已经添加了广告
    public static boolean hasAddAds = false;
    private Context context;
    private List<News.BaseEntity> list;

    public NewsAdapter(Context context)
    {
        this.context = context;
        list = new ArrayList<>();
    }

    public static int getTypeCount() {
        return typeCount;
    }

    @Override
    public int getItemViewType(int position) {
        News.BaseEntity entity = list.get(position);
        if(entity.getHasAD() == 1)
        {
            return ADS;
        }
        if(entity.getUrl_3w() != null)
        {
            return NORMAL;
        }
        else
            return NOADS;
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

        News.BaseEntity entity = list.get(position);
        int type = getItemViewType(position);
        NormalViewHolder normal = null;
        ADViewHolder ads = null;
        NoADsViewHolder noads = null;
        if(convertView == null)
        {
            switch (type)
            {
                case NORMAL :
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_modulefragment,parent,false);
                    convertView.setTag(normal = new NormalViewHolder(convertView));
                    break;
                case ADS :
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_ads_newsmodulefragment,parent,false);
                    convertView.setTag(ads = new ADViewHolder(convertView));
                    break;
                case NOADS:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_noads,parent,false);
                    convertView.setTag(noads = new NoADsViewHolder(convertView));
                    break;
                default:break;
            }
        }else {
            switch (type)
            {
                case NORMAL :
                    if(normal == null)
                    {
                        convertView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_modulefragment,parent,false);
                        convertView.setTag(normal = new NormalViewHolder(convertView));
                    }else {
                        normal = (NormalViewHolder) convertView.getTag();
                    }break;
                case ADS:
                    if(ads == null)
                    {
                        convertView = LayoutInflater.from(context).inflate(R.layout.item_ads_newsmodulefragment,parent,false);
                        convertView.setTag(ads = new ADViewHolder(convertView));
                    }else {
                        ads = (ADViewHolder) convertView.getTag();
                    }
                    break;
                case  NOADS:
                    if(noads == null)
                    {
                        convertView = LayoutInflater.from(context).inflate(R.layout.item_noads,parent,false);
                        convertView.setTag(noads = new NoADsViewHolder(convertView));
                    }else {
                        noads = (NoADsViewHolder) convertView.getTag();
                    }
            }
        }

        switch (type){
            case NORMAL :
                initNormalItem(entity,normal);
                break;
            case ADS :
                initAdsItem(entity,ads);
                break;
            case NOADS:
                initNoAdsItem(entity,noads);
                break;
            default:break;
        }
        return convertView;
    }

    //初始化广告item
    private void initAdsItem(News.BaseEntity entity, ADViewHolder holder) {
        if (!holder.adsimage.hasData()) {
            //已经添加广告
            hasAddAds = true;
            List<String> adsUrl = new ArrayList<>();
            List<News.BaseEntity.AdsEntity> ads = entity.getAds();
            if(ads != null) {
                //添加广告的Url
                for (News.BaseEntity.AdsEntity ad : ads) {
                    adsUrl.add(ad.getImgsrc());
                }
                holder.adsimage.setImageUrlByList(adsUrl);
                holder.adsimage.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
                holder.adsimage.show();
                AutoPlayManager autoBrocastManager = new AutoPlayManager(holder.adsimage);
                autoBrocastManager.setBroadcastEnable(true);
                autoBrocastManager.setBroadCastTimes(5);//loop times
                autoBrocastManager.setBroadcastTimeIntevel(2 * 1000, 2 * 1000);//set first play time and interval
                autoBrocastManager.loop();
            }
        }
    }
    //初始化正常新闻item
    private void initNormalItem(News.BaseEntity entity, NormalViewHolder holder) {
        if (entity.getImgsrc() != null )
        holder.image.setImageURI(Uri.parse(entity.getImgsrc()));

        if(entity.getTitle() != null)
        {
            holder.tv_title.setText(entity.getTitle());
        }

        if(entity.getDigest() != null)
        {
            holder.tv_content.setText(entity.getDigest());
        }

        if(entity.getReplyCount() != 0)
        {
            holder.tv_comment.setVisibility(View.VISIBLE);
            holder.special.setVisibility(View.GONE);
            holder.tv_comment.setText(entity.getReplyCount()+"跟帖");
        }

        if(entity.getSkipType() != null && entity.getSkipType().equals("special"))
        {
            holder.tv_comment.setVisibility(View.GONE);
            holder.special.setVisibility(View.VISIBLE);
        }
    }

    //初始化无广告item,待加
    private void initNoAdsItem(News.BaseEntity entity, NoADsViewHolder holder)
    {
        if(entity.getTitle() != null)
        {
            holder.textView.setText(entity.getTitle());
        }
    }

    //上拉加载使用方法
    public void addAll(Collection<? extends News.BaseEntity> collection)
    {
        int size = list.size();
        list.addAll(size,collection);
        //如果已经添加了广告
        if(hasAddAds){
            //将添加进来的广告数据删除
            list.remove(size);
        }
        notifyDataSetChanged();
    }

    //刷新操作使用方法
    public void refresh(Collection<? extends News.BaseEntity> collection){
        //清空所有的数据
        list.clear();
        list.addAll(collection);
        notifyDataSetChanged();
    }


    //广告ViewHolder
    public static class ADViewHolder{
        private NetWorkImageIndicatorView adsimage;
        private TextView adstitle;
        public ADViewHolder(View itemView)
        {
            adsimage = (NetWorkImageIndicatorView) itemView.findViewById(R.id.ads_image_network);
            adstitle = (TextView) itemView.findViewById(R.id.ads_title);
        }
    }
    //正常新闻ViewHolder
    public static class NormalViewHolder{
        private TextView tv_title;
        private SimpleDraweeView image;
        private TextView tv_content;
        private TextView tv_comment;
        private TextView special;

        public NormalViewHolder(View itemView)
        {
            special = (TextView) itemView.findViewById(R.id.special);
            image = (SimpleDraweeView) itemView.findViewById(R.id.simpledrawee);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_comment = (TextView) itemView.findViewById(R.id.tv_comment);

        }
    }
    //无广告ViewHolder
    public static class NoADsViewHolder{
        private TextView textView;
        public NoADsViewHolder(View itemView){
            textView = (TextView) itemView.findViewById(R.id.mytexttest);
        }
    }
}
