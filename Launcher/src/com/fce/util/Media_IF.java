package com.fce.util;

import android.graphics.Bitmap;
import android.os.RemoteException;
import com.android.launcher2.LauncherApplication;
import com.fc.aidl.IMediaCallBack;
import com.fc.define.ModeDef;
import com.fc.define.MediaDef.MediaState;
import com.fc.define.MediaDef.PlayState;
import com.fc.define.MediaDef.RandomMode;
import com.fc.define.MediaDef.RepeatMode;
import com.fc.define.MediaDef.ScanState;
import com.fc.serviceif.MediaService_IF;
import com.fc.serviceif.MediaService_Listener;

public class Media_IF extends MediaService_IF {

	private final String TAG = this.getClass().getSimpleName();
	private static Media_IF mSelf = null;
	private Media_CallBack mMediaCallBack = null;

	public Media_IF() {
		mContext = LauncherApplication.getInstance();
		mMode = ModeDef.LAUNCHER;
		mMediaCallBack = new Media_CallBack();

		// 以下处理服务回调
		mICallBack = new IMediaCallBack.Stub() {
			@Override
			public void onDataChange(int mode, int func, int data1, int data2)
					throws RemoteException {
				mMediaCallBack.onDataChange(mode, func, data1, data2);
			}
		};
	}

	// 获取接口实例
	public static Media_IF getInstance() {
		if (mSelf == null) {
			mSelf = new Media_IF();
		}
		return mSelf;
	}

	// 注册媒体服务回调（全局状态变化）
	public void registerMediaCallBack(MediaService_Listener listener) {
		mMediaCallBack.registerMediaCallBack(listener);
	}

	// 注销媒体服务回调（全局状态变化）
	public void unregisterMediaCallBack(MediaService_Listener listener) {
		mMediaCallBack.unregisterMediaCallBack(listener);
	}

	// 注册媒体服务回调（模块相关变化）
	public void registerModeCallBack(Media_Listener listener) {
		mMediaCallBack.registerModeCallBack(listener);
	}

	// 注销媒体服务回调（模块相关变化）
	public void unregisterModeCallBack(Media_Listener listener) {
		mMediaCallBack.unregisterModeCallBack(listener);
	}

	@Override
	protected void onServiceConn() {
		mMediaCallBack.onServiceConn();
	}
	
	// 获取当前媒体源
	public int getMediaSource() {
		try {
			return mServiceIF.getMediaSource();
		} catch (Exception e) {
		}
		return ModeDef.NULL;
	}
	
	// 获取当前扫描状态
	public int getScanState() {
		try {
			return mServiceIF.getCurScanState();
		} catch (Exception e) {
		}
		return ScanState.IDLE;
	}

	// 获取当前媒体状态
	public int getMediaState() {
		try {
			return mServiceIF.getCurMediaState();
		} catch (Exception e) {
		}
		return MediaState.IDLE;
	}

	// 播放
	public void play(int pos) {
		try {
			mServiceIF.play(pos);
		} catch (Exception e) {
		}
	}

	// 上一曲
	public void playPre() {
		try {
			mServiceIF.pre();
		} catch (Exception e) {
		}
	}

	// 下一曲
	public void playNext() {
		try {
			mServiceIF.next();
		} catch (Exception e) {
		}
	}

	// 改变播放状态
	public void changePlayState() {
		try {
			int state = getPlayState();
			if (state == PlayState.PLAY) {
				state = PlayState.PAUSE;
			} else {
				state = PlayState.PLAY;
			}
			setPlayState(state);

		} catch (Exception e) {
		}
	}

	// 设置播放状态
	public void setPlayState(int state) {
		try {
			mServiceIF.setPlayState(state);
		} catch (Exception e) {
		}
	}

	// 获取播放状态
	public int getPlayState() {
		try {
			return mServiceIF.getPlayState();
		} catch (Exception e) {
		}
		return PlayState.STOP;
	}

	// 改变循环模式
	public void changeRepeatMode() {
		try {
			int mode = getRepeatMode();
			if (mode == RepeatMode.OFF) {
				mode = RepeatMode.CIRCLE;
			} else if (mode == RepeatMode.CIRCLE) {
				mode = RepeatMode.ONE;
			} else {
				mode = RepeatMode.OFF;
			}
			setRepeatMode(mode);

		} catch (Exception e) {
		}
	}

	// 设置循环模式
	public void setRepeatMode(int mode) {
		try {
			mServiceIF.setRepeatMode(mode);
		} catch (Exception e) {
		}
	}

	// 获取循环模式
	public int getRepeatMode() {
		try {
			return mServiceIF.getRepeatMode();
		} catch (Exception e) {
		}
		return RepeatMode.OFF;
	}

	// 改变随机模式
	public void changeRandomMode() {
		try {
			int mode = getRandomMode();
			if (mode == RandomMode.OFF) {
				mode = RandomMode.ON;
			} else {
				mode = RepeatMode.OFF;
			}
			setRandomMode(mode);

		} catch (Exception e) {
		}
	}

	// 设置随机模式
	public void setRandomMode(int mode) {
		try {
			mServiceIF.setRandomMode(mode);
		} catch (Exception e) {
		}
	}

	// 获取随机模式
	public int getRandomMode() {
		try {
			return mServiceIF.getRandomMode();
		} catch (Exception e) {
		}
		return RandomMode.OFF;
	}

	// 获取播放总时间
	public int getDuration() {
		try {
			return mServiceIF.getDuration() / 1000;
		} catch (Exception e) {
		}
		return 0;
	}

	// 设置播放当前时间
	public void setPosition(int time) {
		try {
			mServiceIF.setPosition(time * 1000);
		} catch (Exception e) {
		}
	}

	// 获取播放当前时间
	public int getPosition() {
		try {
			return mServiceIF.getPosition() / 1000;
		} catch (Exception e) {
		}
		return 0;
	}

	// 获取文件路径
	public String getPlayFilePath() {
		try {
			return mServiceIF.getPlayFilePath();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取文件名
	public String getPlayFileName() {
		try {
			return mServiceIF.getPlayFileName();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取歌曲名
	public String getPlayId3Title() {
		try {
			return mServiceIF.getPlayId3Title();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取艺术家
	public String getPlayId3Artist() {
		try {
			return mServiceIF.getPlayId3Artist();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取专辑名
	public String getPlayId3Album() {
		try {
			return mServiceIF.getPlayId3Album();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取作曲家
	public String getPlayId3Composer() {
		try {
			return mServiceIF.getPlayId3Composer();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取风格
	public String getPlayId3Genre() {
		try {
			return mServiceIF.getPlayId3Genre();
		} catch (Exception e) {
		}
		return null;
	}

	// 获取专辑图片
	public Bitmap getPlayId3AlbumBmp() {
		try {
			return mServiceIF.getPlayId3AlbumBmp();
		} catch (Exception e) {
		}
		return null;
	}

}
