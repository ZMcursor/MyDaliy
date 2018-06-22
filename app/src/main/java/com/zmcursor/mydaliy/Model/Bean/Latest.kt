package com.zmcursor.mydaliy.Model.Bean

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

class Latest {
    var date: String = ""
    var stories: List<Story>? = null
    var top_stories: List<TopStory>? = null

    override fun toString(): String {
        return "Latest{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}'
    }
}
