package com.fce.fcefloatingmenu;

import android.app.Activity;
import android.os.Bundle;

public class Test extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.floating_to_drawmenu);
        LogUtils.e("dzq","跳转了");
    }
}
