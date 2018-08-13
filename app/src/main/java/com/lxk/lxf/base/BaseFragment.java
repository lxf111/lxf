package com.lxk.lxf.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lxk.lxf.utils.SPUtils;


/**
 * 项目名称：ProjectFramework
 * 类名称：BaseFragment
 * 类描述：Fragment基础类
 * 创建人：Tiramisu
 * 创建时间：2017/1/17 11:49
 */

public abstract class BaseFragment extends Fragment {
    protected Context context;
    protected String uid;
    public String lat;
    public String lng;
    public String city;

    public View bindView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        uid = (String) SPUtils.get(getActivity(), "uid", "");
        lat = (String) SPUtils.get(getActivity(), "lat", "34.755702");
        lng = (String) SPUtils.get(getActivity(), "lng", "113.740335");
        city = (String) SPUtils.get(getActivity(), "city", "郑州");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView(bindView);
        initData();
        initEvent();
    }

    /**
     * 初始化控件
     */
    protected abstract void initView(View view);


    /**
     * 访问网络，处理数据
     */
    protected abstract void initData();

    /**
     * 监听事件
     */
    protected abstract void initEvent();
}
