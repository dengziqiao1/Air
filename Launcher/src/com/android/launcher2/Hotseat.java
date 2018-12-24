
/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.launcher2;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import com.android.launcher.R;

public class Hotseat extends FrameLayout {
    @SuppressWarnings("unused")
    private static final String TAG = "Hotseat";

    private Launcher mLauncher;
    private CellLayout mContent, mContentApps, mContentRecent, mContentSubscribe;
    private BubbleTextView hotAllAppsButton, allAppsButton, recentButton, subscribeButton;
    private RelativeLayout hotseatRl;
    private Context mContext;

    private int mCellCountX;
    private int mCellCountY;
    private int mAllAppsButtonRank;

    private boolean mTransposeLayoutWithOrientation;
    private boolean mIsLandscape;

    public Hotseat(Context context) {
        this(context, null);
    }

    public Hotseat(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Hotseat(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Hotseat, defStyle, 0);
        Resources r = context.getResources();
        mCellCountX = a.getInt(R.styleable.Hotseat_cellCountX, -1);
        mCellCountY = a.getInt(R.styleable.Hotseat_cellCountY, -1);
        mAllAppsButtonRank = r.getInteger(R.integer.hotseat_all_apps_index);
        mTransposeLayoutWithOrientation = r.getBoolean(R.bool.hotseat_transpose_layout_with_orientation);
        mIsLandscape = context.getResources().getConfiguration().orientation ==
            Configuration.ORIENTATION_LANDSCAPE;
    }

    public void setup(Launcher launcher) {
        mLauncher = launcher;
        setOnKeyListener(new HotseatIconKeyEventListener());
    }

    CellLayout getLayout() {
        return mContent;
    }
  
    private boolean hasVerticalHotseat() {
    	//xmc.launcher
        return false;//(mIsLandscape && mTransposeLayoutWithOrientation);
    }

    /* Get the orientation invariant order of the item in the hotseat for persistence. */
    int getOrderInHotseat(int x, int y) {
        return hasVerticalHotseat() ? (mContent.getCountY() - y - 1) : x;
    }
    /* Get the orientation specific coordinates given an invariant order in the hotseat. */
    int getCellXFromOrder(int rank) {
        return hasVerticalHotseat() ? 0 : rank;
    }
    int getCellYFromOrder(int rank) {
        return hasVerticalHotseat() ? (mContent.getCountY() - (rank + 1)) : 0;
    }
    public boolean isAllAppsButtonRank(int rank) {
        return rank == mAllAppsButtonRank;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

       
        
        if (mCellCountX < 0) mCellCountX = LauncherModel.getCellCountX();
        if (mCellCountY < 0) mCellCountY = LauncherModel.getCellCountY();
        
        mContent = (CellLayout) findViewById(R.id.layout);
        mContent.setGridSize(mCellCountX, mCellCountY);
        mContent.setIsHotseat(true);
        
        hotseatRl = (RelativeLayout)findViewById(R.id.hotseat_three_ll);
        
        
        mContentApps = (CellLayout) findViewById(R.id.layout_apps);
        mContentApps.setGridSize(mCellCountX, mCellCountY);
        mContentApps.setIsHotseat(true);  
        
        mContentRecent = (CellLayout) findViewById(R.id.layout_recent);
        mContentRecent.setGridSize(mCellCountX, mCellCountY);
        mContentRecent.setIsHotseat(true);
        
        mContentSubscribe = (CellLayout) findViewById(R.id.layout_subscribe);
        mContentSubscribe.setGridSize(mCellCountX, mCellCountY);
        mContentSubscribe.setIsHotseat(true);

        resetLayout();
    }

