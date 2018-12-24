package com.fce.ui.widget;

import com.android.launcher.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Widget_Three_Colourful extends LinearLayout implements OnClickListener{
	// 内部变量
	private Context mContext;
	private ImageView imgEat;
	private ImageView imgPlay;
	private ImageView imgMusic;
	private ImageView imgNews;
	private ImageView imgCars;

	public Widget_Three_Colourful(Context context) {
		super(context);
		Init(context);
	}

	public Widget_Three_Colourful(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init(context);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	public void onActivityResume() {
		// Log.v(TAG, "onActivityResume");
		// sourceChanged(mIF.getCurSource());
	}

	// 初始化控件
	private void Init(Context context) {
		mContext = context;
		// 加载控件
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_three_colourful, null);
		imgEat = (ImageView)view.findViewById(R.id.near_switch_onoff);
		imgPlay = (ImageView)view.findViewById(R.id.play_switch_onoff);
		imgMusic = (ImageView)view.findViewById(R.id.enjoy_switch_onoff);
		imgNews = (ImageView)view.findViewById(R.id.news_switch_onoff);
		imgCars = (ImageView)view.findViewById(R.id.cars_switcher_onoff);

		this.addView(view);
	}

	@Override
	public void onClick(View view) {
		 switch (view.getId()) {
         default:
             break;
     }
	}

}