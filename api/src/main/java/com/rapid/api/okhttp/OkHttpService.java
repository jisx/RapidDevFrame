package com.rapid.api.okhttp;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
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
            Object service = httpServiceMap.get(serviceClass);
            callApi = serviceClass.isInstance(service) ? serviceClass.cast(service) : null;
        } else {
            callApi = OkHttpFactory.INSTANCE.getHttpRetrofit().create(serviceClass);
            httpServiceMap.put(serviceClass, callApi);
        }
        return callApi;
    }

    public <T> T getHttpsService(Class<T> serviceClass, InputStream inputStream) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        T callApi;
        if (httpsServiceMap.containsKey(serviceClass)
                && httpsServiceMap.get(serviceClass) != null) {
            Object service = httpsServiceMap.get(serviceClass);
            callApi = serviceClass.isInstance(service) ? serviceClass.cast(service) : null;
        } else {
            callApi = OkHttpFactory.INSTANCE.getHttpsRetrofit(inputStream).create(serviceClass);
            httpsServiceMap.put(serviceClass, callApi);
        }

        return callApi;
    }

}
