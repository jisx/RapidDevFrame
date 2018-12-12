package com.rapid.frame.application;


import android.content.Context;

import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.LogConfiguration;
import com.fc.jisx.jlog.printer.config.FileConfig;
import com.rapid.base.util.Constants;
import com.rapid.base.util.ToastUtils;
import com.rapid.frame.BuildConfig;
import com.rapid.sql.utils.DBManage;

import butterknife.ButterKnife;

/**
 * Created by jisx on 2016/7/7.
 */
//@AcraHttpSender(uri = "https://yourdomain.com/acra/report",
//        httpMethod = HttpSender.Method.POST)
public class MyApplication extends BaseApplication {

    private static MyApplication mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        ACRA.init(this);

    }


    public void onCreate() {
        super.onCreate();
        mContext = this;

        initLog();

        Constants.BASE_URL = BuildConfig.REMOTE_HOST;
        Constants.DEBUG = BuildConfig.DEBUG;

        ToastUtils.init(this);

        ButterKnife.setDebug(Constants.DEBUG);

        DBManage.open(this);
    }

    private void initLog() {
        JLog.init(new LogConfiguration.Builder()
                .android()
                .border()
                .file(new FileConfig(getExternalFilesDir("log").getAbsolutePath(), 7, 5 * 1024 * 1024, JLogLevel.VERBOSE))
                .track()
                .logLevel(JLogLevel.DEBUG.getLevel())
                .build());

    }

    public static MyApplication getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
        DBManage.close();
    }

}
