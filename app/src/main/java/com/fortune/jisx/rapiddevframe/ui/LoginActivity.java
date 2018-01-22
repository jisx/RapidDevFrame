package com.fortune.jisx.rapiddevframe.ui;

import android.os.Bundle;

import com.fortune.jisx.rapiddevframe.R;
import com.fortune.jisx.rapiddevframe.ui.base.BaseAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        //TODO check version
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        goNext(MainActivity.class);
        finish();
    }
}
