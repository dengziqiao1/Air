package com.fce.air;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AirActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View
        .OnClickListener {
    private VerticalSeekBar leftVerSeekBar, rightVerSeekBar;  //垂直进度条
    private SeekBar leftLeveSeekBar, rightLeveSeekBar; //风量左右 等级进度条
    private ImageView leftLeveLess, rightLeveLess, leftLeveAdd, rightLeveAdd; // 空调风量加减 键
    //左右两边显示温度
    private TextView leftSeekBarTex, leftSeekBarTex1, rightSeekBarTex, rightSeekBarTex1;
    //空调风量等级
    private float leftVerProgress, rightVerProgress, leftLeveProgeress, rightLeveProgeress;

    private ImageView airLeftWin, airRightWin, airLeftFace, airLeftFeet, airLeftFaceFeet,
            airRightFace, airRightFeet, airRightFaceFeet;//左 右 风向
    private int leftWindNum = 0, rightWindNum = 0;     //风向 显示 值

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air);
        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {
        leftVerSeekBar = this.findViewById(R.id.air_seekBar);
        rightVerSeekBar = this.findViewById(R.id.air_seekBar2);

        leftSeekBarTex = this.findViewById(R.id.air_seekBar_left_text);
        leftSeekBarTex1 = this.findViewById(R.id.air_seekBar_left_text1);

        rightSeekBarTex = this.findViewById(R.id.air_seekBar_right_text);
        rightSeekBarTex1 = this.findViewById(R.id.air_seekBar_right_text1);

        leftLeveSeekBar = this.findViewById(R.id.air_level);
        rightLeveSeekBar = this.findViewById(R.id.air_right_level);

        leftLeveLess = this.findViewById(R.id.air_level_less);
        leftLeveAdd = this.findViewById(R.id.air_level_add);
        rightLeveLess = this.findViewById(R.id.air_right_level_less);
        rightLeveAdd = this.findViewById(R.id.air_rightlevel_add);

        airLeftWin = this.findViewById(R.id.air_direction1);
        airRightWin = this.findViewById(R.id.air_direction2);

        airLeftFace = this.findViewById(R.id.air_volume1_face);
        airLeftFeet = this.findViewById(R.id.air_volume1_feet);
        airLeftFaceFeet = this.findViewById(R.id.air_volume1_face_feet);
        airRightFace = this.findViewById(R.id.air_volume2_face);
        airRightFeet = this.findViewById(R.id.air_volume2_feet);
        airRightFaceFeet = this.findViewById(R.id.air_volume2_face_feet);


        leftVerSeekBar.setOnSeekBarChangeListener(this);
        rightVerSeekBar.setOnSeekBarChangeListener(this);
        leftLeveSeekBar.setOnSeekBarChangeListener(this);
        rightLeveSeekBar.setOnSeekBarChangeListener(this);
        leftLeveLess.setOnClickListener(this);
        leftLeveAdd.setOnClickListener(this);
        rightLeveLess.setOnClickListener(this);
        rightLeveAdd.setOnClickListener(this);
        airLeftWin.setOnClickListener(this);
        airRightWin.setOnClickListener(this);


    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {   //进度监听
        switch (seekBar.getId()) {
            case R.id.air_seekBar:    //风量
                String temperatureTex = String.valueOf((float) ((progress / 10 * 0.5) + 18));
                String[] str = temperatureTex.split("\\.");
                leftSeekBarTex.setY(-progress * 2.0f);
                leftSeekBarTex1.setY(-progress * 2.0f);

                leftSeekBarTex.setText(str.length == 2 ? str[0] : temperatureTex);
                leftSeekBarTex1.setText(String.valueOf("." + (str.length == 2 ? str[1] :
                        temperatureTex)));
                break;
            case R.id.air_seekBar2:
                String temperatureTex1 = String.valueOf((float) ((progress / 10 * 0.5) + 18));
                String[] str1 = temperatureTex1.split("\\.");
                rightSeekBarTex.setY(-progress * 2.0f);
                rightSeekBarTex1.setY(-progress * 2.0f);

                rightSeekBarTex.setText(str1.length == 2 ? str1[0] : temperatureTex1);
                rightSeekBarTex1.setText(String.valueOf("." + (str1.length == 2 ? str1[1] :
                        temperatureTex1)));
                break;
            case R.id.air_level:
                leftLeveProgeress = progress / 12.5f;
                setAirLevel(leftLeveSeekBar, leftLeveProgeress);
                break;
            case R.id.air_right_level:
                rightLeveProgeress = progress / 12.5f;
                setAirLevel(rightLeveSeekBar, rightLeveProgeress);

                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.air_direction1:    //风向
                setLeftAirWind();
                break;
            case R.id.air_direction2:
                setRightAirWind();
                break;
            case R.id.air_level_less:    //风量
                leftLeveSeekBar.setProgress(getAirLevel(leftLeveSeekBar, 0));
                break;
            case R.id.air_level_add:
                leftLeveSeekBar.setProgress(getAirLevel(leftLeveSeekBar, 1));
                break;
            case R.id.air_right_level_less:
                rightLeveSeekBar.setProgress(getAirLevel(rightLeveSeekBar, 0));
                break;
            case R.id.air_rightlevel_add:
                rightLeveSeekBar.setProgress(getAirLevel(rightLeveSeekBar, 1));
                break;
        }
    }

    /**
     * 空调左右风向
     */
    private void setRightAirWind() {
        if (rightWindNum == 0) {
            airRightFace.setVisibility(View.VISIBLE);
            airRightFeet.setVisibility(View.GONE);
            airRightFaceFeet.setVisibility(View.GONE);
            rightWindNum = 1;
        } else if (rightWindNum == 1) {
            airRightFace.setVisibility(View.GONE);
            airRightFeet.setVisibility(View.VISIBLE);
            airRightFaceFeet.setVisibility(View.GONE);
            rightWindNum = 2;
        } else {
            airRightFace.setVisibility(View.GONE);
            airRightFeet.setVisibility(View.GONE);
            airRightFaceFeet.setVisibility(View.VISIBLE);
            rightWindNum = 0;
        }
    }

    /**
     * 左边风向显示
     */
    private void setLeftAirWind() {
        if (leftWindNum == 0) {
            airLeftFace.setVisibility(View.VISIBLE);
            airLeftFeet.setVisibility(View.GONE);
            airLeftFaceFeet.setVisibility(View.GONE);
            leftWindNum = 1;
        } else if (leftWindNum == 1) {
            airLeftFace.setVisibility(View.GONE);
            airLeftFeet.setVisibility(View.VISIBLE);
            airLeftFaceFeet.setVisibility(View.GONE);
            leftWindNum = 2;
        } else {
            airLeftFace.setVisibility(View.GONE);
            airLeftFeet.setVisibility(View.GONE);
            airLeftFaceFeet.setVisibility(View.VISIBLE);
            leftWindNum = 0;
        }

    }

    /**
     * 设置空调左右两边风量等级
     */
    private void setAirLevel(SeekBar seekBar, float progress) {
        if (progress <= 1) {
            // showToast(this.getResources().getString(R.string.air_leve_less));
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind1));
        } else if (progress <= 2) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind2));
        } else if (progress <= 3) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind3));
        } else if (progress <= 4) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind4));
        } else if (progress <= 5) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind5));
        } else if (progress <= 6) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind6));
        } else if (progress <= 7) {
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind7));
        } else if (progress <= 8) {
            // showToast(this.getResources().getString(R.string.air_leve_max));
            seekBar.setProgressDrawable(this.getDrawable(R.drawable.wind8));
        }
    }

    /**
     * @param text Toast
     */
    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param seekBar 空调风量  加减 进度
     * @param flas    0 减 1 加
     * @return
     */
    private int getAirLevel(SeekBar seekBar, int flas) {
        return flas == 0 ? (int) (seekBar.getProgress() - 12.5f) : (int) (seekBar.getProgress() +
                12.5f);
    }
}
