package com.lxk.lxf.ui.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lxk.lxf.R;

/**
 * Created by Administrator on 2018/8/10 0010.
 */

public class HomeSchoolAdapter extends BaseAdapter {

    private Context context;

    public HomeSchoolAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=View.inflate(context, R.layout.item_home_school,null);
        return view;
    }
}
