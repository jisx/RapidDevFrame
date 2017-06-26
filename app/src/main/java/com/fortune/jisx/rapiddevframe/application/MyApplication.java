package com.fortune.jisx.rapiddevframe.application;


import com.fortune.jisx.model.utils.Constants;

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
