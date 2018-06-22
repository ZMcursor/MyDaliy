package com.zmcursor.mydaliy.UI.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.zmcursor.mydaliy.MyAPP;
import com.zmcursor.mydaliy.R;
import com.zmcursor.mydaliy.UI.base.BaseActivity;
import com.zmcursor.mydaliy.UI.main.MainActivity;

/**
 * Created by ZMcursor on 2018/6/2 0002.
 */

public class SplashActivity extends BaseActivity {

    private static final String TAG = "SplashActivity";

    public static final int msg_init = 354500;
    private static final String isInited = "isInited";

    private SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        presenter = new SplashPresenter(this);

        presenter.initData();

        ((MyAPP) getApplication()).getHandler().postDelayed(() -> navigator(false), 3000);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (!isFinishing()) presenter.saveData();
    }

    private boolean navigator(boolean isInited) {
        Log.e(TAG, "navigator");
//        if (isDestroyed()) return;
        if (isFinishing()) return false;
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        intent.putExtra(SplashActivity.isInited, isInited);
        startActivity(intent);
        finish();
        Log.e(TAG, "navigator true");
        return true;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case msg_init:
                return navigator(true);
//                break;
            default:
                return false;
        }
//        return true;
    }
}
