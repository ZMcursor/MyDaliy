package com.zmcursor.mydaliy.Model;

import android.content.Context;
import android.os.Handler;

import com.zmcursor.mydaliy.Model.Bean.Latest;
import com.zmcursor.mydaliy.Model.Bean.StoriesOfDate;
import com.zmcursor.mydaliy.Model.Bean.TopStory;
import com.zmcursor.mydaliy.UI.Splash.SplashModel;
import com.zmcursor.mydaliy.UI.main.MainModel;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ZMcursor on 2018/6/1 0001.
 */

public class StoryDateHolder implements MainModel, SplashModel {

    private List<TopStory> topStories;
    private List<StoriesOfDate> storiesOfDateList;

    private ImageLoader imageLoader;

    public StoryDateHolder(Context context) {
        imageLoader = new ImageLoader(context);
        storiesOfDateList = new ArrayList<>();
    }

    @Override
    public void initData(Handler handler, int msg) {
        DataLoader.getLatest(data -> {
            Latest latest = (Latest) data;
            topStories = latest.top_stories;
            storiesOfDateList.add(new StoriesOfDate(latest.date, latest.stories));
            handler.sendEmptyMessage(msg);

            imageLoader.getTopImages(topStories);
            imageLoader.getImages(storiesOfDateList.get(0), null);
        });
    }

    @Override
    public void refresh(Handler handler, int msg) {
        DataLoader.getLatest(data -> {
            Latest latest = (Latest) data;
            topStories = latest.top_stories;
            if (latest.date.equals(storiesOfDateList.get(0).getDate())) {
                storiesOfDateList.set(0, new StoriesOfDate(latest.date, latest.stories));
            } else {
                storiesOfDateList.add(0, new StoriesOfDate(latest.date, latest.stories));
            }
            handler.sendEmptyMessage(msg);

            imageLoader.getTopImages(topStories);
            imageLoader.getImages(storiesOfDateList.get(0), null);
        });
    }

    @Override
    public void loadMore(Handler handler, int msg) {
        String beforeDate = storiesOfDateList.get(storiesOfDateList.size() - 1).getDate();
        DataLoader.getBefore(beforeDate, data -> {
            StoriesOfDate storiesOfDate = (StoriesOfDate) data;
            storiesOfDateList.add(storiesOfDate);
            handler.sendEmptyMessage(msg);

            imageLoader.getImages(storiesOfDate, beforeDate);
        });
    }

    @Override
    public void saveData() {
        imageLoader.saveDateList();
    }

    @Override
    public List<TopStory> getTopStories() {
        return topStories;
    }

    @Override
    public List<StoriesOfDate> getStoriesOfDateList() {
        return storiesOfDateList;
    }

}
