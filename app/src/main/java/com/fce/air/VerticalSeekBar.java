package com.fce.air;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 垂直进度条
 */
public class VerticalSeekBar extends AppCompatSeekBar {
    public VerticalSeekBar(Context context) {
        super(context);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerticalSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        //将SeekBar转转90度
        canvas.rotate(-90);
        //将旋转后的视图移动回来
        canvas.translate(-getHeight(), 0);
        super.onDraw(canvas);
    }

    float downX, downY = 600;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
//                Log.e("dzq", "状态：" + event.getY() + "   " + downY + "   " + Math.abs(event.getY()
//                        - downY) + "     " + getProgress());
//                if (!getStatus(event)) {
//                int num = getProgress() < 60 ? 10 : 40;
//                if (Math.abs(event.getY() - downY) > 60) {
                int i = getMax() - (int) (getMax() * event.getY() / getHeight());
                //设置进度
                setProgress(i);
                downY = event.getY();
                //每次拖动SeekBar都会调用
                onSizeChanged(getWidth(), getHeight(), 0, 0);
//                }
                break;
            case MotionEvent.ACTION_UP:
//                Log.e("dzq", "抬起：" + event.getY() + "   " + downY + "   " + Math.abs(event
// .getY()
////                        - downY) + "     " + getProgress());
////                int num1 = getProgress() < 30 ? 10 : 40;
////                if (getProgress() < 10) {
////
////                }
//                if (Math.abs(event.getY() - downY) > 60) {
//                    int i = getMax() - (int) (getMax() * event.getY() / getHeight());
//                    //设置进度
//                    setProgress(i);
//                    //每次拖动SeekBar都会调用
//                    downY = event.getY();
//                    onSizeChanged(getWidth(), getHeight(), 0, 0);
//                }
                break;

        }
        downX = (int) event.getRawX();
        if (downX < 180 || downX > 1600) {
            return true;
        } else {
            return false;
        }
    }

//    private boolean getStatus(MotionEvent event) {
//        Log.e("dzq", "进度： " + getProgress());
//        int progress = getProgress();
//        Log.e("dzq", "距离： " + ((getHeight() - progress * getHeight() / getMax())) + "    " +
//                event.getY());
//        double f = Math.abs(((getHeight() - progress * getHeight() / getMax())) - event.getY());
//        double num = progress > 235 || progress < 90 ? 40.0 : 61.0;
//        return f < num ? true : false;
//    }
}
