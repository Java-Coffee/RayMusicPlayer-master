package com.raymondqk.raymusicplayer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.raymondqk.raymusicplayer.utils.MySharedPreference;


public class LaunchActivity extends Activity {
    public static final String IS_FIRST_LAUNCH = "isFirstLaunch";
    private int count = 3;
    private TextView mTextView;
    private Handler mHandler;
    private MySharedPreference mPreference;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mTextView = (TextView) findViewById(R.id.tv_launch_count);
        mPreference = new MySharedPreference(this);
        mHandler = new Handler();

        count();
    }

    private void count() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (count == 1) {
                    if (mPreference.getData(IS_FIRST_LAUNCH)) {
                        mIntent = new Intent(LaunchActivity.this, GuideActivity.class);
                    } else {
                        mIntent = new Intent(LaunchActivity.this, MainActivity.class);
                    }
                    startActivity(mIntent);
                    finish();
                } else {
                    count--;
                    mTextView.setText(count + "");
                    count();

                }

            }
        }, 1000);
    }
}
