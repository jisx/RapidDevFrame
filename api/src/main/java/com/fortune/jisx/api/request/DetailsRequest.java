package com.fortune.jisx.api.request;

import com.alibaba.fastjson.JSON;
import com.fortune.jisx.api.base.OkHttpRequest;
import com.fortune.jisx.api.service.CommonService;
import com.fortune.jisx.model.model.TestModel;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import trikita.log.Log;

/**
 * <pre>
 *    Author :  天府萧炎恋熏儿（WangShuJie）
 *    Time   :  2017/01/16 21:33
 *    Usage  :
 *    desc   :
 *    other  :
 * </pre>
 */


public class DetailsRequest extends OkHttpRequest{


    private static DetailsRequest INSTANCE;

    private static Call<Void> call;

    private static EventBus mEventBus;

    /**
     * Request采用单例，并重用
     */
    private DetailsRequest() {
    }

    private static DetailsRequest getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DetailsRequest();
        }
        return INSTANCE;
    }

    public static DetailsRequest with(EventBus eventBus, String userid, String beViewedUserid) {
        mEventBus = eventBus;

        call = getService(CommonService.class).getOpenIdByCode(userid,null);

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

    private void postEvent(Object object){
        if(mEventBus != null){
            mEventBus.post(object);
        }
    }

    public static void main(String... age) {
    }

}
