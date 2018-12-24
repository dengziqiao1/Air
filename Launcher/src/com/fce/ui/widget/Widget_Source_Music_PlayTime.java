package com.fce.ui.widget;

import com.android.launcher.R;
import com.fce.util.Media_IF;
import com.fc.define.ModeDef;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Widget_Source_Music_PlayTime extends LinearLayout {

	private TextView mPlayTime;
	private Context mContext;
	private Media_IF mIF;

	/**
	 * 更新当前播放时间
	 */
	public void updateCurTime() {
		int curSource = mIF.getMediaSource();
		if (curSource == ModeDef.AUDIO || curSource == ModeDef.VIDEO
				|| curSource == ModeDef.IMAGE) {
			int curTime = (int) mIF.getPosition();
			int totleTime = (int) mIF.getDuration();
			String curTimeStr = TimeFormat(curTime);
			String totleTimeStr = TimeFormat(totleTime);
			mPlayTime.setText(curTimeStr + "/" + totleTimeStr);
		} else {
			mPlayTime.setText("");
		}
	}

	public Widget_Source_Music_PlayTime(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mIF = Media_IF.getInstance();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	// 初始化控件
	private void initView() {
		// 获取控件
		//mPlayTime = (TextView) this.findViewById(R.id.widget_music_playtime);

	}

	// 转化为时间格式（分秒）
	private String TimeFormat(int num) {
		String sTime = "";
		int minute = (int) (num / 60);
		int second = (int) (num % 60);
		String sMinute = ConvertToDoubleNum(minute);
		String sSecond = ConvertToDoubleNum(second);
		sTime = sMinute + ":" + sSecond;
		return sTime;
	}

	// 将数字转化为两位字符串
	private String ConvertToDoubleNum(int num) {
		String result = "";
		if (num < 10) {
			result = "0" + num;
		} else {
			result = num + "";
		}
		return result;
	}

}
