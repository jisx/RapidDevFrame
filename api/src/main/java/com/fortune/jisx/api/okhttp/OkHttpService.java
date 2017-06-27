package com.fortune.jisx.api.okhttp;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by jsx on 2017/6/20.
 */

public enum OkHttpService {

    INSTANCE;


    private HashMap<Class, Object> httpServiceMap = new HashMap<>();

    private HashMap<Class, Object> httpsServiceMap = new HashMap<>();

    public <T> T getHttpService(Class<T> serviceClass) {
        T callApi;
        if (httpServiceMap.containsKey(serviceClass)
                && httpServiceMap.get(serviceClass) != null) {
            callApi = (T) httpServiceMap.get(serviceClass);
        } else {
            callApi = OkHttpFactory.INSTANCE.getHttpRetrofit().create(serviceClass);
            httpServiceMap.put(serviceClass, callApi);
        }
        return callApi;
    }

    public <T> T getHttpsService(Class<T> serviceClass, InputStream inputStream) throws Exception {
        T callApi;
        if (httpsServiceMap.containsKey(serviceClass)
                && httpsServiceMap.get(serviceClass) != null) {
            callApi = (T) httpsServiceMap.get(serviceClass);
        } else {
            callApi = OkHttpFactory.INSTANCE.getHttpsRetrofit(inputStream).create(serviceClass);
            httpsServiceMap.put(serviceClass, callApi);
        }

        return callApi;
    }

}
