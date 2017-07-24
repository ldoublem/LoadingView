package com.ldoublem.loadingView

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.ldoublem.appleviewlib.LVComputer
import com.ldoublem.appleviewlib.LVComputerDesktop
import com.ldoublem.appleviewlib.LVComputerIpad

/**
 * Created by steve_000 on 24/7/2017.
 * for project LoadingView
 * package name com.ldoublem.loadingView
 */
class MacActivity: AppCompatActivity(){

    lateinit internal var mLVComputerDesktop: LVComputerDesktop
    lateinit internal var mLVComputer: LVComputer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)//去掉信息栏
        setContentView(R.layout.layout_mac)
        mLVComputerDesktop = findViewById(R.id.lv_computer_desktop) as LVComputerDesktop
        mLVComputer = findViewById(R.id.lv_computer) as LVComputer

    }

    fun startAnim(v: View) {
        if (v.id == R.id.lv_computer_ipad) {
            (v as LVComputerIpad).startAnim(4000)
            mLVComputerDesktop.startAnim(4000)
            mLVComputer.startAnim(4000)
        } else if (v.id == R.id.lv_computer_desktop) {
            (v as LVComputerDesktop).startAnim(4000)
        } else if (v.id == R.id.lv_computer) {
            (v as LVComputer).startAnim(6000)
        }
    }
}