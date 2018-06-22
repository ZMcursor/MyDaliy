package com.zmcursor.mydaliy.UI.main;

import android.os.Bundle;
import android.os.Message;
import android.widget.Button;

import com.zmcursor.mydaliy.R;
import com.zmcursor.mydaliy.UI.base.BaseActivity;

public class MainActivity extends BaseActivity {

    public static final int msg_init = 354500;
    public static final int msg_refresh = 354511;
    public static final int msg_load_more = 354522;

    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenterImpl(this);


        Button more = findViewById(R.id.more);
        Button refresh = findViewById(R.id.refresh);

        more.setOnClickListener(v -> presenter.loadMore());

        refresh.setOnClickListener(v -> presenter.refresh());

    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.saveDate();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case msg_init:
                break;
            case msg_refresh:
                break;
            case msg_load_more:
                break;
            default:
                return false;
        }
        return true;
    }
}
