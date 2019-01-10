package com.rapid.view.dialog;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rapid.view.R;

public class LoadingDialog extends DialogFragment {


    public LoadingDialog() {
        setStyle(STYLE_NO_FRAME, R.style.Common_Dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_loading, container, false);
        return view;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);
        } catch (Exception e) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace();
        }
    }
}
