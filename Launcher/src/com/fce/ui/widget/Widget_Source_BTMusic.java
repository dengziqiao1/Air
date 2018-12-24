package com.fce.ui.widget;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.launcher.R;
import com.fce.util.BT_IF;
import com.fce.util.BT_Listener;
import com.fc.define.BTDef.BTFunc;
import com.fc.define.ModeDef;
import com.fc.serviceif.BTService_Listener;

public class Widget_Source_BTMusic extends Fragment implements
		BTService_Listener, BT_Listener {

	private final String TAG = this.getClass().getSimpleName();
	private Context mContext;
	private BT_IF mIF;
//	private FCButtonText mBtnPre;
//	private FCButtonText mBtnPP;
//	private FCButtonText mBtnNext;
	private Bitmap mPlay;
	private Bitmap mPause;
	private Bitmap mPlayDown;
	private Bitmap mPauseDown;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mIF = BT_IF.getInstance();
		mIF.registerBTCallBack(this);
		mIF.registerModeCallBack(this);
		mIF.bindBTService(mContext);

		View view = inflater.inflate(R.layout.fc_widget_source_btmusic, null);
//		mBtnPre = (FCButtonText) view.findViewById(R.id.widget_btmusic_pre);
//		mBtnPP = (FCButtonText) view.findViewById(R.id.widget_btmusic_pp);
//		mBtnNext = (FCButtonText) view.findViewById(R.id.widget_btmusic_next);
//		mBtnPre.SetFCTouchListener(this);
//		mBtnPP.SetFCTouchListener(this);
//		mBtnNext.SetFCTouchListener(this);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		mIF.registerBTCallBack(this);
		mIF.registerModeCallBack(this);
	}

	@Override
	public void onStop() {
		super.onStop();
		mIF.unregisterBTCallBack(this);
		mIF.unregisterModeCallBack(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

//	@Override
//	public void OnFCTouchEvent(View view, TOUCH_ACTION action) {
//		if (action == TOUCH_ACTION.BTN_CLICKED) {
//			switch (view.getId()) {
//			case R.id.widget_btmusic_pre:
//				mIF.music_pre();
//				break;
//
//			case R.id.widget_btmusic_pp:
//				if (mIF.music_isPlaying()) {
//					mIF.music_pause();
//				} else {
//					mIF.music_play();
//				}
//				break;
//
//			case R.id.widget_btmusic_next:
//				mIF.music_next();
//				break;
//			}
//		}
//
//	}

	@Override
	public void onBTDataChange(int mode, int func, int data) {
		if (mode == ModeDef.BT) {
			switch (func) {
			case BTFunc.PLAY_STATE:
				updatePPStatus();
				break;
			}
		}

	}

	@Override
	public void onBTServiceConn() {
		updatePPStatus();
	}

	// 更新播放暂停状态
	private void updatePPStatus() {
		boolean mode = mIF.music_isPlaying();
		Bitmap normal = getBitmap(mode);
		Bitmap press = getBitmapDown(mode);
//		mBtnPP.SetPicUp(normal);
//		mBtnPP.SetPicDown(press);
	}

	private Bitmap getBitmap(boolean mode) {
		Bitmap bitmap = null;
		Options bitmapOptions = new Options();
		bitmapOptions.inScaled = false;
		if (mode) {
			if (mPlay == null) {
//				mPlay = BitmapFactory.decodeResource(mContext.getResources(),
//						R.drawable.widget_source_play_n, bitmapOptions);
			}
			bitmap = mPlay;
		} else {
			if (mPause == null) {
//				mPause = BitmapFactory.decodeResource(mContext.getResources(),
//						R.drawable.widget_source_pause_n, bitmapOptions);
			}
			bitmap = mPause;
		}
		return bitmap;
	}

	private Bitmap getBitmapDown(boolean mode) {
		Bitmap bitmap = null;
		Options bitmapOptions = new Options();
		bitmapOptions.inScaled = false;

		if (mode) {
			if (mPlayDown == null) {
//				mPlayDown = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_play_p, bitmapOptions);
			}
			bitmap = mPlayDown;
		} else {
			if (mPauseDown == null) {
//				mPauseDown = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_pause_p, bitmapOptions);
			}
			bitmap = mPauseDown;
		}
		return bitmap;
	}
}
