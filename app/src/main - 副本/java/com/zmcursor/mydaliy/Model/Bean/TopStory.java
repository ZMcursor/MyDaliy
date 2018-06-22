package com.zmcursor.mydaliy.Model.Bean;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public class TopStory extends BaseStory {

    public String image;

    @Override
    public String getImageUrl() {
        return image;
    }

    @Override
    public String toString() {
        return "TopStory{" +
                "type=" + type +
                ", id=" + id +
                ", ga_prefix=" + ga_prefix +
                ", multipic=" + multipic +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

}
