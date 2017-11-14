package com.fortune.jisx.rapiddevframe.application;


import android.util.Log;

import com.fc.jisx.jlog.JBuilder;
import com.fc.jisx.jlog.JLog;
import com.fortune.jisx.model.util.Constants;
import com.fortune.jisx.sql.utils.DBManage;
import com.fortune.jisx.view.util.Utils;

import butterknife.ButterKnife;

/**
 * Created by jisx on 2016/7/7.
 */
public class MyApplication extends BaseApplication {

    private static MyApplication mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        ButterKnife.setDebug(Constants.DEBUG);

        DBManage.open(this);

        Utils.init(this);
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
