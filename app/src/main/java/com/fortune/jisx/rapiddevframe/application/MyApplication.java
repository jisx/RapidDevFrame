package com.fortune.jisx.rapiddevframe.application;


import android.content.Context;

import com.fc.jisx.jlog.JLog;
import com.fc.jisx.jlog.JLogLevel;
import com.fc.jisx.jlog.LogConfiguration;
import com.fc.jisx.jlog.printer.config.FileConfig;
import com.fortune.jisx.model.util.Constants;
import com.fortune.jisx.rapiddevframe.R;
import com.fortune.jisx.rapiddevframe.util.SDCardUtils;
import com.fortune.jisx.sql.utils.DBManage;
import com.fortune.jisx.view.util.Utils;

import org.acra.ACRA;
import org.acra.ErrorReporter;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSenderFactory;

import butterknife.ButterKnife;

/**
 * Created by jisx on 2016/7/7.
 */
@ReportsCrashes(
        mode = ReportingInteractionMode.TOAST,
        resToastText = R.string.app_crash_error,
        reportSenderFactoryClasses ={HttpSenderFactory.class},
        formUri = "http://localhost:8083/smm/api/log",
        customReportContent={ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME, ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL, ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT}
)
public class MyApplication extends BaseApplication {

    private static MyApplication mContext;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ACRA.init(this);

    }


    public void onCreate() {
        super.onCreate();
        mContext = this;

        ButterKnife.setDebug(Constants.DEBUG);

        DBManage.open(this);

        Utils.init(this);

        initLog();
    }

    private void initLog() {
        JLog.init(new LogConfiguration.Builder()
                .android()
                .border()
                .track()
                .logLevel(JLogLevel.DEBUG.getLevel())
                .file(new FileConfig(SDCardUtils.getSDPath() + Constants.LogPath,
                        14, 20 * 1025 * 1024,
                        JLogLevel.WARN)).build());

    }

    public static MyApplication getContext() {
        return mContext;
    }

    @Override
    public void onTerminate() {
        // 程序终止的时候执行
        super.onTerminate();
    }

}
