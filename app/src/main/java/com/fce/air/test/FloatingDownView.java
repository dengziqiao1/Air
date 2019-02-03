package com.fce.air.test;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.fce.fcefloatingmenu.R;
import com.fce.fcefloatingmenu.adapter.ItemToucherHelperCallback;
import com.fce.fcefloatingmenu.adapter.RecyclerSimpleAdapter;
import com.fce.fcefloatingmenu.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉菜单
 * 上方悬浮菜单
 */
public class FloatingDownView extends RelativeLayout implements View.OnClickListener,
        View.OnTouchListener, BottomFinshLayout.OnFinishListener {
    private Context context;
    private View mView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private FrameLayout dropDownLayout, rightTopLayout;//下拉区域, 右上角
    private RecyclerSimpleAdapter adapter;
    private RecyclerView recyclerView;
    private List<String[]> notifaList;
    private GestureDetector gestureDetector;
    private Scroller mScroller;
    private BottomFinshLayout dropDownMenuLayout;   //下拉菜单 显示区域

    public FloatingDownView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    /**
     * 初始化数据
     */
    private void initView() {
        mWindowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        mView = LayoutInflater.from(context).inflate(R.layout.floating_top_menu, null);
//        dropDownLayout = mView.findViewById(R.id.floating_down);
        rightTopLayout = mView.findViewById(R.id.floating_top_menu);
        dropDownMenuLayout = mView.findViewById(R.id.floating_drop_menu);
        recyclerView = mView.findViewById(R.id.floating_recycler_notif);
        rightTopLayout.setOnClickListener(this);
        dropDownLayout.setOnClickListener(this);
        mView.setOnTouchListener(this);
        rightTopLayout.setOnTouchListener(this);
        dropDownLayout.setOnTouchListener(this);
        dropDownMenuLayout.setOnFinishListener(this);
        notifaList = new ArrayList<>();
        setAdapter();
        gestureDetector = new GestureDetector(context, new listViewGestureListener());
        mScroller = new Scroller(context);
    }


    /**
     * 悬浮窗显示
     */
    public void show() {
        mParams = new WindowManager.LayoutParams();
        mParams.gravity = Gravity.TOP | Gravity.START;
        mParams.x = 0;
        mParams.y = 0;
        //总是出现在应用程序窗口之上
        mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        //设置图片格式，效果为背景透明
        mParams.format = PixelFormat.RGBA_8888;
        mParams.flags =
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_SCALED | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
        mParams.width = 1920;
        mParams.height = 40;
        mWindowManager.addView(mView, mParams);

    }

    /**
     * 移除悬浮窗
     */
    public void hide() {
        mWindowManager.removeView(mView);
    }

    /**
     * 更新悬浮窗
     *
     * @param view
     * @param params
     */
    public void upLayout(View view, ViewGroup.LayoutParams params) {
        mWindowManager.updateViewLayout(view, params);
    }

    /**
     * 通知数据
     */
    private void setAdapter() {
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        notifaList.add(new String[]{"今日用量:  73.89M", "本月已用:500.57GB   可用:2000GB", "18:08"});
        adapter = new RecyclerSimpleAdapter<String[]>(context, notifaList, R.layout.item_notiaf,
                false) {
            @Override
            public void convert(RecyclerViewHolder holder, String[] data, int position) {
                holder.setText(R.id.item_notaif_center_top, data[0]);
                holder.setText(R.id.item_notaif_center_bottom, data[1]);
                holder.setText(R.id.item_notaif_right, data[2]);
            }
        };
        adapter.setRecyclerView(context, recyclerView, false);
        recyclerView.setAdapter(adapter);
        ItemTouchHelper.Callback callback = new ItemToucherHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    /**
     * 手势监听
     */
    private class listViewGestureListener implements GestureDetector.OnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {

            return false;
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.e("dzq", "onShowPress");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.e("dzq", "onSingleTapUp");
            return false;
        }


        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.e("dzq", "打开 ：  " + isOpen());
            if (!isOpen()) {
                srcbase = true;
                mParams.width = 1920;
                mParams.height = (int) e2.getY();
                upLayout(mView, mParams);
                dropDownMenuLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) e2.getY()));
            } else {
                mParams.width = 1920;
                mParams.height = (int) e2.getY();
                upLayout(mView, mParams);
                dropDownMenuLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, (int) e2.getY()));
            }
            return false;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.e("dzq", "onLongPress");
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.e("dzq", "抬起");
            if (!isOpen()) {

            }
            return false;
        }

    }

    private boolean srcbase = false;

    @Override
    public void onFinish() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                if (srcbase) {
                    srcbase = false;
                    mParams.width = 1920;
                    mParams.height = 720;
                    upLayout(mView, mParams);
                    dropDownMenuLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 720));
                }
                break;
        }
        if (gestureDetector.onTouchEvent(event)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.floating_down:
                //    closeMenu();
//                Log.e("dzq", "floating_down");
//                break;
            case R.id.floating_top_menu:
                Log.e("dzq", "floating_top_menu");
                // closeMenu();
                break;
        }
    }

    /**
     * @return 下拉是否打开是否打开
     */
    private boolean isOpen() {
        if (mView == null) {
            return false;
        }
        if (mView.getMeasuredHeight() > 600) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 关闭菜单
     */
    private void closeMenu() {
        mParams.width = 1920;
        mParams.height = 0;
        upLayout(mView, mParams);
        dropDownMenuLayout.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, 0));
    }

}
