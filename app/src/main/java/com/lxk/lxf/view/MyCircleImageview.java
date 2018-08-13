package com.lxk.lxf.view;

import android.content.Context;
import android.util.AttributeSet;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Administrator on 2017/1/12.
 */

public class MyCircleImageview extends CircleImageView {
    public MyCircleImageview(Context context) {
        super(context);
    }

    public MyCircleImageview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCircleImageview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) Math.round(width * 1);
        setMeasuredDimension(width, height);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
