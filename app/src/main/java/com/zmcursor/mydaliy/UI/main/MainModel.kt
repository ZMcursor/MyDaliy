package com.zmcursor.mydaliy.UI.main

import android.os.Handler

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Model.Bean.TopStory

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

interface MainModel {

    val topStories: List<TopStory>

    val storiesOfDateList: List<StoriesOfDate>

    //    void initData(Handler handler, int msg);

    fun refresh(handler: Handler, msg: Int)

    fun loadMore(handler: Handler, msg: Int)

    fun saveData()
}
