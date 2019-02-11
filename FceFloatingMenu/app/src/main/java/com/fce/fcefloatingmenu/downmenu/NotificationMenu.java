package com.fce.fcefloatingmenu.downmenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.fce.fcefloatingmenu.R;
import com.fce.fcefloatingmenu.adapter.ItemToucherHelperCallback;
import com.fce.fcefloatingmenu.adapter.RecyclerSimpleAdapter;
import com.fce.fcefloatingmenu.adapter.RecyclerViewHolder;
import com.fce.fcefloatingmenu.floating.FloatingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * 通知 菜单
 */
public class NotificationMenu extends FloatingMenu {
    private List<String[]> notifaList;
    private RecyclerSimpleAdapter adapter;
    private RecyclerView recyclerView;
    private boolean isFirst = false;

    public NotificationMenu(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void drawMenuView(View menuView) {
        recyclerView = menuView.findViewById(R.id.floating_recycler_notif);
        notifaList = new ArrayList<>();
        setAdapter();
    }

    @Override
    protected void onMenuClick(View v) {
        
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
        if (notifaList == null || notifaList.size() < 1) {
            return;
        }
        if (!isFirst) {
            adapter = new RecyclerSimpleAdapter<String[]>(getContext(), notifaList,
                    R.layout.item_notiaf, false) {
                @Override
                public void convert(RecyclerViewHolder holder, String[] data, int position) {
                    holder.setText(R.id.item_notaif_center_top, data[0]);
                    holder.setText(R.id.item_notaif_center_bottom, data[1]);
                    holder.setText(R.id.item_notaif_right, data[2]);
                }
            };
            adapter.setRecyclerView(getContext(), recyclerView, false);
            recyclerView.setAdapter(adapter);
            ItemTouchHelper.Callback callback = new ItemToucherHelperCallback(adapter);
            ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
            touchHelper.attachToRecyclerView(recyclerView);
            isFirst = true;
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
