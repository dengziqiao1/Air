package com.fce.ui.widget;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.launcher.R;
import com.fce.util.Launcher_CarListener;
import com.fce.util.Launcher_IF;
import com.fc.define.ModeDef;
import com.fc.define.RadioDef.Band_5;
import com.fc.define.RadioDef.RadioFunc;
import com.fc.serviceif.CarService_Listener;

public class Widget_Source_Radio extends Fragment implements
		Launcher_CarListener, CarService_Listener{

	private static final String TAG = "Launcher.Widget_Source_Radio";
	private Launcher_IF mIF;
	private TextView mBand;
	private TextView mFreq;
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mIF = Launcher_IF.getInstance();
		mIF.registerCarCallBack(this);
		mIF.registerModeCallBack(this);
		mIF.bindCarService(mContext);

		View view = inflater.inflate(R.layout.fc_widget_source_radio, null);
//		FCButtonText bandBtn = (FCButtonText) view
//				.findViewById(R.id.widget_radio_band_btn);
//		FCButtonText preBtn = (FCButtonText) view
//				.findViewById(R.id.widget_radio_pre_btn);
//		FCButtonText nextBtn = (FCButtonText) view
//				.findViewById(R.id.widget_radio_next_btn);
		mBand = (TextView) view.findViewById(R.id.widget_radio_band);
		mFreq = (TextView) view.findViewById(R.id.widget_radio_freq);
//		bandBtn.SetFCTouchListener(this);
//		preBtn.SetFCTouchListener(this);
//		nextBtn.SetFCTouchListener(this);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
		mIF.registerCarCallBack(this);
		mIF.registerModeCallBack(this);
		updateState();
	}

	@Override
	public void onStop() {
		super.onStop();
		mIF.unregisterCarCallBack(this);
		mIF.unregisterModeCallBack(this);
	}

//	@Override
//	public void OnFCTouchEvent(View view, TOUCH_ACTION action) {
//		if (action == TOUCH_ACTION.BTN_CLICKED) {
//			switch (view.getId()) {
//			case R.id.widget_radio_band_btn:
//				mIF.setCurBand();
//				break;
//
//			case R.id.widget_radio_pre_btn:
//				mIF.setPreStep();
//				break;
//
//			case R.id.widget_radio_next_btn:
//				mIF.setNextStep();
//				break;
//			}
//		}
//	}

	@Override
	public void onServiceConn() {
		// TODO Auto-generated method stub
		mIF.registerCarCallBack(this);
		mIF.registerModeCallBack(this);
		updateState();
	}

	@Override
	public void sourceChanged(int source) {
	}

	@Override
	public void keyChanged(int keyValue) {
	}

	@Override
	public void onCarDataChange(int mode, int func, int data) {
		if (mode == ModeDef.RADIO) {
			switch (func) {
			case RadioFunc.FREQ:
				freqChanged(data);
				break;
			case RadioFunc.BAND:
				bandChanged(data);
				break;
			}
		}
	}

	private void updateState() {
		freqChanged(mIF.getCurFreq());
		bandChanged(mIF.getCurBand());
	}

	private void freqChanged(int data) {
		String sFreq = freqIntToString(data);
		mFreq.setText(sFreq);
	}

	private void bandChanged(int data) {
		Log.v(TAG, "bandChanged(int data) data = "+data);
		String curBand = "FM1";
		switch (data) {
		case Band_5.FM1:
			curBand = "FM1"; 
			break;
		case Band_5.FM2:
			curBand = "FM2"; 
			break;
		case Band_5.FM3:
			curBand = "FM3";
			break;
		case Band_5.AM1:
			curBand = "AM1"; 
			break;
		case Band_5.AM2:
			curBand = "AM2"; 
			break;
		}
		mBand.setText(curBand);
	}

	public String freqIntToString(int freq) {
		String result = freq + "";
		if (result.length() > 5)
			return null;

		StringBuffer sBuffer = new StringBuffer(10);
		sBuffer.append(result);
		if (freq > 5000) {
			if (freq > 9999) {
				result = sBuffer.insert(3, ".").toString();
			} else {
				result = sBuffer.insert(2, ".").toString();
			}
		}
		return result;
	}

}
