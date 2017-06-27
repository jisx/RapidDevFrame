package com.fortune.jisx.sql.utils;

import android.content.Context;

import com.fortune.jisx.model.util.Constants;
import com.fortune.jisx.sql.greendao.DaoMaster;
import com.fortune.jisx.sql.greendao.DaoSession;
import com.fortune.jisx.sql.greendao.DownloadEntityDao;

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

    public static DownloadEntityDao getDownloadEntityDao() {
        return getSession().getDownloadEntityDao();
    }

}
