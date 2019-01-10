package com.rapid.frame.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.rapid.frame.adapter.base.FragmentModel;

import java.util.List;


/**
 * 主页面的适配器，用于fragment的切换
 * Created by jsx on 2016/4/12.
 */
public class FragmentAdapter extends FragmentPagerAdapter {

    private List<FragmentModel> fragmentList;

    public FragmentAdapter(FragmentManager fm, List<FragmentModel> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentList.get(position).getTitle();
    }

}
