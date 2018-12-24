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
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Widget_Main_Navigation extends RelativeLayout implements
		CarService_Listener, OnClickListener{

	private final String TAG = this.getClass().getSimpleName();
	private Launcher mLauncher;
	private Launcher_IF mIF;
	private FragmentManager mFragmentManager = null;
//	private Widget_Source_Music mMusicFragment = null;
//	private Widget_Source_Radio mRadioFragment = null;
//	private Widget_Source_BTMusic mBTMusicFragment = null;
	private ImageView mHotNavi;
	private Context mContext;


	public Widget_Main_Navigation(Context context, AttributeSet attrs) {
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
		mHotNavi = (ImageView) this.findViewById(R.id.widget_navigation_area);
		mHotNavi.setOnClickListener(this);
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
				transaction.replace(R.id.widget_navigation_fragment, fragment);
			}
			transaction.commitAllowingStateLoss();

		} catch (Exception e) {
		}
	}

	// 获取当前Fragment
	private Fragment getCurFragment() {
		Fragment fragment = mFragmentManager
				.findFragmentById(R.id.widget_navigation_fragment);
		return fragment; 
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.widget_navigation_area:
			Intent intent = new Intent();
			PackageManager mPackageManager = mContext.getPackageManager();
			intent = mPackageManager.getLaunchIntentForPackage("com.autonavi.amapauto");
			if(intent != null) {
				mContext.startActivity(intent);
			}
			break;

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
		 default:
			 break;
		}
		
	}


}