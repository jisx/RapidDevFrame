package com.rapid.base.util;

import android.app.Activity;
import android.view.View;

import static android.os.Build.VERSION;
import static android.os.Build.VERSION_CODES;

public class StatusBarFontUtils {

    /**
     * 黑色字体状态栏
     *
     * @param activity 当前页面
     */
    public static void setLightStatusBarColor(Activity activity) {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 白色字体状态栏
     *
     * @param activity 当前页面
     */
    public static void setWhiteStatusBarColor(Activity activity) {
        if (VERSION.SDK_INT >= VERSION_CODES.M) {
            activity.getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
}
