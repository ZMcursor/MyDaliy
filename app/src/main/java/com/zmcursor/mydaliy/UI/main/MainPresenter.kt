package com.zmcursor.mydaliy.UI.main

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Model.Bean.TopStory

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

interface MainPresenter {

    val topStories: List<TopStory>

    val storiesOfDateList: List<StoriesOfDate>

    //    void initData();

    fun refresh()

    fun loadMore()

    fun saveDate()

}
