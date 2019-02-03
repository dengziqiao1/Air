package com.fce.fcefloatingmenu.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 通知左右侧滑删除
 */
public class ItemToucherHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    public ItemToucherHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int dragFlags = ItemTouchHelper.ACTION_STATE_IDLE;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }


    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }
}
