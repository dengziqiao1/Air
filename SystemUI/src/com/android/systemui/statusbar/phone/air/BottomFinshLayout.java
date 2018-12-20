package com.android.systemui.statusbar.phone.air;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * 向上滑动销毁
 */
public class BottomFinshLayout extends RelativeLayout {
    /**
     * BottomFinishLayout布局的父布局
     */
    private ViewGroup mParentView;
    /**
     * 滑动的最小距离
     */
    private int mTouchSlop;
    /**
     * 按下点的X坐标
     */
    private int downX;
    /**
     * 按下点的Y坐标
     */
    private int downY;
    /**
     * 临时存储X坐标
     */
    private int tempY;
    /**
     * 滑动类
     */
    private Scroller mScroller;
    /**
     * BottomFinishLayout的宽度
     */
    private int viewHeight;

    private boolean isSilding;

    private OnFinishListener onFinishListener;
    private boolean isFinish;

    public BottomFinshLayout(Context context) {
        super(context);
    }

    public BottomFinshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mScroller = new Scroller(context);
    }

    public BottomFinshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    /**
     * 事件拦截操作
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        downX = (int) ev.getRawX();
        downY = tempY = (int) ev.getRawY();
        if (downX < 180 || downX > 1600) {
            return false;
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) ev.getRawY();
                if (Math.abs(downY - moveY) > mTouchSlop && Math.abs((int) ev.getRawX() - downX)
                        < mTouchSlop) {
                    return true;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int moveY = (int) event.getRawY();// 触摸点相对于屏幕的位置
                int deltaY = moveY - tempY;
                tempY = moveY;
                if (Math.abs(downY - moveY) > mTouchSlop && Math.abs((int) event.getRawX() -
                        downX) < mTouchSlop) {
                    isSilding = true;
                }

                if (downY - moveY >= 0 && isSilding) {
                    mParentView.scrollBy(0, -deltaY);
                }
                break;
            case MotionEvent.ACTION_UP:
                isSilding = false;
                if (mParentView.getScrollY() >= viewHeight / 3) {
                    isFinish = true;
                    scrollTop();
                } else {
                    scrollOrigin();
                    isFinish = false;
                }
                break;
        }
        downX = (int) event.getRawX();
        if (downX >= 180 && downX <= 1600) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            mParentView = (ViewGroup) this.getParent();
            viewHeight = this.getHeight();
        }
    }

    /***
     * 接口回调
     */
    public void setOnFinishListener(OnFinishListener onSildingFinishListener) {
        this.onFinishListener = onSildingFinishListener;
    }

    /**
     * 滚动出界面
     */
    private void scrollTop() {
        final int delta = (viewHeight - mParentView.getScrollY());
        mScroller.startScroll(0, mParentView.getScrollY(), 0, delta - 1, Math.abs(delta));
        postInvalidate();
    }

    /**
     * 滚动到起始位置
     */
    private void scrollOrigin() {
        int delta = mParentView.getScrollY();
        mScroller.startScroll(0, mParentView.getScrollY(), 0, -delta, Math.abs(delta));
        postInvalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            mParentView.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
			 if (mScroller.getCurrY() > 639 && isFinish) {
                mScroller.forceFinished(true);
            }
            if (mScroller.isFinished() && isFinish) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish();
                } else {
                    scrollOrigin();
                    isFinish = false;
                }
            }
        }
    }

    public interface OnFinishListener {
        void onFinish();
    }
}
