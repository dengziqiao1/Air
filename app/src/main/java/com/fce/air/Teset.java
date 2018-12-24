package com.fce.air;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * com.autonavi.amapauto  地图
 * net.easyconn  连接
 * com.fce.media 音乐
 * com.fce.aw360 360
 * com.fce.dvr   dvr
 * com.fce.fcephoneui 电话
 * com.fce.radio 收音机
 * com.fce.imagebrowser 图片
 * com.fce.videoplayer 视频
 * com.android.documentsui 文件管理器
 * com.fce.ifly  讯飞
 * com.android.settings 设置
 * com.fce.system.settings  设置
 * com.softwinner.usbroleswitch USB
 * com.iflytek.cutefly.speechclient 语音
 */
public class Teset extends Service {


    String titleContent;
    private Context mContext;
    String pkg = "";
    @Override
    public void onCreate() {
        super.onCreate();
      
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
