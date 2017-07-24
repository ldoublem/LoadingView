package com.ldoublem.loadingView

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.ldoublem.loadingviewlib.view.LVRingProgress
import java.util.*

/**
 * Created by steve_000 on 24/7/2017.
 * for project LoadingView
 * package name com.ldoublem.loadingView
 */
class TestActivity:AppCompatActivity() {


    lateinit var mLVRingProgress: LVRingProgress

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)//去掉信息栏
        setContentView(R.layout.layout_test)
        mLVRingProgress = findViewById(R.id.lv_ringp) as LVRingProgress
    }

    internal var mValueLVRingProgress = 0
    var mTimerLVRingProgress: Timer? = Timer()// 定时器

    fun timerTaskLVRingProgress() {
        mTimerLVRingProgress!!.schedule(object : TimerTask() {
            override fun run() {
                if (mValueLVRingProgress < 100) {

                    mValueLVRingProgress++
                    val msg = mHandle.obtainMessage(0)
                    msg.arg1 = mValueLVRingProgress
                    mHandle.sendMessage(msg)

                } else {
                    mTimerLVRingProgress!!.cancel()
                }
            }
        }, 0, 50)
    }

    fun automatic(v: View) {

        mLVRingProgress.startAnim()


    }

    fun byuser(v: View) {
        mLVRingProgress.stopAnim()
        startLVRingProgressAnim()


    }

    private fun startLVRingProgressAnim() {
        mValueLVRingProgress = 0
        if (mTimerLVRingProgress != null) {
            mTimerLVRingProgress!!.cancel()// 退出之前的mTimer
        }
        mTimerLVRingProgress = Timer()
        timerTaskLVRingProgress()
    }

    private fun stopLVRingProgressAnim() {
        if (mTimerLVRingProgress != null) {
            mTimerLVRingProgress!!.cancel()// 退出之前的mTimer
            mLVRingProgress.progress = mValueLVRingProgress
        }
    }


    private val mHandle = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            mLVRingProgress.progress = msg.arg1
        }
    }

}