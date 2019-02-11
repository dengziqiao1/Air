package com.fce.fcefloatingmenu.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.fce.fcefloatingmenu.R;

/**
 * 悬浮窗
 */
public class FloatingManager {
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams toDrawParams;
    private final int FLOATING_DRAW_HIDE_HIGHT = 50;     //初始高度

    FloatingManager(Context context) {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 下拉
     * 悬浮窗
     */
    private void topDrawShow(View mView) {
        toDrawParams = new WindowManager.LayoutParams();
        toDrawParams.gravity = Gravity.TOP | Gravity.START;
        toDrawParams.x = 0;
        toDrawParams.y = 0;
        //总是出现在应用程序窗口之上
        toDrawParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        toDrawParams.format = PixelFormat.RGBA_8888;
        toDrawParams.flags =
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        toDrawParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        toDrawParams.height = FLOATING_DRAW_HIDE_HIGHT;
        FrameLayout rightTopLayout = mView.findViewById(R.id.floating_top_right_menu);
        //状态栏 右侧
        FrameLayout.LayoutParams rightParams =
                (FrameLayout.LayoutParams) rightTopLayout.getLayoutParams();
        rightParams.height = 50;
        rightTopLayout.setLayoutParams(rightParams);
        addLayOut(mView, toDrawParams);
    }

    /**
     * 用户 语音
     * 悬浮窗显示
     */
    private void userShow(View mView) {
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 1800;
        mParams.y = 630;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.width = 180;
        mParams.height = 90;
        addLayOut(mView, mParams);
    }

    /**
     * 添加悬浮窗
     * * @param mView
     * * @param params
     */
    private void addLayOut(View mView, ViewGroup.LayoutParams params) {
        mWindowManager.addView(mView, params);
    }

    /**
     * 移除悬浮窗
     */
    public void hide(View mView) {
        mWindowManager.removeViewImmediate(mView);
    }

    /**
     * 更新悬浮窗
     */
    public void upLayout(View view) {
        mWindowManager.updateViewLayout(view, toDrawParams);
    }

    /**
     * @param flags 0关闭 高度 1 打开高度
     */
    void setHieght(int flags) {
        if (flags == 0) {
            toDrawParams.height = FLOATING_DRAW_HIDE_HIGHT;
        } else {
            toDrawParams.height = WindowManager.LayoutParams.MATCH_PARENT;
        }
    }

    /**
     * @param view 下拉悬浮
     */
    void initDrawMenu(View view) {
        topDrawShow(view);
    }

    /**
     * @param view 用户语音
     */
    void initUserMenu(View view) {
        userShow(view);
    }
}
