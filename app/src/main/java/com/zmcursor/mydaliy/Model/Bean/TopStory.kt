package com.zmcursor.mydaliy.Model.Bean

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

class TopStory : BaseStory() {

    var image: String? = null

    override var imageUrl: String? = null
        get() = image

    override fun toString(): String {
        return "TopStory{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix=" + ga_prefix +
                ", multipic=" + multipic +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}'
    }

}
