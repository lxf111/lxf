package com.lxk.lxf.ui.my.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.my.pop.CashPwdPop;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2018/1/19 0019.
 * 提现
 */

public class CashActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvBank;
    private EditText etMoney;
    private Button btnSubmit;
    private TextView tvRule;

    private CashPwdPop pop;

    private String loginPwd = "";//登录密码

    private String bankId = "";//提现银行卡Id

    private String money = "";//提现的金额

    private String balance="";//余额

    @Override
    protected void initView() {
        setContentView(R.layout.activity_cash);
        tvBank = (TextView) findViewById(R.id.tv_bank);
        etMoney = (EditText) findViewById(R.id.et_money);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        tvRule = (TextView) findViewById(R.id.tv_rule);
    }

    @Override
    protected void initData() {
        initTitle("余额提现");

        balance=getIntent().getStringExtra("balance");
        pop = new CashPwdPop(context, onClickListener, watcher);

//        /**
//         * 获取默认银行卡
//         */
//        Api.defaultCard(context, uid, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                String defaultCardName = "";
//                String cardId = "";
//                String tiXianProportion = "";
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
//                    if (object.has("defaultCardName") && !object.isNull("defaultCardName")) {
//                        defaultCardName = object.getString("defaultCardName");
//                    }
//                    if (object.has("cardId") && !object.isNull("cardId")) {
//                        cardId = object.getString("cardId");
//                    }
//                    if (object.has("tiXianProportion") && !object.isNull("tiXianProportion")) {
//                        tiXianProportion = object.getString("tiXianProportion");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (result.equals("0")) {
//                    tvBank.setText("" + defaultCardName);
//                    bankId = cardId;
//                    tvRule.setText("提现说明：\n1.最低提现金额为1元。\n2.提现手续费为" + tiXianProportion + "%，手续费不足1元按照1元收取。");
//                } else {
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    //取消或者确定按钮
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_cancel:
                    pop.dismiss();
                    break;
                case R.id.btn_submit:
                    String pwd = (String) SPUtils.get(context, "pwd", "");


                    if (loginPwd.equals("")) {
                        Toast.makeText(context, "请输入登录密码", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!pwd.equals(loginPwd)) {
                        Toast.makeText(context, "密码错误", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    submit();

                    break;
            }
        }
    };

    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            loginPwd = charSequence.toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    @Override
    protected void initEvent() {
        tvBank.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bank:
//                Intent intent = new Intent(context, BankListActivity.class);
//                startActivityForResult(intent, Constants.CASH_BANK);
                break;
            case R.id.btn_submit:
                money = etMoney.getText().toString().trim();

                if (TextUtils.isEmpty(money)) {
                    Toast.makeText(context, "请输入提现金额", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Double.valueOf(money)<1){
                    Toast.makeText(context, "至少提现1元", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(Double.valueOf(balance)<=Double.valueOf(money)){
                    Toast.makeText(context, "余额不足", Toast.LENGTH_SHORT).show();
                    return;
                }

                pop.showAtLocation(tvBank, Gravity.CENTER, 0, 0);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == Constants.CASH_BANK && resultCode == RESULT_OK) {
//            if (data != null) {
//                tvBank.setText("" + data.getStringExtra("bankName"));
//                bankId = data.getStringExtra("bankId");
//            }
//        }
    }

    /**
     * 提现申请
     */
    private void submit() {
//        Api.cash(context, uid, bankId, money, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                String shouXuMoney = "";
//                String date = "";
//                String resultMoney = "";
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
//                    if (object.has("shouXuMoney") && !object.isNull("shouXuMoney")) {
//                        shouXuMoney = object.getString("shouXuMoney");
//                    }
//                    if (object.has("date") && !object.isNull("date")) {
//                        date = object.getString("date");
//                    }
//                    if (object.has("resultMoney") && !object.isNull("resultMoney")) {
//                        resultMoney = object.getString("resultMoney");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (result.equals("0")) {
//                    pop.dismiss();
//                    Intent intent = new Intent(context, CashApplyActivity.class);
//                    intent.putExtra("money", "" + money);
//                    intent.putExtra("shouXuMoney", "" + shouXuMoney);
//                    intent.putExtra("date", "" + date);
//                    intent.putExtra("resultMoney", "" + resultMoney);
//                    startActivity(intent);
//                    finish();
//                    AppManager.finishActivity(WalletActivity.class);
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }
}
