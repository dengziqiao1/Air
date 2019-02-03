package com.fce.air;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.fce.air.test.DrawerLayout;


/**
 * 空调
 */
public class AirActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View
        .OnClickListener, BottomFinshLayout.OnFinishListener {
    private VerticalSeekBar leftVerSeekBar, rightVerSeekBar;
    //垂直进度条
    private SeekBar leftLeveSeekBar, rightLeveSeekBar; //风量左右 等级进度条
    private ImageView leftLeveLess, rightLeveLess, leftLeveAdd, rightLeveAdd; // 空调风量加减 键
    //左右两边显示温度
    private TextView leftSeekBarTex, leftSeekBarTex1, rightSeekBarTex, rightSeekBarTex1;
    //空调风量等级
    private float leftVerProgress, rightVerProgress, leftLeveProgeress, rightLeveProgeress;

    private ImageView airLeftWin, airRightWin, airLeftFace, airLeftFeet, airLeftFaceFeet,
            airLeftFeetShuang, airRightFace, airRightFeet, airRightFaceFeet, airRightFeetShuang;
    //左 右 风向
    private int leftWindNum = 0, rightWindNum = 0;     //风向 显示 值

    private TextView airContaminatedInTex, airContaminatedInLevel, airContaminatedOutTex,
            airContaminatedOutLevel; //车内 车外 污染等级 指数

    private ImageView airAuto, airEcon, airOff, airChuQu, airChuShaunag, airAcMax, airSync,
            airRear, airAc, airNixunhuan;    //控制开关

    private DrawerLayout drawerLayout;

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
        myBraocast braocast = new myBraocast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.action.OPENSUBSCRIBE");
        intentFilter.addAction("com.action.OPENAPP");
        intentFilter.addAction("com.action.HISTORY");
        registerReceiver(braocast, intentFilter);

        // BottomFinshLayout bottomFinshLayout = this.findViewById(R.id.air);
        drawerLayout = this.findViewById(R.id.air_drawer);
        leftVerSeekBar = this.findViewById(R.id.air_seekBar);
        rightVerSeekBar = this.findViewById(R.id.air_seekBar2);
        leftVerSeekBar.setVisibility(View.GONE);
        leftVerSeekBar.setVisibility(View.VISIBLE);
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
        airLeftFeetShuang = this.findViewById(R.id.air_volume1_feet_shuang);
        airRightFeetShuang = this.findViewById(R.id.air_volume1_feet_shuang2);

        airContaminatedInTex = this.findViewById(R.id.air_contaminated_in_tex);
        airContaminatedInLevel = this.findViewById(R.id.air_contaminated_in_level);

        airContaminatedOutTex = this.findViewById(R.id.air_contaminated_out_tex);
        airContaminatedOutLevel = this.findViewById(R.id.air_contaminated_out_level);

        airAuto = this.findViewById(R.id.air_auto);
        airEcon = this.findViewById(R.id.air_econ);
        airOff = this.findViewById(R.id.air_off);
        airChuQu = this.findViewById(R.id.air_chuqu);
        airChuShaunag = this.findViewById(R.id.air_chushuang);
        airAcMax = this.findViewById(R.id.air_acmax);
        airSync = this.findViewById(R.id.air_sync);
        airRear = this.findViewById(R.id.air_rear);
        airAc = this.findViewById(R.id.air_ac);
        airNixunhuan = this.findViewById(R.id.air_nixunhuan);
        airAuto.setOnClickListener(this);
        airEcon.setOnClickListener(this);
        airOff.setOnClickListener(this);
        airChuQu.setOnClickListener(this);
        airChuShaunag.setOnClickListener(this);
        airAcMax.setOnClickListener(this);
        airSync.setOnClickListener(this);
        airRear.setOnClickListener(this);
        airAc.setOnClickListener(this);
        airNixunhuan.setOnClickListener(this);

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
        // bottomFinshLayout.setOnFinishListener(this);

        airOff.setSelected(true);  //off 默认开启

//        try{
//            Runtime runtime=Runtime.getRuntime();
//            runtime.exec("input keyevent " + KeyEvent.KEYCODE_BACK);
//        }catch(IOException e){
//            Log.e("Exception when doBack", e.toString());
//        }


    }

    class myBraocast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.e("dzq", "接收到的广播： " + intent.getAction());
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {   //进度监听
        switch (seekBar.getId()) {
            case R.id.air_seekBar:    //空调 左边 温度
                String temperatureTex = String.valueOf((float) ((progress / 10 * 0.5) + 18));
                String[] str = temperatureTex.split("\\.");
                leftSeekBarTex.setY(-progress * 1.85f);
                leftSeekBarTex1.setY(-progress * 1.85f);

                leftSeekBarTex.setText(str.length == 2 ? str[0] : temperatureTex);
                leftSeekBarTex1.setText(String.valueOf("." + (str.length == 2 ? str[1] :
                        temperatureTex)));

                break;
            case R.id.air_seekBar2:    //空调 右边 温度
                String temperatureTex1 = String.valueOf((float) ((progress / 10 * 0.5) + 18));
                String[] str1 = temperatureTex1.split("\\.");
                rightSeekBarTex.setY(-progress * 1.8f);
                rightSeekBarTex1.setY(-progress * 1.8f);

                rightSeekBarTex.setText(str1.length == 2 ? str1[0] : temperatureTex1);
                rightSeekBarTex1.setText(String.valueOf("." + (str1.length == 2 ? str1[1] :
                        temperatureTex1)));
                break;
            case R.id.air_level:     //左边风量等级
                leftLeveProgeress = progress / 12.5f;
                setAirLevel(leftLeveSeekBar, leftLeveProgeress);
                break;
            case R.id.air_right_level:  //右边风量等级
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
            case R.id.air_auto:
                setSelection(airAuto);
                break;
            case R.id.air_econ:
                setSelection(airEcon);
                break;
            case R.id.air_off:
                setSelection(airOff);
                if (airOff.isSelected()) {
                    setOffStatus(true);
                } else {
                    setOffStatus(false);
                }
                break;
            case R.id.air_chuqu:
                setSelection(airChuQu);
                break;
            case R.id.air_chushuang:
                setSelection(airChuShaunag);
                break;
            case R.id.air_acmax:

                setSelection(airAcMax);
                break;
            case R.id.air_sync:
                Intent Intent2 = new Intent("");
                this.sendBroadcast(Intent2);
                setSelection(airSync);
                break;
            case R.id.air_rear:
                Intent Intent1 = new Intent("");
                this.sendBroadcast(Intent1);
                setSelection(airRear);
                break;
            case R.id.air_ac:
                setSelection(airAc);
                break;
            case R.id.air_nixunhuan:
                setSelection(airNixunhuan);
                break;
        }
    }

    /**
     * @param status off 关闭 所有功能都不可点击
     */
    private void setOffStatus(boolean status) {
        leftVerSeekBar.setEnabled(status);
        rightVerSeekBar.setEnabled(status);
        leftLeveSeekBar.setEnabled(status);
        rightLeveSeekBar.setEnabled(status);
        airLeftWin.setEnabled(status);
        airRightWin.setEnabled(status);
        leftLeveLess.setEnabled(status);
        rightLeveLess.setEnabled(status);
        leftLeveAdd.setEnabled(status);
        rightLeveAdd.setEnabled(status);
        airAuto.setEnabled(status);
        airEcon.setEnabled(status);
        airChuQu.setEnabled(status);
        airChuShaunag.setEnabled(status);
        airNixunhuan.setEnabled(status);
        airAcMax.setEnabled(status);
        airSync.setEnabled(status);
        airRear.setEnabled(status);
        airAc.setEnabled(status);
        if (!status) {
            showToast(this.getResources().getString(R.string.air_off));
        }
    }

    /**
     * @param imageView 控制功能 开关      状态
     */
    private void setSelection(ImageView imageView) {
        if (imageView.isSelected()) {
            imageView.setSelected(false);
        } else {
            imageView.setSelected(true);
        }
    }

    /**
     * @param imageView 获取 控制功能 开关      状态
     */
    private boolean getSelection(ImageView imageView) {
        return imageView.isSelected();
    }

    /**
     * 空调左右风向
     */
    private void setRightAirWind() {
        if (rightWindNum == 0) {
            airRightFace.setVisibility(View.VISIBLE);
            airRightFeet.setVisibility(View.GONE);
            airRightFaceFeet.setVisibility(View.GONE);
            airRightFeetShuang.setVisibility(View.GONE);
            rightWindNum = 1;
        } else if (rightWindNum == 1) {
            airRightFace.setVisibility(View.GONE);
            airRightFeet.setVisibility(View.VISIBLE);
            airRightFaceFeet.setVisibility(View.GONE);
            airRightFeetShuang.setVisibility(View.GONE);
            rightWindNum = 2;
        } else if (rightWindNum == 2) {
            airRightFace.setVisibility(View.GONE);
            airRightFeet.setVisibility(View.GONE);
            airRightFaceFeet.setVisibility(View.VISIBLE);
            airRightFeetShuang.setVisibility(View.GONE);
            if (airChuQu.isSelected()) {
                rightWindNum = 3;
            } else {
                rightWindNum = 0;
            }
        } else {
            airRightFace.setVisibility(View.GONE);
            airRightFeet.setVisibility(View.GONE);
            airRightFaceFeet.setVisibility(View.GONE);
            airRightFeetShuang.setVisibility(View.VISIBLE);
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
            airLeftFeetShuang.setVisibility(View.GONE);
            leftWindNum = 1;
        } else if (leftWindNum == 1) {
            airLeftFace.setVisibility(View.GONE);
            airLeftFeet.setVisibility(View.VISIBLE);
            airLeftFaceFeet.setVisibility(View.GONE);
            airLeftFeetShuang.setVisibility(View.GONE);
            leftWindNum = 2;
        } else if (leftWindNum == 2) {
            airLeftFace.setVisibility(View.GONE);
            airLeftFeet.setVisibility(View.GONE);
            airLeftFaceFeet.setVisibility(View.VISIBLE);
            airLeftFeetShuang.setVisibility(View.GONE);
            if (airChuQu.isSelected()) {
                leftWindNum = 3;
            } else {
                leftWindNum = 0;
            }
        } else {
            airLeftFace.setVisibility(View.GONE);
            airLeftFeet.setVisibility(View.GONE);
            airLeftFaceFeet.setVisibility(View.GONE);
            airLeftFeetShuang.setVisibility(View.VISIBLE);
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
     */
    private int getAirLevel(SeekBar seekBar, int flas) {
        return flas == 0 ? (int) (seekBar.getProgress() - 12.5f) : (int) (seekBar.getProgress() +
                12.5f);
    }

    @Override
    public void onFinish() {
        //  overridePendingTransition(R.anim.fade_out, 0);
//         finish();
        new Thread() {
            public void run() {
                try {
                    Instrumentation inst = new Instrumentation();
                    inst.sendCharacterSync(4);
                } catch (Exception e) {
                    Log.e("Exception when onBack", e.toString());
                }
            }
        }.start();
    }

    private final String SYSTEMUI_UP_DATA_TITLE = "com.android.systemui.up.data.statusbar.title";
    //更新标题广播
    private final String SYSTEMUI_UP_DATA_TITLE_CONTENT = "systemui_title_content";

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(SYSTEMUI_UP_DATA_TITLE);
        intent.putExtra(SYSTEMUI_UP_DATA_TITLE_CONTENT, "systemUITitle");
        sendBroadcast(intent);
        super.onDestroy();
    }
}
