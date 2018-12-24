package com.fce.util;

import android.os.RemoteException;
import android.util.Log;

import com.android.launcher2.LauncherApplication;
import com.fc.aidl.IBTCallBack;
import com.fc.define.ModeDef;
import com.fc.serviceif.BTService_IF;
import com.fc.serviceif.BTService_Listener;

public class BT_IF extends BTService_IF {

	private final String TAG = this.getClass().getSimpleName();
	private static BT_IF mSelf = null;
	private BT_CallBack mBTCallBack = null;

	public BT_IF() {
		mContext = LauncherApplication.getInstance();
		mMode = ModeDef.LAUNCHER;
		mBTCallBack = new BT_CallBack();

		// 以下处理服务回调
		mICallBack = new IBTCallBack.Stub() {
			@Override
			public void onDataChange(int mode, int func, int data)
					throws RemoteException {
				mBTCallBack.onDataChange(mode, func, data);
			}
		};
	}

	// 获取接口实例
	public static BT_IF getInstance() {
		if (mSelf == null) {
			mSelf = new BT_IF();
		}
		return mSelf;
	}

	// 注册媒体服务回调（全局状态变化）
	public void registerBTCallBack(BTService_Listener listener) {
		mBTCallBack.registerBTCallBack(listener);
	}

	// 注销媒体服务回调（全局状态变化）
	public void unregisterBTCallBack(BTService_Listener listener) {
		mBTCallBack.unregisterBTCallBack(listener);
	}

	// 注册媒体服务回调（模块相关变化）
	public void registerModeCallBack(BT_Listener listener) {
		mBTCallBack.registerModeCallBack(listener);
	}

	// 注销媒体服务回调（模块相关变化）
	public void unregisterModeCallBack(BT_Listener listener) {
		mBTCallBack.unregisterModeCallBack(listener);
	}

	@Override
	protected void onServiceConn() {
		mBTCallBack.onServiceConn();
	}

	// 播放
	public void music_play() {
		try {
			Log.v(TAG, "music_play()");
			mServiceIF.music_play();
		} catch (Exception e) {
		}
	}

	// 暂停
	public void music_pause() {
		try {
			Log.v(TAG, "music_pause()");
			mServiceIF.music_pause();
		} catch (Exception e) {
		}
	}

	// 上一曲
	public void music_pre() {
		try {
			mServiceIF.music_pre();
		} catch (Exception e) {
		}
	}

	// 下一曲
	public void music_next() {
		try {
			mServiceIF.music_next();
		} catch (Exception e) {
		}
	}

	// 播放状态
	public boolean music_isPlaying() {
		try {
			return mServiceIF.music_isPlaying();
		} catch (Exception e) {
		}
		return false;
	}

	// 获取ID3标题
	public String music_getTitle() {
		try {
			return mServiceIF.music_getTitle();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取ID3艺术家
	public String music_getArtist() {
		try {
			return mServiceIF.music_getArtist();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取ID3专辑
	public String music_getAlbum() {
		try {
			return mServiceIF.music_getAlbum();
		} catch (Exception e) {
		}
		return null;
	}
}
