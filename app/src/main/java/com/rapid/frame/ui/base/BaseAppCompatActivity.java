package com.rapid.frame.ui.base;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.rapid.base.util.ThrowableUtils;
import com.rapid.base.util.ToastUtils;
import com.rapid.frame.application.MyApplication;
import com.rapid.base.util.StatusBarUtil;
import com.rapid.view.dialog.LoadingDialog;

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

    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.addActivity(this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        //当FitsSystemWindows设置 true 时，会在屏幕最上方预留出状态栏高度的 padding
        StatusBarUtil.setRootViewFitsSystemWindows(this, true);
        //设置状态栏白色背景
        StatusBarUtil.setTranslucentStatus(this);
        if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
            //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
            //这样半透明+白=灰, 状态栏的文字能看得清
            StatusBarUtil.setStatusBarColor(this, 0x55000000);
        }

        mEventBus = EventBus.builder().build();
        mEventBus.register(this);

        loadingDialog = new LoadingDialog();

        /*初始化数据*/
        initData();
    }

    @Override
    protected void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();
        unbinder = null;

        if (mEventBus != null)
            mEventBus.unregister(this);

        mEventBus = null;

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
        ToastUtils.showShort(ThrowableUtils.parseException(throwable));
        onError();
        hideLoading();
    }

    public void showLoading() {
        //默认不可以被手动取消
        showLoading(false);
    }

    public void showLoading(boolean cancelAble) {
        if (loadingDialog != null) {
            loadingDialog.setCancelable(cancelAble);
            loadingDialog.show(getSupportFragmentManager(), LoadingDialog.class.getName());
        }
    }

    public void hideLoading() {
        if (!isFinishing() && loadingDialog != null && !loadingDialog.isHidden()) {
            loadingDialog.dismiss();
        }
    }

    public void onError() {

    }

    public abstract void initData();
}
