package com.fortune.jisx.view.util;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.StringRes;
import android.widget.Toast;

/**
 * Created by jisx on 2017/6/26.
 */
public class ToastUtil {

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    private ToastUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Toast sShortToast;
    public static Toast sLongToast;

    public static void show(@StringRes int resId) {
        show(Utils.getContext().getString(resId));
    }

    public static void show(String msg) {

        if (sShortToast != null) {
            sShortToast.cancel();
            sShortToast = null;
        }

        if (sLongToast != null) {
            sLongToast.cancel();
            sLongToast = null;
        }

        sShortToast = createShortToast();


        sShortToast.setText(msg);
        sShortToast.setDuration(Toast.LENGTH_SHORT);
        sShortToast.show();
    }

    public static void showLong(@StringRes int resId) {
        showLong(Utils.getContext().getString(resId));
    }

    public static void showLong(String msg) {

        if (sLongToast != null) {
            sLongToast.cancel();
            sLongToast = null;
        }

        if (sShortToast != null) {
            sShortToast.cancel();
            sShortToast = null;
        }

        sLongToast = createLongToast();

        sLongToast.setText(msg);
        sLongToast.setDuration(Toast.LENGTH_LONG);
        sLongToast.show();
    }

    public static void showSafe(@StringRes int resId) {
        showSafe(Utils.getContext().getString(resId));
    }

    public static void showSafe(final String msg) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                show(msg);
            }
        });
    }

    public static void showLongSafe(@StringRes int resId) {
        showLongSafe(Utils.getContext().getString(resId));
    }

    public static void showLongSafe(final String msg) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showLong(msg);
            }
        });
    }


    /**
     * 可以直接修改这个方法
     *
     * @return 自定义toast
     */
    private static Toast createShortToast() {
        Toast toast = Toast.makeText(Utils.getContext(), "", Toast.LENGTH_SHORT);
        return toast;
    }

    /**
     * 可以直接修改这个方法
     *
     * @return 自定义toast
     */
    private static Toast createLongToast() {
        Toast toast = Toast.makeText(Utils.getContext(), "", Toast.LENGTH_LONG);
        return toast;
    }

}
