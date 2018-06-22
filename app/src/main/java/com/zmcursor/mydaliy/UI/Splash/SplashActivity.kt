package com.zmcursor.mydaliy.UI.Splash

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.util.Log

import com.zmcursor.mydaliy.MyAPP
import com.zmcursor.mydaliy.R
import com.zmcursor.mydaliy.UI.base.BaseActivity
import com.zmcursor.mydaliy.UI.main.MainActivity

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

class SplashActivity : BaseActivity() {

    private var presenter: SplashPresenter = SplashPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        presenter.initData()

        (application as MyAPP).handler.postDelayed({ navigator(false) }, 3000)
    }


    override fun onPause() {
        super.onPause()
        if (!isFinishing) presenter.saveData()
    }

    private fun navigator(isInited: Boolean): Boolean {
        Log.e(TAG, "navigator")
        //        if (isDestroyed()) return;
        if (isFinishing) return false
        val intent = Intent(this@SplashActivity, MainActivity::class.java)
        intent.putExtra(SplashActivity.isInited, isInited)
        startActivity(intent)
        finish()
        Log.e(TAG, "navigator true")
        return true
    }

    override fun handleMessage(msg: Message): Boolean {
        return when (msg.what) {
            msg_init -> navigator(true)
            else -> false
        }
    }

    companion object {

        private val TAG = "SplashActivity"

        private val msg_init = 354500
        public val isInited = "isInited"
    }
}
