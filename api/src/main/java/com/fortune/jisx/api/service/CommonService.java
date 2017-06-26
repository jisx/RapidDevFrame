package com.fortune.jisx.api.service;


import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * 数据请求
 * Created by jsx on 2017/6/20.
 */
public interface CommonService {

    @GET
    Call<Void> getOpenIdByCode(@Url String url, @QueryMap Map<String, Object> map);

}
