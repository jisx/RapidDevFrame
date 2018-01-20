package com.fortune.jisx.rapiddevframe.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fc.jisx.jlog.JLog;
import com.fortune.jisx.model.util.Constants;
import com.fortune.jisx.rapiddevframe.application.MyApplication;
import com.fortune.jisx.rapiddevframe.util.RecordUtils;
import com.fortune.jisx.sql.utils.DBManage;
import com.fortune.jisx.view.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jsx on 2016/4/12.
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    EventBus mEventBus;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        unbinder = ButterKnife.bind(this);

        /*初始化数据*/
        initData();
    }

    @Override
    public void onStart() {
        mEventBus = EventBus.builder().build();
        mEventBus.register(this);
        super.onStart();
    }

    @Override
    public void onStop() {
        if (mEventBus != null)
            mEventBus.unregister(this);

        mEventBus = null;
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();

        unbinder = null;

        MyApplication.removeActivity(this);

        super.onDestroy();
    }

    public void goNext(Class toClass, Intent intent) {
        intent.setClass(this, toClass);
        startActivity(intent);
    }

    public void goNext(Class toClass) {
        Intent intent = new Intent(this, toClass);
        startActivity(intent);
    }

    public void goNextForResult(int requestCode, Class toClass) {
        Intent intent = new Intent(this, toClass);
        startActivityForResult(intent, requestCode);
    }

    public void goNextForResult(int requestCode, Class toClass, Intent intent) {
        intent.setClass(this, toClass);
        startActivityForResult(intent, requestCode);
    }

    public EventBus getEventBus() {
        return mEventBus;
    }

    /**
     * 处理异常
     *
     * @param throwable 异常
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(Throwable throwable) {
        JLog.e(throwable);
        //异常保存到数据库
        RecordUtils.saveLog(throwable.getClass().getName(), throwable.getMessage());

    }

    public abstract void initData();
}
