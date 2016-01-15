package com.example.androidpro.wangyinews;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.example.androidpro.wangyinews.bean.ChannelManager;
import com.example.jp.DaoMaster;
import com.example.jp.DaoSession;
import com.facebook.drawee.backends.pipeline.Fresco;
/**
 * Created by lupengcheng
 * 2016/1/10  20:21
 * Email: 18855586117@163.com
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Fresco.initialize(this);
        ActiveAndroid.initialize(this);
        //初始化频道数据
        ChannelManager.initChannelItems(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
        //整体摧毁的时候调用这个方法
    }

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    //创建的数据库名称
    public static final String DB_NAME = "wangyinews_db";
    public static DaoMaster getDaoMaster(Context context)
    {
        if (daoMaster == null)
        {
            //创建数据库。
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }
    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context)
    {
        if (daoSession == null)
        {
            if (daoMaster == null)
            {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
