package com.rapid.api.okhttp;

import com.fc.jisx.jlog.JLog;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/**
 * Created by jsx on 2017/6/20.
 */
public abstract class OkHttpRequest {

    private EventBus mEventBus;

    public OkHttpRequest() {
        this.mEventBus = null;
    }

    public OkHttpRequest(EventBus mEventBus) {
        this.mEventBus = mEventBus;
    }

    protected <T> T getService(Class<T> serviceClass) {
        return OkHttpService.INSTANCE.getHttpService(serviceClass);
    }

    protected <T> T getService(Class<T> serviceClass, InputStream inputStream) {
        T httpsService = null;
        try {
            httpsService = OkHttpService.INSTANCE.getHttpsService(serviceClass, inputStream);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return httpsService;
    }

    protected void postEvent(Object object) {
        if (mEventBus != null) {
            JLog.d(object);
            mEventBus.post(object);
        }
    }

    public abstract void doRequest();

}
