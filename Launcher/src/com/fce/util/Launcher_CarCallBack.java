package com.fce.util;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.fc.define.ModeDef;
import com.fc.define.McuDef.McuFunc;
import com.fc.serviceif.CarService_Listener;

public class Launcher_CarCallBack {

	private final String TAG = this.getClass().getSimpleName();
	private CallBackHandler mHandler = null; // 本地消息处理句柄
	private ArrayList<Launcher_CarListener> mListenerList = new ArrayList<Launcher_CarListener>();
	private ArrayList<CarService_Listener> mCarListenerList = new ArrayList<CarService_Listener>();

	public Launcher_CarCallBack() {
		mHandler = new CallBackHandler();
	}

	//CarService_Listener类
	protected void onServiceConn() {
		for (int i = 0; i < mCarListenerList.size(); i++) {
			CarService_Listener listener = mCarListenerList.get(i);
			if (listener == null)
				continue;

			listener.onServiceConn();
		}
	}

	public void registerCarCallBack(CarService_Listener listener) {
		boolean found = false;
		for (int i = 0; i < mCarListenerList.size(); i++) {
			if (mCarListenerList.get(i) == listener) {
				found = true;
				break;
			}
		}
		if (!found)
			mCarListenerList.add(listener);
	}

	public void unregisterCarCallBack(CarService_Listener listener) {
		for (int i = 0; i < mCarListenerList.size(); i++) {
			if (mCarListenerList.get(i) == listener) {
				mCarListenerList.remove(i);
				break;
			}
		}
	}
	//CarService_Listener类

	public void registerModeCallBack(Launcher_CarListener listener) {
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

	public void unregisterModeCallBack(Launcher_CarListener listener) {
		for (int i = 0; i < mListenerList.size(); i++) {
			if (mListenerList.get(i) == listener) {
				mListenerList.remove(i);
				break;
			}
		}
	}

	public void onDataChange(int mode, int func, int data) {
		if (mHandler == null) {
			return;
		}

		int curMode = Launcher_IF.getInstance().getMode();
		if (mode == curMode || mode == ModeDef.MCU || mode == ModeDef.RADIO) {
			Log.v(TAG, "FCE------------onDataChange mode=" + mode + ", func="
					+ func + ", data=" + data);
			Message message = mHandler.obtainMessage();
			message.what = mode; // 模式ID
			message.arg1 = func; // 功能ID
			message.arg2 = data; // 参数
			mHandler.sendMessage(message);
		}
	}

	// 本地消息处理类
	@SuppressLint("HandlerLeak")
	private class CallBackHandler extends Handler {
		public void handleMessage(Message msg) {
			int curMode = Launcher_IF.getInstance().getMode();
			int mode = msg.what;
			int func = msg.arg1;
			int data = msg.arg2;

			if (mode == curMode || mode == ModeDef.RADIO) {
				for (int i = 0; i < mListenerList.size(); i++) {
					Launcher_CarListener listener = mListenerList.get(i);
					if (listener == null)
						continue;

					listener.onCarDataChange(mode, func, data);
				}

			} else if (mode == ModeDef.MCU) {
				for (int i = 0; i < mCarListenerList.size(); i++) {
					CarService_Listener listener = mCarListenerList.get(i);
					if (listener == null)
						continue;

					switch (func) {
					case McuFunc.SOURCE:
						listener.sourceChanged(data);
						break;
					}
				}
			}
		}
	}
}
