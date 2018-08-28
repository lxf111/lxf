package com.lxk.lxf.ui.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseFragment;
import com.lxk.lxf.view.MyWebView;

/**
 * Created by Administrator on 2018/8/10 0010.
 */

public class BusinessFragment extends BaseFragment{
    private WebView webView;

    private int type = 0;//0注册协议，1公告 2积分规则 3关于我们 4贫困说明

    private String url = "http://www.baidu.com/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView=inflater.inflate(R.layout.fragment_business,null);
        return bindView;
    }

    @Override
    protected void initView(View view) {
        MyWebView myWebView = (MyWebView) view.findViewById(R.id.webView);
        webView = myWebView.getWebView();
    }

    @Override
    protected void initData() {
        WebSettings settings = webView.getSettings();
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        // 设置支持js
        settings.setJavaScriptEnabled(true);
        // 关闭缓存
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 支持自动加载图片
        settings.setLoadsImagesAutomatically(true);
        // 设置出现缩放工具
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        // 扩大比例的缩放
        settings.setUseWideViewPort(true);
        // 自适应屏幕
        settings.setLoadWithOverviewMode(true);
        webView.loadUrl("http://www.baidu.com/");

//        if (type == 0) {
//
//        } else if (type == 1) {
//            webView.loadUrl("http://120.77.247.30/education/toubu/disPlayDetail?id=1");
//        } else if (type == 2) {
//            webView.loadUrl("http://120.77.247.30/education/aboutus/disPlayDetail?id=4");
//        } else if (type == 3) {
//            webView.loadUrl("http://120.77.247.30/education/aboutus/disPlayDetail?id=2");
//        } else if (type == 4) {
//            webView.loadUrl("http://120.77.247.30/education/aboutus/disPlayDetail?id=3");
//        }
    }

    @Override
    protected void initEvent() {

    }
}
