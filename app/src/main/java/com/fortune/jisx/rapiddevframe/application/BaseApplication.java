package com.fortune.jisx.rapiddevframe.application;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import java.util.Stack;


/**
 * BaseApplication 把所有Activity收集，方便退出
 */
public class BaseApplication extends MultiDexApplication {

    private static Stack<Activity> mActivitys;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivitys = new Stack<>();
    }

    public static void addActivity(Activity activity) {
        if (mActivitys.contains(activity))
            return;
        mActivitys.add(activity);
    }

    public static void removeActivity(Activity activity) {
        if (mActivitys.isEmpty())
            return;
        mActivitys.remove(activity);
    }

    public static Stack<Activity> getActivityList() {
       return mActivitys;
    }

    public static void finishAll() {
        while (!mActivitys.isEmpty()) {
            mActivitys.pop().finish();
        }
    }

    /**
     * 退出应用
     */
    public static void exit() {
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
