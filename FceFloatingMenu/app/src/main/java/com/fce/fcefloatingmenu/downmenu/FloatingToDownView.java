package com.fce.fcefloatingmenu.downmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fce.fcefloatingmenu.R;
import com.fce.fcefloatingmenu.floating.FloatingMenu;
import com.fce.fcefloatingmenu.utils.LogUtils;

/**
 * 下拉菜单
 * 上方悬浮菜单
 */
public class FloatingToDownView extends FloatingMenu implements View.OnLongClickListener {
    private NotificationMenu notificationMenu;
    private TextView wifi, bluetooh, data4g;
    private AppCompatSeekBar mediaSeekBar, mapSeekBar, phoneSeekBar, lightSeekBar;
    private ImageView mediaLess, mediaAdd, mapLess, mapAdd, phoneLess, phoneAdd, lightLess,
            lightAdd;

    public FloatingToDownView(@NonNull Context context) {
        super(context);
        notificationMenu = new NotificationMenu(getContext());
    }

    @Override
    protected void drawMenuView(View menuView) {
        wifi = menuView.findViewById(R.id.floating_wifi);
        bluetooh = menuView.findViewById(R.id.floating_bluetooth);
        data4g = menuView.findViewById(R.id.floating_data_4g);
        wifi.setOnClickListener(this);
        bluetooh.setOnClickListener(this);
        data4g.setOnClickListener(this);
        wifi.setOnLongClickListener(this);
        bluetooh.setOnLongClickListener(this);
        data4g.setOnLongClickListener(this);
    }

    @Override
    protected void onMenuClick(View v) {
        switch (v.getId()) {
            case R.id.floating_wifi:
                LogUtils.e("dzq", "floating_wifi");
                break;
            case R.id.floating_bluetooth:
                LogUtils.e("dzq", "floating_bluetooth");
                break;
            case R.id.floating_data_4g:
                LogUtils.e("dzq", "floating_data_4g");
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.floating_wifi:
                LogUtils.e("dzq", "onLongClick--->floating_wifi");
                break;
            case R.id.floating_bluetooth:
                LogUtils.e("dzq", "onLongClick--->floating_bluetooth");
                break;
            case R.id.floating_data_4g:
                LogUtils.e("dzq", "onLongClick-->floating_data_4g");
                break;
        }
        return false;
    }
}
