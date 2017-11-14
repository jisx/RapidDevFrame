package com.fortune.jisx.api.okhttp;

import com.fortune.jisx.api.okhttp.parse.FastJsonConverterFactory;
import com.fortune.jisx.model.util.Constants;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

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
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(FastJsonConverterFactory.create())
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

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.sslSocketFactory(getSocketFactory(inputStream))
                .hostnameVerifier(new HostVerifier());//验证主机地址
        if (Constants.DEBUG) {
            builder.addInterceptor(getLoggingInterceptor());
        }
        return builder.build();
    }

    private SSLSocketFactory getSocketFactory(InputStream inputStream) throws Exception {

        SSLContext sslContext = SSLContext.getInstance("TLS");

        if (inputStream == null) {
            sslContext.init(null, new TrustManager[]{new MyTrustManager()}, new SecureRandom());
        } else {
            /* 提供证书的情况下 */
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Certificate certificate = certificateFactory.generateCertificate(inputStream);

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            keyStore.setCertificateEntry("trust", certificate);

            inputStream.close();

            TrustManagerFactory trustManagerFactory = TrustManagerFactory
                    .getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);

            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
        }

        return sslContext.getSocketFactory();
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
