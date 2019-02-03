package com.fce.fcefloatingmenu.floating;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fce.fcefloatingmenu.R;

/**
 * 悬浮窗
 */
public class FloatingManager {
    private Context context;
    private WindowManager mWindowManager;
    private FrameLayout rightTopLayout;//下拉区域, 右上角
    private Button handleBtn;//下拉高度

    public FloatingManager(Context context) {
        this.context = context;
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }

    /**
     * 下拉
     * 悬浮窗
     */
    public WindowManager.LayoutParams topDrawShow(View mView, int height) {
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 0;
        mParams.y = 0;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.width = 1920;
        mParams.height = height;
        rightTopLayout = mView.findViewById(R.id.floating_top_right_menu);         //状态栏 右侧
        handleBtn = mView.findViewById(R.id.floating_handle);       //下拉背景
        FrameLayout.LayoutParams rightParams =
                (FrameLayout.LayoutParams) rightTopLayout.getLayoutParams();
        rightParams.height = 50;
        rightTopLayout.setLayoutParams(rightParams);

//        TopDrawLayout.LayoutParams params1 = handleBtn.getLayoutParams();
//        params1.height = 20;
//        handleBtn.setLayoutParams(params1);
        addLayOut(mView, mParams);
        return mParams;
    }

    /**
     * 下拉
     * 悬浮窗
     */
    public WindowManager.LayoutParams topDrawMenuShow(View mView, int height) {
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 0;
        mParams.y = 0;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.width = 1920;
        mParams.height = height;
//        rightTopLayout = mView.findViewById(R.id.floating_top_menu);         //状态栏 右侧
        handleBtn = mView.findViewById(R.id.floating_handle);       //下拉背景
//        FrameLayout.LayoutParams rightParams =
//                (FrameLayout.LayoutParams) rightTopLayout.getLayoutParams();
//        rightParams.height = 50;
//        rightTopLayout.setLayoutParams(rightParams);

//        TopDrawLayout.LayoutParams params1 = handleBtn.getLayoutParams();
//        params1.height = 20;
//        handleBtn.setLayoutParams(params1);

        addLayOut(mView, mParams);
        return mParams;
    }

    /**
     * 用户 语音
     * 悬浮窗显示
     */
    public WindowManager.LayoutParams userShow(View mView) {
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 1800;
        mParams.y = 630;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        mParams.width = 180;
        mParams.height = 90;

        addLayOut(mView, mParams);
        return mParams;
    }

    /**
     * 添加悬浮窗
     * * @param mView
     * * @param params
     */
    public void addLayOut(View mView, ViewGroup.LayoutParams params) {
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
    public void upLayout(View view, ViewGroup.LayoutParams params) {
        mWindowManager.updateViewLayout(view, params);
    }
}
