package com.lxk.lxf.ui.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseFragment;
import com.lxk.lxf.ui.my.activity.PersonEditorActivity;
import com.lxk.lxf.ui.my.activity.WalletActivity;
import com.lxk.lxf.ui.order.OrderActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/8/10 0010.
 */

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout relData;
    private CircleImageView ivHeader;
    private TextView tvName;
    private TextView tvSchool;
    private LinearLayout linName;
    private TextView tvPoor;
    private RelativeLayout relName;
    private TextView tvTime;
    private LinearLayout linWatch;
    private TextView tvMoney;
    private LinearLayout linWallet;
    private TextView tvIntegral;
    private LinearLayout linIntegral;
    private TextView tvAllExchange;
    private ImageView ivPay;
    private TextView tvPayNumber;
    private LinearLayout linPay;
    private ImageView ivSend;
    private TextView tvSendNumber;
    private LinearLayout linSend;
    private ImageView ivShou;
    private TextView tvShou;
    private LinearLayout linShou;
    private LinearLayout linComplete;
    private TextView tvRecord;
    private TextView tvCollection;
    private TextView tvCustomer;
    private TextView tvSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = inflater.inflate(R.layout.fragment_my, null);
        initView(bindView);
        return bindView;
    }

    @Override
    protected void initView(View view) {

        relData = (RelativeLayout) view.findViewById(R.id.rel_data);
        relData.setOnClickListener(this);
        ivHeader = (CircleImageView) view.findViewById(R.id.iv_header);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvSchool = (TextView) view.findViewById(R.id.tv_school);
        linName = (LinearLayout) view.findViewById(R.id.lin_name);
        tvPoor = (TextView) view.findViewById(R.id.tv_poor);
        relName = (RelativeLayout) view.findViewById(R.id.rel_name);
        relName.setOnClickListener(this);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tvTime.setOnClickListener(this);
        linWatch = (LinearLayout) view.findViewById(R.id.lin_watch);
        linWatch.setOnClickListener(this);
        tvMoney = (TextView) view.findViewById(R.id.tv_money);
        tvMoney.setOnClickListener(this);
        linWallet = (LinearLayout) view.findViewById(R.id.lin_wallet);
        linWallet.setOnClickListener(this);
        tvIntegral = (TextView) view.findViewById(R.id.tv_integral);
        tvIntegral.setOnClickListener(this);
        linIntegral = (LinearLayout) view.findViewById(R.id.lin_integral);
        linIntegral.setOnClickListener(this);
        tvAllExchange = (TextView) view.findViewById(R.id.tv_all_exchange);
        tvAllExchange.setOnClickListener(this);
        ivPay = (ImageView) view.findViewById(R.id.iv_pay);
        ivPay.setOnClickListener(this);
        tvPayNumber = (TextView) view.findViewById(R.id.tv_pay_number);
        tvPayNumber.setOnClickListener(this);
        linPay = (LinearLayout) view.findViewById(R.id.lin_pay);
        linPay.setOnClickListener(this);
        ivSend = (ImageView) view.findViewById(R.id.iv_send);
        ivSend.setOnClickListener(this);
        tvSendNumber = (TextView) view.findViewById(R.id.tv_send_number);
        tvSendNumber.setOnClickListener(this);
        linSend = (LinearLayout) view.findViewById(R.id.lin_send);
        linSend.setOnClickListener(this);
        ivShou = (ImageView) view.findViewById(R.id.iv_shou);
        ivShou.setOnClickListener(this);
        tvShou = (TextView) view.findViewById(R.id.tv_shou);
        tvShou.setOnClickListener(this);
        linShou = (LinearLayout) view.findViewById(R.id.lin_shou);
        linShou.setOnClickListener(this);
        linComplete = (LinearLayout) view.findViewById(R.id.lin_complete);
        linComplete.setOnClickListener(this);
        tvRecord = (TextView) view.findViewById(R.id.tv_record);
        tvRecord.setOnClickListener(this);
        tvCollection = (TextView) view.findViewById(R.id.tv_collection);
        tvCollection.setOnClickListener(this);
        tvCustomer = (TextView) view.findViewById(R.id.tv_customer);
        tvCustomer.setOnClickListener(this);
        tvSetting = (TextView) view.findViewById(R.id.tv_setting);
        tvSetting.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            //商家认证
            case R.id.lin_watch:
                break;
            //钱包
            case R.id.lin_wallet:
                intent = new Intent(context, WalletActivity.class);
                break;
            //优惠卷
            case R.id.lin_integral:
                break;
            //个人资料
            case R.id.rel_data:
                intent = new Intent(context, PersonEditorActivity.class);
                break;
            //全部订单
            case R.id.tv_all_exchange:
                intent = new Intent(context, OrderActivity.class);
                intent.putExtra("", 0);
                break;
            //待支付
            case R.id.lin_pay:
                intent = new Intent(context, OrderActivity.class);
                intent.putExtra("", 1);
                break;
            //待收货
            case R.id.lin_shou:
                intent = new Intent(context, OrderActivity.class);
                intent.putExtra("", 2);
                break;
            //已完成
            case R.id.lin_complete:
                intent = new Intent(context, OrderActivity.class);
                intent.putExtra("", 3);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
