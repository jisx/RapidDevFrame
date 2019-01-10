package com.rapid.sql.utils;

import android.content.Context;

import com.rapid.sql.greendao.MyObjectBox;

import io.objectbox.Box;
import io.objectbox.BoxStore;


/**
 * Created by jisx on 2016/8/15.
 */
public enum DBManage {

    INSTANCE {

        BoxStore mBoxStore;
        Context mContext;

        @Override
        public void init(Context context) {
            this.mContext = context;
            mBoxStore = MyObjectBox.builder().androidContext(context).build();
        }

        @Override
        public <T> Box<T> getSession(Class<T> tClass) {
            if(mContext == null){
                throw new NullPointerException("DBManage must init");
            }
            return mBoxStore.boxFor(tClass);
        }


        @Override
        public void close() {
            if (mBoxStore != null) {
                mBoxStore.close();
                mBoxStore = null;
            }
        }
    };

    public abstract void init(Context context);

    public abstract <T> Box<T> getSession(Class<T> tClass);

    public abstract void close();
}
