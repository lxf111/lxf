package com.lxk.lxf.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hss01248.dialog.StyledDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.Call;

/**
 * Created by zhf on 2018/6/22.
 */

public class MyOkhttp {
    public interface CallBack {
        void onRequestComplete(String response, String result, String resultNote);
    }

    /**
     * @param context  上下文
     * @param param    post参数
     * @param callBack 回调
     */
    public static void post(final Context context, final String url, Map<String, String> param, final CallBack callBack) {
        StyledDialog.init(context);
        StyledDialog.buildLoading().show();
        Log.e("TAG", "url=" + url);
        Log.e("TAG", "param=" + new Gson().toJson(param));
        OkHttpUtils
                .post()
                .params(param)
                .url(url)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "e=" + e.getMessage());
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        StyledDialog.dismissLoading();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        StyledDialog.dismissLoading();
                        Log.e("TAG", "response=" + url + "\n" + response);
                        String result = "1";
                        String resultNote = "服务器异常";
                        try {
                            JSONObject object = new JSONObject(response);

                            if (object.has("code") && !object.isNull("code")) {
                                result = object.getString("code");
                            }

                            if (object.has("msg") && !object.isNull("msg")) {
                                resultNote = object.getString("msg");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callBack.onRequestComplete(response, result, resultNote);
                    }
                });
    }

    /**
     * @param context  上下文
     * @param callBack 回调
     */
    public static void get(final Context context, final String url, final CallBack callBack) {
        StyledDialog.init(context);
        StyledDialog.buildLoading().show();
        Log.e("TAG", "url=" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("TAG", "e=" + e.getMessage());
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                        StyledDialog.dismissLoading();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        StyledDialog.dismissLoading();
                        Log.e("TAG", "response=" + url + "\n" + response);
                        String result = "1";
                        String resultNote = "服务器异常";
                        try {
                            JSONObject object = new JSONObject(response);

                            if (object.has("code") && !object.isNull("code")) {
                                result = object.getString("code");
                            }

                            if (object.has("msg") && !object.isNull("msg")) {
                                resultNote = object.getString("msg");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callBack.onRequestComplete(response, result, resultNote);
                    }
                });
    }
}
