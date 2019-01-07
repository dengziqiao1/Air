package com.fce.air;

import android.app.Activity;
import android.car.Car;
import android.car.media.CarAudioManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

public class TestActivity extends Activity {
    private CarAudioManager mCarAudioManager;
    private Car mCar;
       private SeekBar seekBar;
       private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.car_volume_dialog);
        seekBar = this.findViewById(R.id.car_seekbar);
        imageView.setBackgroundResource(R.drawable.air_ac);
        seekBar.setMax(40);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.e("dzq","进度： "+progress);
                
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

}
