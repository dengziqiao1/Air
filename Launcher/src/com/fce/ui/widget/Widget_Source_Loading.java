package com.fce.ui.widget;

import com.android.launcher.R;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Widget_Source_Loading extends Fragment {

	// ------------------------------外部接口 start------------------------------
	// ------------------------------外部接口 end------------------------------

	// 内部变量
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated constructor stub
		mContext = getActivity();
		// 初始化控件
		View view = inflater.inflate(R.layout.fc_widget_source_loading, null);
		return view;
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
	}
}
