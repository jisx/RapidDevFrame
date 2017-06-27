package com.fortune.jisx.api.request;

import com.fc.jisx.jlog.JLog;
import com.fortune.jisx.api.okhttp.OkHttpRequest;
import com.fortune.jisx.api.okhttp.request.UploadProgressListener;
import com.fortune.jisx.api.okhttp.request.ProgressRequestBody;
import com.fortune.jisx.api.service.CommonService;

import org.greenrobot.eventbus.EventBus;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */
public class UploadRequest extends OkHttpRequest {


    private static UploadRequest INSTANCE;

    private static Call<Void> call;

    private static EventBus mEventBus;

    /**
     * Request采用单例，并重用
     */
    private UploadRequest() {
    }

    private static UploadRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UploadRequest();
        }
        return INSTANCE;
    }

    public static UploadRequest with(EventBus eventBus, String url, MultipartBody.Part part, String name) {
        mEventBus = eventBus;

        call = getService(CommonService.class).uploadFile(url, part);

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
        File file = new File("D:\\1.png");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        ProgressRequestBody uploadProgressRequest = new ProgressRequestBody(requestBody, new UploadProgressListener() {
            @Override
            public void onProgress(long bytesWritten, long contentLength, boolean hasFinish) {
                JLog.TEXT.w((int) (bytesWritten * 1.0 / contentLength * 100) + "%");
            }
        });

        MultipartBody.Part part = MultipartBody.Part.createFormData("uploadimg", file.getName(), uploadProgressRequest);
        UploadRequest.with(null, "http://www.chuantu.biz/upload.php", part, file.getName()).doRequest();
    }

}
