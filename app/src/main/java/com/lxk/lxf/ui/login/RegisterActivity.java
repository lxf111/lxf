package com.lxk.lxf.ui.login;

import android.content.Intent;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.constant.Constant;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.MyOkhttp;
import com.lxk.lxf.utils.PhoneAndPwdUtil;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.TimerUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;


/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private TextView tvYzm;
    private EditText etYzm;
    private EditText etPwd1;
    private EditText etPwd2;
    private LinearLayout linAgree;
    private Button btnSubmit;
    private TextView tvGoLogin;
    private ImageView ivSelect;
    private TextView tvSelect;
    private TextView tvXieYi;

    private boolean isAgree = true;//是否同意

    private String phone = "";
    private String code = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_register);
        etPhone = (EditText) findViewById(R.id.et_phone);
        tvYzm = (TextView) findViewById(R.id.tv_yzm);
        etYzm = (EditText) findViewById(R.id.et_yzm);
        etPwd1 = (EditText) findViewById(R.id.et_pwd1);
        etPwd2 = (EditText) findViewById(R.id.et_pwd2);
        linAgree = (LinearLayout) findViewById(R.id.lin_agree);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        tvGoLogin = (TextView) findViewById(R.id.tv_go_login);
        ivSelect = (ImageView) findViewById(R.id.iv_select);
        tvSelect = (TextView) findViewById(R.id.tv_select);
        tvXieYi = (TextView) findViewById(R.id.tv_xieyi);
    }

    @Override
    protected void initData() {
        initTitle("注册");
    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(this);
        tvYzm.setOnClickListener(this);
        tvGoLogin.setOnClickListener(this);
        tvSelect.setOnClickListener(this);
        ivSelect.setOnClickListener(this);
        tvXieYi.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

                if (TextUtils.isEmpty(yzm)) {
                    Toast.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
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

                if (!isAgree) {
                    Toast.makeText(context, "请同意注册协议", Toast.LENGTH_SHORT).show();
                    return;
                }

                register(phone2, yzm, pwd);

                break;
            case R.id.tv_go_login:
                finish();
                break;
            case R.id.tv_select:
            case R.id.iv_select:
                if (isAgree) {
                    ivSelect.setImageResource(R.mipmap.register_agree_no);
                    isAgree = false;
                } else {
                    ivSelect.setImageResource(R.mipmap.register_agree);
                    isAgree = true;
                }
                break;
            case R.id.tv_xieyi:
//                Intent intentWatch = new Intent(context, AgreementActivity.class);
//                intentWatch.putExtra("type", 0);
//                startActivity(intentWatch);
                break;
        }
    }

    /**
     * 发送验证码
     * <p>
     * existence  1已注册  0未注册
     */
    private void sendYzm() {
        MyOkhttp.get(context, "http://60.205.190.247:8088/api/register/getCode?mobile=" + phone, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                if (result.equals("0")) {
                    TimerUtil timerUtil = new TimerUtil(tvYzm);
                    timerUtil.timers();
                    Toast.makeText(RegisterActivity.this, "验证码发送成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, resultNote, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 注册
     *
     * @param phone  手机号
     * @param number 验证码code
     */
    private void register(String phone, String number, String pwd) {
        Map<String, String> param = new HashMap<>();
        param.put("account", "" + phone);
        param.put("password", "" + pwd);
        param.put("mobile", "" + phone);
        param.put("code", "" + number);
        MyOkhttp.post(context, Constant.base_url + Constant.register, param, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                if (result.equals("0")) {
                    finish();
                    Toast.makeText(RegisterActivity.this, resultNote, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, resultNote, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
