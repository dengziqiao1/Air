package com.fce.ui.widget;

import com.android.launcher.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Widget_One_Quiker extends LinearLayout implements OnClickListener{
	// 内部变量
	private Context mContext;
	private ImageView imgDriverWarn;
	private ImageView imgMergeAssist;
	private ImageView imgTspMark;
	private ImageView imgAutomaticLight;
	private ImageView imgDoubleLight;
	private ImageView imgDriverKnowledge;
	private ImageView imgRecommendRadio;
	private ImageView imgRecommendMusic;
	private ImageView imGupdateApp;
	private ImageView imgRecommendNews;
	
    private boolean view1 = true;
    private boolean view2 = true;
    private boolean view3 = true;
    private boolean view4 = true;
    private boolean view5 = true;
    private boolean view6 = true;
    private boolean view7 = true;
    private boolean view8 = true;
    private boolean view9 = true;
    private boolean view10 = true;
	
	public Widget_One_Quiker(Context context) {
		super(context);
		Init(context);
	}

	public Widget_One_Quiker(Context context, AttributeSet attrs) {
		super(context, attrs);
		Init(context);
	}

	public void onActivityResume() {
		// Log.v(TAG, "onActivityResume");
		// sourceChanged(mIF.getCurSource());
		
	}
	

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}
	
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		super.onAttachedToWindow();
	}

	// 初始化控件
	private void Init(Context context) {
		mContext = context;
		// 加载控件
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_one_source, null);
		imgDriverWarn = (ImageView)view.findViewById(R.id.driver_switch_onoff);
		imgMergeAssist = (ImageView)view.findViewById(R.id.mergeline_switch_onoff);
		imgTspMark = (ImageView)view.findViewById(R.id.tsp_switch_onoff);
		imgAutomaticLight = (ImageView)view.findViewById(R.id.automatic_switch_onoff);
		imgDoubleLight = (ImageView)view.findViewById(R.id.braking_switcher_onoff);
		imgDriverKnowledge = (ImageView)view.findViewById(R.id.driver_knowledge);
		imgRecommendRadio = (ImageView)view.findViewById(R.id.recommend_radio_station);
		imgRecommendMusic = (ImageView)view.findViewById(R.id.recommend_music);
		imGupdateApp = (ImageView)view.findViewById(R.id.update_app);
		imgRecommendNews = (ImageView)view.findViewById(R.id.recommend_news);
		
		//添加点击事件
		imgDriverWarn.setOnClickListener(this);
		imgMergeAssist.setOnClickListener(this);
		imgTspMark.setOnClickListener(this);
		imgAutomaticLight.setOnClickListener(this);
		imgDoubleLight.setOnClickListener(this);
		imgDriverKnowledge.setOnClickListener(this);
		imgRecommendRadio.setOnClickListener(this);
		imgRecommendMusic.setOnClickListener(this);
		imGupdateApp.setOnClickListener(this);
		imgRecommendNews.setOnClickListener(this);
		
		this.addView(view);
		
		

	}

	@Override
	public void onClick(View view) {
		 switch (view.getId()) {
         case R.id.driver_switch_onoff:
              if(view1 == true/*mIF.getTiredDrvierState()*/){
            	  imgDriverWarn.setBackgroundResource(R.drawable.laun_drivertired_p);
                  //mIF.closeTiredDrvier()
                  view1 = false;
              }else{
                  //mIF.openTiredDrvier()
            	  imgDriverWarn.setBackgroundResource(R.drawable.laun_drivertired_n);
                  view1 = true;
              }
             break;
         case R.id.mergeline_switch_onoff:
             if(view2 == true/*mIF.getTiredDrvierState()*/){
            	 imgMergeAssist.setBackgroundResource(R.drawable.laun_mergeline_p);
                 //mIF.closeTiredDrvier()
                 view2 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgMergeAssist.setBackgroundResource(R.drawable.laun_mergeline_n);
                 view2 = true;
             }
             break;
         case R.id.tsp_switch_onoff:
             if(view3 == true/*mIF.getTiredDrvierState()*/){
            	 imgTspMark.setBackgroundResource(R.drawable.laun_tsr_p);
                 //mIF.closeTiredDrvier()
                 view3 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgTspMark.setBackgroundResource(R.drawable.laun_tsr_n);
                 view3 = true;
             }
             break;
         case R.id.automatic_switch_onoff:
             if(view4 == true/*mIF.getTiredDrvierState()*/){
            	 imgAutomaticLight.setBackgroundResource(R.drawable.laun_lightauto_p);
                 //mIF.closeTiredDrvier()
                 view4 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgAutomaticLight.setBackgroundResource(R.drawable.laun_lightauto_n);
                 view4 = true;
             }
             break;
         case R.id.braking_switcher_onoff:
             if(view5 == true/*mIF.getTiredDrvierState()*/){
            	 imgDoubleLight.setBackgroundResource(R.drawable.laun_doublelight_p);
                 //mIF.closeTiredDrvier()
                 view5 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgDoubleLight.setBackgroundResource(R.drawable.laun_doublelight_n);
                 view5 = true;
             }
             break;
         case R.id.driver_knowledge:
             if(view6 == true/*mIF.getTiredDrvierState()*/){
            	 imgDriverKnowledge.setBackgroundResource(R.drawable.laun_taiya_p);
                 //mIF.closeTiredDrvier()
                 view6 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgDriverKnowledge.setBackgroundResource(R.drawable.laun_taiya_n);
                 view6 = true;
             }
             break;
         case R.id.recommend_radio_station:
             if(view7 == true/*mIF.getTiredDrvierState()*/){
            	 imgRecommendRadio.setBackgroundResource(R.drawable.laun_all360_p);
                 //mIF.closeTiredDrvier()
                 view7 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgRecommendRadio.setBackgroundResource(R.drawable.laun_all360_n);
                 view7 = true;
             }
             break;
         case R.id.recommend_music:
             if(view8 == true/*mIF.getTiredDrvierState()*/){
            	 imgRecommendMusic.setBackgroundResource(R.drawable.laun_fcw_p);
                 //mIF.closeTiredDrvier()
                 view8 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgRecommendMusic.setBackgroundResource(R.drawable.laun_fcw_n);
                 view8 = true;
             }
             break;
         case R.id.update_app:
             if(view9 == true/*mIF.getTiredDrvierState()*/){
            	 imGupdateApp.setBackgroundResource(R.drawable.laun_ldw_p);
                 //mIF.closeTiredDrvier()
                 view9 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imGupdateApp.setBackgroundResource(R.drawable.laun_ldw_n);
                 view9 = true;
             }
             break;
         case R.id.recommend_news:
             if(view10 == true/*mIF.getTiredDrvierState()*/){
            	 imgRecommendNews.setBackgroundResource(R.drawable.laun_speedcross_p);
                 //mIF.closeTiredDrvier()
                 view10 = false;
             }else{
                 //mIF.openTiredDrvier()
            	 imgRecommendNews.setBackgroundResource(R.drawable.laun_speedcross_n);
                 view10 = true;
             }
             break;
         default:
             break;
     }

		
	}
	

}