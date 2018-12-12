package com.rapid.api.service;


import com.rapid.api.responseVo.SmsVerifyResponseVo;
import com.rapid.base.util.Constants;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 数据请求
 * Created by jsx on 2017/6/20.
 */
public interface UserService {

    /**
     * 发送手机验证码
     *
     * @return
     */
    @POST("/sms/verification")
    Call<SmsVerifyResponseVo> smsVerify(@Query("mobile") String mobile);

}
