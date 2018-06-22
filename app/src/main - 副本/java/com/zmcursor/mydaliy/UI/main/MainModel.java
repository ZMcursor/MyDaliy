package com.zmcursor.mydaliy.UI.main;

import android.os.Handler;

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Model.Bean.TopStory;

import java.util.List;

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public interface MainModel {

//    void initData(Handler handler, int msg);

    void refresh(Handler handler, int msg);

    void loadMore(Handler handler, int msg);

    void saveData();

    List<TopStory> getTopStories();

    List<StoriesOfDate> getStoriesOfDateList();
}
