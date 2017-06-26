package com.fortune.jisx.model.base;

import com.alibaba.fastjson.JSON;
import com.fortune.jisx.model.Interfaces.StringFormat;
import com.socks.library.KLog;

/**
 * Created by jisx on 2017/6/21.
 */

public abstract class BaseModel implements StringFormat{

    @Override
    public void stringFormat() {
        KLog.i(this.getClass().getName(), JSON.toJSON(this));
    }
}
