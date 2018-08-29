package com.lxk.lxf.ui.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.constant.Constant;
import com.lxk.lxf.ui.MainActivity;
import com.lxk.lxf.utils.Md5Util;
import com.lxk.lxf.utils.MyOkhttp;
import com.lxk.lxf.utils.PhoneAndPwdUtil;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText etPhone;
    private EditText etPwd;
    private Button btnSubmit;
    private TextView tvGoRegister;
    private TextView tvGoForget;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_login);
        StatusBarUtil.setTransparent(this);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnSubmit = (Button) findViewById(R.id.btn_submit);
        tvGoRegister = (TextView) findViewById(R.id.tv_go_register);
        tvGoForget = (TextView) findViewById(R.id.tv_go_forget);
    }

    @Override
    protected void initData() {
        String phone = (String) SPUtils.get(context, "phone", "");
        String pwd = (String) SPUtils.get(context, "pwd", "");

        SPUtils.put(context, "isFirst", false);

        if (phone != null) {
            etPhone.setText("" + phone);
        }

        if (pwd != null) {
            etPwd.setText("" + pwd);
        }
        check();
    }

    private void check() {
        if (ContextCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 11);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 11:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //用户同意授权
                    return;
                } else {
                    //用户拒绝授权
//                    check();
                }
                break;
        }
    }

    @Override
    protected void initEvent() {
        btnSubmit.setOnClickListener(this);
        tvGoForget.setOnClickListener(this);
        tvGoRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_submit:
                String phone = etPhone.getText().toString().trim();
                String pwd = etPwd.getText().toString().trim();

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!PhoneAndPwdUtil.isPhone(phone)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
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

                SPUtils.put(context, "phone", phone);
                SPUtils.put(context, "pwd", pwd);

                try {
                    submit(phone, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.tv_go_forget:
                intent = new Intent(context, ForgetActivity.class);
                break;
            case R.id.tv_go_register:
                intent = new Intent(context, RegisterActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * 登录
     */
    private void submit(String phone, String pwd) {
        Map<String, String> param = new HashMap<>();
        param.put("account", "" + phone);
        param.put("password", "" + pwd);
        MyOkhttp.post(context, Constant.base_url + Constant.login, param, new MyOkhttp.CallBack() {
            @Override
            public void onRequestComplete(String response, String result, String resultNote) {
                if (result.equals("0")) {
                    SPUtils.put(context, "isLogin", false);
                    Intent intent = new Intent(context, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
