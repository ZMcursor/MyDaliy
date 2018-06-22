package com.zmcursor.mydaliy.UI.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.zmcursor.mydaliy.MyAPP;

/**
 * Created by ZMcursor on 2018/5/30 0030.
 */

public abstract class BaseActivity extends Activity {

    public abstract boolean handleMessage(Message msg);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyAPP) getApplication()).addActivity(this);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        ((MyAPP) getApplication()).addActivity(this);
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ((MyAPP) getApplication()).removeActivity(this);
    }
}
