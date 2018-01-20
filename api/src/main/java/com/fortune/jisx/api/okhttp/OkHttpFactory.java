package com.fortune.jisx.api.okhttp;

import com.fortune.jisx.api.okhttp.parse.EnumTypeAdapterFactory;
import com.fortune.jisx.model.util.Constants;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
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

    public Retrofit getHttpsRetrofit(InputStream inputStream) throws Exception {
        if (retrofitHttps == null) {
            retrofitHttps = createRetrofit(getOkHttpsClient(inputStream));
        }
        return retrofitHttps;
    }

    private Retrofit createRetrofit(OkHttpClient okHttpClient) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new EnumTypeAdapterFactory());

        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (Constants.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        return builder.build();
    }

    private OkHttpClient getOkHttpsClient(InputStream inputStream) throws Exception {

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
        sslContext.init(null, new TrustManager[]{trustManager}, null);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), trustManager);

        if (Constants.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        return builder.build();
    }

    private HttpLoggingInterceptor getLoggingInterceptor() {
          /*日志*/
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }

    private class HostVerifier implements HostnameVerifier {

        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }

    }

    private class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            X509Certificate[] x509Certificates = new X509Certificate[0];
            return x509Certificates;
        }
    }

}
