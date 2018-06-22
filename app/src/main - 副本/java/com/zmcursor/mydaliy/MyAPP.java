package com.zmcursor.mydaliy;

import android.app.Application;
import android.os.Handler;
import android.os.Message;

import com.zmcursor.mydaliy.Model.StoryDateHolder;
import com.zmcursor.mydaliy.UI.base.BaseActivity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public class MyAPP extends Application {

    private StoryDateHolder storyDateHolder;
    private Set<BaseActivity> baseActivities;

    @Override
    public void onCreate() {
        super.onCreate();
        baseActivities = new HashSet<>(3);
        storyDateHolder = new StoryDateHolder(this);
    }

    public StoryDateHolder getStoryDateHolder() {
        return storyDateHolder;
    }

    public void addActivity(BaseActivity baseActivity) {
        baseActivities.add(baseActivity);
    }

    public void removeActivity(BaseActivity baseActivity) {
        baseActivities.remove(baseActivity);
    }

    public Handler getHandler() {
        return new MyHandler();
    }

    private class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            for (BaseActivity baseActivity : baseActivities) {
                if (baseActivity.handleMessage(msg)) break;
            }
//            int i = 0;
//            while (i < baseActivities.size() && !baseActivities.get(i).handleMessage(msg)) i++;
        }
    }
}
