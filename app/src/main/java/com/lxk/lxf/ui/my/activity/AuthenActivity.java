package com.lxk.lxf.ui.my.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;
import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.view.MyImageView2;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhf on 2018/8/29.
 */

public class AuthenActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvRight;
    private RelativeLayout relTitle;
    private MyImageView2 ivPositive;
    private TextView tvPositive;
    private MyImageView2 ivSide;
    private TextView tvSide;
    private MyImageView2 ivHold;
    private TextView tvHold;

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
    private FunctionConfig config = new FunctionConfig();

    private int type = 0;//0正面照 1反面照 2手持照

    @Override
    protected void initView() {
        setContentView(R.layout.activity_authen);
        initTitle("公司认证");
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvRight.setText("提交");
        tvRight.setVisibility(View.VISIBLE);
        ivPositive = (MyImageView2) findViewById(R.id.iv_positive);
        tvPositive = (TextView) findViewById(R.id.tv_positive);
        ivSide = (MyImageView2) findViewById(R.id.iv_side);
        tvSide = (TextView) findViewById(R.id.tv_side);
        ivHold = (MyImageView2) findViewById(R.id.iv_hold);
        tvHold = (TextView) findViewById(R.id.tv_hold);
    }

    @Override
    protected void initData() {
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
    }

    @Override
    protected void initEvent() {
        ivPositive.setOnClickListener(this);
        ivSide.setOnClickListener(this);
        ivHold.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_positive:
                // 先初始化参数配置，在启动相册
                type = 0;
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(AuthenActivity.this, resultCallback);
                break;
            case R.id.iv_side:
                type = 1;
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(AuthenActivity.this, resultCallback);
                break;
            case R.id.iv_hold:
                type = 2;
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(AuthenActivity.this, resultCallback);
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
                if (type == 0) {
                    Glide.with(AuthenActivity.this)
                            .load(selectMedia.get(0).getCompressPath())
                            .asBitmap().centerCrop()
                            .placeholder(R.color.color_f6)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivPositive);
                } else if (type == 1) {
                    Glide.with(AuthenActivity.this)
                            .load(selectMedia.get(0).getCompressPath())
                            .asBitmap().centerCrop()
                            .placeholder(R.color.color_f6)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivSide);
                } else if (type == 2) {
                    Glide.with(AuthenActivity.this)
                            .load(selectMedia.get(0).getCompressPath())
                            .asBitmap().centerCrop()
                            .placeholder(R.color.color_f6)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(ivHold);
                }

//                Bitmap bitmap = BitmapFactory.decodeFile(selectMedia.get(0).getCompressPath());
//                userIcon = Bitmap2StrByBase64(bitmap).replaceAll("\r|\n", "").trim();
//                updateIcon();
            }
        }
    };
}
