package com.ldoublem.loadingView

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.ldoublem.loadingviewlib.LVChromeLogo
import com.ldoublem.loadingviewlib.LVCircular
import com.ldoublem.loadingviewlib.LVCircularCD
import com.ldoublem.loadingviewlib.LVLineWithText
import com.ldoublem.loadingviewlib.view.*
import java.util.*

/**
 * Created by steve_000 on 24/7/2017.
 * for project LoadingView
 * package name com.ldoublem.loadingView
 */
class MainActivity:AppCompatActivity() {

    lateinit internal var mLVPlayBall: LVPlayBall
    lateinit internal var mLVCircularRing: LVCircularRing
    lateinit internal var mLVCircular: LVCircular
    lateinit internal var mLVCircularJump: LVCircularJump
    lateinit internal var mLVCircularZoom: LVCircularZoom
    lateinit internal var mLVLineWithText: LVLineWithText
    lateinit internal var mLVEatBeans: LVEatBeans
    lateinit internal var mLVCircularCD: LVCircularCD
    lateinit internal var mLVCircularSmile: LVCircularSmile

    lateinit internal var mLVGears: LVGears
    lateinit internal var mLVGearsTwo: LVGearsTwo
    lateinit internal var mLVFinePoiStar: LVFinePoiStar
    lateinit internal var mLVChromeLogo: LVChromeLogo
    lateinit internal var mLVBattery: LVBattery
    lateinit internal var mLVWifi: LVWifi

    lateinit internal var mLVNews: LVNews
    lateinit internal var mLVBlock: LVBlock
    lateinit internal var mLVGhost: LVGhost
    lateinit internal var mLVFunnyBar: LVFunnyBar
    lateinit internal var mLVRingProgress: LVRingProgress

    lateinit internal var mLVBlazeWood: LVBlazeWood

