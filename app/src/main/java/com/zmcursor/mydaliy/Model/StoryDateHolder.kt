package com.zmcursor.mydaliy.Model

import android.content.Context
import android.os.Handler
import com.zmcursor.mydaliy.Model.Bean.Latest
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Model.Bean.TopStory
import com.zmcursor.mydaliy.Net.OnDataGetListener
import com.zmcursor.mydaliy.UI.Splash.SplashModel
import com.zmcursor.mydaliy.UI.main.MainModel
import java.util.*


/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

class StoryDateHolder(context: Context) : MainModel, SplashModel {

    override var topStories: List<TopStory>? = null
        private set
    private val storiesOfDateList: MutableList<StoriesOfDate>

    private val imageLoader: ImageLoader

    init {
        imageLoader = ImageLoader(context)
        storiesOfDateList = ArrayList()
    }

    override fun initData(handler: Handler, msg: Int) {
        DataLoader.getLatest(object :OnDataGetListener<Latest>{
            override fun onDataGet(data: Latest) {
                topStories = data.top_stories
                storiesOfDateList.add(StoriesOfDate(data.date, data.stories))
                handler.sendEmptyMessage(msg)

                imageLoader.getTopImages(topStories)
                imageLoader.getImages(storiesOfDateList[0], null)
            }
        })



        DataLoader.getLatest { data ->
            val latest = data as Latest
            topStories = latest.top_stories
            storiesOfDateList.add(StoriesOfDate(latest.date!!, latest.stories!!))
            handler.sendEmptyMessage(msg)

            imageLoader.getTopImages(topStories)
            imageLoader.getImages(storiesOfDateList[0], null)
        }
    }

    override fun refresh(handler: Handler, msg: Int) {
        DataLoader.getLatest { data ->
            val latest = data as Latest
            topStories = latest.top_stories
            if (latest.date == storiesOfDateList[0].date) {
                storiesOfDateList[0] = StoriesOfDate(latest.date!!, latest.stories!!)
            } else {
                storiesOfDateList.add(0, StoriesOfDate(latest.date!!, latest.stories!!))
            }
            handler.sendEmptyMessage(msg)

            imageLoader.getTopImages(topStories)
            imageLoader.getImages(storiesOfDateList[0], null)
        }
    }

    override fun loadMore(handler: Handler, msg: Int) {
        val beforeDate = storiesOfDateList[storiesOfDateList.size - 1].date
        DataLoader.getBefore(beforeDate) { data ->
            val storiesOfDate = data as StoriesOfDate
            storiesOfDateList.add(storiesOfDate)
            handler.sendEmptyMessage(msg)

            imageLoader.getImages(storiesOfDate, beforeDate)
        }
    }

    override fun saveData() {
        imageLoader.saveDateList()
    }

    override fun getStoriesOfDateList(): List<StoriesOfDate> {
        return storiesOfDateList
    }

}
