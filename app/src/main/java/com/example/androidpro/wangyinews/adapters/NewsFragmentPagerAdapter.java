package com.example.androidpro.wangyinews.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.androidpro.wangyinews.fragments.NewsModuleFragment;

import java.util.Collection;
import java.util.List;

/**
 * Created by lupengcheng
 * 2016/1/11  12:37
 * Email: 18855586117@163.com
 */
public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public NewsFragmentPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public void addAll(Collection<? extends Fragment> collection)
    {
        fragments.addAll(collection);
        notifyDataSetChanged();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return (CharSequence) fragments.get(position).getArguments().get(NewsModuleFragment.TYPE);
    }
}
