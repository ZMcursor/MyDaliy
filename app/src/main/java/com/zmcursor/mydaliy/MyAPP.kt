package com.zmcursor.mydaliy

import android.app.Application
import android.os.Handler
import android.os.Message
import com.zmcursor.mydaliy.Model.StoryDateHolder
import com.zmcursor.mydaliy.UI.base.BaseActivity
import java.util.*

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

class MyAPP : Application() {

    var storyDateHolder: StoryDateHolder = StoryDateHolder(this)
        private set
    private var baseActivities: MutableSet<BaseActivity> = HashSet(3)

    var handler: Handler = MyHandler()
        private set


    fun addActivity(baseActivity: BaseActivity) {
        baseActivities.add(baseActivity)
    }

    fun removeActivity(baseActivity: BaseActivity) {
        baseActivities.remove(baseActivity)
    }

    private inner class MyHandler : Handler() {

        override fun handleMessage(msg: Message) {
            baseActivities.forEach {
                if (it.handleMessage(msg)) return
            }
        }
    }
}
