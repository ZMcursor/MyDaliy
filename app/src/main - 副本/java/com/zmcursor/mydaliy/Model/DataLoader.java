package com.zmcursor.mydaliy.Model;

import android.accounts.NetworkErrorException;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zmcursor.mydaliy.AppConfig;
import com.zmcursor.mydaliy.Model.Bean.Latest;
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Net.HttpGet;
import com.zmcursor.mydaliy.Net.OnDataGetListener;
import com.zmcursor.mydaliy.Utils.TaskPool.Task;
import com.zmcursor.mydaliy.Utils.TaskPool.TaskExecutor;

import java.io.IOException;

/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

public class DataLoader {

    private static final String TAG = "DataLoader";

    private static TaskExecutor taskExecutor = new TaskExecutor();


    public static void getLatest(OnDataGetListener listener) {
        Log.e(TAG, "getLatest");
        getBean(AppConfig.latest_url, new TypeReference<Latest>() {
        }, listener);
    }

    public static void getBefore(String date, OnDataGetListener listener) {
        Log.e(TAG, "getBefore");
        getBean(AppConfig.before_url + date, new TypeReference<StoriesOfDate>() {
        }, listener);
    }

    public static void getStoryDetail(String id, OnDataGetListener listener) {

    }

    private static <T> void getBean(String url, TypeReference<T> type, OnDataGetListener listener) {
        Log.e(TAG, "getBean:" + url);
        download(true, url, data -> {
            String json = (String) data;
            T bean = JSON.parseObject(json, type);
            Log.e(TAG, "bean:" + bean.toString());
            listener.onDataGet(bean);
        });
    }

    public static void download(boolean isString, String url, OnDataGetListener listener) {
        Log.e(TAG, "download:" + url);
        taskExecutor.addTask(getTask(isString, url, listener));
    }

    private static Task getTask(boolean isString, String url, OnDataGetListener listener) {
        if (isString) {
            return new Task(10) {
                @Override
                public void run() {
                    Object data = null;
                    try {
                        data = HttpGet.getString(url);
                    } catch (IOException | NetworkErrorException ignored) {
                    }
                    listener.onDataGet(data);
                }
            };
        } else {
            return new Task() {
                @Override
                public void run() {
                    Object data = null;
                    try {
                        data = HttpGet.getImage(url);
                    } catch (IOException | NetworkErrorException ignored) {
                    }
                    listener.onDataGet(data);
                }
            };
        }
    }
}
