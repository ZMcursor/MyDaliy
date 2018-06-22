package com.zmcursor.mydaliy.Model.Bean

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

class Story : BaseStory() {

    var images: List<String>? = null

    override var imageUrl: String? = null
        get() = images?.get(0)

    override fun toString(): String {
        return "Story{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix=" + ga_prefix +
                ", multipic=" + multipic +
                ", title='" + title + '\'' +
                ", image=" + imageUrl +
                '}'
    }
}
