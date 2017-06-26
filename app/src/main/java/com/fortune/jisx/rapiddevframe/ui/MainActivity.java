package com.fortune.jisx.rapiddevframe.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.fortune.jisx.rapiddevframe.R;
import com.fortune.jisx.rapiddevframe.ui.base.BaseAppCompatActivity;
import com.fortune.jisx.view.util.ToastUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.tv_toast_short)
    public void onMTvToastShortClicked() {
        ToastUtil.show("测试机");
    }

    @OnClick(R.id.tv_toast_long)
    public void onMTvToastLongClicked() {

        ToastUtil.showLong("测试机");
    }
}
