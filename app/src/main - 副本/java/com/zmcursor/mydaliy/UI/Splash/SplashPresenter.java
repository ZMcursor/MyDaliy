package com.zmcursor.mydaliy.UI.Splash;

import com.zmcursor.mydaliy.Model.StoryDateHolder;
import com.zmcursor.mydaliy.MyAPP;

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public class SplashPresenter {

    private SplashActivity splashActivity;
    private StoryDateHolder storyDateholder;

    public SplashPresenter(SplashActivity splashActivity) {
        this.splashActivity = splashActivity;
        storyDateholder = ((MyAPP) splashActivity.getApplication()).getStoryDateHolder();
    }

    public void initData() {
        storyDateholder.initData(((MyAPP) splashActivity.getApplication()).getHandler(), SplashActivity.msg_init);
    }

    public void saveData() {
        storyDateholder.saveData();
    }
}
