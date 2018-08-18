package com.lxk.lxf.ui.my.activity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.lxk.lxf.R;
import com.lxk.lxf.base.BaseActivity;
import com.lxk.lxf.ui.my.adapter.BalanceAdapter;
import com.lxk.lxf.view.NormalFooterView;
import com.lxk.lxf.view.NormalHeaderView;
import com.ybao.pullrefreshview.layout.BaseFooterView;
import com.ybao.pullrefreshview.layout.BaseHeaderView;


/**
 * Created by Administrator on 2018/1/20 0020.
 * 零钱明细
 */

public class BalanceListActivity extends BaseActivity implements BaseHeaderView.OnRefreshListener, BaseFooterView.OnLoadListener {

    private ListView listView;
    private NormalHeaderView headerView;
    private NormalFooterView footerView;

    private BalanceAdapter adapter;

    private int nowPage = 1;
//    private BalanceBean bean;
//    private BalanceBean resultBean = new BalanceBean();

    private String balance = "";

    @Override
    protected void initView() {
        setContentView(R.layout.activity_balance_list);
        listView = (ListView) findViewById(R.id.list_balance);
        headerView = (NormalHeaderView) findViewById(R.id.headerView);
        footerView = (NormalFooterView) findViewById(R.id.footerView);

        balance = getIntent().getStringExtra("balance");
    }

    @Override
    protected void initData() {
        initTitle("余额明细");
//        Api.balanceList(context, uid, nowPage, new MyCallBack() {
//            @Override
//            public void onSuccess(String response) {
//                String result = "";
//                String resultNote = "";
//                try {
//                    JSONObject object = new JSONObject(response);
//
//                    if (object.has("result") && !object.isNull("result")) {
//                        result = object.getString("result");
//                    }
//
//                    if (object.has("resultNote") && !object.isNull("resultNote")) {
//                        resultNote = object.getString("resultNote");
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                Gson gson = new Gson();
//                bean = gson.fromJson(response, BalanceBean.class);
//                if (result.equals("0")) {
//                    if (nowPage == 1) {
//                        headerView.stopRefresh();
//                        resultBean.setResultList(bean.getResultList());
//                        adapter = new BalanceAdapter(context, resultBean);
//                        listView.setAdapter(adapter);
//                    } else {
//                        resultBean.getResultList().addAll(bean.getResultList());
//                        adapter.notifyDataSetChanged();
//                        footerView.stopLoad();
//                    }
//                } else {
//                    Toast.makeText(context, resultNote, Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
    }

    @Override
    protected void initEvent() {

        headerView.setOnRefreshListener(this);
        footerView.setOnLoadListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(context, BalanceDetailActivity.class);
//                intent.putExtra("bean", resultBean.getResultList().get(i));
//                intent.putExtra("balance",""+balance);
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoad(BaseFooterView baseFooterView) {
        baseFooterView.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (bean != null && bean.getToalPage() > nowPage) {
//                    nowPage++;
//                    initData();
//                } else {
//                    Toast.makeText(context, "数据全部加载", Toast.LENGTH_SHORT).show();
//                    footerView.stopLoad();
//                }
            }
        }, 0);
    }

    @Override
    public void onRefresh(BaseHeaderView baseHeaderView) {
        baseHeaderView.postDelayed(new Runnable() {
            @Override
            public void run() {
                nowPage = 1;
                initData();
            }
        }, 0);
    }
}
