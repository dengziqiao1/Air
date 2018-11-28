package com.fce.air;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class AirActivity extends Activity {
    protected TextView open;
    protected TextView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {

    }


}
