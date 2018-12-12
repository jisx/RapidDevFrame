package com.rapid.api.okhttp;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rapid.base.BuildConfig;
import com.rapid.base.util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jisx on 2017/6/20.
 */

public enum OkHttpFactory {

    INSTANCE;

    private Retrofit retrofitHttp;
    private Retrofit retrofitHttps;

    public Retrofit getHttpRetrofit() {
        if (retrofitHttp == null) {
            retrofitHttp = createRetrofit(getOkHttpClient());
        }
        return retrofitHttp;
    }

    public Retrofit getHttpsRetrofit(InputStream inputStream) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException {
        if (retrofitHttps == null) {
            retrofitHttps = createRetrofit(getOkHttpsClient(inputStream));
        }
        return retrofitHttps;
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        builder.connectTimeout(15, TimeUnit.SECONDS);
        return builder.build();
    }

    private OkHttpClient getOkHttpsClient(InputStream inputStream) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, IOException, KeyManagementException {

        KeyStore keyStore = null;

        if (inputStream != null) {
            //提供证书的情况下
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(inputStream);

            keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("trust", certificate);

            inputStream.close();
        }

        SSLContext sslContext = SSLContext.getInstance("TLS");

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);

        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();

        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }

        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];
        sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager);

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        return builder.build();
    }

    private HttpLoggingInterceptor getLoggingInterceptor() {
        /*日志*/
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

}
