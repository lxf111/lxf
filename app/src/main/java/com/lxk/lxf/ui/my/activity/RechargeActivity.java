package com.lxk.lxf.ui.my.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/19 0019.
 * 充值界面
 */

public class RechargeActivity extends BaseActivity implements View.OnClickListener {
    private RadioButton tvStateWx;
    private RadioButton tvStateAply;
    private TextView tvmoneytitle;
    private EditText etMoney;
    private Button btnSubmit;

    private String channel = "wx";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_recharge);

        tvStateWx = (RadioButton) findViewById(R.id.tv_state_wx);
        tvStateAply = (RadioButton) findViewById(R.id.tv_state_aply);
        tvmoneytitle = (TextView) findViewById(R.id.tvmoneytitle);
        etMoney = (EditText) findViewById(R.id.et_money);
        btnSubmit = (Button) findViewById(R.id.btn_submit);

    }

    @Override
    protected void initData() {
        initTitle("余额充值");
    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(this);
        tvStateWx.setOnClickListener(this);
        tvStateAply.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //微信
            case R.id.tv_state_wx:
                channel = "wx";
                break;
            //支付宝
            case R.id.tv_state_aply:
                channel = "alipay";
                break;
            case R.id.btn_submit:

                String money = etMoney.getText().toString().trim();

                if (TextUtils.isEmpty(money)) {
                    Toast.makeText(context, "价格不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (channel.equals("wx")) {
                    getOrder("1", money);
                } else {
                    getOrder("0", money);
                }


                break;
        }
    }

    /**
     * 获取订单号
     *
     * @param payType 支付宝0 微信1 余额2
     */
    private void getOrder(String payType, final String money) {
        btnSubmit.setEnabled(false);
//        Api.getOrder(context, uid, "1", "", payType, money, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                String orderId = "";
//                try {
//                    JSONObject object = new JSONObject(response);
//
//                    if (object.has("result") && !object.isNull("result")) {
//                        result = object.getString("result");
//                    }
//
//                    if (object.has("resultNote") && !object.isNull("resultNote")) {
//                        resultNote = object.getString("resultNote");
//                    }
//                    if (object.has("orderId") && !object.isNull("orderId")) {
//                        orderId = object.getString("orderId");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                if (result.equals("0")) {
//                    getCharge(orderId, money);
//                } else {
//                    btnSubmit.setEnabled(true);
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

    /**
     * 获取charge
     *
     * @param orderNum
     * @param money
     */
    private void getCharge(String orderNum, String money) {
//        String payPrice = String.valueOf(Integer.valueOf((int) (Double.valueOf(money) * 100)));
//        Api.purchase(context, uid, payPrice, orderNum, channel, "充值到嘉笳微课", new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                String charge = "";
//                try {
//                    JSONObject object = new JSONObject(response);
//
//                    if (object.has("result") && !object.isNull("result")) {
//                        result = object.getString("result");
//                    }
//
//                    if (object.has("resultNote") && !object.isNull("resultNote")) {
//                        resultNote = object.getString("resultNote");
//                    }
//                    if (object.has("charge") && !object.isNull("charge")) {
//                        charge = object.getString("charge");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                if (result.equals("0")) {
//                    Pingpp.createPayment(RechargeActivity.this, charge);
//                } else {
//                    btnSubmit.setEnabled(true);
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        //支付页面返回处理
//        if (requestCode == Pingpp.REQUEST_CODE_PAYMENT) {
//            if (resultCode == Activity.RESULT_OK) {
//                String result = data.getExtras().getString("pay_result");
//            /* 处理返回值
//             * "success" - 支付成功
//             * "fail"    - 支付失败
//             * "cancel"  - 取消支付
//             * "invalid" - 支付插件未安装（一般是微信客户端未安装的情况）
//             * "unknown" - app进程异常被杀死(一般是低内存状态下,app进程被杀死)
//             */
//                String errorMsg = data.getExtras().getString("error_msg"); // 错误信息
//                String extraMsg = data.getExtras().getString("extra_msg"); // 错误信息
//                if (result.equals("success")) {
////                    Intent intent = new Intent(context, SuccessActivity.class);
////                    intent.putExtra("name", "" + cityName);
////                    startActivity(intent);
//                    String money = (String) SPUtils.get(context, "balance", "");
//
//                    double allMoney = Double.valueOf(money) + Double.valueOf(etMoney.getText().toString().trim());
//
//                    SPUtils.put(context, "balance", "" + allMoney);
//
//                    setResult(RESULT_OK);
//                    finish();
//                    AppManager.finishActivity(WalletActivity.class);
//                    Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
//                } else if (result.equals("fail")) {
//                    Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
//                } else if (result.equals("cancel")) {
//                    Toast.makeText(context, "支付取消", Toast.LENGTH_SHORT).show();
//                }
//                btnSubmit.setEnabled(true);
//            }
//        }
    }


}
