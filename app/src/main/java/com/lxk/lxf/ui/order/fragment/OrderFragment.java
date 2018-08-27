package com.lxk.lxf.ui.order.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseFragment;
import com.lxk.lxf.ui.order.adapter.OrderAdapter;

/**
 * Created by zhf on 2018/8/27.
 */

public class OrderFragment extends BaseFragment {

    private ListView listView;

    private OrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = inflater.inflate(R.layout.fragment_order, null);
        return bindView;
    }

    @Override
    protected void initView(View view) {
        listView = view.findViewById(R.id.lv_order);
    }

    @Override
    protected void initData() {
        adapter = new OrderAdapter(context);
        listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }
}
