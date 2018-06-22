package com.zmcursor.mydaliy.Model

import android.util.Log
import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.TypeReference
import com.zmcursor.mydaliy.AppConfig
import com.zmcursor.mydaliy.Model.Bean.Latest
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Net.HttpGet
import com.zmcursor.mydaliy.Net.OnDataGetListener
import com.zmcursor.mydaliy.Utils.TaskPool.Task
import com.zmcursor.mydaliy.Utils.TaskPool.TaskExecutor

/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

object DataLoader {

    private val TAG = "DataLoader"

    private val taskExecutor = TaskExecutor()


    fun getLatest(listener: OnDataGetListener<Latest>) {
        Log.e(TAG, "getLatest")
        getBean(AppConfig.latest_url, listener)
    }

    fun getBefore(date: String, listener: OnDataGetListener<StoriesOfDate>) {
        Log.e(TAG, "getBefore")
        getBean(AppConfig.before_url + date, listener)
    }

//    fun getStoryDetail(id: String, listener: OnDataGetListener<String>) {
//
//    }

    private fun <T> getBean(url: String, listener: OnDataGetListener<T>) {
        Log.e(TAG, "getBean:" + url)
        download(url, object : OnDataGetListener<String> {
            override fun onDataGet(data: String) {
                val bean = JSON.parseObject(data, object : TypeReference<T>() {})
                Log.e(TAG, "bean:" + bean.toString())
                listener.onDataGet(bean)
            }
        })
    }

    fun <T> download(url: String, listener: OnDataGetListener<T>) {
        Log.e(TAG, "download:" + url)
        taskExecutor.addTask(getTask(url, listener))
    }

    private fun <T> getTask(url: String, listener: OnDataGetListener<T>): Task {
        val t: T? = null
        return if (t is String) {
            object : Task(10) {
                override fun run() {
                    val data = HttpGet.getString(url) as T
                    listener.onDataGet(data)
                }
            }
        } else {
            object : Task() {
                override fun run() {
                    val data = HttpGet.getImage(url) as T
                    listener.onDataGet(data)
                }
            }
        }
    }
}
