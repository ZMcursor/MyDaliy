package com.zmcursor.mydaliy.UI.main;

import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Model.Bean.TopStory;
import com.zmcursor.mydaliy.Model.StoryDateHolder;
import com.zmcursor.mydaliy.MyAPP;

import java.util.List;


/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainActivity mainActivity;
    private StoryDateHolder storyDateholder;

    public MainPresenterImpl(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        storyDateholder = ((MyAPP) mainActivity.getApplication()).getStoryDateHolder();
    }

//    @Override
//    public void initData() {
//        storyDateholder.initData(((MyAPP) mainActivity.getApplication()).getHandler(), MainActivity.msg_init);
//    }

    @Override
    public void refresh() {
        storyDateholder.refresh(((MyAPP) mainActivity.getApplication()).getHandler(), MainActivity.msg_refresh);
    }

    @Override
    public void loadMore() {
        storyDateholder.loadMore(((MyAPP) mainActivity.getApplication()).getHandler(), MainActivity.msg_load_more);
    }

    @Override
    public void saveDate() {
        storyDateholder.saveData();
    }

    @Override
    public List<TopStory> getTopStories() {
        return storyDateholder.getTopStories();
    }

    @Override
    public List<StoriesOfDate> getStoriesOfDateList() {
        return storyDateholder.getStoriesOfDateList();
    }
}
