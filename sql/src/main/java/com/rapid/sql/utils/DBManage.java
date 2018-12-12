package com.rapid.sql.utils;

import android.content.Context;

import com.rapid.base.util.Constants;
import com.rapid.sql.greendao.DaoMaster;
import com.rapid.sql.greendao.DaoSession;


/**
 * Created by jisx on 2016/8/15.
 */
public class DBManage {


    static DaoMaster.DevOpenHelper helper;

    static DaoSession daoSession;

    static Context mContext;


    public static DaoMaster.DevOpenHelper open(Context context) {
        mContext = context;
        helper = new DaoMaster.DevOpenHelper(context, Constants.DB_NAME, null);
        daoSession = new DaoMaster(helper.getWritableDatabase()).newSession();
        return helper;
    }

    public static boolean isOpen() {
        if (mContext == null || helper == null || helper.getWritableDatabase() == null || daoSession == null) {
            return false;
        }

        return true;
    }

    public static void clear() {
        if (daoSession != null) {
            daoSession.clear();
        }
    }

    public static void close() {
        if (helper != null) {
            helper.close();
            helper = null;
        }
    }

    public static DaoSession getSession() {
        if (daoSession == null) {
            if (mContext != null) {
                open(mContext);
            }
        }
        return daoSession;
    }

}
