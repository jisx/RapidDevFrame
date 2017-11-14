package com.fortune.jisx.api.okhttp;

import java.io.InputStream;

/**
 * Created by jsx on 2017/6/20.
 */
public abstract class OkHttpRequest {

    public static <T> T getService(Class<T> serviceClass) {
        return getService(serviceClass,false);
    }

    public static <T> T getService(Class<T> serviceClass, InputStream inputStream) {
        return getService(serviceClass, true, inputStream);
    }

    public static <T> T getService(Class<T> serviceClass, boolean isHttps) {
        return getService(serviceClass, isHttps, null);
    }

    public static <T> T getService(Class<T> serviceClass, boolean isHttps, InputStream inputStream) {
        if (isHttps) {
            try {
                return OkHttpService.INSTANCE.getHttpsService(serviceClass, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return OkHttpService.INSTANCE.getHttpService(serviceClass);
        }
    }

    public abstract void doRequest();

}
