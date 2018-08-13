package com.lxk.lxf.ui;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.business.BusinessFragment;
import com.lxk.lxf.ui.home.HomeFragment;
import com.lxk.lxf.ui.job.JobFragment;
import com.lxk.lxf.ui.my.MyFragment;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    private FrameLayout framelayoutMain;
    private RadioGroup mainRadiogroup;
    private RadioButton rbMainHome;
    private RadioButton rbMainNear;
    private RadioButton rbMainOrder;
    private RadioButton rbMainMy;

    private FragmentManager fm;
    private FragmentTransaction ft;

    private HomeFragment homeFragment;//首页
    private JobFragment shopFragment;//购物
    private BusinessFragment solicitationFragment;//征集
    private MyFragment myFragment;//我的

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTransparent(this);
        framelayoutMain = (FrameLayout) findViewById(R.id.framelayout_main);
        mainRadiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        rbMainHome = (RadioButton) findViewById(R.id.rb_main_home);
        rbMainNear = (RadioButton) findViewById(R.id.rb_main_near);
        rbMainOrder = (RadioButton) findViewById(R.id.rb_main_order);
        rbMainMy = (RadioButton) findViewById(R.id.rb_main_my);
        SPUtils.put(context, "isLogin", false);
    }

    @Override
    protected void initData() {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        homeFragment = new HomeFragment();
        shopFragment = new JobFragment();
        solicitationFragment = new BusinessFragment();
        myFragment = new MyFragment();

        ft.add(R.id.framelayout_main, homeFragment);
        ft.add(R.id.framelayout_main, shopFragment);
        ft.add(R.id.framelayout_main, solicitationFragment);
        ft.add(R.id.framelayout_main, myFragment);

        ft.show(homeFragment);

        goFragment();

        ft.commit();
    }

    @Override
    protected void initEvent() {
        rbMainHome.setOnClickListener(this);
        rbMainNear.setOnClickListener(this);
        rbMainOrder.setOnClickListener(this);
        rbMainMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction ft = fm.beginTransaction();
        switch (view.getId()) {
            //首页
            case R.id.rb_main_home:
                goFragment();
                ft.show(homeFragment);
                break;
            //购物
            case R.id.rb_main_near:
                goFragment();
                ft.show(shopFragment);
                break;
            //征集
            case R.id.rb_main_order:
                goFragment();
                ft.show(solicitationFragment);
                break;
            //我的
            case R.id.rb_main_my:
                goFragment();
                ft.show(myFragment);
                break;

        }

        ft.commit();
    }

    private void goFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(homeFragment);
        ft.hide(shopFragment);
        ft.hide(solicitationFragment);
        ft.hide(myFragment);
        ft.commit();
    }

    private void check() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 11);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            //进入到这里代表没有权限.
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, 11);
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
                    check();
                }
                break;
        }
    }

    /**
     * 双击退出程序
     */
    private int time = 1;

    private void getTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3 * 1000);
                    time = 1;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!homeFragment.isVisible()) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                goFragment();
                ft.show(homeFragment);
                ft.commit();
                rbMainHome.setChecked(true);

                return false;
            }
            if (time == 1) {
                Toast.makeText(MainActivity.this, "再次点击退出程序", Toast.LENGTH_LONG).show();
                time += 1;
                getTime();
                return false;
            } else {
                AppManager.finishAllActivity();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
