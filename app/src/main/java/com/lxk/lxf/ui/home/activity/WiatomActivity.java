package com.lxk.lxf.ui.home.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.home.adapter.HomeRecommendAdapter;
import com.lxk.lxf.view.MyGridView;

/**
 * Created by Administrator on 2018/8/13 0013.
 */

public class WiatomActivity extends BaseActivity implements View.OnClickListener {
    private ImageView ivAdver;
    private TextView tvWiatom;
    private TextView tvLift;
    private TextView tvJob;
    private TextView tvChange;
    private TextView tvSignMore;
    private MyGridView gvSign;
    private TextView tvTrainMore;
    private MyGridView gvTrain;
    private TextView tvInterestMore;
    private MyGridView gvInterest;
    private TextView tvStoryMore;
    private MyGridView gvStory;

    private HomeRecommendAdapter recommendAdapter;//考试推荐

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_wiatom);
        initTitle("慧学习");
        ivAdver = (ImageView) findViewById(R.id.iv_adver);
        tvWiatom = (TextView) findViewById(R.id.tv_wiatom);
        tvLift = (TextView) findViewById(R.id.tv_lift);
        tvJob = (TextView) findViewById(R.id.tv_job);
        tvChange = (TextView) findViewById(R.id.tv_change);
        tvSignMore = (TextView) findViewById(R.id.tv_sign_more);
        gvSign = (MyGridView) findViewById(R.id.gv_sign);
        gvSign.setFocusable(false);
        tvTrainMore = (TextView) findViewById(R.id.tv_train_more);
        gvTrain = (MyGridView) findViewById(R.id.gv_train);
        gvTrain.setFocusable(false);
        tvInterestMore = (TextView) findViewById(R.id.tv_interest_more);
        gvInterest = (MyGridView) findViewById(R.id.gv_interest);
        gvInterest.setFocusable(false);
        tvStoryMore = (TextView) findViewById(R.id.tv_story_more);
        gvStory = (MyGridView) findViewById(R.id.gv_story);
        gvStory.setFocusable(false);
    }

    @Override
    protected void initData() {
        recommendAdapter = new HomeRecommendAdapter(context);
        gvSign.setAdapter(recommendAdapter);
        gvTrain.setAdapter(recommendAdapter);
        gvInterest.setAdapter(recommendAdapter);
        gvStory.setAdapter(recommendAdapter);
    }

    @Override
    protected void initEvent() {
        tvSignMore.setOnClickListener(this);
        tvTrainMore.setOnClickListener(this);
        tvInterestMore.setOnClickListener(this);
        tvStoryMore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //考试报名
            case R.id.tv_sign_more:
                break;
            //学习培训
            case R.id.tv_train_more:
                break;
            //兴趣爱好
            case R.id.tv_interest_more:
                break;
            //故事
            case R.id.tv_story_more:
                break;
        }
    }
}
