package com.example.androidpro.wangyinews.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidpro.wangyinews.R;
import com.example.androidpro.wangyinews.adapters.NewsFragmentPagerAdapter;
import com.example.androidpro.wangyinews.bean.ChannelManager;
import com.example.androidpro.wangyinews.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class NewsFragment extends Fragment{
    private ViewPager pager;
    private TabLayout tabs;
    private NewsFragmentPagerAdapter adapter;
    public NewsFragment(){}
    private List<Fragment> fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragments = new ArrayList<>();
        //1 14号 22:50 修改的代码
        List<String> names = ChannelManager.getCanShowChannelNames();
        for (String name : names) {
            fragments.add(NewsModuleFragment.newInstance(name));
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = (ViewPager) view.findViewById(R.id.viewpager_newsmodul_newsfragment);
        tabs = (TabLayout) view.findViewById(R.id.tablayout_newsmodul_newsfragment);
        FragmentManager fragmentManager = getChildFragmentManager();
        adapter = new NewsFragmentPagerAdapter(fragmentManager,fragments);
        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);
    }

}
