package com.lxk.lxf.ui.home.activity;

import android.widget.GridView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.home.adapter.HomeRecommendAdapter;
import com.lxk.lxf.view.MyGridView;
import com.lxk.lxf.view.NormalFooterView;
import com.lxk.lxf.view.NormalHeaderView;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;

/**
 * Created by zhf on 2018/8/17.
 * 考试报名
 */

public class SignUpActivity extends BaseActivity implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    private GridView gridView;
    private HomeRecommendAdapter adapter;

    private NormalHeaderView headerView;
    private NormalFooterView footerView;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_sign_up);
        initTitle("考试报名");

        gridView = (GridView) findViewById(R.id.gv_sign_up);
        headerView = (NormalHeaderView) findViewById(R.id.header);
        footerView = (NormalFooterView) findViewById(R.id.footer);
    }

    @Override
    protected void initData() {
        adapter = new HomeRecommendAdapter(context);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {
        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
                footerView.stopLoad();
            }
        }, 1000);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                headerView.stopRefresh();
            }
        }, 1000);
    }
}
