package com.example.androidpro.wangyinews.utils;

import com.example.androidpro.wangyinews.bean.ChannelManager;
import com.example.androidpro.wangyinews.bean.News;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by lupengcheng
 * 2016/1/11  12:08
 * Email: 18855586117@163.com
 */
public class MyUtils {

    public static final String WEBBASEURL = "http://c.3g.163.com/nc/article/";
    public static final String BASEURL = "http://c.3g.163.com";

    //http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html
    public interface Service{
        @GET("nc/article/{type}/{channel}/{range}")
        Call<News> getNewsContent(@Path("type") String type, @Path("channel") String channel, @Path("range")String range);
    }

    private static Service service;

    static {
        service = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Service.class);
    }

    public static Service getService() {
        return service;
    }


    public static String getType(String type)
    {
        return ChannelManager.getChannelType(type);
    }

    public static String getChannel(String channel)
    {
        return ChannelManager.getChannel(channel);
    }

    //获取web的url
    public static String getWebUrl(String docid)
    {
        //http://c.3g.163.com/nc/article/BD70S60Q00234L7P/full.html
        return WEBBASEURL+docid+"/full.html";
    }

    public static Call<News> getNews(int id,String type)
    {
        String range = String.valueOf(id)+"-20.html";
        String myType = getType(type);
        String channel = getChannel(type);
        if(myType == null || channel == null)
        {
            throw new NullPointerException("type或者channel为空");
        }
        return getService().getNewsContent(myType,channel,range);
    }

}
