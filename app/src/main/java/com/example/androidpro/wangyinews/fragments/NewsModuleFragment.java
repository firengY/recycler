package com.example.androidpro.wangyinews.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.androidpro.wangyinews.NewsWebActivity;
import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.adapters.NewsAdapter;
import com.example.androidpro.wangyinews.bean.News;
import com.example.androidpro.wangyinews.utils.MyUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsModuleFragment extends Fragment implements Callback<News>,PullToRefreshBase.OnRefreshListener2, AdapterView.OnItemClickListener {
    private static int fromIndex = 0;
    private View mFragmentView;
   // private ListView recycler;
    private PullToRefreshListView recycler;
    public static final String TYPE = "type";
    private NewsAdapter adapter;
    public NewsModuleFragment() {
        // Required empty public constructor
    }

     public static NewsModuleFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString(TYPE,title);
        NewsModuleFragment fragment = new NewsModuleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //获取类型
    public String getType()
    {
        return (String) getArguments().get(TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if( null == mFragmentView)
        {
            mFragmentView = inflater.inflate(R.layout.fragment_news_module, container, false);
            recycler = (PullToRefreshListView) mFragmentView.findViewById(R.id.recycler_newsmodule_newsmodulefragment);
            adapter = new NewsAdapter(getContext());
            recycler.setAdapter(adapter);
            recycler.setMode(PullToRefreshBase.Mode.BOTH);
            //设置上下拉刷新
            recycler.setOnRefreshListener(this);
            //上拉加载提示设置
            recycler.setPullLabel(getString(R.string.pull_to_refresh));
            recycler.setRefreshingLabel(getString(R.string.pull_to_isrefreshing));
            recycler.setReleaseLabel(getString(R.string.pull_to_refreshed));
            recycler.setOnItemClickListener(this);

            //T1348648756099
            //http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html
            MyUtils.getNews(fromIndex,getType()).enqueue(this);
        }
        return mFragmentView;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if( null != this)
        {
            //getParent():Gets the parent of this view.
            // Note that the parent is a ViewParent and not necessarily a View.

            //removeView():do not invoke this method from draw(android.graphics.Canvas),
            // onDraw(android.graphics.Canvas), dispatchDraw(android.graphics.Canvas) or any
            // related method.
            ((ViewGroup) mFragmentView.getParent()).removeView(mFragmentView);
        }
    }

    @Override
    public void onResponse(Response<News> response, Retrofit retrofit) {
        if(response.body() != null) {
            //判断是刷新获取数据还是加载更多数据
            if(fromIndex == 0) {
                adapter.refresh(response.body().getBaseEntity());

            }else {
                adapter.addAll(response.body().getBaseEntity());
            }
            recycler.onRefreshComplete();
        }
        else {
            Toast.makeText(getContext(),"网络数据传输异常，请刷新重试...",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        t.printStackTrace();
        Toast.makeText(getContext(),"网络错误",Toast.LENGTH_SHORT).show();
        recycler.onRefreshComplete();
    }

    //下拉刷新
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        fromIndex = 0;
        MyUtils.getNews(fromIndex,getType()).enqueue(this);
    }

    //上拉加载
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        fromIndex += 20;
        MyUtils.getNews(fromIndex,getType()).enqueue(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //点击一个条目获取当前的实体类对象
        String docid =  ((News.BaseEntity)adapter.getItem(position-1)).getDocid();
        if(docid != null)
        {
            Intent intent = new Intent(getContext(),NewsWebActivity.class);
            intent.putExtra(MyUtils.WEBBASEURL,MyUtils.getWebUrl(docid));
            getContext().startActivity(intent);
        }
    }
}
