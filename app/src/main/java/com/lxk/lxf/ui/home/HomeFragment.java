package com.lxk.lxf.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseFragment;
import com.lxk.lxf.ui.home.activity.WiatomActivity;
import com.lxk.lxf.ui.home.adapter.HomeRecommendAdapter;
import com.lxk.lxf.ui.home.adapter.HomeSchoolAdapter;
import com.lxk.lxf.utils.PicassoUtils;
import com.lxk.lxf.view.MyGridView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/10 0010.
 */

public class HomeFragment extends BaseFragment implements XBanner.XBannerAdapter, View.OnClickListener {
    private ImageView ivLogo;
    private ImageView ivSearch;
    private XBanner xbanner;
    private TextView tvWiatom;
    private TextView tvLift;
    private TextView tvJob;
    private TextView tvChange;
    private MyGridView gridHomeRecommend;
    private MyGridView gridHomeSchool;

    private HomeRecommendAdapter recommendAdapter;//考试推荐

    private HomeSchoolAdapter schoolAdapter;//学校推荐

    private List<String> xbannerData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindView = inflater.inflate(R.layout.fragment_home, null);
        return bindView;
    }

    @Override
    protected void initView(View view) {
        ivLogo = (ImageView) view.findViewById(R.id.iv_logo);
        ivSearch = (ImageView) view.findViewById(R.id.iv_search);
        xbanner = (XBanner) view.findViewById(R.id.xbanner);
        tvWiatom = (TextView) view.findViewById(R.id.tv_wiatom);
        tvLift = (TextView) view.findViewById(R.id.tv_lift);
        tvJob = (TextView) view.findViewById(R.id.tv_job);
        tvChange = (TextView) view.findViewById(R.id.tv_change);
        gridHomeRecommend = (MyGridView) view.findViewById(R.id.grid_home_recommend);
        gridHomeSchool = (MyGridView) view.findViewById(R.id.grid_home_school);
    }

    @Override
    protected void initData() {
        recommendAdapter = new HomeRecommendAdapter(context);
        gridHomeRecommend.setAdapter(recommendAdapter);

        schoolAdapter = new HomeSchoolAdapter(context);
        gridHomeSchool.setAdapter(schoolAdapter);

        xbanner.setmAdapter(this);

        xbannerData.add("http://img2.imgtn.bdimg.com/it/u=3331037431,630346627&fm=27&gp=0.jpg");
        xbannerData.add("http://img1.imgtn.bdimg.com/it/u=1910375278,3999666451&fm=27&gp=0.jpg");
        xbannerData.add("http://img2.imgtn.bdimg.com/it/u=2749742904,3298950930&fm=27&gp=0.jpg");
        xbannerData.add("http://img0.imgtn.bdimg.com/it/u=2334337476,2723911054&fm=27&gp=0.jpg");

        xbanner.setData(xbannerData, null);
    }

    @Override
    protected void initEvent() {
        tvWiatom.setOnClickListener(this);
        tvLift.setOnClickListener(this);
        tvJob.setOnClickListener(this);
        tvChange.setOnClickListener(this);
    }

    @Override
    public void loadBanner(XBanner banner, Object model, View view, int position) {
        PicassoUtils.showPhoto(context, xbannerData.get(position), (ImageView) view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            //会学习
            case R.id.tv_wiatom:
                intent = new Intent(context, WiatomActivity.class);
                intent.putExtra("type",1);
                break;
            //会生活
            case R.id.tv_lift:
                intent = new Intent(context, WiatomActivity.class);
                intent.putExtra("type",2);
                break;
            //会求职
            case R.id.tv_job:
                intent = new Intent(context, WiatomActivity.class);
                intent.putExtra("type",3);
                break;
            //会创业
            case R.id.tv_change:
                intent = new Intent(context, WiatomActivity.class);
                intent.putExtra("type",4);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
