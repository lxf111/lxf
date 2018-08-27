package com.lxk.lxf.ui.order.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lxk.lxf.R;

/**
 * Created by zhf on 2018/8/27.
 */

public class OrderAdapter extends BaseAdapter {

    private Context context;

    public OrderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=View.inflate(context, R.layout.item_order,null);
        return convertView;
    }
}
