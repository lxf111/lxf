package com.lxk.lxf.ui.order.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/12/27.
 */

public class OrderPagerAdapter extends FragmentPagerAdapter {

    private String[] tabName;
    private List<Fragment> fragments;

    public OrderPagerAdapter(FragmentManager fm, String[] tabName, List<Fragment> fragments) {
        super(fm);
        this.tabName = tabName;
        this.fragments = fragments;
    }


    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabName[position];
    }

}
