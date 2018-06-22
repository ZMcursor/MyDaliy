package com.zmcursor.mydaliy.Model.Bean;

import java.util.List;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class Latest {
    public String date;
    public List<Story> stories;
    public List<TopStory> top_stories;

    @Override
    public String toString() {
        return "Latest{" +
                "date='" + date + '\'' +
                ", stories=" + stories +
                ", top_stories=" + top_stories +
                '}';
    }
}
