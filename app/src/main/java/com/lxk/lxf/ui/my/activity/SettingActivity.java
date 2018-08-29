package com.lxk.lxf.ui.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.login.LoginActivity;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.SPUtils;

/**
 * Created by zhf on 2018/8/29.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvUpdatePwd;
    private TextView tvUpdate;
    private TextView tvCache;
    private LinearLayout linCustomer;
    private TextView tvCustomer;
    private Button btnSubmit;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_setting);
        initTitle("设置");
        tvUpdatePwd = (TextView) findViewById(R.id.tv_update_pwd);
        tvUpdate = (TextView) findViewById(R.id.tv_update);
        tvCache = (TextView) findViewById(R.id.tv_cache);
        linCustomer = (LinearLayout) findViewById(R.id.lin_customer);
        tvCustomer = (TextView) findViewById(R.id.tv_customer);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出登录
            case R.id.btn_submit:
                SPUtils.remove(context, "isLogin");
                AppManager.finishAllActivity();
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
