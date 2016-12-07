package com.ldoublem.loadingView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ldoublem.loadingviewlib.view.LVRingProgress;

import java.util.Timer;
import java.util.TimerTask;


public class TestActivity extends AppCompatActivity {


    LVRingProgress mLVRingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.layout_test);
        mLVRingProgress = (LVRingProgress) findViewById(R.id.lv_ringp);
    }

    int mValueLVRingProgress = 0;
    public Timer mTimerLVRingProgress = new Timer();// 定时器

    public void timerTaskLVRingProgress() {
        mTimerLVRingProgress.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVRingProgress < 100) {

                    mValueLVRingProgress++;
                    Message msg = mHandle.obtainMessage(0);
                    msg.arg1 = mValueLVRingProgress;
                    mHandle.sendMessage(msg);

                } else {
                    mTimerLVRingProgress.cancel();
                }
            }
        }, 0, 50);
    }

    public void automatic(View v) {

        mLVRingProgress.startAnim();


    }

    public void byuser(View v) {
        mLVRingProgress.stopAnim();
        startLVRingProgressAnim();


    }

    private void startLVRingProgressAnim() {
        mValueLVRingProgress = 0;
        if (mTimerLVRingProgress != null) {
            mTimerLVRingProgress.cancel();// 退出之前的mTimer
        }
        mTimerLVRingProgress = new Timer();
        timerTaskLVRingProgress();
    }

    private void stopLVRingProgressAnim() {
        if (mTimerLVRingProgress != null) {
            mTimerLVRingProgress.cancel();// 退出之前的mTimer
            mLVRingProgress.setProgress(mValueLVRingProgress);
        }
    }


    private Handler mHandle = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLVRingProgress.setProgress(msg.arg1);
        }
    };


}
