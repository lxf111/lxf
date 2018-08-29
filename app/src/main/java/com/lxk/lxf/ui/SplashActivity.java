package com.lxk.lxf.ui;

import android.content.Intent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.login.LoginActivity;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;


/**
 * Created by Administrator on 2017/8/30 0030.
 */

public class SplashActivity extends BaseActivity {

    private ImageView iv_spash;

    protected void initView() {
        setContentView(R.layout.activity_splash);
        StatusBarUtil.setTransparent(this);
        iv_spash = (ImageView) findViewById(R.id.iv_splash);
        //0代表不显示，1代表显示
        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 1);
        alphaAnimation.setDuration(2000);// 设置动画持续时长
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setRepeatCount(0);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean isFirst = (boolean) SPUtils.get(SplashActivity.this, "isFirst", true);
                boolean isLogin = (boolean) SPUtils.get(SplashActivity.this, "isLogin", true);

//                if(isFirst){
//                    Intent intent= new Intent(SplashActivity.this, GuidActivity.class);
//                    startActivity(intent);
//                    finish();
//                    return;
//                }

                if (isLogin) {
                    Intent intentFirst = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intentFirst);
                    finish();
                } else {
                    Intent intentMain = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                }
            }
        });
        iv_spash.startAnimation(alphaAnimation);
    }

    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }
}
