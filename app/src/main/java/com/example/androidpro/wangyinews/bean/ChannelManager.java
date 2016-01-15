package com.example.androidpro.wangyinews.bean;

/**
 * Created by lupengcheng
 * 2016/1/13  23:13
 * Email: 18855586117@163.com
 */

import android.content.Context;
import android.util.Log;

import com.example.androidpro.wangyinews.BaseApplication;
import com.example.jp.Channel;
import com.example.jp.ChannelDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 这个类是用与管理 所有的子频道的
 */
public class ChannelManager {

    private static Context mContext;
    //所有的频道条目
    private static List<Channel> allChannel;

    //所有可以显示的频道条目
    private static List<Channel> canShowChannel;

    //所有不可以显示的频道啊条目
    private static List<Channel> cannotShowChannel;


    static {
        allChannel = new ArrayList<>();
        canShowChannel = new ArrayList<>();
        cannotShowChannel = new ArrayList<>();

    }

    /**
     * 初始化所有的，可以显示或者尚未显示的频道信息,注意这个方法应该在BseAplication中调用
     */
    public static void initChannelItems(Context context){
        mContext = context;

        //初始化所有频道列表集合
        initAllChannel();
        //初始化所有可视化频道列表集合
        initCanShowChannel();
        //初始化所有尚未显示的频道信息
        initCannotShowChannel();
    }

    private static void initAllChannel() {
        /**
         * this.sortId = sortId;
         this.name = name;
         this.type = type;
         this.channel = channel;
         */
        //初始化所有频道信息
        //如果数据库表中没有数据，则初始化默认值
        ChannelDao channelDao = BaseApplication.getDaoSession(mContext).getChannelDao();
        Log.d("ChannelManager","channeldao :"+channelDao.count());
        if(channelDao.count() == 0) {
            //初始化所有的频道信息
            allChannel.add(new Channel(null, 1l, "头条", "headline", "T1348647909107", true));
            allChannel.add(new Channel(null, 2l, "NBA", "list", "T1348649145984", true));
            allChannel.add(new Channel(null, 3l, "财经", "list", "T1348648756099", true));
            allChannel.add(new Channel(null, 4l, "娱乐", "list", "T1348648517839", true));
            allChannel.add(new Channel(null, 5l, "科技", "list", "T1348649580692", true));
            allChannel.add(new Channel(null, 6l, "时尚", "list", "T1348650593803", false));
            allChannel.add(new Channel(null, 7l, "历史", "list", "T1368497029546", false));
            allChannel.add(new Channel(null, 8l, "家居", "list", "T1348654105308", false));
            //将所有的数据插入表中
            channelDao.insertInTx(allChannel);
        }else {
            //从数据库中获取
            allChannel = channelDao.loadAll();
            Collections.sort(allChannel, new Comparator<Channel>() {
                @Override
                public int compare(Channel lhs, Channel rhs) {
                    long i = lhs.getSortId()-rhs.getSortId();
                    return i<0?-1:(i==0?0:1);
                }
            });
        }
    }


    private static void initCanShowChannel() {
        for (Channel channel : allChannel){
            if(channel.getIsShow()){
                Log.d("ChannelManager", "initCanShowChannel: "+channel.getName());
                canShowChannel.add(channel);
            }
        }
    }

    private static void initCannotShowChannel() {
        for (Channel channel : allChannel){
            if (!channel.getIsShow()){
                cannotShowChannel.add(channel);
            }
        }
    }

    /**
     * 获取所有可以显示的频道列表
     */
    public static List<Channel> getCanShowChannels()
    {
        return canShowChannel;
    }

    /**
     * 获取所有可以显示的频道列表的中文名称
     * @return
     */
    public static List<String> getCanShowChannelNames(){
        List<String> names = new ArrayList<>();
        for(int i = 0 ; i < canShowChannel.size() ; i ++)
        {
            names.add(canShowChannel.get(i).getName());
        }
        return names;
    }

    //根据频道名称获取频道type字段
    public static String getChannelType(String s){
        String type = null;
        for (Channel channel : allChannel){
            if (s.equals(channel.getName()))
            {
                type = channel.getType();
                return type;
            }
        }
        return type;
    }

    //根据频道名称获取频道channel字段
    public static String getChannel(String s){
        String channel = null;
        for(int i = 0 ; i < allChannel.size() ; i ++){
            if(s.equals(allChannel.get(i).getName()))
            {
                channel = allChannel.get(i).getChannel();
                return channel;
            }
        }
        return channel;
    }

    /**
     * 获取所有尚未显示的频道列表
     */
    public static List<Channel> getCannotShowChannel(){

        return cannotShowChannel;

    }

    /**
     *  向可以显示的频道列表中添加，并且在尚未显示的频道列表中删除
     *  向尚未显示的频道列表中添加，并且在已经显示的列表中删除
     */
    public static void changeChannel( String name){
        for(int i = 0; i < allChannel.size() ; i++)
        {
            if(name.equals(allChannel.get(i).getName())){
                Channel change = allChannel.get(i);
                if(change.getIsShow()){
                    //从列表中删除 ，并在数据库中改变isShow状态,向未显示列表中添加
                    Channel changeChannel = canShowChannel.remove(i);
                    changeChannel.setIsShow(false);
                    changeChannel.setSortId(System.currentTimeMillis());
                    BaseApplication.getDaoSession(mContext).getChannelDao().update(changeChannel);
                    //向不能显示的列表中添加 Channel
                    cannotShowChannel.add(changeChannel);
                }else {
                    //从未显示列表中删除，往显示列表中添加，并且向数据库中添加
                    Channel changeChannel = cannotShowChannel.remove(i);
                    changeChannel.setIsShow(true);
                    //将当前修改的时间放入其中
                    changeChannel.setSortId(System.currentTimeMillis());
                    canShowChannel.add(changeChannel);
                    //更新channel的数据
                    BaseApplication.getDaoSession(mContext).getChannelDao().update(changeChannel);
                }
            }
        }

    }


}
