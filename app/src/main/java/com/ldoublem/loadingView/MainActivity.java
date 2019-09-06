package com.ldoublem.loadingView;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.ldoublem.loadingviewlib.LVSunSetView;
import com.ldoublem.loadingviewlib.view.LVBattery;
import com.ldoublem.loadingviewlib.view.LVBlazeWood;
import com.ldoublem.loadingviewlib.view.LVBlock;
import com.ldoublem.loadingviewlib.LVChromeLogo;
import com.ldoublem.loadingviewlib.LVCircular;
import com.ldoublem.loadingviewlib.LVCircularCD;
import com.ldoublem.loadingviewlib.view.LVCircularJump;
import com.ldoublem.loadingviewlib.view.LVCircularRing;
import com.ldoublem.loadingviewlib.view.LVCircularSmile;
import com.ldoublem.loadingviewlib.view.LVCircularZoom;
import com.ldoublem.loadingviewlib.view.LVEatBeans;
import com.ldoublem.loadingviewlib.view.LVFinePoiStar;
import com.ldoublem.loadingviewlib.view.LVFunnyBar;
import com.ldoublem.loadingviewlib.view.LVGears;
import com.ldoublem.loadingviewlib.view.LVGearsTwo;
import com.ldoublem.loadingviewlib.view.LVGhost;
import com.ldoublem.loadingviewlib.LVLineWithText;
import com.ldoublem.loadingviewlib.view.LVNews;
import com.ldoublem.loadingviewlib.view.LVPlayBall;
import com.ldoublem.loadingviewlib.view.LVRingProgress;
import com.ldoublem.loadingviewlib.view.LVWifi;

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
    LVBlock mLVBlock;
    LVGhost mLVGhost;
    LVFunnyBar mLVFunnyBar;
    LVRingProgress mLVRingProgress;
    LVSunSetView lv_sunset;
    LVBlazeWood mLVBlazeWood;

    int mValueLVLineWithText = 0;
    int mValueLVNews = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.activity_main);

        mLVCircular = (LVCircular) findViewById(R.id.lv_circular);
        mLVCircular.setViewColor(Color.rgb(255, 99, 99));
        mLVCircular.setRoundColor(Color.rgb(255, 0, 0));

        mLVCircularCD = (LVCircularCD) findViewById(R.id.lv_circularCD);
        mLVCircularCD.setViewColor(Color.rgb(0, 255, 0));


        mLVLineWithText = (LVLineWithText) findViewById(R.id.lv_linetext);
        mLVLineWithText.setViewColor(Color.rgb(33, 66, 77));
        mLVLineWithText.setTextColor(Color.rgb(233, 166, 177));


        mLVCircularJump = (LVCircularJump) findViewById(R.id.lv_circularJump);
        mLVCircularJump.setViewColor(Color.rgb(133, 66, 99));


        mLVCircularRing = (LVCircularRing) findViewById(R.id.lv_circularring);
        mLVCircularRing.setViewColor(Color.argb(100, 255, 255, 255));
        mLVCircularRing.setBarColor(Color.YELLOW);

        mLVCircularSmile = (LVCircularSmile) findViewById(R.id.lv_circularSmile);
        mLVCircularSmile.setViewColor(Color.rgb(144, 238, 146));


        mLVCircularZoom = (LVCircularZoom) findViewById(R.id.lv_circularZoom);
        mLVCircularZoom.setViewColor(Color.rgb(255, 0, 122));


        mLVEatBeans = (LVEatBeans) findViewById(R.id.lv_eatBeans);
        mLVEatBeans.setViewColor(Color.WHITE);
        mLVEatBeans.setEyeColor(Color.BLUE);


        mLVFinePoiStar = (LVFinePoiStar) findViewById(R.id.lv_finePoiStar);
        mLVFinePoiStar.setViewColor(Color.WHITE);
        mLVFinePoiStar.setCircleColor(Color.YELLOW);
        mLVFinePoiStar.setDrawPath(true);

        mLVGears = (LVGears) findViewById(R.id.lv_gears);
        mLVGears.setViewColor(Color.rgb(55, 155, 233));

        mLVGearsTwo = (LVGearsTwo) findViewById(R.id.lv_gears_two);
        mLVGearsTwo.setViewColor(Color.rgb(155, 55, 233));


        mLVWifi = (LVWifi) findViewById(R.id.lv_wifi);
        mLVWifi.setViewColor(Color.BLACK);


        mLVNews = (LVNews) findViewById(R.id.lv_news);
        mLVNews.setViewColor(Color.WHITE);


        mLVRingProgress = (LVRingProgress) findViewById(R.id.lv_ringp);
        mLVRingProgress.setViewColor(Color.WHITE);
        mLVRingProgress.setTextColor(Color.BLACK);
        mLVRingProgress.setPorBarStartColor(Color.YELLOW);
        mLVRingProgress.setPorBarEndColor(Color.BLUE);


        mLVGhost = (LVGhost) findViewById(R.id.lv_ghost);
        mLVGhost.setViewColor(Color.WHITE);
        mLVGhost.setHandColor(Color.BLACK);

        mLVPlayBall = (LVPlayBall) findViewById(R.id.lv_playball);
        mLVPlayBall.setViewColor(Color.WHITE);
        mLVPlayBall.setBallColor(Color.RED);


        mLVChromeLogo = (LVChromeLogo) findViewById(R.id.lv_chromeLogo);

        mLVBattery = (LVBattery) findViewById(R.id.lv_battery);
        mLVBattery.setBatteryOrientation(LVBattery.BatteryOrientation.VERTICAL);//LVBattery.BatteryOrientation.HORIZONTAL
        mLVBattery.setShowNum(false);

        mLVBattery.setViewColor(Color.WHITE);
        mLVBattery.setCellColor(Color.GREEN);

        mLVBlock = (LVBlock) findViewById(R.id.lv_block);

        mLVBlock.setViewColor(Color.rgb(245, 209, 22));
        mLVBlock.setShadowColor(Color.BLACK);
