package com.lxk.lxf.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.utils.Md5Util;
import com.lxk.lxf.utils.PhoneAndPwdUtil;
import com.lxk.lxf.utils.SPUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    private EditText etPhone;
    private TextView tvYzm;
    private EditText etYzm;
    private EditText etPwd1;
    private EditText etPwd2;
    private Button btnSubmit;

    private String phone = "";
    private String code = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_forget);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvYzm = (TextView) findViewById(R.id.tv_yzm);
        etYzm = (EditText) findViewById(R.id.et_yzm);
        etPwd1 = (EditText) findViewById(R.id.et_pwd1);
        etPwd2 = (EditText) findViewById(R.id.et_pwd2);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    protected void initData() {
        initTitle("忘记密码");
    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(this);
        tvYzm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                String phone2 = etPhone.getText().toString().trim();
                String pwd = etPwd1.getText().toString().trim();
                String pwd2 = etPwd2.getText().toString().trim();
                String yzm = etYzm.getText().toString().trim();

                if (TextUtils.isEmpty(phone2)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!PhoneAndPwdUtil.isPhone(phone2)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!phone2.equals(phone)) {
                    Toast.makeText(this, "请使用获取验证码的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (code.equals("")) {
                    Toast.makeText(this, "请先获取验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(yzm)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!code.equals(yzm)) {
                    Toast.makeText(this, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (pwd.length() < 6) {
                    Toast.makeText(this, "密码至少6位", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pwd2)) {
                    Toast.makeText(this, "请确认密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pwd.equals(pwd2)) {
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                SPUtils.put(context, "phone", phone2);
                SPUtils.put(context, "pwd", pwd);

                try {
                    submit(phone2, Md5Util.md5Encode(pwd));
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                finish();

                break;
            case R.id.tv_yzm:
                phone = etPhone.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(context, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!PhoneAndPwdUtil.isPhone(phone)) {
                    Toast.makeText(context, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }


                sendYzm();
                break;
        }
    }

    /**
     * 验证手机号是否已经注册
     * <p>
     * existence  1已注册  0未注册
     */
    private void sendYzm() {
//        Api.confirmPhone(context, phone, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                String existence = "";
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
//
//                    if (object.has("existence") && !object.isNull("existence")) {
//                        existence = object.getString("existence");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (result.equals("0")) {
//                    if (existence.equals("1")) {
//                        code = TimerUtil.getNum();
////                        etYzm.setText(code);
//                        sendMessage(phone, code);
//                    } else {
//                        Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    /**
     * 发送短信
     *
     * @param phone  手机号
     * @param number 验证码code
     */
    private void sendMessage(String phone, String number) {

        Map<String, String> params = new HashMap<>();
        try {
            params.put("tpl_value", URLEncoder.encode("#code#=" + number, "utf-8"));
            params.put("dtype", "json");
            params.put("tpl_id", "62407");
            params.put("key", "da27d42232c1d8018e2a7d9acf77a936");
            params.put("mobile", phone);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        OkHttpUtils
//                .post()
//                .params(params)
//                .url(Constants.JUHE_SEND_URL)
//                .build()//
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        Log.i("TAG", "e=" + e.getMessage());
//                        Toast.makeText(context, "验证码发送失败", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        Log.i("TAG", "response" + response);
//                        Gson gson = new Gson();
//                        VerificationCodemodel Vbean = gson.fromJson(response, VerificationCodemodel.class);
//                        if ("操作成功".equals(Vbean.reason)) {
//                            Toast.makeText(context, "验证码已发送", Toast.LENGTH_SHORT).show();
//                            TimerUtil timerUtil = new TimerUtil(tvYzm);
//                            timerUtil.timers();
//                        } else {
//                            Toast.makeText(context, "验证码发送失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
    }

    /**
     * 提交修改密码
     */
    private void submit(String phone, String pwd) {
//        Api.forgetPwd(context, phone, pwd, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
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
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                if (result.equals("0")) {
//                    finish();
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

}
