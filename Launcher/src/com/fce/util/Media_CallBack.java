package com.fce.util;

import java.util.ArrayList;
import com.fc.serviceif.MediaService_Listener;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

public class Media_CallBack {

	private final String TAG = this.getClass().getSimpleName();
	private CallBackHandler mHandler = null; // 本地消息处理句柄
	private ArrayList<Media_Listener> mListenerList = new ArrayList<Media_Listener>();
	private ArrayList<MediaService_Listener> mMediaListenerList = new ArrayList<MediaService_Listener>();

	public Media_CallBack() {
		mHandler = new CallBackHandler();
	}

	protected void onServiceConn() {
		for (int i = 0; i < mMediaListenerList.size(); i++) {
			MediaService_Listener listener = mMediaListenerList.get(i);
			if (listener == null)
				continue;

			listener.onMediaServiceConn();
		}
	}

	public void registerMediaCallBack(MediaService_Listener listener) {
		boolean found = false;
		for (int i = 0; i < mMediaListenerList.size(); i++) {
			if (mMediaListenerList.get(i) == listener) {
				found = true;
				break;
			}
		}
		if (!found)
			mMediaListenerList.add(listener);
	}

	public void unregisterMediaCallBack(MediaService_Listener listener) {
		for (int i = 0; i < mMediaListenerList.size(); i++) {
			if (mMediaListenerList.get(i) == listener) {
				mMediaListenerList.remove(i);
				break;
			}
		}
	}

	public void registerModeCallBack(Media_Listener listener) {
		boolean found = false;
		for (int i = 0; i < mListenerList.size(); i++) {
			if (mListenerList.get(i) == listener) {
				found = true;
				break;
			}
		}
		if (!found)
			mListenerList.add(listener);
	}

	public void unregisterModeCallBack(Media_Listener listener) {
		for (int i = 0; i < mListenerList.size(); i++) {
			if (mListenerList.get(i) == listener) {
				mListenerList.remove(i);
				break;
			}
		}
	}

	public class DataElement {
		public int mData1 = 0;
		public int mData2 = 0;
		
		public DataElement(int data1, int data2) {
			mData1 = data1;
			mData2 = data2;
		}
	}
	
	public void onDataChange(int mode, int func, int data1, int data2) {
		if (mHandler == null) {
			return;
		}

		Log.v(TAG, "FCE------------onDataChange mode=" + mode + ", func="
				+ func + ", data1=" + data1 + ", data2=" + data2);
		Message message = mHandler.obtainMessage();
		message.what = mode; // 模式ID
		message.arg1 = func; // 功能ID
		message.obj = new DataElement(data1, data2); // 参数
		mHandler.sendMessage(message);
	}

	// 本地消息处理类
	@SuppressLint("HandlerLeak")
	private class CallBackHandler extends Handler {
		public void handleMessage(Message msg) {
			int mode = msg.what;
			int func = msg.arg1;
			DataElement datas = (DataElement) msg.obj;

			for (int i = 0; i < mListenerList.size(); i++) {
				Media_Listener listener = mListenerList.get(i);
				if (listener == null)
					continue;

				listener.onMediaDataChange(mode, func, datas.mData1,
						datas.mData2);
			}
		}
	}
}
