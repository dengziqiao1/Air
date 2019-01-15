package com.fce.air.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.fce.air.R;


/**
 *
 *
 */
public class WindowsPupow extends RelativeLayout implements View.OnClickListener, View
        .OnTouchListener {
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private FrameLayout rl;
    private FrameLayout view, view1;

    public WindowsPupow(Context context) {
        super(context);
        Log.e("dzq", "WindowsPupow");
        mView = LayoutInflater.from(context).inflate(R.layout.activity_popu, null);
        view = mView.findViewById(R.id.adb);
        view1 = mView.findViewById(R.id.adb1);
        rl = mView.findViewById(R.id.main_rl);
        view.setOnClickListener(this);
        view1.setOnClickListener(this);
        view.setOnTouchListener(this);
        view1.setOnTouchListener(this);
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 0;
        mParams.y = 0;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags = WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager
                .LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SCALED |
                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams
                .FLAG_LAYOUT_IN_SCREEN;
        mParams.width = 1920;
        mParams.height = 60;
        mWindowManager.addView(mView, mParams);

        Log.e("dzq", "show() ");
    }

    public void hide() {
        mWindowManager.removeView(mView);
    }

    public void upLayout(View view, ViewGroup.LayoutParams params) {
        mWindowManager.updateViewLayout(view, params);
    }

    private int y, heigt = 0;
    private boolean isDown, in; //可下拉


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.adb:
                Log.e("dzq", "下拉");
                break;
            case R.id.adb1:
                Log.e("dzq", "点击");
                break;
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                // if (isDown) Log.e("dzq", "ACTION_MOVE");
                Log.e("dzq", "ACTION_MOVE");
                mParams.width = 1920;
                mParams.height = (int) event.getY();
                upLayout(mView, mParams);
                rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams
                        .MATCH_PARENT, (int) event.getY()));
                break;
            case MotionEvent.ACTION_DOWN:
                Log.e("dzq", "ACTION_DOWN");
                y = (int) event.getY();
                if (0 < y && y < 500) isDown = true;

                break;
            case MotionEvent.ACTION_UP:
                Log.e("dzq", "ACTION_UP");
                if (event.getY() < 300) {
                    rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout
                            .LayoutParams.MATCH_PARENT, 0));
                } else {
                    mParams.width = 1920;
                    mParams.height = 720;
                    upLayout(mView, mParams);
                    rl.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout
                            .LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
                }
                break;
        }
        return super.onTouchEvent(event);
    }
}
