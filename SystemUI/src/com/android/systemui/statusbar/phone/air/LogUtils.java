package com.android.systemui.statusbar.phone.air;

import android.util.Log;

/**
 * log 信息 类
 */
public class LogUtils {
    private static int LOG_LEVE =0; // log开关  0 关闭 2 打开
    private static int ERROR = 1;

    /**
     * log 信息   E
     *
     * @param e
     * @param text
     */
    public static void e(String e, String text) {
        if (LOG_LEVE > ERROR) {
            Log.e(e, text);
        }
    }
}
