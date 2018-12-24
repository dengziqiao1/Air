package com.fce.ui.widget;


import com.android.launcher.R;
import com.fce.util.Launcher_IF;
import com.fce.util.Media_IF;
import com.fce.util.Media_Listener;
import com.fc.define.MediaDef.MediaFunc;
import com.fc.define.MediaDef.MediaState;
import com.fc.define.MediaDef.PlayState;
import com.fc.define.ModeDef;
import com.fc.serviceif.MediaService_Listener;
import com.fc.window.FCWindowManager;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Widget_Source_Music extends Fragment implements
		MediaService_Listener, Media_Listener {

	// ------------------------------外部接口 start------------------------------
	// ------------------------------外部接口 end------------------------------

	// 内部变量
	private final String TAG = this.getClass().getSimpleName();
	private Context mContext;
	private Media_IF mIF;
	private Widget_Source_Music_SeekBar mTimeSeekBar;
	private Widget_Source_Music_Id3 mId3Info;
	private Widget_Source_Music_PlayTime mPlayTime;
	private Bitmap mPlay;
	private Bitmap mPause;
	private Bitmap mPlayDown;
	private Bitmap mPauseDown;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mIF = Media_IF.getInstance();
		mIF.registerMediaCallBack(this);
		mIF.registerModeCallBack(this);
		
		mIF.bindMediaService(mContext);
		
		// 加载控件
		View view = inflater.inflate(R.layout.fc_widget_source_music, null);
		mTimeSeekBar = (Widget_Source_Music_SeekBar) view
				.findViewById(R.id.widget_time_seekbar);
		mPlayTime = (Widget_Source_Music_PlayTime) view
				.findViewById(R.id.widget_time_playtime);
		mId3Info = (Widget_Source_Music_Id3) view.findViewById(R.id.widget_id3);

		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		mIF.registerMediaCallBack(this);
		mIF.registerModeCallBack(this);
		updateStatus(); // 更新数据
	}

	@Override
	public void onStop() {
		super.onStop();
		mIF.unregisterMediaCallBack(this);
		mIF.unregisterModeCallBack(this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}

	@Override
	public void onMediaServiceConn() {
		// TODO Auto-generated method stub
		Log.v(TAG, "FCE------------onMediaServiceConn");
		mIF.registerMediaCallBack(this);
		mIF.registerModeCallBack(this);
		updateStatus(); // 更新数据
	}

	@Override
	public void onMediaDataChange(int mode, int func, int data1, int data2) {
		// TODO Auto-generated method stub
		switch (func) {
		case MediaFunc.PREPARED:
			onPrepared();
			break;
		case MediaFunc.PLAY_STATE:
			playStateChanged(data1);
			break;
		}
	}

	private void onPrepared() {
		// TODO Auto-generated method stub
		Log.v(TAG, "FCE------------onPrepared");
		if (mIF.getMediaSource() != ModeDef.AUDIO) {
			return;
		}
		// 快速切换曲时，在收到onPrepared()后，mediaState可能已经不是PREPARED状态，需要过滤不处理
		int mediaState = mIF.getMediaState();
		if (mediaState != MediaState.PREPARED) {
			return;
		}
		updateStatus();
	}

	private void playStateChanged(int state) {
		Log.v(TAG, "FCE------------playStateChanged state=" + state);
		updatePPStatus();
	}

//	@Override
//	public void OnFCTouchEvent(View view, TOUCH_ACTION action) {
//		Log.v(TAG, "FCE--------------source widget OnHKTouchEvent() View = "
//				+ view.getId());
//		if (action == TOUCH_ACTION.BTN_CLICKED) {
//			switch (view.getId()) {
//			case R.id.widget_pre:
//				mIF.playPre();
//				break;
//
//			case R.id.widget_pp:
//				mIF.changePlayState();
//				break;
//
//			case R.id.widget_next:
//				mIF.playNext();
//				break;
//			}
//		}
//	}

	// 更新状态
	private void updateStatus() {
		Log.v(TAG, "FCE------------updateStatus");
		mId3Info.updateStatus();
		mTimeSeekBar.updateTimeInfo();
		mPlayTime.updateCurTime();
		updatePPStatus();
	}

	// 更新播放暂停状态
	private void updatePPStatus() {
		int mode = mIF.getPlayState();
//		if (mIF.getMediaSource() != ModeDef.AUDIO) {
//			mode = PlayState.PAUSE;
//		}

		Bitmap normal = getBitmap(mode);
		Bitmap press = getBitmapDown(mode);
//		mBtnPP.SetPicUp(normal);
//		mBtnPP.SetPicDown(press);
	}

	private Bitmap getBitmap(int mode) {
		Bitmap bitmap = null;
		Options bitmapOptions = new Options();
		bitmapOptions.inScaled = false;

		switch (mode) {
		case PlayState.PLAY:
			if (mPlay == null) {
//				mPlay = BitmapFactory.decodeResource(mContext.getResources(),
//						R.drawable.widget_source_play_n, bitmapOptions);
			}
			bitmap = mPlay;
			break;

		case PlayState.PAUSE:
		case PlayState.STOP:
			if (mPause == null) {
//				mPause = BitmapFactory.decodeResource(mContext.getResources(),
//						R.drawable.widget_source_pause_n, bitmapOptions);
			}
			bitmap = mPause;
			break;
		}
		return bitmap;
	}

	private Bitmap getBitmapDown(int mode) {
		Bitmap bitmap = null;
		Options bitmapOptions = new Options();
		bitmapOptions.inScaled = false;

		switch (mode) {
		case PlayState.PLAY:
			if (mPlayDown == null) {
//				mPlayDown = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_play_p, bitmapOptions);
			}
			bitmap = mPlayDown;
			break;

		case PlayState.PAUSE:
		case PlayState.STOP:
			if (mPauseDown == null) {
//				mPauseDown = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_pause_p, bitmapOptions);
			}
			bitmap = mPauseDown;
			break;
		}
		return bitmap;
	}
}
