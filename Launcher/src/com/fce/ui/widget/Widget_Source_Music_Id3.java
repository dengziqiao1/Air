package com.fce.ui.widget;

import com.android.launcher.R;
import com.fce.ui.widget.Music_Album_Bitmap.ALBUM_SHAPE;
import com.fce.util.Launcher_IF;
import com.fce.util.Media_IF;
import com.fc.define.ModeDef;
import com.fc.define.MediaDef.PlayState;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Widget_Source_Music_Id3 extends LinearLayout {

	// ------------------------------外部接口 start------------------------------
	/**
	 * 更新状态
	 */
	public void updateStatus() {
		int curSource = mLauncherIF.getCurSource();
		//mImgAlbumBg.SetImage(getAlbumBgBitmap(curSource));
		if (curSource != ModeDef.AUDIO) {
			mTrack.setText("");
			mArtist.setText("");
			mAlbum.setText("");
			mImgAlbum.setVisibility(View.GONE);
			return;
		}
		String track = mIF.getPlayId3Title();
		String artist = mIF.getPlayId3Artist();
		String album = mIF.getPlayId3Album();
		mTrack.setText(track);
		mArtist.setText(artist);
		mAlbum.setText(album);

		Bitmap bmp = mBmpAlbum.getAlbumBitmap(ALBUM_SHAPE.SQUARE); // 获得方形专辑图片
		if (bmp == null) {
			if (mImgAlbum.getVisibility() != View.GONE)
				mImgAlbum.setVisibility(View.GONE);

		} else {
			if (mImgAlbum.getVisibility() != View.VISIBLE)
				mImgAlbum.setVisibility(View.VISIBLE);
			//mImgAlbum.SetImage(bmp);
		}
	}

	// ------------------------------外部接口 end------------------------------

	// 内部变量
	private Context mContext;
	private Media_IF mIF;
	private Launcher_IF mLauncherIF;
	private TextView mTrack;
	private TextView mArtist;
	private TextView mAlbum;
	private Music_Album_Bitmap mBmpAlbum; // 专辑图片
	private ImageView mImgAlbum;
	private ImageView mImgAlbumBg;
	private Bitmap mMusicAlbumBg;
	private Bitmap mVideoAlbumBg;

	public Widget_Source_Music_Id3(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;
		mIF = Media_IF.getInstance();
		mLauncherIF = Launcher_IF.getInstance();
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		initView();
	}

	// 初始化控件
	private void initView() {
//		mTrack = (TextView) this.findViewById(R.id.widget_id3_track);
//		mArtist = (TextView) this.findViewById(R.id.widget_id3_artist);
//		mAlbum = (TextView) this.findViewById(R.id.widget_id3_album);
//		mImgAlbum = (ImageView) this.findViewById(R.id.widget_album);
//		mImgAlbumBg = (ImageView) this.findViewById(R.id.widget_album_bg);

		// 设置专辑图片尺寸
		mBmpAlbum = new Music_Album_Bitmap(mContext);
		mBmpAlbum.setMeasure(mImgAlbum.getLayoutParams().width,
				mImgAlbum.getLayoutParams().height);

		// 更新数据
		updateStatus();
	}
	
	private Bitmap getAlbumBgBitmap(int source) {
		Bitmap bitmap = null;
		Options bitmapOptions = new Options();
		bitmapOptions.inScaled = false;
		Log.v("xml", "getAlbumBgBitmap");
		switch (source) {
		case ModeDef.AUDIO:
			if (mMusicAlbumBg == null) {
//				mMusicAlbumBg = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_album_bg_music, bitmapOptions);
			}
			Log.v("xml", "mMusicAlbumBg");
			bitmap = mMusicAlbumBg;
			break;

		case ModeDef.VIDEO:
			if (mVideoAlbumBg == null) {
//				mVideoAlbumBg = BitmapFactory.decodeResource(
//						mContext.getResources(),
//						R.drawable.widget_source_album_bg_video, bitmapOptions);
			}
			Log.v("xml", "mVideoAlbumBg");
			bitmap = mVideoAlbumBg;
			break;
		}
		return bitmap;
	}
}
