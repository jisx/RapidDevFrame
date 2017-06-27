package com.fortune.jisx.api.request;

import com.fortune.jisx.api.okhttp.OkHttpRequest;
import com.fortune.jisx.api.service.CommonService;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class DownloadRequest extends OkHttpRequest {


    private static DownloadRequest INSTANCE;

    private static Call<Void> call;

    private static EventBus mEventBus;

    /**
     * Request采用单例，并重用
     */
    private DownloadRequest() {
    }

    private static DownloadRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DownloadRequest();
        }
        return INSTANCE;
    }

    public static DownloadRequest with(EventBus eventBus, String url) {
        mEventBus = eventBus;

        call = getService(CommonService.class).downloadFile(url);

        return getInstance();
    }


    @Override
    public void doRequest() {
        if (call == null) {

        } else if (call.isExecuted() || call.isCanceled()) {
            call = call.clone();
        }
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }

    public void Cancel() {
        if (!call.isCanceled()) {
            call.cancel();
        }
    }

    private void postEvent(Object object) {
        if (mEventBus != null) {
            mEventBus.post(object);
        }
    }

    public static void main(String... age) {

    }

}
