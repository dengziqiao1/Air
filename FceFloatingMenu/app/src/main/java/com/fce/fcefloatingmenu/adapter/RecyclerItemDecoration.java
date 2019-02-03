package com.fce.fcefloatingmenu.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * recyclerview 分隔线
 */

public class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private Drawable mDivider;
    private int mOrientation;
    private final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    private final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Paint mPaint;
    private int mDividerHeight;//分割线高度，默认为1px
    private boolean status;
    //我们通过获取系统属性中的listDivider来添加，在系统中的AppTheme中设置
    private static final int[] ATRRS = new int[]{android.R.attr.listDivider};

    public RecyclerItemDecoration(Context context, int orientation) {
        this.mContext = context;
        this.status = status;
        final TypedArray ta = context.obtainStyledAttributes(ATRRS);
        this.mDivider = ta.getDrawable(0);
        ta.recycle();
        setOrientation(orientation);
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation  方向
     * @param dividerColor 颜色
     * @param status       是否画最后一条线 true 画 false 不画
     */
    public RecyclerItemDecoration(Context context, int orientation, int dividerColor, boolean
            status) {
        this(context, orientation);
        mDividerHeight = 1;
        this.status = status;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(dividerColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    //设置屏幕的方向
    private void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == HORIZONTAL_LIST) {
            drawVerticalLine(c, parent, state);
        } else {
            drawHorizontalLine(c, parent, state);
        }
    }

    //画横线, 这里的parent其实是显示在屏幕显示的这部分
    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        if (!status) {
            childCount = parent.getChildCount() - 1;
        }
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            if (mPaint != null) {
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //画竖线
    private void drawVerticalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            if (mPaint != null) {
                c.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //由于Divider也有长宽高，每一个Item需要向下或者向右偏移
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State
            state) {
        if (mOrientation == HORIZONTAL_LIST) {
            //画横线，就是往下偏移一个分割线的高度
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        } else {
            //画竖线，就是往右偏移一个分割线的宽度
            outRect.set(0, 0, 0, 0);
        }
    }

}
