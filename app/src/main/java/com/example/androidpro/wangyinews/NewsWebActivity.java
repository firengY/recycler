package com.example.androidpro.wangyinews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidpro.wangyinews.utils.MyUtils;

public class NewsWebActivity extends AppCompatActivity {

    private WebView webnews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);
        String weburl = getIntent().getStringExtra(MyUtils.WEBBASEURL);
        webnews = (WebView) findViewById(R.id.news_newswebactivity);



        WebSettings webSettings = webnews.getSettings();
        //webSettings.setJavaScriptEnabled(true);
        //直接调用loadurl会打开系统自带的浏览器需要使用webviewclient设置
        
        webnews.loadUrl(weburl);
        webnews.setWebViewClient(new WebViewClient());
    }



}
