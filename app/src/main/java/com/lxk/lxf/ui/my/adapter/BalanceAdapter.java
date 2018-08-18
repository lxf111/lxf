package com.lxk.lxf.ui.my.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxk.lxf.R;


/**
 * Created by Administrator on 2018/1/20 0020.
 */

public class BalanceAdapter extends BaseAdapter {

    private Context context;
//    private BalanceBean bean;

    private ViewHolder holder = null;

    public BalanceAdapter(Context context) {
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(context, R.layout.item_balance, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

//        if(bean.getResultList().get(i).getIsJiaJian().equals("0")){
//            holder.tvMoney.setTextColor(context.getResources().getColor(R.color.balance_add));
//            holder.tvMoney.setText("+"+bean.getResultList().get(i).getMoney());
//        }else if(bean.getResultList().get(i).getIsJiaJian().equals("1")){
//            holder.tvMoney.setTextColor(context.getResources().getColor(R.color.balance_delete));
//            holder.tvMoney.setText("-"+bean.getResultList().get(i).getMoney());
//        }
//
//        holder.tvTime.setText(""+bean.getResultList().get(i).getDate());
//        holder.tvTitle.setText(""+bean.getResultList().get(i).getContent());

        return view;
    }

    protected class ViewHolder {
        private TextView tvTitle;
        private TextView tvTime;
        private ImageView ivBalanceRight;
        private TextView tvMoney;

        public ViewHolder(View view) {
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvTime = (TextView) view.findViewById(R.id.tv_time);
            ivBalanceRight = (ImageView) view.findViewById(R.id.iv_balance_right);
            tvMoney = (TextView) view.findViewById(R.id.tv_money);
        }
    }
}
