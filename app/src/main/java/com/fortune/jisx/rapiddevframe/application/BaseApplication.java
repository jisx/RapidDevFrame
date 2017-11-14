package com.fortune.jisx.rapiddevframe.application;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import java.util.Stack;


/**
 * BaseApplication 把所有Activity收集，方便退出
 */
public class BaseApplication extends MultiDexApplication {

    private static Stack<Activity> mActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        mActivities = new Stack<>();
    }

    public static void addActivity(Activity activity) {
        if (mActivities.contains(activity))
            return;
        mActivities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        if (mActivities.isEmpty())
            return;
        mActivities.remove(activity);
    }

    public static Stack<Activity> getActivityList() {
        return mActivities;
    }

    public static void finishAll() {
        while (!mActivities.isEmpty()) {
            mActivities.pop().finish();
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
