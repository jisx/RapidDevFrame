package com.fortune.jisx.rapiddevframe.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(setContentView(), container, false);

        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onDestroy() {
        if (unbinder != null)
            unbinder.unbind();

        unbinder = null;

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

    }

    public abstract int setContentView();

    public abstract void initData();

}
