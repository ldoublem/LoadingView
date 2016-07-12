package com.ldoublem.loadingView;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.ldoublem.appleviewlib.LVComputer;
import com.ldoublem.appleviewlib.LVComputerDesktop;
import com.ldoublem.appleviewlib.LVComputerIpad;



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
