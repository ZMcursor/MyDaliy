package com.zmcursor.mydaliy.UI.base

import android.app.Activity
import android.os.Bundle
import android.os.Message

import com.zmcursor.mydaliy.MyAPP

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

abstract class BaseActivity : Activity() {

    abstract fun handleMessage(msg: Message): Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyAPP).addActivity(this)
    }

    //    @Override
    //    protected void onStart() {
    //        super.onStart();
    //        ((MyAPP) getApplication()).addActivity(this);
    //    }

    override fun onDestroy() {
        super.onDestroy()
        (application as MyAPP).removeActivity(this)
    }
}
