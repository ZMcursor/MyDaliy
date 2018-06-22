package com.zmcursor.mydaliy.UI.main

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Model.Bean.TopStory
import com.zmcursor.mydaliy.Model.StoryDateHolder
import com.zmcursor.mydaliy.MyAPP


/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

class MainPresenterImpl(private val mainActivity: MainActivity) : MainPresenter {
    private val storyDateholder: StoryDateHolder?

    override val topStories: List<TopStory>?
        get() = storyDateholder!!.topStories

    override val storiesOfDateList: List<StoriesOfDate>
        get() = storyDateholder!!.storiesOfDateList

    init {
        storyDateholder = (mainActivity.application as MyAPP).storyDateHolder
    }

    //    @Override
    //    public void initData() {
    //        storyDateholder.initData(((MyAPP) mainActivity.getApplication()).getHandler(), MainActivity.msg_init);
    //    }

    override fun refresh() {
        storyDateholder!!.refresh((mainActivity.application as MyAPP).handler, MainActivity.msg_refresh)
    }

    override fun loadMore() {
        storyDateholder!!.loadMore((mainActivity.application as MyAPP).handler, MainActivity.msg_load_more)
    }

    override fun saveDate() {
        storyDateholder!!.saveData()
    }
}
