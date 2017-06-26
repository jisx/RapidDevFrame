package com.fortune.jisx.api.base;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by jsx on 2017/6/20.
 */

public enum OkHttpService {

    INSTANCE;


    private HashMap<Class, Object> httpServiceMap = new HashMap<>();

    private HashMap<Class, Object> httpsServiceMap = new HashMap<>();

    public <T> T  getHttpService(Class<T> serviceClass) {
        T service = null;
        if (httpServiceMap.containsKey(serviceClass)
                && httpServiceMap.get(serviceClass) != null) {
            service = (T) httpServiceMap.get(serviceClass);
        } else {
            service = OkHttpFactory.INSTANCE.getHttpRetrofit().create(serviceClass);
            httpServiceMap.put(serviceClass, service);
        }
        return service;
    }

    public <T> T getHttpsService(Class<T> serviceClass, InputStream inputStream) throws Exception {
        T service = null;
        if (httpsServiceMap.containsKey(serviceClass)
                && httpsServiceMap.get(serviceClass) != null) {
            service = (T) httpsServiceMap.get(serviceClass);
        } else {
            service = OkHttpFactory.INSTANCE.getHttpsRetrofit(inputStream).create(serviceClass);
            httpsServiceMap.put(serviceClass, service);
        }

        return service;
    }

}
