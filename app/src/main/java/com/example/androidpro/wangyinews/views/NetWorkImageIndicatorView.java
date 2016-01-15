package com.example.androidpro.wangyinews.views;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;

import com.facebook.drawee.view.SimpleDraweeView;
import com.panxw.android.imageindicator.ImageIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lupengcheng
 * 2016/1/12  16:18
 * Email: 18855586117@163.com
 */
public class NetWorkImageIndicatorView extends ImageIndicatorView {

    List<String> list = new ArrayList<>();
    public NetWorkImageIndicatorView(Context context) {
        super(context);
    }

    public NetWorkImageIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrlByList(List<String> list)
    {
        this.list = list;
        for (String url : list) {
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getContext());
            simpleDraweeView.setImageURI(Uri.parse(url));
            addViewItem(simpleDraweeView);
        }
    }

    public boolean hasData()
    {
        return !list.isEmpty();
    }
}
