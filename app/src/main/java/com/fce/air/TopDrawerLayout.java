package com.fce.air;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 *
 */
public class TopDrawerLayout extends ViewGroup {
    private ViewDragHelper dragHelper;
    private View mDragbar, mContentView;
    private int dragRange;
    private OnOpenListener mOnOpenListener;
    private OnCloseListener mOnCloseListener;

    private boolean isOpen;//是否打开着

    public TopDrawerLayout(Context context) {
        super(context);
        init();
    }

    public TopDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TopDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TopDrawerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        dragHelper = ViewDragHelper.create(this, mCallback);
    }


    @Override
    protected void onLayout(boolean b, int l, int t, int r, int bo) {

        mDragbar.layout(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(),
                mDragbar.getMeasuredHeight() + getPaddingTop());

        mContentView.layout(getPaddingLeft(), getPaddingTop() - mContentView.getMeasuredHeight(),
                getWidth() - getPaddingRight(), getPaddingTop());

    }


    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            dragHelper.cancel();
            return false;
        }
        return dragHelper.shouldInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        dragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return child == mDragbar;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            Log.e("dzq","高度： "+(top - mContentView.getHeight()));
            // top为mDragbar的top值
            mContentView.layout(getPaddingLeft(), top - mContentView.getHeight(), getWidth() -
                    getPaddingRight(), top);

        }


        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            int topBound = getPaddingTop();//顶端边界
            int bottomBound = getHeight() - mDragbar.getHeight() - getPaddingBottom();//底端边界
            return Math.min(Math.max(topBound, top), bottomBound);
        }


        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            // 禁止水平滑动
            return getPaddingLeft();
        }


        @Override
        public int getViewVerticalDragRange(View child) {
            dragRange = mContentView.getMeasuredHeight();
            return dragRange;
        }


        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {

            if (mContentView.getBottom() - getPaddingTop() > mContentView.getHeight() / 2) {
                smoothToBottom();
            } else if (mContentView.getBottom() - getPaddingTop() <= mContentView.getHeight() / 2) {
                smoothToTop();
            }
            invalidate();
        }
    };


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mDragbar = getChildAt(0);
        mContentView = getChildAt(1);


        measureChild(mDragbar, widthMeasureSpec, heightMeasureSpec);

        int dragBarHeight = mDragbar.getMeasuredHeight();


        measureChild(mContentView, widthMeasureSpec, heightMeasureSpec);

        int contentHeight = mContentView.getMeasuredHeight();


        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), dragBarHeight + contentHeight
                + getPaddingBottom() + getPaddingTop());


    }


    private void smoothToTop() {
        if (dragHelper.smoothSlideViewTo(mDragbar, getPaddingLeft(), getPaddingTop())) {
            ViewCompat.postInvalidateOnAnimation(this);
            isOpen = false;
            if (mOnCloseListener != null) mOnCloseListener.close();
        }
    }

    private void smoothToBottom() {
        if (dragHelper.smoothSlideViewTo(mDragbar, getPaddingLeft(), getHeight() -
                getPaddingBottom() - mDragbar.getHeight())) {
            ViewCompat.postInvalidateOnAnimation(this);
            isOpen = true;
            if (mOnOpenListener != null) mOnOpenListener.open();
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void openContent() {
        if (!isOpen) smoothToBottom();
    }

    public void closeContent() {
        if (isOpen) smoothToTop();
    }

    public interface OnOpenListener {
        void open();
    }

    public interface OnCloseListener {
        void close();
    }


    public void setOnOpenListener(OnOpenListener mOnOpenListener) {
        this.mOnOpenListener = mOnOpenListener;
    }

    public void setOnCloseListener(OnCloseListener mOnCloseListener) {
        this.mOnCloseListener = mOnCloseListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() != 2) {
            throw new IllegalStateException("Just contain two Views/ViewGroups ");
        }
    }
}
