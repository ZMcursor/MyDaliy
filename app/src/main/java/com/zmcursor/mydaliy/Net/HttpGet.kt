package com.zmcursor.mydaliy.Net

import android.accounts.NetworkErrorException
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

object HttpGet {
    private val TAG = "HttpsGet"

    fun getImage(url: String): Bitmap? {
        Log.e(TAG, "getImage:" + url)
        val httpURLConnection = getURLConnection(url)
        val inputStream = httpURLConnection.inputStream ?: return null

        val bitmap = BitmapFactory.decodeStream(inputStream)

        inputStream.close()
        httpURLConnection.disconnect()

        return bitmap
    }

    fun getString(url: String): String? {
        Log.e(TAG, "getString:" + url)
        val httpURLConnection = getURLConnection(url)
        val inputStream = httpURLConnection.inputStream ?: return null

        val byteArrayOutputStream = ByteArrayOutputStream()
        // 模板代码 必须熟练
        val buffer = ByteArray(1024)
        var len: Int
        do {
            len = inputStream.read(buffer)
            if (len == -1) break
            byteArrayOutputStream.write(buffer, 0, len)
        } while (true)
        inputStream.close()
        val string = byteArrayOutputStream.toString()// 把流中的数据转换成字符串,采用的编码是utf-8(模拟器默认编码)
        byteArrayOutputStream.close()
        httpURLConnection.disconnect()
        return string
    }

    //    @Throws(IOException::class, NetworkErrorException::class)
    private fun getURLConnection(url: String): HttpURLConnection {
        val httpURLConnection: HttpURLConnection = URL(url).openConnection() as HttpURLConnection

        // 利用string url构建URL对象
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.readTimeout = 5000
        httpURLConnection.connectTimeout = 10000

        return if (httpURLConnection.responseCode == 200) {
            httpURLConnection
        } else {
            throw NetworkErrorException("response status is " + httpURLConnection.responseCode)
        }
    }
}
