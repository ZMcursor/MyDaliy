package com.zmcursor.mydaliy.Model.Bean

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

class StoriesOfDate {
    var date: String = ""

    var stories: List<Story>? = null

    constructor() {}

    constructor(date: String, stories: List<Story>?) {
        this.date = date
        this.stories = stories
    }

    override fun toString(): String {
        return "StoriesOfDate{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}'
    }
}
