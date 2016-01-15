package com.example.androidpro.wangyinews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.RadioGroup;

import com.example.androidpro.wangyinews.fragments.MySelfFragment;
import com.example.androidpro.wangyinews.fragments.NewsFragment;
import com.example.androidpro.wangyinews.fragments.ReadFragment;
import com.example.androidpro.wangyinews.fragments.SeeAndHearFragment;
import com.example.androidpro.wangyinews.fragments.TopicFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private List<Fragment> fragments;

    private RadioGroup bottomtab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        bottomtab = (RadioGroup) findViewById(R.id.bottomtab);
        bottomtab.setOnCheckedChangeListener(this);


        //判断fragments是否为空，避免重复创建Fragment对象
        if(fragments == null ) {
            fragments = new ArrayList<>();
            //初始化5个类型的Fragment，将其添加在List中
            //新闻Fragment
            fragments.add(new NewsFragment());
            //阅读Fragment
            fragments.add(new ReadFragment());
            //视听Fragment
            fragments.add(new SeeAndHearFragment());
            //话题Fragment
            fragments.add(new TopicFragment());
            //我Fragmen
            fragments.add(new MySelfFragment());
        }

        FragmentManager manager = getSupportFragmentManager();
        //判断MainActivity中的FragmentManager是否为空，为空则添加fragment
        if (manager.getFragments() == null) {
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.container, fragments.get(0))
                    .add(R.id.container, fragments.get(1))
                    .add(R.id.container, fragments.get(2))
                    .add(R.id.container, fragments.get(3))
                    .add(R.id.container, fragments.get(4))
                    .show(fragments.get(0))
                    .hide(fragments.get(1))
                    .hide(fragments.get(2))
                    .hide(fragments.get(3))
                    .hide(fragments.get(4));
            transaction.commit();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (checkedId)
        {
            case R.id.rb_news:
                transaction.show(fragments.get(0))
                        .hide(fragments.get(1))
                        .hide(fragments.get(2))
                        .hide(fragments.get(3))
                        .hide(fragments.get(4));
                break;
            case R.id.rb_read:
                transaction.hide(fragments.get(0))
                        .show(fragments.get(1))
                        .hide(fragments.get(2))
                        .hide(fragments.get(3))
                        .hide(fragments.get(4));
                break;
            case R.id.rb_seeAndHear:
                transaction.hide(fragments.get(0))
                        .hide(fragments.get(1))
                        .show(fragments.get(2))
                        .hide(fragments.get(3))
                        .hide(fragments.get(4));
                break;
            case R.id.rb_topics:
                transaction.hide(fragments.get(0))
                        .hide(fragments.get(1))
                        .hide(fragments.get(2))
                        .show(fragments.get(3))
                        .hide(fragments.get(4));
                break;
            case R.id.rb_myself:
                transaction.hide(fragments.get(0))
                        .hide(fragments.get(1))
                        .hide(fragments.get(2))
                        .hide(fragments.get(3))
                        .show(fragments.get(4));
                break;
        }
        transaction.commit();
    }

}
