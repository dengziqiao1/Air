package com.android.systemui.statusbar.phone.air;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * systemui
 * 设置标题
 * 服务
 */
public class SystemUITitleService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    private SystemUITitleController.Stub stub = new SystemUITitleController.Stub() {
        @Override
        public void setTitle(SystemUITitle title) {

        }
    };
}
