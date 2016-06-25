package com.ldoublem.loadingView;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
import com.ldoublem.loadingView.view.LVPlayBall;

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

    int mValueLVLineWithText = 0;

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
        mLVFinePoiStar= (LVFinePoiStar) findViewById(R.id.lv_finePoiStar);

        mLVChromeLogo= (LVChromeLogo) findViewById(R.id.lv_chromeLogo);
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
        }else if(v instanceof  LVGears)
        {
            ((LVGears) v).startAnim();
        }else if(v instanceof  LVGearsTwo)
        {
            ((LVGearsTwo) v).startAnim();
        }
        else if(v instanceof  LVFinePoiStar)
        {
            ((LVFinePoiStar) v).setDrawPath(false);
            ((LVFinePoiStar) v).startAnim();
        }

        else if(v instanceof LVChromeLogo)
        {
            ((LVChromeLogo) v).startAnim();
//            Intent i=new Intent();
//            i.setClass(this,ChromeLogoActivity.class);
//            startActivity(i);

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
    }


    public Timer mTimer = new Timer();// 定时器

    private void startLVLineWithTextAnim() {
        if (mTimer != null) {

            mTimer.cancel();// 退出之前的mTimer
        }
        mTimer = new Timer();// new一个Timer,否则会报错
        timerTask();
    }

    private void stopLVLineWithTextAnim() {
        if (mTimer != null) {

            mTimer.cancel();// 退出之前的mTimer
            mLVLineWithText.setValue(mValueLVLineWithText);
        }
    }


    public void timerTask() {
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mValueLVLineWithText < 100) {

                    mValueLVLineWithText++;
                    Message msg = mHandle.obtainMessage();
                    msg.arg1 = mValueLVLineWithText;
                    mHandle.sendMessage(msg);

                } else {
                    mTimer.cancel();
                }
            }
        }, 0, 50);
    }


    private Handler mHandle = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mLVLineWithText.setValue(msg.arg1);
        }
    };


}
