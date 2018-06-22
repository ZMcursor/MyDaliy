package com.zmcursor.mydaliy.Net

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

interface OnDataGetListener<in T> {
    fun onDataGet(data: T)
}
