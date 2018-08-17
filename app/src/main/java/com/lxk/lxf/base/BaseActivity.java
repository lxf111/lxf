package com.lxk.lxf.base;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.utils.AppManager;
import com.lxk.lxf.utils.SPUtils;
import com.lxk.lxf.utils.StatusBarUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * Created by Slingge on 2017/1/24 0024.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public String uid;
    public Context context;
    public String lat;
    public String lng;
    public String city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.addActivity(this);
        context = this;
        uid = (String) SPUtils.get(this, "uid", "1");
        lat = (String) SPUtils.get(this, "lat", "34.755702");
        lng = (String) SPUtils.get(this, "lng", "113.740335");
        city = (String) SPUtils.get(this, "city", "郑州");

        setMiuiStatusBarDarkMode(this, false);
        setMeizuStatusBarDarkIcon(this, false);
        if (Build.VERSION.SDK_INT > 19) {
            StatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.theme));
        }
        initView();
        initData();
        initEvent();
    }

    /**
     * 标题栏：主题色背景
     *
     * @param title 标题名
     */
    protected void initTitle(String title) {
        TextView tv_title = (TextView) findViewById(R.id.tv_title);
        tv_title.setText(title);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.finishActivity((Activity) context);
                finish();
            }
        });
    }

    /**
     * 初始化控件
     */
    protected  abstract void initView();

    /**
     * 访问网络，处理数据
     */
    protected  abstract void initData();

    /**
     * 监听事件
     */
    protected abstract void initEvent();


    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
