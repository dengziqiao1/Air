package com.fce.util;

import android.os.RemoteException;
import android.util.Log;

import com.android.launcher2.LauncherApplication;
import com.fc.aidl.ICarCallBack;
import com.fc.define.ModeDef;
import com.fc.define.RadioDef.Band_5;
import com.fc.serviceif.CarService_IF;
import com.fc.serviceif.CarService_Listener;

public class Launcher_IF extends CarService_IF {

	private final String TAG = this.getClass().getSimpleName();
	private static Launcher_IF mSelf = null;
	private Launcher_CarCallBack mCarCallBack = null;

	public Launcher_IF() {
		mContext = LauncherApplication.getInstance();
		mMode = ModeDef.LAUNCHER;
		mCarCallBack = new Launcher_CarCallBack();
		
		// 以下处理服务回调
		mICallBack = new ICarCallBack.Stub() {
			@Override
			public void onDataChange(int mode, int func, int data)
					throws RemoteException {
				// TODO Auto-generated method stub
				mCarCallBack.onDataChange(mode, func, data);
			}
		};
	}
	
	// 获取接口实例
	public static Launcher_IF getInstance() {
		if (mSelf == null) {
			mSelf = new Launcher_IF();
		}
		return mSelf;
	}

	// 获取模式
	public int getMode() {
		return mMode;
	}
	
	// 服务已经绑定成功，需要刷新动作
	@Override
	protected void onServiceConn() {
		// TODO Auto-generated method stub
		mCarCallBack.onServiceConn();
	}

	// 注册车载服务回调（全局状态变化）
	public void registerCarCallBack(CarService_Listener listener) {
		mCarCallBack.registerCarCallBack(listener);
	}

	// 注销车载服务回调（全局状态变化）
	public void unregisterCarCallBack(CarService_Listener listener) {
		mCarCallBack.unregisterCarCallBack(listener);
	}
	
	// 注册车载服务回调（模块相关变化）
	public void registerModeCallBack(Launcher_CarListener listener) {
		mCarCallBack.registerModeCallBack(listener);
	}

	// 注销车载服务回调（模块相关变化）
	public void unregisterModeCallBack(Launcher_CarListener listener) {
		mCarCallBack.unregisterModeCallBack(listener);
	}
	
	// 设置当前源
	public boolean setCurSource(int source) {
		try {
			return mServiceIF.mcu_setCurSource(source);
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }	
		return false;
	}

	// 获取当前源
	public int getCurSource() {
		try {
			return mServiceIF.mcu_getCurSource();
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }	
		return ModeDef.NULL;
	}

	// 获取频率值
	public int getCurFreq() {
		int freq = 0;
		try {
			freq = mServiceIF.radio_getCurFreq();
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }	
		return freq;
	}
	
	// 左步进
	public void setPreStep() {
		try {
			mServiceIF.radio_setPreStep();
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }
	}

	// 右步进
	public void setNextStep() {
		try {
			mServiceIF.radio_setNextStep();
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }
	}
	
	// 设置波段
	public void setCurBand() {
		try {
			int band = Band_5.FM1;
			switch (getCurBand()) {
			case Band_5.FM1:
				band = Band_5.FM2; 
				break;
			case Band_5.FM2: 
				band = Band_5.FM3; 
				break;
			case Band_5.FM3:
				band = Band_5.AM1; 
				break;
			case Band_5.AM1: 
				band = Band_5.AM2; 
				break;
			case Band_5.AM2:
				band = Band_5.FM1;
				break;
			}
			mServiceIF.radio_setCurBand(band);
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }
	}
	
	// 获取波段
	public int getCurBand() {
		int band = Band_5.FM1;
		try {
			band = mServiceIF.radio_getCurBand();
        } catch (Exception e) {
    		Log.e(TAG, "FCE------------interface e="+e.getMessage());
        }	
		return band;
	}
}
