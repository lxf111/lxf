package com.lxk.lxf.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.lxk.lxf.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * Created by Administrator on 2017/3/9 0009.
 */

public class PicassoUtils {

    public static void showPhoto(Context context, String uri, ImageView imageView) {

        if (uri == null || uri.equals("")) {
            imageView.setImageResource(R.mipmap.ic_launcher);
            return;
        }
        Picasso.with(context).load(uri)
                .transform(transformation)
                .error(R.mipmap.ic_launcher)
//                .resize(DensityUtils.dp2px(context, 400), DensityUtils.dp2px(context, 280))
//                .centerCrop()
//                .centerInside()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
    }


    public static Transformation transformation = new Transformation() {

        @Override
        public Bitmap transform(Bitmap source) {

            int targetWidth = 650;

            if (source.getWidth() == 0) {
                return source;
            }

            //如果图片小于设置的宽度，则返回原图
            if (source.getWidth() < targetWidth) {
                return source;
            } else {
                //如果图片大小大于等于设置的宽度，则按照设置的宽度比例来缩放
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                if (targetHeight != 0 && targetWidth != 0) {
                    Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                    if (result != source) {
                        // Same bitmap is returned if sizes are the same
                        source.recycle();
                    }
                    return result;
                } else {
                    return source;
                }
            }

        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
    };
}
