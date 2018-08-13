package com.lxk.lxf.ui.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseFragment;

/**
 * Created by Administrator on 2018/8/10 0010.
 */

public class MyFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView=inflater.inflate(R.layout.fragment_my,null);
        return bindView;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
