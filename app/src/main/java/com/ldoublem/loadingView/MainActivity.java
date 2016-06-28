package com.ldoublem.loadingView;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ldoublem.loadingView.view.LVBattery;
import com.ldoublem.loadingView.view.LVChromeLogo;
import com.ldoublem.loadingView.view.LVCircular;
import com.ldoublem.loadingView.view.LVCircularCD;
import com.ldoublem.loadingView.view.LVCircularJump;
import com.ldoublem.loadingView.view.LVCircularRing;
import com.ldoublem.loadingView.view.LVCircularSmile;
import com.ldoublem.loadingView.view.LVCircularZoom;
import com.ldoublem.loadingView.view.LVEatBeans;
import com.ldoublem.loadingView.view.LVFinePoiStar;
import com.ldoublem.loadingView.view.LVGears;
import com.ldoublem.loadingView.view.LVGearsTwo;
import com.ldoublem.loadingView.view.LVLineWithText;
import com.ldoublem.loadingView.view.LVNews;
import com.ldoublem.loadingView.view.LVPlayBall;
import com.ldoublem.loadingView.view.LVWifi;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {


    LVPlayBall mLVPlayBall;
    LVCircularRing mLVCircularRing;
    LVCircular mLVCircular;
    LVCircularJump mLVCircularJump;
    LVCircularZoom mLVCircularZoom;
    LVLineWithText mLVLineWithText;
    LVEatBeans mLVEatBeans;
    LVCircularCD mLVCircularCD;
    LVCircularSmile mLVCircularSmile;

    LVGears mLVGears;
    LVGearsTwo mLVGearsTwo;
    LVFinePoiStar mLVFinePoiStar;
    LVChromeLogo mLVChromeLogo;
    LVBattery mLVBattery;
    LVWifi mLVWifi;

    LVNews mLVNews;
    int mValueLVLineWithText = 0;
    int mValueLVNews = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLVPlayBall = (LVPlayBall) findViewById(R.id.lv_playball);
        mLVCircularRing = (LVCircularRing) findViewById(R.id.lv_circularring);
        mLVCircular = (LVCircular) findViewById(R.id.lv_circular);
        mLVCircularJump = (LVCircularJump) findViewById(R.id.lv_circularJump);
        mLVCircularZoom = (LVCircularZoom) findViewById(R.id.lv_circularZoom);
        mLVLineWithText = (LVLineWithText) findViewById(R.id.lv_linetext);
        mLVEatBeans = (LVEatBeans) findViewById(R.id.lv_eatBeans);
        mLVCircularCD = (LVCircularCD) findViewById(R.id.lv_circularCD);
        mLVCircularSmile = (LVCircularSmile) findViewById(R.id.lv_circularSmile);
        mLVGears = (LVGears) findViewById(R.id.lv_gears);
        mLVGearsTwo = (LVGearsTwo) findViewById(R.id.lv_gears_two);
        mLVFinePoiStar = (LVFinePoiStar) findViewById(R.id.lv_finePoiStar);
        mLVChromeLogo = (LVChromeLogo) findViewById(R.id.lv_chromeLogo);
        mLVBattery = (LVBattery) findViewById(R.id.lv_battery);
        mLVBattery.setBatteryOrientation(LVBattery.BatteryOrientation.VERTICAL);
//        mLVBattery.setBatteryOrientation(LVBattery.BatteryOrientation.HORIZONTAL);
        mLVBattery.setValue(50);
        mLVBattery.setShowNum(false);
        mLVWifi = (LVWifi) findViewById(R.id.lv_wifi);
        mLVNews = (LVNews) findViewById(R.id.lv_news);


    }

    public void startAnim(View v) {

        stopAll();
        if (v instanceof LVCircular) {
            ((LVCircular) v).startAnim();
        } else if (v instanceof LVCircularCD) {
            ((LVCircularCD) v).startAnim();
        } else if (v instanceof LVCircularSmile) {
            ((LVCircularSmile) v).startAnim();
        } else if (v instanceof LVCircularRing) {
            ((LVCircularRing) v).startAnim();
        } else if (v instanceof LVCircularZoom) {
            ((LVCircularZoom) v).startAnim();
        } else if (v instanceof LVCircularJump) {
            ((LVCircularJump) v).startAnim();
        } else if (v instanceof LVEatBeans) {
            ((LVEatBeans) v).startAnim();
        } else if (v instanceof LVPlayBall) {
            ((LVPlayBall) v).startAnim();
        } else if (v instanceof LVLineWithText) {
            startLVLineWithTextAnim();
        } else if (v instanceof LVGears) {
            ((LVGears) v).startAnim();
        } else if (v instanceof LVGearsTwo) {
            ((LVGearsTwo) v).startAnim();
        } else if (v instanceof LVFinePoiStar) {
            ((LVFinePoiStar) v).setDrawPath(false);
            ((LVFinePoiStar) v).startAnim();
        } else if (v instanceof LVChromeLogo) {
            ((LVChromeLogo) v).startAnim();
        } else if (v instanceof LVBattery) {
            ((LVBattery) v).startAnim();
        } else if (v instanceof LVWifi) {
            ((LVWifi) v).startAnim();
        } else if (v instanceof LVNews) {
//            ((LVNews) v).startAnim();
            startLVNewsAnim();

        }


    }


    public void startAnimAll(View v) {
        mLVCircular.startAnim();
        mLVCircularRing.startAnim();
        mLVPlayBall.startAnim();
        mLVCircularJump.startAnim();
        mLVCircularZoom.startAnim();
        startLVLineWithTextAnim();
        mLVEatBeans.startAnim();
        mLVCircularCD.startAnim();
        mLVCircularSmile.startAnim();
        mLVGears.startAnim();
        mLVGearsTwo.startAnim();
        mLVFinePoiStar.setDrawPath(true);
        mLVFinePoiStar.startAnim();
        mLVChromeLogo.startAnim();
        mLVBattery.startAnim();
        mLVWifi.startAnim();
//        mLVNews.startAnim();
        startLVNewsAnim();
    }


    public void stopAnim(View v) {

        stopAll();

    }

    private void stopAll() {
        mLVCircular.stopAnim();
        mLVPlayBall.stopAnim();
        mLVCircularJump.stopAnim();
        mLVCircularZoom.stopAnim();
        mLVCircularRing.stopAnim();
        mLVEatBeans.stopAnim();
        stopLVLineWithTextAnim();
        mLVCircularCD.stopAnim();
        mLVCircularSmile.stopAnim();
        mLVGears.stopAnim();
        mLVGearsTwo.stopAnim();
        mLVFinePoiStar.stopAnim();
        mLVChromeLogo.stopAnim();
        mLVBattery.stopAnim();
        mLVWifi.stopAnim();
        stopLVNewsAnim();
    }


    public Timer mTimerLVLineWithText = new Timer();// 定时器
    public Timer mTimerLVNews = new Timer();// 定时器


    private void startLVLineWithTextAnim() {
        mValueLVLineWithText = 0;
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText.cancel();// 退出之前的mTimer
        }
        mTimerLVLineWithText = new Timer();
        timerTaskLVLineWithText();
    }

    private void stopLVLineWithTextAnim() {
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText.cancel();// 退出之前的mTimer
            mLVNews.setValue(mValueLVNews);
        }
    }


    private void startLVNewsAnim() {
        mValueLVNews = 0;
        if (mTimerLVNews != null) {

            mTimerLVNews.cancel();
        }
        mTimerLVNews = new Timer();
        timerTaskLVNews();
    }

    private void stopLVNewsAnim() {
        mLVNews.stopAnim();
        if (mTimerLVNews != null) {
            mTimerLVNews.cancel();
            mLVLineWithText.setValue(mValueLVLineWithText);
        }
    }


    public void timerTaskLVNews() {
        mTimerLVNews.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVNews < 100) {
                    mValueLVNews++;
                    Message msg = mHandle.obtainMessage(1);
                    msg.arg1 = mValueLVNews;
                    mHandle.sendMessage(msg);
                } else {
                    mTimerLVNews.cancel();
                }
            }
        }, 0, 50);
    }


    public void timerTaskLVLineWithText() {
        mTimerLVLineWithText.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVLineWithText < 100) {

                    mValueLVLineWithText++;
                    Message msg = mHandle.obtainMessage(2);
                    msg.arg1 = mValueLVLineWithText;
                    mHandle.sendMessage(msg);

                } else {
                    mTimerLVLineWithText.cancel();
                }
            }
        }, 0, 50);
    }


    private Handler mHandle = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 2)
                mLVLineWithText.setValue(msg.arg1);
            else if (msg.what == 1) {
                mLVNews.setValue(msg.arg1);
            }
        }
    };


}
