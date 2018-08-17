package com.lxk.lxf.ui.my.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;
import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zhf on 2018/8/17.
 */

public class PersonEditorActivity extends BaseActivity implements View.OnClickListener {

    //图片选择的参数
    private int selectMode = FunctionConfig.MODE_SINGLE;
    private List<LocalMedia> selectMedia = new ArrayList<>();
    private boolean isShow = true;
    private boolean enablePreview = true;
    private boolean isPreviewVideo = true;
    private boolean enableCrop = false;
    private boolean theme = false;
    private boolean selectImageType = false;
    private boolean isCompress = true;
    private boolean isCheckNumMode = false;
    private int compressFlag = 1;// 1 系统自带压缩 2 luban压缩

    private RelativeLayout relHeader;
    private ImageView ivRight;
    private CircleImageView ivHeader;
    private LinearLayout linName;
    private TextView tvName;
    private LinearLayout linSchool;
    private TextView tvSchool;
    private LinearLayout linBirth;
    private TextView tvBirth;
    private LinearLayout linSex;
    private TextView tvSex;
    private LinearLayout linPhone;
    private TextView tvPhone;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_person_editor);
        initTitle("个人信息");
        relHeader = (RelativeLayout) findViewById(R.id.rel_header);
        ivRight = (ImageView) findViewById(R.id.iv_right);
        ivHeader = (CircleImageView) findViewById(R.id.iv_header);
        linName = (LinearLayout) findViewById(R.id.lin_name);
        tvName = (TextView) findViewById(R.id.tv_name);
        linSchool = (LinearLayout) findViewById(R.id.lin_school);
        tvSchool = (TextView) findViewById(R.id.tv_school);
        linBirth = (LinearLayout) findViewById(R.id.lin_birth);
        tvBirth = (TextView) findViewById(R.id.tv_birth);
        linSex = (LinearLayout) findViewById(R.id.lin_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        linPhone = (LinearLayout) findViewById(R.id.lin_phone);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        relHeader.setOnClickListener(this);
        linName.setOnClickListener(this);
        linSchool.setOnClickListener(this);
        linBirth.setOnClickListener(this);
        linSex.setOnClickListener(this);
        linPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //头像
            case R.id.rel_header:
                FunctionConfig config = new FunctionConfig();
                config.setCompress(isCompress);
                config.setEnablePixelCompress(false);
                config.setEnableQualityCompress(true);
                config.setMaxSelectNum(1);
                config.setSelectMode(selectMode);
                config.setShowCamera(isShow);
                config.setEnablePreview(enablePreview);
                config.setEnableCrop(enableCrop);
                config.setPreviewVideo(isPreviewVideo);
                config.setImageSpanCount(4);
                config.setCompressFlag(compressFlag);
                // 先初始化参数配置，在启动相册
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(PersonEditorActivity.this, resultCallback);
                break;
            //昵称
            case R.id.lin_name:
                break;
            //学校
            case R.id.lin_school:
                break;
            //出生日期
            case R.id.lin_birth:
                break;
            //性别
            case R.id.lin_sex:
                break;
            //手机号
            case R.id.lin_phone:
                break;
        }
    }

    /**
     * 获取图片回调方法
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            if (selectMedia != null) {
                Glide.with(PersonEditorActivity.this)
                        .load(selectMedia.get(0).getCompressPath())
                        .asBitmap().centerCrop()
                        .placeholder(R.color.color_f6)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivHeader);
//                Bitmap bitmap = BitmapFactory.decodeFile(selectMedia.get(0).getCompressPath());
//                userIcon = Bitmap2StrByBase64(bitmap).replaceAll("\r|\n", "").trim();
//                updateIcon();
            }
        }
    };
}
