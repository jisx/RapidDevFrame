package com.fortune.jisx.model.base;

import com.alibaba.fastjson.JSON;
import com.fc.jisx.jlog.JLog;
import com.fortune.jisx.model.Interfaces.StringFormat;

/**
 * Created by jisx on 2017/6/21.
 */

public abstract class BaseModel implements StringFormat{

    @Override
    public void stringFormat() {
        JLog.TEXT.d(this.getClass().getName(), JSON.toJSON(this));
    }
}
