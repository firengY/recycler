package com.example.androidpro.wangyinews.bean;

/**
 * Created by lupengcheng
 * 2016/1/13  22:54
 * Email: 18855586117@163.com
 */
//这个类用于管理所有的Channel信息
public class ChannelItem {

    //存储频道的中文信息
    private String name;
    //存储接口类型的字段
    private String type;
    //http://c.3g.163.com/nc/article/headline/T1348647909107/0-20.html
    //存储接口中 类似 T1348647909107 的数据
    private String channel;

    public ChannelItem(String name, String type, String channel) {
        this.name = name;
        this.type = type;
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
