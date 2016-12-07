package com.ldoublem.loadingviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import static android.R.style.Animation;

/**
 * Created by lumingmin on 16/6/20.
 */

public class LVCircular extends View {

    private Paint mPaintCenter;
    private Paint mPaintRound;


    private float mWidth = 0f;
    private float mAnimatedValue = 0f;
    private int mStartAngle = 0;
    private float mMaxRadius = 4;
    RotateAnimation mProgerssRotateAnim;

    public LVCircular(Context context) {
        this(context, null);
    }

    public LVCircular(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVCircular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();

        mMaxRadius = mWidth / 30f;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < 9; i++) {
            float x2 = (float) ((mWidth / 2.f - mMaxRadius) * Math.cos(mStartAngle + 45f * i * Math.PI / 180f));
            float y2 = (float) ((mWidth / 2.f - mMaxRadius) * Math.sin(mStartAngle + 45f * i * Math.PI / 180f));
            canvas.drawCircle(mWidth / 2.f - x2, mWidth / 2.f - y2, mMaxRadius, mPaintRound);
        }

        canvas.drawCircle(mWidth / 2.f, mWidth / 2.f, mWidth / 2.f - mMaxRadius * 6, mPaintCenter);


    }


    private void initPaint() {
        mPaintCenter = new Paint();
        mPaintCenter.setAntiAlias(true);
        mPaintCenter.setStyle(Paint.Style.FILL);
        mPaintCenter.setColor(Color.WHITE);
        mPaintRound= new Paint();
        mPaintRound.setAntiAlias(true);
        mPaintRound.setStyle(Paint.Style.FILL);
        mPaintRound.setColor(Color.WHITE);



        mProgerssRotateAnim = new RotateAnimation(0f, 360f, android.view.animation.Animation.RELATIVE_TO_SELF,
                0.5f, android.view.animation.Animation.RELATIVE_TO_SELF, 0.5f);
        mProgerssRotateAnim.setRepeatCount(-1);
        mProgerssRotateAnim.setInterpolator(new LinearInterpolator());//不停顿
        mProgerssRotateAnim.setFillAfter(true);//停在最后


    }

    public void setViewColor(int color) {
        mPaintCenter.setColor(color);
        postInvalidate();
    }
    public void setRoundColor(int color) {
        mPaintRound.setColor(color);
        postInvalidate();
    }




    public void startAnim() {
        stopAnim();
        mProgerssRotateAnim.setDuration(3500);
        startAnimation(mProgerssRotateAnim);
    }
    public void startAnim(int time) {
        stopAnim();
        mProgerssRotateAnim.setDuration(time);
        startAnimation(mProgerssRotateAnim);
    }
    public void stopAnim() {
        clearAnimation();
    }


}
