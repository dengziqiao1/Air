package com.fce.air;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 空调界面
 */
public class PhoneStatusBarView extends FrameLayout {
    private Context context;
    private TextView title;
    private ImageView imageView;
    private String titleContent;
    private final String SYSTEMUI_UP_DATA_TITLE = "com.android.systemui.up.data.statusbar.title";
    //更新标题广播
    private final String SYSTEMUI_UP_DATA_TITLE_CONTENT = "systemui_title_content";
    private systemUITitle systemUITitle;
        private SystemUITitleInfo systemUITitleInfo;
    public PhoneStatusBarView(Context context) {
        super(context);

    }

 
    public PhoneStatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        systemUITitle = new systemUITitle();
        systemUITitleInfo.setTitle(context.getResources().getString(R.string.air_leve_max));
        IntentFilter filter = new IntentFilter(SYSTEMUI_UP_DATA_TITLE);
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        context.registerReceiver(systemUITitle, filter);
        RxBus.getInstance().toSend(new SystemUITitleInfo("a"));
             
    }
    /**
     * 更新标题广播
     */
    public class systemUITitle extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            if (SYSTEMUI_UP_DATA_TITLE.equals(intent.getAction())) {
                titleContent = intent.getStringExtra(SYSTEMUI_UP_DATA_TITLE_CONTENT);
                RxBus.getInstance().toSend(this);
//                 LogUtils.e("fce", "标题名称: "+intent.getAction);
            }
        }
    }

    private void setTitle() {
        RxBus.getInstance().toFlowable(SystemUITitleInfo.class).subscribe(new ApiSubscriberCallBack<SystemUITitleInfo>() {
            @Override
            public void onPiteSuccess(SystemUITitleInfo phoneStatusBarView) {

                title.setText(titleContent);
            }

            @Override
            public void onPiteFailure(Throwable e) {
//                 LogUtils.e("fce", "更新标题失败: " + e.toString());
            }
        });
    }
}
