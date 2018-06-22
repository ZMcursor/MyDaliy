package com.zmcursor.mydaliy.Model

import android.content.Context
import android.graphics.Bitmap
import com.zmcursor.mydaliy.AppConfig
import com.zmcursor.mydaliy.Model.Bean.BaseStory
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate
import com.zmcursor.mydaliy.Model.Bean.TopStory
import com.zmcursor.mydaliy.Model.Storage.DataStorage
import com.zmcursor.mydaliy.Net.OnDataGetListener

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

class ImageLoader(private val context: Context) {
    private val dateList: DateList

    init {
        val sp = context.getSharedPreferences(AppConfig.save_data, Context.MODE_PRIVATE)
        dateList = DateList(sp.getString(AppConfig.date_list_key, null))
    }

    fun saveDateList() {
        val sp = context.getSharedPreferences(AppConfig.save_data, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString(AppConfig.date_list_key, dateList.toString())
        editor.apply()
    }

    fun getTopImages(topStories: List<TopStory>) {
        DataStorage.initTopImage(context)
        for (topStory in topStories) {
            if (topStory.imageBitmap == null) {
                val image = DataStorage.getImage(context, null, topStory.id)
                if (image == null) downloadImage(null, topStory, true)
            }
        }
        DataStorage.deleteOldTopImage(context)
    }

    fun getImages(storiesOfDate: StoriesOfDate, beforeDate: String) {
        val old = dateList.add(storiesOfDate.date, beforeDate)
        var saveImage = true
        var dateSaved = false
        when {
            "0" == old -> saveImage = false
            "1" == old -> dateSaved = true
            old != null -> DataStorage.deleteImageOfDate(context, old)
        }
//        if (storiesOfDate.stories == null) return
        for (story in storiesOfDate.stories!!) {
            if (story.imageBitmap == null) {
                var image: Bitmap? = null
                if (dateSaved)
                    image = DataStorage.getImage(context, storiesOfDate.date, story.id)
                if (image == null) downloadImage(storiesOfDate.date, story, saveImage)
            }
        }
    }

    private fun downloadImage(date: String?, story: BaseStory, isSave: Boolean) {
        DataLoader.download(story.imageUrl!!, object : OnDataGetListener<Bitmap> {
            override fun onDataGet(data: Bitmap) {
                story.imageBitmap = data
                if (isSave) DataStorage.saveImage(context, data, date!!, story.id)
            }
        })
    }

    private inner class DateList internal constructor(list: String?) {
        private var dateList: Array<String>? = null

        init {
            if (list != null)
                dateList = splitDate(list, '#')
            else
                dateList = arrayOfNulls(maxSavedDate)
        }

        internal fun add(date: String, beforeDate: String?): String? {
            var pos = 0
            if (beforeDate != null) {
                pos = have(beforeDate) + 1
                if (pos >= dateList!!.size) return "0"
            }
            return if (date != dateList!![pos]) {
                add(pos, date)
            } else "1"
        }

        private fun have(date: String): Int {
            var pos = 0
            while (pos < dateList!!.size && date != dateList!![pos]) pos++
            return pos
        }

        private fun add(pos: Int, date: String): String {
            val old: String
            var i = dateList!!.size - 1
            old = dateList!![i]
            while (i > pos) {
                dateList[i] = dateList!![--i]
            }
            dateList[i] = date
            return old
        }

        private fun splitDate(list: String, regex: Char): Array<String> {
            val dates = arrayOfNulls<String>(maxSavedDate)
            var i: Short = 0
            var r: Short = 0
            var l: Short = 0
            while (r < list.length) {
                if (list.get(r) == regex) {
                    dates[i] = list.substring(l.toInt(), r.toInt())
                    l = ++r
                    i++
                }
                r++
            }
            return dates
        }

        override fun toString(): String {
            val sb = StringBuilder()
            for (s in dateList!!) {
                if (s == null) break
                sb.append(s)
                sb.append('#')
            }
            return sb.toString()
        }
    }

    companion object {

        private val maxSavedDate = 7
    }
}
