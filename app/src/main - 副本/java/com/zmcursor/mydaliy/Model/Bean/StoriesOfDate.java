package com.zmcursor.mydaliy.Model.Bean;

import java.util.List;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class StoriesOfDate {
    public String date;
    public List<Story> stories;

    public StoriesOfDate() {
    }

    public StoriesOfDate(String date, List<Story> stories) {
        this.date = date;
        this.stories = stories;
    }

    public String getDate() {
        return date;
    }

//    public void setDate(String date) {
//        this.date = date;
//    }

    public List<Story> getStories() {
        return stories;
    }

//    public void setStories(List<Story> stories) {
//        this.stories = stories;
//    }

    @Override
    public String toString() {
        return "StoriesOfDate{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                '}';
    }
}
