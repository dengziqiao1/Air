package com.fce.fcefloatingmenu.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 通用 viewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SparseArray<View> views;
    private ItemContentOnClick contentOnClick;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        views = new SparseArray<>();
    }

    /**
     * 绑定id
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    public void setText(int viewId, String str) {
        TextView view = getView(viewId);
        view.setText(str);
    }

    public void setText(View v, String str) {
        TextView view = (TextView) v;
        view.setText(str);
    }

    public void setTextClick(int viewId, boolean status) {
        TextView view = getView(viewId);
        view.setSelected(status);
    }

    public void setTextColor(int viewId, int color) {
        TextView view = getView(viewId);
        view.setTextColor(color);
    }

    public void setRelativeLayout(int viewId, int Resources) {
        RelativeLayout relativeLayout = getView(viewId);
        relativeLayout.setBackgroundResource(Resources);
    }

    /**
     * 显示 隐藏
     *
     * @param viewId
     * @param vis
     */
    public void setTextVisibility(int viewId, int vis) {
        TextView view = getView(viewId);
        view.setVisibility(vis);
    }

    public void setImagerView(int viewId, int Resources) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(Resources);
    }

    public void setView(int viewId, int view) {
        View imageView = getView(viewId);
        imageView.setVisibility(view);
    }

    public void setCheck(int viewId, boolean status) {
        CheckBox checkBox = getView(viewId);
        checkBox.setChecked(status);
    }

    /**
     * item button点击
     *
     * @param viewId
     */
    public void setButton(int viewId, String str) {
        Button button = getView(viewId);
        button.setText(str);
    }

    @Override
    public void onClick(View v) {
        if (contentOnClick != null) {
            contentOnClick.contentClick(v, Integer.parseInt(v.getTag().toString()));
        }
    }

    /**
     * item 内容点击
     */
    public interface ItemContentOnClick {
        void contentClick(View v, int position);

    }

    /**
     * 内容点击
     */
    public void setOnItemContentClickListener(int view, int positon, ItemContentOnClick listener) {
        this.contentOnClick = listener;
        View view1 = getView(view);
        view1.setTag(positon);
        view1.setOnClickListener(this);
    }

}
