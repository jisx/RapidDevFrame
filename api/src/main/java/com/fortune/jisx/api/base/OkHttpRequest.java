package com.fortune.jisx.api.base;

import com.fortune.jisx.model.utils.Constants;
import com.socks.library.KLog;

import java.io.InputStream;

/**
 * Created by jsx on 2017/6/20.
 */
public abstract class OkHttpRequest {

    public static <T> T getService(Class<T> serviceClass) {
        if (Constants.HTTPS) {
            return getService(serviceClass, null);
        } else {
            return OkHttpService.INSTANCE.getHttpService(serviceClass);
        }
    }

    public static <T> T getService(Class<T> serviceClass, InputStream inputStream) {
        try {
            return OkHttpService.INSTANCE.getHttpsService(serviceClass, inputStream);
        } catch (Exception e) {
            KLog.e(e.getCause() + e.getMessage());
            return null;
        }
    }

    public static <T> T getService(Class<T> serviceClass, boolean isHttps) {
        if (isHttps) {
            return getService(serviceClass, true, null);
        } else {
            return OkHttpService.INSTANCE.getHttpService(serviceClass);
        }
    }

    public static <T> T getService(Class<T> serviceClass, boolean isHttps, InputStream inputStream) {
        if (isHttps) {
            try {
                return OkHttpService.INSTANCE.getHttpsService(serviceClass, inputStream);
            } catch (Exception e) {
                KLog.e(e.getCause() + e.getMessage());
                return null;
            }
        } else {
            return OkHttpService.INSTANCE.getHttpService(serviceClass);
        }
    }

    public abstract void doRequest();

}
