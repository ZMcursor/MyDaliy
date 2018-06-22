package com.zmcursor.mydaliy.UI.main;

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Model.Bean.TopStory;

import java.util.List;

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public interface MainPresenter {

//    void initData();

    void refresh();

    void loadMore();

    void saveDate();

    List<TopStory> getTopStories();

    List<StoriesOfDate> getStoriesOfDateList();

}
