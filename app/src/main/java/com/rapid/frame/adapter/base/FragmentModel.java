package com.rapid.frame.adapter.base;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;

/**
 * Created by jisx on 2016/7/8.
 */
public class FragmentModel {

    /**
     * 页面名称
     */
    private String title;
    /**
     * 显示的页面
     */
    private Fragment fragment;
    /**
     * 切换的图标
     */
    private Drawable icon;

    public FragmentModel(String title, Fragment fragment, Drawable icon) {
        this.title = title;
        this.fragment = fragment;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }
}
