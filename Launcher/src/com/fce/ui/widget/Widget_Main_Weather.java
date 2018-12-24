package com.fce.ui.widget;

import com.android.launcher.R;
import com.android.launcher2.Launcher;
import com.fc.define.ModeDef;
import com.fc.serviceif.CarService_Listener;
import com.fc.window.FCWindowManager;
import com.fce.util.Launcher_IF;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Widget_Main_Weather extends RelativeLayout implements
		CarService_Listener, OnClickListener{

	private final String TAG = this.getClass().getSimpleName();
	private Launcher mLauncher;
	private Launcher_IF mIF;
	private FragmentManager mFragmentManager = null;
//	private Widget_Source_Music mMusicFragment = null;
//	private Widget_Source_Radio mRadioFragment = null;
//	private Widget_Source_BTMusic mBTMusicFragment = null;
	private ImageView mHotArea;
	private Context mContext;


	public Widget_Main_Weather(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = getContext();
		mLauncher = (Launcher) context;
		//mIF = Launcher_IF.getInstance();
		//mIF.registerCarCallBack(this); // 注册服务监听
		//mIF.bindCarService(mContext);

		mFragmentManager = mLauncher.getFragmentManager();
//		mMusicFragment = new Widget_Source_Music();
//		mRadioFragment = new Widget_Source_Radio();
//		mBTMusicFragment = new Widget_Source_BTMusic();
	}

	public void onActivityResume() {
		Log.v(TAG, "onActivityResume");
		//sourceChanged(mIF.getCurSource());
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		//mHotArea = (ImageView) this.findViewById(R.id.widget_dy_bg);
		//mHotArea.setOnClickListener(this);;
		//sourceChanged(mIF.getCurSource());
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		Log.v(TAG, "onAttachedToWindow");
		//sourceChanged(mIF.getCurSource());
	}

	@Override
	public void onServiceConn() {
		Log.v(TAG, "FCE------------onServiceConn");
		//sourceChanged(mIF.getCurSource());
	}

	@Override
	public void sourceChanged(int source) {
		//mIF.getCurSource();
		Log.v(TAG, "FCE------------mIF.getCurSource() =" + mIF.getCurSource());
		if (source == ModeDef.RADIO) {
			//replaceFragment(mRadioFragment);
		} else if (source == ModeDef.BT) {
			//replaceFragment(mBTMusicFragment);
		} else {
			//replaceFragment(mMusicFragment);
		}
	}
	
	@Override
	public void keyChanged(int keyValue) {
	}

	// Fragment替换
	private void replaceFragment(Fragment fragment) {
		if (fragment == null)
			return;

		try {
			FragmentTransaction transaction = mFragmentManager.beginTransaction();
			if (getCurFragment() != fragment) {
				transaction.replace(R.id.widget_weather_fragment, fragment);
			}
			transaction.commitAllowingStateLoss();

		} catch (Exception e) {
		}
	}

	// 获取当前Fragment
	private Fragment getCurFragment() {
		Fragment fragment = mFragmentManager
				.findFragmentById(R.id.widget_weather_fragment);
		return fragment; 
	}

	@Override
	public void onClick(View view) {
//		if(view.getId()==R.id.widget_hot_area){
//			switch (mIF.getCurSource()) {
//			case ModeDef.RADIO:
//				FCWindowManager.getInstance().openActivityByAction(mLauncher,
//						FCWindowManager.ACTION_NAME_RADIO);
//				break;
//				
//			case ModeDef.BT:
//				FCWindowManager.getInstance().openActivityByAction(mLauncher,
//						FCWindowManager.ACTION_NAME_BT_MUSIC);
//				break;
//
//			case ModeDef.AUDIO:
//				FCWindowManager.getInstance().openActivityByAction(mLauncher,
//						FCWindowManager.ACTION_NAME_MUSIC);
//				break;
//				
//			case ModeDef.VIDEO:
//				FCWindowManager.getInstance().openActivityByAction(mLauncher,
//						FCWindowManager.ACTION_NAME_VIDEO);
//				break;
//			}
//		}
		
	}


}