package com.zmcursor.mydaliy.UI.Splash

import com.zmcursor.mydaliy.Model.StoryDateHolder
import com.zmcursor.mydaliy.MyAPP

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

class SplashPresenter(private val splashActivity: SplashActivity) {
    private val storyDateholder: StoryDateHolder?

    init {
        storyDateholder = (splashActivity.application as MyAPP).storyDateHolder
    }

    fun initData() {
        storyDateholder!!.initData((splashActivity.application as MyAPP).handler, SplashActivity.msg_init)
    }

    fun saveData() {
        storyDateholder!!.saveData()
    }
}
