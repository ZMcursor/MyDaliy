package com.zmcursor.mydaliy.UI.main

import android.os.Bundle
import android.os.Message
import android.widget.Button

import com.zmcursor.mydaliy.R
import com.zmcursor.mydaliy.UI.base.BaseActivity

class MainActivity : BaseActivity() {

    internal var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenterImpl(this)


        val more = findViewById<Button>(R.id.more)
        val refresh = findViewById<Button>(R.id.refresh)

        more.setOnClickListener { v -> presenter.loadMore() }

        refresh.setOnClickListener { v -> presenter.refresh() }

    }

    override fun onPause() {
        super.onPause()
        presenter.saveDate()
    }

    override fun handleMessage(msg: Message): Boolean {
        when (msg.what) {
            msg_init -> {
            }
            msg_refresh -> {
            }
            msg_load_more -> {
            }
            else -> return false
        }
        return true
    }

    companion object {

        val msg_init = 354500
        val msg_refresh = 354511
        val msg_load_more = 354522
    }
}