    internal var mValueLVLineWithText = 0
    internal var mValueLVNews = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)//去掉信息栏

        setContentView(R.layout.activity_main)

        mLVCircular = findViewById(R.id.lv_circular) as LVCircular
        mLVCircular.setViewColor(Color.rgb(255, 99, 99))
        mLVCircular.setRoundColor(Color.rgb(255, 0, 0))

        mLVCircularCD = findViewById(R.id.lv_circularCD) as LVCircularCD
        mLVCircularCD.setViewColor(Color.rgb(0, 255, 0))


        mLVLineWithText = findViewById(R.id.lv_linetext) as LVLineWithText
        mLVLineWithText.setViewColor(Color.rgb(33, 66, 77))
        mLVLineWithText.setTextColor(Color.rgb(233, 166, 177))


        mLVCircularJump = findViewById(R.id.lv_circularJump) as LVCircularJump
        mLVCircularJump.setViewColor(Color.rgb(133, 66, 99))


        mLVCircularRing = findViewById(R.id.lv_circularring) as LVCircularRing
        mLVCircularRing.setViewColor(Color.argb(100, 255, 255, 255))
        mLVCircularRing.setBarColor(Color.YELLOW)

        mLVCircularSmile = findViewById(R.id.lv_circularSmile) as LVCircularSmile
        mLVCircularSmile.setViewColor(Color.rgb(144, 238, 146))


        mLVCircularZoom = findViewById(R.id.lv_circularZoom) as LVCircularZoom
        mLVCircularZoom.setViewColor(Color.rgb(255, 0, 122))


        mLVEatBeans = findViewById(R.id.lv_eatBeans) as LVEatBeans
        mLVEatBeans.setViewColor(Color.WHITE)
        mLVEatBeans.setEyeColor(Color.BLUE)


        mLVFinePoiStar = findViewById(R.id.lv_finePoiStar) as LVFinePoiStar
        mLVFinePoiStar.setViewColor(Color.WHITE)
        mLVFinePoiStar.setCircleColor(Color.YELLOW)
        mLVFinePoiStar.setDrawPath(true)

        mLVGears = findViewById(R.id.lv_gears) as LVGears
        mLVGears.setViewColor(Color.rgb(55, 155, 233))

        mLVGearsTwo = findViewById(R.id.lv_gears_two) as LVGearsTwo
        mLVGearsTwo.setViewColor(Color.rgb(155, 55, 233))


        mLVWifi = findViewById(R.id.lv_wifi) as LVWifi
        mLVWifi.setViewColor(Color.BLACK)


        mLVNews = findViewById(R.id.lv_news) as LVNews
        mLVNews.setViewColor(Color.WHITE)


        mLVRingProgress = findViewById(R.id.lv_ringp) as LVRingProgress
        mLVRingProgress.setViewColor(Color.WHITE)
        mLVRingProgress.setTextColor(Color.BLACK)
        mLVRingProgress.setPorBarStartColor(Color.YELLOW)
        mLVRingProgress.setPorBarEndColor(Color.BLUE)


        mLVGhost = findViewById(R.id.lv_ghost) as LVGhost
        mLVGhost.setViewColor(Color.WHITE)
        mLVGhost.setHandColor(Color.BLACK)

        mLVPlayBall = findViewById(R.id.lv_playball) as LVPlayBall
        mLVPlayBall.setViewColor(Color.WHITE)
        mLVPlayBall.setBallColor(Color.RED)


        mLVChromeLogo = findViewById(R.id.lv_chromeLogo) as LVChromeLogo

        mLVBattery = findViewById(R.id.lv_battery) as LVBattery
        mLVBattery.setBatteryOrientation(LVBattery.BatteryOrientation.VERTICAL)//LVBattery.BatteryOrientation.HORIZONTAL
        mLVBattery.setShowNum(false)

        mLVBattery.setViewColor(Color.WHITE)
        mLVBattery.setCellColor(Color.GREEN)

        mLVBlock = findViewById(R.id.lv_block) as LVBlock

        mLVBlock.setViewColor(Color.rgb(245, 209, 22))
        mLVBlock.setShadowColor(Color.BLACK)
        //        mLVBlock.isShadow(false);


        mLVFunnyBar = findViewById(R.id.lv_funnybar) as LVFunnyBar
        mLVFunnyBar.setViewColor(Color.rgb(234, 167, 107))



        mLVBlazeWood = findViewById(R.id.lv_wood) as LVBlazeWood

        //        mLVLineWithText.setValue(50);


    }

    fun startAnim(v: View) {

        stopAll()
        (v as? LVCircular)?.startAnim() ?: ((v as? LVCircularCD)?.startAnim() ?: ((v as? LVCircularSmile)?.startAnim() ?: ((v as? LVCircularRing)?.startAnim() ?: ((v as? LVCircularZoom)?.startAnim() ?: ((v as? LVCircularJump)?.startAnim() ?: ((v as? LVEatBeans)?.startAnim(3500) ?: ((v as? LVPlayBall)?.startAnim() ?: if (v is LVLineWithText) {
            startLVLineWithTextAnim()
        } else (v as? LVGears)?.startAnim() ?: ((v as? LVGearsTwo)?.startAnim() ?: if (v is LVFinePoiStar) {
            v.setDrawPath(false)
            v.startAnim(3500)
        } else (v as? LVChromeLogo)?.startAnim() ?: ((v as? LVBattery)?.startAnim(5000) ?: ((v as? LVWifi)?.startAnim(9000) ?: if (v is LVNews) {
            startLVNewsAnim()
        } else (v as? LVBlock)?.startAnim() ?: ((v as? LVGhost)?.startAnim() ?: ((v as? LVFunnyBar)?.startAnim() ?: ((v as? LVRingProgress)?.startAnim(3000) ?: (v as? LVBlazeWood)?.startAnim(500))))))))))))))


    }

    fun startAnimAll(v: View) {
        mLVCircular.startAnim()
        mLVCircularRing.startAnim()
        mLVPlayBall.startAnim()
        mLVCircularJump.startAnim()
        mLVCircularZoom.startAnim()
        startLVLineWithTextAnim()
        mLVEatBeans.startAnim(3500)
        mLVCircularCD.startAnim()
        mLVCircularSmile.startAnim(1000)
        mLVGears.startAnim()
        mLVGearsTwo.startAnim()
        mLVFinePoiStar.setDrawPath(true)
        mLVFinePoiStar.startAnim(3500)
        mLVChromeLogo.startAnim()
        mLVBattery.startAnim(5000)
        mLVWifi.startAnim(9000)
        startLVNewsAnim()
        mLVBlock.startAnim()
        mLVGhost.startAnim()
        mLVFunnyBar.startAnim()
        mLVRingProgress.startAnim(3000)
        mLVBlazeWood.startAnim(500)
    }

    fun stopAnim(v: View) {

        stopAll()

    }

    private fun stopAll() {
        mLVCircular.stopAnim()
        mLVPlayBall.stopAnim()
        mLVCircularJump.stopAnim()
        mLVCircularZoom.stopAnim()
        mLVCircularRing.stopAnim()
        mLVEatBeans.stopAnim()
        stopLVLineWithTextAnim()
        mLVCircularCD.stopAnim()
        mLVCircularSmile.stopAnim()
        mLVGears.stopAnim()
        mLVGearsTwo.stopAnim()
        mLVFinePoiStar.stopAnim()
        mLVChromeLogo.stopAnim()
        mLVBattery.stopAnim()
        mLVWifi.stopAnim()
        stopLVNewsAnim()
        //        mLVNews.stopLVNewsAnim();
        mLVBlock.stopAnim()
        mLVGhost.stopAnim()
        mLVFunnyBar.stopAnim()
        mLVRingProgress.stopAnim()
        mLVBlazeWood.stopAnim()

    }


    var mTimerLVLineWithText: Timer? = Timer()// 定时器
    var mTimerLVNews: Timer? = Timer()// 定时器


    private fun startLVLineWithTextAnim() {
        mValueLVLineWithText = 0
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText!!.cancel()// 退出之前的mTimer
        }
        mTimerLVLineWithText = Timer()
        timerTaskLVLineWithText()
    }

    private fun stopLVLineWithTextAnim() {
        if (mTimerLVLineWithText != null) {
            mTimerLVLineWithText!!.cancel()// 退出之前的mTimer
            mLVNews.setValue(mValueLVNews)
        }
    }


    private fun startLVNewsAnim() {
        mValueLVNews = 0
        if (mTimerLVNews != null) {

            mTimerLVNews!!.cancel()
        }
        mTimerLVNews = Timer()
        timerTaskLVNews()
    }

    private fun stopLVNewsAnim() {
        mLVNews.stopAnim()
        if (mTimerLVNews != null) {
            mTimerLVNews!!.cancel()
            mLVLineWithText.setValue(mValueLVLineWithText)
        }
    }


    fun timerTaskLVNews() {
        mTimerLVNews?.schedule(object : TimerTask() {
            override fun run() {
                if (mValueLVNews < 100) {
                    mValueLVNews++
                    val msg = mHandle.obtainMessage(1)
                    msg.arg1 = mValueLVNews
                    mHandle.sendMessage(msg)
                } else {
                    mTimerLVNews?.cancel()
                }
            }
        }, 0, 10)
    }


    fun timerTaskLVLineWithText() {
        mTimerLVLineWithText?.schedule(object : TimerTask() {
            override fun run() {
                if (mValueLVLineWithText < 100) {

                    mValueLVLineWithText++
                    val msg = mHandle.obtainMessage(2)
                    msg.arg1 = mValueLVLineWithText
                    mHandle.sendMessage(msg)

                } else {
                    mTimerLVLineWithText?.cancel()
                }
            }
        }, 0, 50)
    }


    private val mHandle = object : Handler() {

        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 2)
                mLVLineWithText.setValue(msg.arg1)
            else if (msg.what == 1) {
                mLVNews.setValue(msg.arg1)
            }
        }
    }
}