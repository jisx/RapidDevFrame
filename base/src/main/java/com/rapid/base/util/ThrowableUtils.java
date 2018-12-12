package com.rapid.base.util;

import com.fc.jisx.jlog.JLog;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class ThrowableUtils {

    public static String parseException(Throwable throwable){
        if (throwable instanceof SocketTimeoutException){
            return "网络连接超时，请检查网络";
        }else if (throwable instanceof ConnectException){
            return "连接不到服务器";
        }else if (throwable instanceof JsonSyntaxException){
            return "数据解析异常";
        }else{
            return "连接出错啦，请稍后再试";
        }

    }
}
