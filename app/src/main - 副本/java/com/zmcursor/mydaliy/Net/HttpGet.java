package com.zmcursor.mydaliy.Net;

import android.accounts.NetworkErrorException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class HttpGet {
    private static final String TAG = "HttpsGet";

    @Nullable
    public static Bitmap getImage(String url) throws IOException, NetworkErrorException {
        Log.e(TAG, "getImage:" + url);
        HttpURLConnection httpURLConnection = getURLConnection(url);
        if (httpURLConnection == null) return null;
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) return null;

        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

        inputStream.close();
        httpURLConnection.disconnect();

        return bitmap;
    }

    @Nullable
    public static String getString(String url) throws IOException, NetworkErrorException {
        Log.e(TAG, "getString:" + url);
        HttpURLConnection httpURLConnection = getURLConnection(url);
        if (httpURLConnection == null) return null;
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) return null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 模板代码 必须熟练
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        inputStream.close();
        String string = byteArrayOutputStream.toString();// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        byteArrayOutputStream.close();
        httpURLConnection.disconnect();
        return string;
    }

    private static HttpURLConnection getURLConnection(String url) throws IOException, NetworkErrorException {
        HttpURLConnection httpURLConnection;

        // 利用string url构建URL对象
        httpURLConnection = (HttpURLConnection) ((new URL(url)).openConnection());
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setConnectTimeout(10000);

        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection;
        } else {
            throw new NetworkErrorException("response status is " + responseCode);
        }
    }
}
