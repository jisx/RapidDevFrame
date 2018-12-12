package com.rapid.api.request;

import com.rapid.api.okhttp.OkHttpRequest;
import com.rapid.api.responseVo.SmsVerifyResponseVo;
import com.rapid.api.service.UserService;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 *
 */
public class SmsVerifyRequest extends OkHttpRequest {


    private Call<SmsVerifyResponseVo> call;

    /**
     * Request采用单例，并重用
     */
    public SmsVerifyRequest(EventBus eventBus) {
        super(eventBus);
    }


    public OkHttpRequest setParams(String mobile) {

        call = getService(UserService.class).smsVerify(mobile);

        return this;
    }


    @Override
    public void doRequest() {
        if (call == null) {
//            postEvent(new NoShowException("请求还未设置参数"));
        } else if (call.isExecuted() || call.isCanceled()) {
            call = call.clone();
        }
        call.enqueue(new Callback<SmsVerifyResponseVo>() {
            @Override
            public void onResponse(Call<SmsVerifyResponseVo> call, Response<SmsVerifyResponseVo> response) {
                if (response.isSuccessful()) {
                    postEvent(response.body());
                } else {
                    postEvent(new HttpException(response));
                }
            }

            @Override
            public void onFailure(Call<SmsVerifyResponseVo> call, Throwable t) {
                postEvent(t);
            }
        });
    }

    public static void main(String[] args) {

    }

}
