package com.fce.air;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.fce.air.service.FloatingService;

public class MainActivity extends Activity implements Cloneable {

    private FrameLayout rl;
    private RelativeLayout btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,FloatingService.class);
        startService(intent);

//        rl = findViewById(R.id.main_rl);
//        btn1 = findViewById(R.id.adb);
//        btn2 = findViewById(R.id.adb1);
//        btn1.setOnClickListener(this);
//        btn2.setOnClickListener(this);
    }

    private int y, heigt = 0;
    private boolean isDown, in; //可下拉

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_MOVE:
////                if (isDown)
//                    rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout
//                            .LayoutParams.MATCH_PARENT, (int) event.getY()));
//                break;
//            case MotionEvent.ACTION_DOWN:
//                y = (int) event.getY();
//                if (0 < y && y < 500) isDown = true;
//                break;
//            case MotionEvent.ACTION_UP:
//                if (event.getY() < 300) {
//                    rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout
//                            .LayoutParams.MATCH_PARENT, 0));
////                    finish();
////                    overridePendingTransition(0, 0);
//                } else {
//                    rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout
//                            .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//                }
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.adb:
//                Log.e("dzq", "下拉");
//                break;
//            case R.id.adb1:
//                Log.e("dzq", "右侧");
//                break;
//        }
//    }
}
