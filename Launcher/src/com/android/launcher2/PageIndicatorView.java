
package com.android.launcher2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import com.android.launcher.R;

//xmc.launcher.indicator
public class PageIndicatorView extends View {
	
	private final String TAG = "PageIndicatorView";

	private int curPage, myPage;
	private int totalPage;
	private int pointSpace = 10;
	private int posY;
	private int pointWidth;
	private int pointHeight;
	private int IndicatorLayoutHeight;	
	
	private Bitmap mPageIconActiveBitmap = null;	
	private Bitmap mPageIconNoActiveBitmap = null;
	private Activity mContext =null ;
	
	public PageIndicatorView(Context context, AttributeSet attrs) {
	    super(context, attrs);	
		
	    mContext = (Activity)context;
		// TODO Auto-generated constructor stub
	    
		mPageIconActiveBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.page_indicator_1);
		
		mPageIconNoActiveBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.page_indicator_2);
		
		Drawable mPageIcon = getResources().getDrawable(R.drawable.page_indicator_1);
				
		pointWidth = mPageIcon.getIntrinsicWidth();
		pointHeight  = mPageIcon.getIntrinsicHeight();
		
		IndicatorLayoutHeight = (int)getResources().getDimension(R.dimen.page_indicator_height);
	}
	
	public void setValue(int c, int t) {
		curPage = c;
		totalPage = t;
		myPage = curPage;
		
		//Utility.Log("pageIndiactor set value ."+c+" . "+t);
	}
	
	public void setCurrentPage(int c) {
		curPage = c;
		myPage = curPage;
		//Utility.Log("pageIndiactor set currentValue ."+c);
	}
	
	public int getCurrPage() {
		return myPage;
	}
	
	@Override
    public void draw(Canvas canvas) {
    	super.draw(canvas);
    }

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		Bitmap mPageIcon;
		
//		DisplayMetrics dM = new DisplayMetrics() ;
//		int width = dM.widthPixels;
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mContext.getWindowManager().getDefaultDisplay().getMetrics(
                displayMetrics);
        int width = displayMetrics.widthPixels;
		
		int layoutwidth = (pointWidth + pointSpace)*totalPage ;
		
		int offset_x = (width - layoutwidth)/2 ;
		int offset_y = (int)(IndicatorLayoutHeight -pointHeight)/2;
		
		//Log.d(TAG, "onDraw width =" +width + ",layoutwidth =" +layoutwidth 
		//		+",offset_x =" +offset_x +",offset_y =" +offset_y );
		
		for (int i = 0; i < totalPage; i++) {
			if (curPage == i) {
				mPageIcon = mPageIconActiveBitmap ;
			} else {
				mPageIcon = mPageIconNoActiveBitmap ;
			}

			if (mPageIcon != null) {
				canvas.drawBitmap(mPageIcon, offset_x + (pointWidth + pointSpace) * i ,
						offset_y, null);
			}
		}
	}	
}