package com.lxk.lxf.ui.order;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.order.adapter.OrderPagerAdapter;
import com.lxk.lxf.ui.order.fragment.OrderFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhf on 2018/8/27.
 */

public class OrderActivity extends BaseActivity {


    private TabLayout tabOrder;
    private ViewPager viewOrder;

    private String tabNames[] = {"待支付", "进行中", "已完成", "已取消"};

    private List<Fragment> fragmentList = new ArrayList<>();

    private OrderPagerAdapter adapter;

    private OrderFragment fragment;

    private int index = 0;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_order);
        initTitle("我的订单");
        tabOrder = findViewById(R.id.tab_order);
        viewOrder = findViewById(R.id.view_order);

        index = getIntent().getIntExtra("", 0);

    }

    @Override
    protected void initData() {
        Bundle bundle = new Bundle();
        bundle.putInt("type", 0);
        fragment = new OrderFragment();
        fragment.setArguments(bundle);
        fragmentList.add(fragment);

        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", 1);
        fragment = new OrderFragment();
        fragment.setArguments(bundle2);
        fragmentList.add(fragment);

        Bundle bundle3 = new Bundle();
        bundle3.putInt("type", 2);
        fragment = new OrderFragment();
        fragment.setArguments(bundle3);
        fragmentList.add(fragment);

        Bundle bundle4 = new Bundle();
        bundle4.putInt("type", 3);
        fragment = new OrderFragment();
        fragment.setArguments(bundle4);
        fragmentList.add(fragment);

        adapter = new OrderPagerAdapter(getSupportFragmentManager(), tabNames, fragmentList);
        viewOrder.setAdapter(adapter);
        tabOrder.setupWithViewPager(viewOrder);
        viewOrder.setOffscreenPageLimit(4);
        tabOrder.setSelectedTabIndicatorColor(getResources().getColor(R.color.theme));
        viewOrder.setCurrentItem(index);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
