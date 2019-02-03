package com.fce.fcefloatingmenu.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 通用适配器
 */
public abstract class RecyclerSimpleAdapter<T> extends RecyclerView.Adapter<RecyclerView
        .ViewHolder> implements ItemTouchHelperAdapter {
    private Context context;
    private List<T> mlist;
    private LayoutInflater miInflater;
    private int TYPE_FOOTER = 0;
    private int TYPE_ITEM = 1;
    private int DATA_EMPTY = 2;//没有数据时
    private FootHolder FootHolder = null;
    private int layout;
    private ItemOnClick onClicks;
    private boolean isFoot = true;     //默认有头

    public RecyclerSimpleAdapter(Context context, List<T> mlist, int layout, boolean isFoot) {
        this.context = context;
        this.mlist = mlist;
        this.layout = layout;
        miInflater = LayoutInflater.from(context);
        this.isFoot = isFoot;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = miInflater.inflate(layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {

    }

    @Override
    public void onItemDissmiss(int position) {
        //移除数据
        mlist.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (mlist.size() == 0) {
            return DATA_EMPTY;
        } else if (position + 1 == getItemCount() && isFoot) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint
            ("RecyclerView") final int position) {
        if (holder instanceof RecyclerViewHolder) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClicks != null) {
                        onClicks.holderClick(v, position);
                    }
                }
            });
            convert((RecyclerViewHolder) holder, mlist.get(position), position);
        } else if (holder instanceof FootHolder) {
            FootHolder = (FootHolder) holder;
        }

    }

    /**
     * 设置数据的方法
     */
    public abstract void convert(RecyclerViewHolder holder, T data, int position);

    @Override
    public int getItemCount() {
        if (mlist == null) return 0;
        if (isFoot) {
            return mlist.size() + 1;
        } else {
            return mlist.size();
        }
    }


    static class FootHolder extends RecyclerView.ViewHolder {
        FootHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 数据为空时
     */
    static class DataEmptyHolder extends RecyclerView.ViewHolder {
        DataEmptyHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * item 点击
     */
    public interface ItemOnClick {
        void holderClick(View v, int position);
    }

    /**
     * @param listener 设置Item点击监听
     */
    public void setOnItemClickListener(ItemOnClick listener) {
        this.onClicks = listener;
    }

    /**
     * @param context      上下文
     * @param recyclerView 设置 RecyclerView 线性
     * @param status       是否有最后一条线
     */
    public void setRecyclerView(Context context, RecyclerView recyclerView, boolean status) {
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new RecyclerItemDecoration(context, LinearLayoutManager
//                .VERTICAL, Color.parseColor("#849552"), status));
        recyclerView.setLayoutManager(manager);
    }

    /**
     * @param context      上下文
     * @param recyclerView 设置 RecyclerView 线性
     * @param status       是否有最后一条线
     */
    public void setRecyclerCardeView(Context context, RecyclerView recyclerView, boolean status) {
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager
                .VERTICAL, false);
        recyclerView.setHasFixedSize(true);
//        recyclerView.addItemDecoration(new PiteItemDecoration(context, LinearLayoutManager
//                .VERTICAL, Color.parseColor("#849552"), status));
        recyclerView.setLayoutManager(manager);
    }

    /**
     * @param context      上下文
     * @param recyclerView 设置 RecyclerView 横向
     * @param status       是否有最后一条线
     */
    public void setRecyclerHizView(Context context, RecyclerView recyclerView, boolean status) {
        LinearLayoutManager manager = new LinearLayoutManager(context, LinearLayoutManager
                .HORIZONTAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new RecyclerItemDecoration(context, LinearLayoutManager
                .VERTICAL, Color.parseColor("#849552"), status));
        recyclerView.setLayoutManager(manager);
    }

    /**
     * @param context      上下文
     * @param recyclerView 设置 setRecyclerGradView 线性
     * @param count        //列数
     */
    public void setRecyclerGradView(Context context, RecyclerView recyclerView, int count) {
        GridLayoutManager manager = new GridLayoutManager(context, count);
        recyclerView.setHasFixedSize(true);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
    }
}
