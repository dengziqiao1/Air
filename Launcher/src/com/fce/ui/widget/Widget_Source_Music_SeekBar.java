package com.fce.ui.widget;

import com.android.launcher.R;
import com.fce.util.Media_IF;
import com.fc.define.ModeDef;
import com.fc.window.FCWindowManager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class Widget_Source_Music_SeekBar extends LinearLayout  {

	// ------------------------------外部接口 start------------------------------

	/**
	 * 更新当前播放时间
	 */
	public void updateCurTime() {
		if (mIF.getMediaSource() != ModeDef.AUDIO) {
			//mSeekBar.SetCurNum(0);
			return;
		}

		if (mCanUpdate == false)
			return;

		int time = (int) mIF.getPosition();
		//mSeekBar.SetCurNum(time);
	}

	/**
	 * 更新播放总时间
	 */
	public void updateTimeInfo() {
		if (mIF.getMediaSource() != ModeDef.AUDIO) {
			//mSeekBar.SetMaxNum(0);
			//mSeekBar.SetCurNum(0);
			return;
		}

		if (mCanUpdate == false)
			return;

		int time = (int) mIF.getDuration();
		//mSeekBar.SetMaxNum(time);
		updateCurTime();
	}

	// ------------------------------外部接口 end------------------------------

	// 内部变量
	private Context mContext;
	private Media_IF mIF;
	//private FCSeekBarText mSeekBar;
	private boolean mCanUpdate = true;


	public Widget_Source_Music_SeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mIF = Media_IF.getInstance();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	// 初始化控件
	private void initView() {
		// 获取控件

	}


//	@Override
//	public void OnFCTouchEvent(View view, TOUCH_ACTION action) {
//		// TODO Auto-generated method stub
//		if (action == TOUCH_ACTION.BTN_DOWN) {
//			switch (view.getId()) {
//			case R.id.widget_seekbar:
//				mCanUpdate = false;
//				break;
//			}
//
//		} else if (action == TOUCH_ACTION.BTN_UP) {
//			switch (view.getId()) {
//			case R.id.widget_seekbar:
//				mCanUpdate = true;
//				mIF.setPosition(mSeekBar.GetCurNum());
//				break;
//			}
//		}
//
//		if (mTouchedListener != null)
//			mTouchedListener.OnFCTouchEvent(view, action);
//	}
}