//        mLVBlock.isShadow(false);


        mLVFunnyBar = (LVFunnyBar) findViewById(R.id.lv_funnybar);
        mLVFunnyBar.setViewColor(Color.rgb(234, 167, 107));


        mLVBlazeWood = (LVBlazeWood) findViewById(R.id.lv_wood);

//        mLVLineWithText.setValue(50);

        lv_sunset = (LVSunSetView) findViewById(R.id.lv_sunset);
        lv_sunset.setSunendTime("2019-09-06 16:20:00");
        lv_sunset.setSunstartTime("2019-09-06 7:16:00");

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
            ((LVEatBeans) v).startAnim(3500);
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
            ((LVFinePoiStar) v).startAnim(3500);
        } else if (v instanceof LVChromeLogo) {
            ((LVChromeLogo) v).startAnim();
        } else if (v instanceof LVBattery) {
            ((LVBattery) v).startAnim(5000);
        } else if (v instanceof LVWifi) {
            ((LVWifi) v).startAnim(9000);
        } else if (v instanceof LVNews) {
            startLVNewsAnim();
        } else if (v instanceof LVBlock) {
            ((LVBlock) v).startAnim();

        } else if (v instanceof LVGhost) {
            ((LVGhost) v).startAnim();
        } else if (v instanceof LVFunnyBar) {
            ((LVFunnyBar) v).startAnim();
        } else if (v instanceof LVRingProgress) {
            ((LVRingProgress) v).startAnim(3000);
        } else if (v instanceof LVBlazeWood) {
            ((LVBlazeWood) v).startAnim(500);
        } else if (v instanceof LVSunSetView) {
            ((LVSunSetView) v).startSunset();
        }


    }

    public void startAnimAll(View v) {
        mLVCircular.startAnim();
        mLVCircularRing.startAnim();
        mLVPlayBall.startAnim();
        mLVCircularJump.startAnim();
        mLVCircularZoom.startAnim();
        startLVLineWithTextAnim();
        mLVEatBeans.startAnim(3500);
        mLVCircularCD.startAnim();
        mLVCircularSmile.startAnim(1000);
        mLVGears.startAnim();
        mLVGearsTwo.startAnim();
        mLVFinePoiStar.setDrawPath(true);
        mLVFinePoiStar.startAnim(3500);
        mLVChromeLogo.startAnim();
        mLVBattery.startAnim(5000);
        mLVWifi.startAnim(9000);
        startLVNewsAnim();
        mLVBlock.startAnim();
        mLVGhost.startAnim();
        mLVFunnyBar.startAnim();
        mLVRingProgress.startAnim(3000);
        mLVBlazeWood.startAnim(500);
        lv_sunset.startSunset();
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
//        mLVNews.stopLVNewsAnim();
        mLVBlock.stopAnim();
        mLVGhost.stopAnim();
        mLVFunnyBar.stopAnim();
        mLVRingProgress.stopAnim();
        mLVBlazeWood.stopAnim();

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
        }, 0, 10);
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
