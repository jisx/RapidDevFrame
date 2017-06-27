package com.fortune.jisx.api.service;


import com.fortune.jisx.api.okhttp.request.ProgressResponseBody;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 数据请求
 * Created by jsx on 2017/6/20.
 */
public interface CommonService {

    @GET
    Call<Void> getOpenIdByCode(@Url String url);

    @Multipart
    @POST
    Call<Void> uploadFile(@Url String url, @Part MultipartBody.Part part);

    @Streaming
    @GET
    Call<Void> downloadFile(@Url String url);

}
