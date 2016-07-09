package com.ldoublem.loadingView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ldoublem.loadingView.view.LVBattery;
import com.ldoublem.loadingView.view.LVBlock;
import com.ldoublem.loadingView.view.LVChromeLogo;
import com.ldoublem.loadingView.view.LVCircular;
import com.ldoublem.loadingView.view.LVCircularCD;
import com.ldoublem.loadingView.view.LVCircularJump;
import com.ldoublem.loadingView.view.LVCircularRing;
import com.ldoublem.loadingView.view.LVCircularSmile;
import com.ldoublem.loadingView.view.LVCircularZoom;
import com.ldoublem.loadingView.view.LVComputer;
import com.ldoublem.loadingView.view.LVComputerDesktop;
import com.ldoublem.loadingView.view.LVComputerIpad;
import com.ldoublem.loadingView.view.LVEatBeans;
import com.ldoublem.loadingView.view.LVFinePoiStar;
import com.ldoublem.loadingView.view.LVFunnyBar;
import com.ldoublem.loadingView.view.LVGears;
import com.ldoublem.loadingView.view.LVGearsTwo;
import com.ldoublem.loadingView.view.LVGhost;
import com.ldoublem.loadingView.view.LVLineWithText;
import com.ldoublem.loadingView.view.LVNews;
import com.ldoublem.loadingView.view.LVPlayBall;
import com.ldoublem.loadingView.view.LVWifi;

import java.util.Timer;
import java.util.TimerTask;


public class MacActivity extends AppCompatActivity {

    LVComputerDesktop mLVComputerDesktop;
    LVComputer mLVComputer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.layout_mac);
        mLVComputerDesktop = (LVComputerDesktop) (findViewById(R.id.lv_computer_desktop));
        mLVComputer = (LVComputer) (findViewById(R.id.lv_computer));

    }

    public void startAnim(View v) {
        if (v.getId() == R.id.lv_computer_ipad) {
            ((LVComputerIpad) v).startAnim(4000);
            mLVComputerDesktop.startAnim(4000);
            mLVComputer.startAnim(4000);
        } else if (v.getId() == R.id.lv_computer_desktop) {
            ((LVComputerDesktop) v).startAnim(4000);
        } else if (v.getId() == R.id.lv_computer) {
            ((LVComputer) v).startAnim(6000);
        }
    }
}
