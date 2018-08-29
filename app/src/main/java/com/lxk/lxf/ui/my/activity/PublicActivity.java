package com.lxk.lxf.ui.my.activity;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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

/**
 * Created by zhf on 2018/8/29.
 * 发布数据
 */

public class PublicActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTitle;
    private EditText etTitle;
    private TextView tvContent;
    private EditText etContent;
    private TextView tvType;
    private EditText etType;
    private TextView tvPhone;
    private EditText etPhone;
    private ImageView ivImg;


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

    @Override
    protected void initView() {
        setContentView(R.layout.activity_public);
        initTitle("我要发布");
        tvTitle = (TextView) findViewById(R.id.tv_title);
        etTitle = (EditText) findViewById(R.id.et_title);
        tvContent = (TextView) findViewById(R.id.tv_content);
        etContent = (EditText) findViewById(R.id.et_content);
        tvType = (TextView) findViewById(R.id.tv_type);
        etType = (EditText) findViewById(R.id.et_type);
        tvPhone = (TextView) findViewById(R.id.tv_phone);
        etPhone = (EditText) findViewById(R.id.et_phone);
        ivImg = (ImageView) findViewById(R.id.iv_img);
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
        ivImg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_img:
                PictureConfig.init(config);
                PictureConfig.getPictureConfig().openPhoto(PublicActivity.this, resultCallback);
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
                Glide.with(PublicActivity.this)
                        .load(selectMedia.get(0).getCompressPath())
                        .asBitmap().centerCrop()
                        .placeholder(R.color.color_f6)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivImg);
//                Bitmap bitmap = BitmapFactory.decodeFile(selectMedia.get(0).getCompressPath());
//                userIcon = Bitmap2StrByBase64(bitmap).replaceAll("\r|\n", "").trim();
//                updateIcon();
            }
        }
    };
}
