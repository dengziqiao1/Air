package com.fce.fcefloatingmenu.floating;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.fce.fcefloatingmenu.LogUtils;
import com.fce.fcefloatingmenu.R;
import com.fce.fcefloatingmenu.adapter.ItemToucherHelperCallback;
import com.fce.fcefloatingmenu.adapter.RecyclerSimpleAdapter;
import com.fce.fcefloatingmenu.adapter.RecyclerViewHolder;
import com.fce.fcefloatingmenu.floating.test.GenericDrawerLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 下拉菜单
 * 上方悬浮菜单
 */
public class FloatingToDownView extends ViewGroup implements View.OnClickListener,
        View.OnTouchListener {
    private Context context;
    public static View mView, userView, menuView;
    public static FloatingManager floatingManager;
    public static WindowManager.LayoutParams toDrawParams, userParams, menuParams; //下拉  用户
    public static FrameLayout rightTopLayout;//下拉区域, 右上角
    private RecyclerSimpleAdapter adapter;
    private RecyclerView recyclerView;
    private List<String[]> notifaList;
    public static TopDrawLayout dropDownMenuLayout;
    private ImageButton userBtn, voiceBtn; //用户 语音
    private GenericDrawerLayout dropDownMenuLayout1;

    public FloatingToDownView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    /**
     * 初始化数据
     */
    private void initView() {
        floatingManager = new FloatingManager(context);
        mView = LayoutInflater.from(context).inflate(R.layout.floating_menu, null);
        userView = LayoutInflater.from(context).inflate(R.layout.floating_user_menu, null);
//        dropDownMenuLayout = mView.findViewById(R.id.floating_drop_menu);
        dropDownMenuLayout1 = mView.findViewById(R.id.floating_menu);

        floatingManager.userShow(userView);
        toDrawParams = floatingManager.topDrawShow(mView, 50);
        userBtn = userView.findViewById(R.id.floating_user);
        voiceBtn = userView.findViewById(R.id.floating_voice);
        rightTopLayout = mView.findViewById(R.id.floating_top_right_menu);

        recyclerView = mView.findViewById(R.id.floating_recycler_notif);
        rightTopLayout.setOnClickListener(this);
        userBtn.setOnClickListener(this);
        voiceBtn.setOnClickListener(this);
        // mView.setOnTouchListener(this);
        //rightTopLayout.setOnTouchListener(this);
        notifaList = new ArrayList<>();
        dropDownMenuLayout1.setDrawerGravity(Gravity.TOP);
        dropDownMenuLayout1.setContentLayout(View.inflate(context, R.layout.floating_to_drawmenu,
                null));
        dropDownMenuLayout1.setDrawerCallback(new GenericDrawerLayout.DrawerCallback() {
            @Override
            public void onStartOpen() {

            }

            @Override
            public void onEndOpen() {

            }

            @Override
            public void onStartClose() {

            }

            @Override
            public void onEndClose() {
                LogUtils.e("dzq", "开始关闭");
                dropDownMenuLayout1.isfase = false;
                FloatingToDownView.toDrawParams.height = 50;
                FloatingToDownView.floatingManager.upLayout(FloatingToDownView.mView,
                        FloatingToDownView.toDrawParams);
            }

            @Override
            public void onPreOpen() {

            }

            @Override
            public void onTranslating(int gravity, float translation, float fraction) {


            }
        });
//        dropDownMenuLayout1.setOpaqueWhenTranslating(true);
//        float v = getResources().getDisplayMetrics().density * 100 + 0.5f; // 100DIP
//        dropDownMenuLayout1.setDrawerEmptySize((int) v);


//        dropDownMenuLayout.setOnDrawerCloseListener(new TopDrawLayout.OnDrawerCloseListener() {
//            @Override
//            public void onDrawerClosed() {
//                dropDownMenuLayout.isIsfase1 =false;
//                dropDownMenuLayout.isIsfase2 =false;
//                dropDownMenuLayout.isIsfase3 =false;
//                toDrawParams.height = 50;
//                floatingManager.upLayout(mView, toDrawParams);
//            }
//        });
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floating_top_right_menu:
                LogUtils.e("fce", "floating_menu");
                break;
            case R.id.floating_user:   //用户
                LogUtils.e("fce", "用户点了");
                break;
            case R.id.floating_voice:    //语音
                LogUtils.e("fce", "语音点了");
                break;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    private boolean isFirst = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtils.e("fce", "移动当中" + "");
        if (event.getY() < 30) {
            FloatingToDownView.toDrawParams.height = 720;
            FloatingToDownView.floatingManager.upLayout(FloatingToDownView.mView,
                    FloatingToDownView.toDrawParams);
        }
//        LogUtils.e("dzq","移动高度： "+dropDownMenuLayout1.getLayoutParams().height);
//        if (dropDownMenuLayout1.getLayoutParams().height>600) {
//            return true;
//        }
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                FloatingToDownView.toDrawParams.height = 720;
//                FloatingToDownView.floatingManager.upLayout(FloatingToDownView.mView,
//                        FloatingToDownView.toDrawParams);
//                break;
//            case MotionEvent.ACTION_MOVE:
//                dropDownMenuLayout1.setLayoutParams(new FrameLayout.LayoutParams(new
// FrameLayout.LayoutParams(1920, (int) event.getRawY())));
//                break;
//        }
//        LogUtils.e("dzq", "拦截onTouch12");
//        if (!isFirst) {
//            floatingManager.hide(mView);
//            menuView = LayoutInflater.from(context).inflate(R.layout.floating_to_drawmenu, null);
//            dropDownMenuLayout = menuView.findViewById(R.id.floating_drop_menu);
//        }
//        if (!dropDownMenuLayout.isOpened() && !isFirst) {
//            LogUtils.e("dzq", "拦截没有打开 进来 ");
//            isFirst = true;
//            menuParams = floatingManager.topDrawMenuShow(menuView, 720);
//        }
////        Intent intent = new Intent(context, Test.class);
////        context.startActivity(intent);
//        //setAdapter();
//        dropDownMenuLayout.setOnDrawerCloseListener(new TopDrawLayout.OnDrawerCloseListener() {
//            @Override
//            public void onDrawerClosed() {
//                LogUtils.e("dzq", "拦截onTouch12 关闭");
//                isFirst = false;
//                floatingManager.topDrawMenuShow(mView, 50);
//                floatingManager.hide(menuView);
//            }
//        });
        return super.onTouchEvent(event);
    }
}
