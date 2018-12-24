package com.fce.ui.widget;

import com.fce.util.Media_IF;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class Music_Album_Bitmap {

	// ------------------------------外部接口 start------------------------------
	/**
	 * 获取专辑图片
	 * 
	 * @param
	 */
	public Bitmap getAlbumBitmap(ALBUM_SHAPE shape) {
		Bitmap bmpAlbum = null;
		Bitmap bmp = Media_IF.getInstance().getPlayId3AlbumBmp();
		if (bmp != null) { // 存在专辑图片
			int srcWidth = bmp.getWidth();
			int srcHeight = bmp.getHeight();
			int desWidth = mWidth;
			int desHeight = mHeight;
			if (srcWidth == 0 || srcHeight == 0) { // 图片非法，不处理

			} else {
				float scaleX = (float) desWidth / srcWidth;
				float scaleY = (float) desHeight / srcHeight;
				Matrix matrix = new Matrix();
				matrix.postScale(scaleX, scaleY);
				Bitmap scaleBmp = Bitmap.createBitmap(bmp, 0, 0, srcWidth,
						srcHeight, matrix, true);
				if (shape == ALBUM_SHAPE.SQUARE) { // 方形
					bmpAlbum = scaleBmp;

				} else if (shape == ALBUM_SHAPE.CIRCLE) { // 圆形
					Paint paint = new Paint();
					paint.setAntiAlias(true);
					paint.setXfermode(new PorterDuffXfermode(
							PorterDuff.Mode.DST_IN));
					Bitmap bmpCircle = createCircleBitmap((int) (desWidth / 2 + 0.5)); // 生成一张圆形黑色图
					Bitmap bmpBlank = Bitmap.createBitmap(desWidth, desWidth,
							Config.ARGB_8888);
					Canvas canvas = new Canvas(bmpBlank);
					canvas.drawBitmap(scaleBmp, 0, 0, null); // 画原图
					canvas.drawBitmap(bmpCircle, 0, 0, paint); // 原图和圆形图遮罩混合
					paint.setXfermode(null);
					bmpAlbum = bmpBlank;
					// 释放无用图片
					scaleBmp.recycle();
					scaleBmp = null;
					bmpCircle.recycle();
					bmpCircle = null;
				}
			}
		}
		return bmpAlbum;
	}

	/**
	 * 设置专辑图片的宽高
	 * 
	 * @param
	 */
	public void setMeasure(int width, int height) {
		mWidth = width;
		mHeight = height;
	}

	// 专辑图片形状
	public enum ALBUM_SHAPE {
		SQUARE, CIRCLE
	}

	// ------------------------------外部接口 end------------------------------

	// 内部变量
	private Context mContext;
	private int mWidth = 100;
	private int mHeight = 100;

	public Music_Album_Bitmap(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	private Bitmap createCircleBitmap(int radius) {
		Bitmap bmp = Bitmap.createBitmap(radius * 2, radius * 2,
				Config.ARGB_8888);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.BLACK);
		Canvas canvas = new Canvas(bmp);
		canvas.drawCircle(radius, radius, radius, paint);
		return bmp;
	}
}
