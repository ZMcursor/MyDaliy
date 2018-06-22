package com.zmcursor.mydaliy.Model.Bean;

import android.graphics.Bitmap;

/**
 * Created by ZMcursor on 2018/6/4 0004.
 */

public abstract class BaseStory {

    public String id;
    public int type;
    public int ga_prefix;
    public boolean multipic = false;
    public String title;
    private Bitmap imageBitmap = null;

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap image) {
        this.imageBitmap = image;
    }

    public abstract String getImageUrl();

}
