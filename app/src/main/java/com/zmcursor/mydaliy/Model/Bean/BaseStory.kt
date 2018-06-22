package com.zmcursor.mydaliy.Model.Bean

import android.graphics.Bitmap

/**
 * Created by ZMcursor on 2018/6/4 0004.
 */

abstract class BaseStory {

    var id: String = ""
    var type: Int = 0
    var ga_prefix: Int = 0
    var multipic = false
    var title: String = ""
    var imageBitmap: Bitmap? = null

    abstract var imageUrl: String?

}
