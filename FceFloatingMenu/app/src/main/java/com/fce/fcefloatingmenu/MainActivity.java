package com.fce.fcefloatingmenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.fce.fcefloatingmenu.floating.FloatingToDownView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new FloatingToDownView(this);
    }

}


