package com.lxk.lxf.ui.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.utils.StatusBarUtil;

/**
 * Created by Administrator on 2018/1/19 0019.
 */

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    private ImageView ivBack;
    private TextView tvTitle;
    private TextView tvRight;

    private ImageView ivPurchase;
    private ImageView ivCash;
    private TextView tvBalance;

    private String balance = "0";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_wallet);
        StatusBarUtil.setTransparent(this);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("余额明细");

        ivBack = (ImageView) findViewById(R.id.iv_back);
        ivPurchase = (ImageView) findViewById(R.id.iv_purchase);
        ivCash = (ImageView) findViewById(R.id.iv_cash);
        tvBalance = (TextView) findViewById(R.id.tv_balance);

    }

    @Override
    protected void initData() {
        balance = getIntent().getStringExtra("balance");
        tvBalance.setText("¥" + balance);
    }

    @Override
    protected void initEvent() {
        ivBack.setOnClickListener(this);
        ivCash.setOnClickListener(this);
        ivPurchase.setOnClickListener(this);
        tvRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            //充值
            case R.id.iv_purchase:
                intent = new Intent(context, RechargeActivity.class);
                break;
            //提现
            case R.id.iv_cash:
                intent = new Intent(context, CashActivity.class);
                intent.putExtra("balance",""+balance);
                break;
            //零钱明细
            case R.id.tv_right:
                intent = new Intent(context, BalanceListActivity.class);
                intent.putExtra("balance", "" + balance);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
