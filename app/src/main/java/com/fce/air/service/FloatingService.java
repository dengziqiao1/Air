package com.fce.air.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 *
 *
 */
public class FloatingService extends Service {
    private WindowsPupow windowsPupow;

    @Override
    public void onCreate() {
        super.onCreate();
        windowsPupow = new WindowsPupow(this);

    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        windowsPupow.show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
