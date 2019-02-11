package com.fce.fcefloatingmenu.floating;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.fce.fcefloatingmenu.R;
import com.fce.fcefloatingmenu.utils.GenericDrawerLayout;
import com.fce.fcefloatingmenu.utils.LogUtils;

/**
 * 悬浮菜单
 */
public abstract class FloatingMenu extends FrameLayout implements View.OnClickListener,
        GenericDrawerLayout.DrawerCallback {
    private Context context;
    private FloatingManager floatingManager;
    private View drawView, userView, menuView; //下拉，用户，下拉内容
    private ImageButton userBtn, voiceBtn; //用户 语音
    private GenericDrawerLayout dropDownMenuLayout; //下拉菜单
    private FrameLayout rightTopLayout;

    public FloatingMenu(@NonNull Context context) {
        super(context);
        this.context = context;
        initView();
    }

    /**
     * 初始化数据
     */
    private void initView() {
        floatingManager = new FloatingManager(context);
        drawView = LayoutInflater.from(context).inflate(R.layout.floating_menu, null);
        userView = LayoutInflater.from(context).inflate(R.layout.floating_user_menu, null);
        menuView = LayoutInflater.from(context).inflate(R.layout.floating_to_drawmenu, null);
        dropDownMenuLayout = drawView.findViewById(R.id.floating_menu);
        userBtn = userView.findViewById(R.id.floating_user);
        voiceBtn = userView.findViewById(R.id.floating_voice);
        rightTopLayout = drawView.findViewById(R.id.floating_top_right_menu);
        rightTopLayout.setOnClickListener(this);
        userBtn.setOnClickListener(this);
        voiceBtn.setOnClickListener(this);
        floatingManager.initDrawMenu(drawView);
        floatingManager.initUserMenu(userView);
        dropDownMenuLayout.setDrawerGravity(Gravity.TOP);
        dropDownMenuLayout.setContentLayout(menuView);
        dropDownMenuLayout.setDrawerCallback(this);
        drawMenuView(menuView);
    }

    protected abstract void drawMenuView(View menuView);    //下拉View

    protected abstract void onMenuClick(View v);    //点击

    @Override
    public void onClick(View v) {
        LogUtils.e("dzq", "用户点了12");
        onMenuClick(v);
        switch (v.getId()) {
            case R.id.floating_top_right_menu:
                LogUtils.e("dzq", "floating_menu");
                break;
            case R.id.floating_user:   //用户
                LogUtils.e("dzq", "用户点了");
                break;
            case R.id.floating_voice:    //语音
                LogUtils.e("dzq", "语音点了");
                break;
        }
    }

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
        floatingManager.setHieght(0);
        floatingManager.upLayout(drawView);
    }

    @Override
    public void onPreOpen() {
        floatingManager.setHieght(1);
        floatingManager.upLayout(drawView);
    }

    @Override
    public void onTranslating(int gravity, float translation, float fraction) {

    }
}
