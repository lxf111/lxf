package com.lxk.lxf.ui.my.pop;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;

import com.lxk.lxf.R;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class CashPwdPop extends PopupWindow {
    private View view;
    private LinearLayout linPop;
    private Button btnCancel;
    private Button btnSubmit;
    private EditText etPwd;


    public CashPwdPop(final Context context, View.OnClickListener onClickListener, TextWatcher watcher) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.pop_cash_pwd, null);

        linPop = view.findViewById(R.id.lin_pop);
        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        etPwd = (EditText) view.findViewById(R.id.et_pwd);

        linPop.setOnClickListener(onClickListener);
        etPwd.addTextChangedListener(watcher);
        btnCancel.setOnClickListener(onClickListener);
        btnSubmit.setOnClickListener(onClickListener);

//        //显示弹出框
        this.setContentView(view);
        this.setWidth(TableRow.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
//        this.setWidth((int) (MyApplication.Width * 0.25));
//        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置弹窗可点击
        this.setFocusable(true);
        //设置弹出窗体的动画效果
        this.setAnimationStyle(R.style.AppTheme);
//        this.setAnimationStyle(R.style.popwin_anim_style);
        ColorDrawable cd = new ColorDrawable(0x00000000);
        this.setBackgroundDrawable(cd);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return true;
            }
        });
    }
}
