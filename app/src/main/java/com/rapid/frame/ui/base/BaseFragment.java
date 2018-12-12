package com.rapid.frame.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.rapid.base.util.ThrowableUtils;
import com.rapid.base.util.ToastUtils;
import com.rapid.frame.util.CommonUtils;
import com.rapid.view.dialog.LoadingDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by jsx on 2016/4/5.
 */
public abstract class BaseFragment extends Fragment {

    EventBus mEventBus;
    Unbinder unbinder;

    LoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(setContentView(), container, false);

        mEventBus = EventBus.builder().build();
        mEventBus.register(this);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        loadingDialog = new LoadingDialog();
        /*初始化数据*/
        initData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();
        unbinder = null;

        if (mEventBus != null)
            mEventBus.unregister(this);
        mEventBus = null;
        super.onDestroy();
    }

    public void goNext(Class toClass, Intent intent) {
        intent.setClass(getActivity(), toClass);
        startActivity(intent);
    }

    public void goNext(Class toClass) {
        Intent intent = new Intent(getActivity(), toClass);
        startActivity(intent);
    }

    public void goNextForResult(int requestCode, Class toClass) {
        Intent intent = new Intent(getActivity(), toClass);
        startActivityForResult(intent, requestCode);
    }

    public void goNextForResult(int requestCode, Class toClass, Intent intent) {
        intent.setClass(getActivity(), toClass);
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
    }


    public void showLoading(boolean cancelAble) {
        if (loadingDialog != null) {
            loadingDialog.setCancelable(cancelAble);
            loadingDialog.show(getChildFragmentManager(), LoadingDialog.class.getName());
        }
    }

    public void hideLoading() {
        if (loadingDialog != null && !loadingDialog.isHidden()) {
            loadingDialog.dismiss();
        }
    }

    public abstract int setContentView();

    public abstract void initData();

    public void onError() {

    }

}