    void resetLayout() {
        mContent.removeAllViewsInLayout();
        mContentApps.removeAllViewsInLayout();
        mContentRecent.removeAllViewsInLayout();
        mContentSubscribe.removeAllViewsInLayout();


        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        
        // Add the Apps button gone
        hotAllAppsButton = (BubbleTextView)inflater.inflate(R.layout.application, mContent, false);
        hotAllAppsButton.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.drawable.all_apps_button_icon), null, null);
        hotAllAppsButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mLauncher != null &&
                    (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    mLauncher.onTouchDownAllAppsButton(v);
                }
                return false;
            }
        });

        hotAllAppsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLauncher != null) {
                    mLauncher.onClickAllAppsButton(v);
                }
            }
        });

        // Note: We do this to ensure that the hotseat is always laid out in the orientation of
        // the hotseat in order regardless of which orientation they were added
        int x = getCellXFromOrder(mAllAppsButtonRank);
        int y = getCellYFromOrder(mAllAppsButtonRank);
        CellLayout.LayoutParams lp = new CellLayout.LayoutParams(x,y,1,1);
        lp.canReorder = false;
        mContent.addViewToCellLayout(hotAllAppsButton, -1, 0, lp, true);
        
        //add allapps button 
        allAppsButton = (BubbleTextView)inflater.inflate(R.layout.application, mContentApps, false);
        allAppsButton.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.drawable.laun_apps), null, null);
        allAppsButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mLauncher != null &&
                    (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    mLauncher.onTouchDownAllAppsButton(v);
                }
                return false;
            }
        });

        allAppsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLauncher != null) {
                    //mLauncher.onClickAllAppsButton(v);
                }
            }
        });

        // Note: We do this to ensure that the hotseat is always laid out in the orientation of
        // the hotseat in order regardless of which orientation they were added
        int x0 = getCellXFromOrder(mAllAppsButtonRank);
        int y0 = getCellYFromOrder(mAllAppsButtonRank);
        CellLayout.LayoutParams lp0 = new CellLayout.LayoutParams(x0,y0,1,1);
        lp0.canReorder = false;
        mContentApps.addViewToCellLayout(allAppsButton, -1, 0, lp0, true);
        
        // Add the recent button
        recentButton = (BubbleTextView)inflater.inflate(R.layout.application, mContentRecent, false);
        recentButton.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.drawable.laun_history), null, null);
        recentButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mLauncher != null &&
                    (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    mLauncher.onTouchDownAllAppsButton(v);
                }
                return false;
            }
        });

        recentButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {//TODO 打开APP历史记录界面
                if (mLauncher != null) {
                    //executeKeyEvent(KeyEvent.KEYCODE_APP_SWITCH);
                }
            }
        });

        // Note: We do this to ensure that the hotseat is always laid out in the orientation of
        // the hotseat in order regardless of which orientation they were added
        int x1 = getCellXFromOrder(mAllAppsButtonRank);
        int y1 = getCellYFromOrder(mAllAppsButtonRank);
        CellLayout.LayoutParams lp1 = new CellLayout.LayoutParams(x1,y1,1,1);
        lp1.canReorder = false;
        mContentRecent.addViewToCellLayout(recentButton, -1, 0, lp1, true);
        
        
        // Add the subscribe button
        subscribeButton = (BubbleTextView)inflater.inflate(R.layout.application, mContentSubscribe, false);
        subscribeButton.setCompoundDrawablesWithIntrinsicBounds(null,
                context.getResources().getDrawable(R.drawable.laun_subscribe), null, null);
        //recentButton.setContentDescription(context.getString(R.string.all_apps_button_label));
        //recentButton.setText(context.getString(R.string.all_apps_button_label));  //add by xmc 全部应用图标增加文字
        subscribeButton.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mLauncher != null &&
                    (event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_DOWN) {
                    mLauncher.onTouchDownAllAppsButton(v);
                }
                return false;
            }
        });

        subscribeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLauncher != null) {//TODO 打开订阅界面
                    //mLauncher.startActivity(new Intent(mLauncher, SubscribeActivity.class));
                }
            }
        });

        // Note: We do this to ensure that the hotseat is always laid out in the orientation of
        // the hotseat in order regardless of which orientation they were added
        int x2 = getCellXFromOrder(mAllAppsButtonRank);
        int y2 = getCellYFromOrder(mAllAppsButtonRank);
        CellLayout.LayoutParams lp2 = new CellLayout.LayoutParams(x2,y2,1,1);
        lp2.canReorder = false;
        mContentSubscribe.addViewToCellLayout(subscribeButton, -1, 0, lp2, true);
    }
    
	private void executeKeyEvent(final int keycode) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				Instrumentation mInst = new Instrumentation();
				mInst.sendKeyDownUpSync(keycode);
			}
		};
		Timer time = new Timer();
		time.schedule(task, 200);
	}
	

}