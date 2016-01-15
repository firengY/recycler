package com.example.androidpro.wangyinews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.androidpro.wangyinews.adapters.TopicExpertAdapter;
import com.example.androidpro.wangyinews.bean.TopicExpertEntity;
import com.example.androidpro.wangyinews.utils.HttpUtils;
import com.example.androidpro.wangyinews.utils.Tool;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class TopicExpertActivity extends AppCompatActivity implements Callback<TopicExpertEntity> {

    private static final String TAG = "TopicExpertActivity";

    private Toolbar toolbar;
    private RecyclerView recycler;
    private TopicExpertAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_expert);

        initView();

        String expertId = null;
        Intent result = getIntent();
        if (result != null) {
            expertId = result.getStringExtra("expertId") + ".html";
            //Tool.showToast(this,expertId, Toast.LENGTH_SHORT);
        }

        adapter = new TopicExpertAdapter(this);
        recycler.setAdapter(adapter);

        HttpUtils.getTopicExpertService().getExpertDetails(expertId).enqueue(this);
    }

    private void initView() {
        toolbar = ((Toolbar) findViewById(R.id.toolbar_topic_expert_activity));
        recycler = (RecyclerView) findViewById(R.id.recycler_topic_expert_activity);

        setSupportActionBar(toolbar);
    }

    @Override
    public void onResponse(Response<TopicExpertEntity> response, Retrofit retrofit) {
        TopicExpertEntity.DataEntity data = response.body().getData();

        Log.d(TAG, "onResponse: Raw "+response.raw());
        Log.d(TAG, "onResponse: Body "+response.body());
        Log.d(TAG, "onResponse: data "+data);

        adapter.addAll(data,"expert");
        adapter.addAll(data,"hot");
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();

        Tool.showToast(this,"网络错误", Toast.LENGTH_SHORT);
    }
}
