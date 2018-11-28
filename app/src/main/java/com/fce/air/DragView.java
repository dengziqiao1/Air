package com.fce.air;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 *
 *
 */
public class DragView extends RelativeLayout {
    float moveX;
    float moveY;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                moveX = event.getX();
                moveY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //setTranslationX(getX() + (event.getX() - moveX));
                Log.e("fce", "移动： " + getY());
                Log.e("fce", "移动111： " + getY() + (event.getY() - moveY));
                if ((getY() + (event.getY() - moveY))<= 0) {
                    setTranslationY(getY() + (event.getY() - moveY));
                }

                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }

        return true;
    }
}
