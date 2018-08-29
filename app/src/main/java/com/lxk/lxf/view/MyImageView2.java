package com.lxk.lxf.view;

import android.content.Context;
import android.util.AttributeSet;


/**
 * Created by Administrator on 2017/1/12.
 */

public class MyImageView2 extends android.support.v7.widget.AppCompatImageView {
    public MyImageView2(Context context) {
        super(context);
    }

    public MyImageView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) Math.round(width * 0.5);
        setMeasuredDimension(width, height);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
