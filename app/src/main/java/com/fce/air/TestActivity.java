package com.fce.air;

import android.app.Activity;
import android.car.Car;
import android.car.CarNotConnectedException;
import android.car.media.CarAudioManager;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class TestActivity extends Activity {
    private Car mCar;
    private CarAudioManager mCarAudioManager;
    private int mediaGroupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCar = Car.createCar(this, mConnection);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    private final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                mCarAudioManager = (CarAudioManager) mCar.getCarManager(Car.AUDIO_SERVICE);
//                mediaGroupId = mCarAudioManager.getVolumeGroupIdForUsage(AudioAttributes
//                        .USAGE_MEDIA);
                Log.e("dzq", "ServiceConnection:  " + mCarAudioManager);
                mCarAudioManager.registerVolumeChangeObserver(new ContentObserver(new Handler()) {
                    @Override
                    public boolean deliverSelfNotifications() {
                        Log.e("dzq", "deliverSelfNotifications：  ");
                        return super.deliverSelfNotifications();
                    }

                    @Override
                    public void onChange(boolean selfChange) {
                        Log.e("dzq", "onChange：  " + selfChange);
                        super.onChange(selfChange);
                    }

                    @Override
                    public void onChange(boolean selfChange, Uri uri) {
                        Log.e("dzq", "onChange：23  " + uri.getAuthority());
                        super.onChange(selfChange, uri);
                    }
                });

            } catch (CarNotConnectedException e) {
                Log.e("dzq", "连接异常:  " + e.getMessage());
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("dzq", "连接断开:  " + name);
        }
    };
}
