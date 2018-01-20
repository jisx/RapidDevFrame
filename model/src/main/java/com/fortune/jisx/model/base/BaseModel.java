package com.fortune.jisx.model.base;

import com.fc.jisx.jlog.JLog;
import com.fortune.jisx.model.Interfaces.StringFormat;
import com.google.gson.Gson;

/**
 * Created by jisx on 2017/6/21.
 */

public abstract class BaseModel implements StringFormat {

    @Override
    public void stringFormat() {
        JLog.d(this.getClass().getName(), new Gson().toJson(this));
    }
}
