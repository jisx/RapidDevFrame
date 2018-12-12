package com.rapid.base.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rapid.base.R;

/**
 * Toast统一管理类
 */
public enum ToastUtils {

    ;

    private static final boolean isShow = true;

    private static View sInflate;
    private static Context sContext;
    private static Toast sToast;

    public static void init(Context context) {
        if (context == null) {
            throw new UnsupportedOperationException("context cannot be null");
        }

        sContext = context;
    }

    private static void isInit() {
        if (sContext == null) {
            throw new UnsupportedOperationException("context cannot be null");
        }

        if (sToast == null || sInflate == null) {
            sToast = new Toast(sContext.getApplicationContext());
            sInflate = View.inflate(sContext.getApplicationContext(), R.layout.toast_custom, null);
            sToast.setView(sInflate);
            sToast.setGravity(Gravity.CENTER, 0, 0);
        }
    }

    /**
     * 短时间显示Toast
     *
     * @param message 显示内容
     */
    public static void showShort(CharSequence message) {
        setToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 短时间显示Toast
     *
     * @param message 显示内容
     */
    public static void showShort(int message) {
        showShort(sContext.getString(message));
    }

    private static void setToast(Object message, int length) {

        if (!isShow || TextUtils.isEmpty(message.toString())) {
            return;
        }

        isInit();

        switch (length) {
            case Toast.LENGTH_LONG:
                sToast.setDuration(Toast.LENGTH_LONG);
                setText(message.toString());
                sToast.show();
                break;
            case Toast.LENGTH_SHORT:
                sToast.setDuration(Toast.LENGTH_SHORT);
                setText(message.toString());
                sToast.show();
                break;
            default:
                sToast.setDuration(length);
                setText(message.toString());
                sToast.show();
                break;

        }
    }

    private static void setText(String message) {
        ((TextView) sInflate.findViewById(R.id.tv_hint)).setText(message);
    }

    /**
     * 长时间显示Toast
     *
     * @param message 显示内容
     */
    public static void showLong(CharSequence message) {
        setToast(message, Toast.LENGTH_LONG);
    }

    /**
     * 长时间显示Toast
     *
     * @param message 显示内容
     */
    public static void showLong(int message) {
        showLong(sContext.getString(message));
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message  显示内容
     * @param duration 显示持续时长
     */
    public static void show(CharSequence message, int duration) {
        setToast(message, duration);
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message  显示内容
     * @param duration 显示持续时长
     */
    public static void show(int message, int duration) {
        show(sContext.getString(message), duration);
    }

}