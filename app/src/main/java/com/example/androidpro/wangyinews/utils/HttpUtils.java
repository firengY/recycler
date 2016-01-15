package com.example.androidpro.wangyinews.utils;


import com.example.androidpro.wangyinews.bean.TopicEntity;
import com.example.androidpro.wangyinews.bean.TopicExpertEntity;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by firengy
 * on 16-1-11.
 * Email: 18811372352@163.com
 */
public class HttpUtils {
    public static final String BASEURL = "http://c.3g.163.com";
    //话题页接口
    public interface TopicService {
        //http://c.3g.163.com/newstopic/list/expert/0-10.html
        @GET("newstopic/list/expert/{range}")
        Call<TopicEntity> getExperts(@Path("range") String range);
    }

    //话题expert详情页接口
    public interface TopicExpertService{
        //http://c.3g.163.com/newstopic/qa/EX02775130046412117148.html
        @GET("newstopic/qa/{id}")
        Call<TopicExpertEntity> getExpertDetails(@Path("id") String id);
    }

    private static TopicService topicService;

    private static TopicExpertService topicExpertService;

    static {
        topicService = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TopicService.class);

        topicExpertService = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TopicExpertService.class);
    }

    public static TopicService getTopicService() {
        return topicService;
    }

    public static TopicExpertService getTopicExpertService() {
        return topicExpertService;
    }
}

