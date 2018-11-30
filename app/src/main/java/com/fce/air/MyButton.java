package com.fce.air;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.nineoldandroids.view.ViewHelper;

/**
 *
 *
 */
public class MyButton extends Button {
    private int lastX;
    private int lastY;
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取view相对于手机屏幕的xy值
        int x=(int) event.getRawX();
        int y=(int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX=x-lastX;
                int deltaY=y-lastY;
                int translationX = (int) (ViewHelper.getTranslationX(this) + 50);
                int translationY = (int) (ViewHelper.getTranslationY(this) + deltaY);
//                ViewHelper.setTranslationX(this,translationX);
                ViewHelper.setTranslationY(this,translationY);

                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
