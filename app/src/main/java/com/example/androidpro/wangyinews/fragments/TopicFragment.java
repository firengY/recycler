package com.example.androidpro.wangyinews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.TopicExpertActivity;
import com.example.androidpro.wangyinews.adapters.TopicAdapter;
import com.example.androidpro.wangyinews.bean.TopicEntity;
import com.example.androidpro.wangyinews.utils.HttpUtils;
import com.example.androidpro.wangyinews.utils.Tool;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopicFragment extends Fragment implements Callback<TopicEntity>, TopicAdapter.OnRecyclerViewItemClickListener, PtrHandler {
    private static final String TAG = "TopicFragment";

    public static final String TYPE = "expert";
    public static final String RANGE = "0-10.html";
    private static int urlStart = 0;
    private static final int urlCount = 10;
    private RecyclerView listTopic;
    private TopicAdapter topicAdapter;
    private PtrClassicFrameLayout ptrTopic;
    private TopicFragment callback;

    //默认不刷新
    private static boolean isRefresh = false;

    //默认没到最后一项
    private static boolean isLast = false;

    public TopicFragment() {
        // Required empty public constructor
    }

    //用urlStart和urlCount拼range的字符串
    public String getRange(int start, int count) {
        return start + "-" + count + ".html";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ptrTopic = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_frame_topicfragment);
        listTopic = (RecyclerView) view.findViewById(R.id.list_topicfragment);


        //设置ptrFramLayout
        // the following are default settings
        //阻尼系数
        ptrTopic.setResistance(1.7f);
        //触发刷新时移动的位置比例
        ptrTopic.setRatioOfHeaderHeightToRefresh(1.2f);
        //回弹延时
        ptrTopic.setDurationToClose(200);
        //头部回弹时间
        ptrTopic.setDurationToCloseHeader(1000);
        //下拉刷新/释放刷新 default is false
        ptrTopic.setPullToRefresh(false);

        //// 刷新时保持头部  default is true
        ptrTopic.setKeepHeaderWhenRefresh(true);
        //ptrTopic.setLastUpdateTimeRelateObject(this);

        //进入界面自动刷新
        ptrTopic.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrTopic.autoRefresh();
            }
        }, 100);

        //Ultra-pull-to-refresh 的刷新事件
        ptrTopic.setPtrHandler(this);


        topicAdapter = new TopicAdapter(getContext());
        listTopic.setAdapter(topicAdapter);

        callback = this;

        //item点击事件
        topicAdapter.setmOnItemClickListener(this);

        //上拉加载处理
        listTopic.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                //当不滚动的时候
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    //获取最后一个完整显示的Item position
                    int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    int totalItemCount = layoutManager.getItemCount();

                    //判断是否滚动到底部
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast) {
                        //TODO：加载网络数据
                        urlStart = urlStart + urlCount;

                        HttpUtils.getTopicService()
                                .getExperts(getRange(urlStart, urlCount))
                                .enqueue(callback);

                        //表示已经滑到底部
                        isLast = true;
                        if (isLast) {
                            Tool.showToast(getContext(), "加载更多", Toast.LENGTH_SHORT);
                            isLast = false;
                        }


                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    isSlidingToLast = true;
                } else {
                    isSlidingToLast = false;
                }
            }
        });
    }

    @Override
    public void onResponse(Response<TopicEntity> response, Retrofit retrofit) {

        List<TopicEntity.DataEntity.ExpertListEntity> expertList = response.body().getData().getExpertList();
        Log.d(TAG, "onResponse: response" + response.raw());
        Log.d(TAG, "onResponse: response" + expertList);

        //Toast.makeText(getContext(), "urlStart = " + urlStart, Toast.LENGTH_SHORT).show();

        //如果刷新，清空数据库
        if (isRefresh) {
            new Delete().from(TopicEntity.DataEntity.ExpertListEntity.class)
                    .where(null)
                    .execute();
            isRefresh = false;
        }

        //如果网络请求无数据，显示全部加载完毕
        if (expertList.size() == 0) {
            Tool.showToast(getContext(), "加载完了，还拉个屁啊", Toast.LENGTH_SHORT);
        }


        ActiveAndroid.beginTransaction();
        try {
            for (int i = 0; i < expertList.size(); i++) {
                //Toast.makeText(getContext(), "第"+i+"条数据", Toast.LENGTH_SHORT).show();
                TopicEntity.DataEntity.ExpertListEntity expertModel = new TopicEntity.DataEntity.ExpertListEntity();

                TopicEntity.DataEntity.ExpertListEntity expert = expertList.get(i);

                expertModel.expertId = expert.getExpertId();
                expertModel.name = expert.getName();
                expertModel.picurl = expert.getPicurl();
                expertModel.headpicurl = expert.getHeadpicurl();
                expertModel.alias = expert.getAlias();
                expertModel.concernCount = expert.getConcernCount();
                expertModel.classification = expert.getClassification();

                expertModel.save();
            }
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
        ptrTopic.refreshComplete();
        topicAdapter.addAll(expertList);

        for (TopicEntity.DataEntity.ExpertListEntity item : expertList) {

            Log.d(TAG, "expertList数据" + item.getName());
        }
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Log.d(TAG, "onFailure: " + t.toString());
        Tool.showToast(getContext(), "网络错误", Toast.LENGTH_SHORT);
        ptrTopic.refreshComplete();
    }

    @Override
    public void onItemClick(View view, TopicEntity.DataEntity.ExpertListEntity itemData) {
        //Toast.makeText(getContext(), "itemData" + itemData.getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), TopicExpertActivity.class);

        //传递expertId,用来拼expert详情页的url
        intent.putExtra("expertId",itemData.getExpertId());

        startActivity(intent);
    }

    //刷新事件处理
    @Override
    public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
        return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
    }

    @Override
    public void onRefreshBegin(PtrFrameLayout frame) {
        if (Tool.isNetworkConnected(getContext())) {
            topicAdapter.clearAll();
        }
        isRefresh = true;
        urlStart = 0;
        HttpUtils.getTopicService()
                .getExperts(getRange(urlStart, urlCount))
                .enqueue(this);
    }
}
