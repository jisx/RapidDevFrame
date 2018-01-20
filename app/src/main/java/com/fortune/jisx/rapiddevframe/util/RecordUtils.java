package com.fortune.jisx.rapiddevframe.util;

import com.fortune.jisx.sql.greendao.LogEntity;
import com.fortune.jisx.sql.greendao.LogEntityDao;
import com.fortune.jisx.sql.greendao.OperateEntity;
import com.fortune.jisx.sql.utils.DBManage;
import com.fortune.jisx.view.util.SystemUtils;

import java.util.Date;

/**
 * File description.
 *
 * @author jisx
 * @date Created in ${T} 2018/1/4
 * @modify By:
 */
public class RecordUtils {

    public static void saveLog(String className, String message) {
        DBManage.getLogDao().insert(new LogEntity(null, className, message, SystemUtils.getDeviceBrand(),
                SystemUtils.getSystemModel(), SystemUtils.getDeviceSDK(),
                SystemUtils.getIMEI(), SystemUtils.getSystemVersion(), new Date()));
    }

    public static void saveOperate(String content) {
        DBManage.getOperateDao().insert(new OperateEntity(null, null, content,
                SystemUtils.getSystemModel(), SystemUtils.getDeviceSDK(),
                SystemUtils.getIMEI(), SystemUtils.getSystemVersion(), new Date()));
    }
}
