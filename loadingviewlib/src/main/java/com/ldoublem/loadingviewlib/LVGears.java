package com.ldoublem.loadingviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lumingmin on 16/6/23.
 */

public class LVGears extends View {
    private float mWidth = 0f;
    private Paint mPaint, mPaintWheelBig, mPaintWheelSmall, mPaintAxle, mPaintCenter;
    private float mPadding = 0f;
    private float mPaintCenterRadius;


    private float mWheelSmallLength, mWheelBigLength;


    private int mWheelSmallSpace = 8;
    private int mWheelBigSpace =6;

    ValueAnimator valueAnimator = null;
    float mAnimatedValue = 0f;


    public LVGears(Context context) {
        this(context, null);
    }

    public LVGears(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVGears(Context context, AttributeSet attrs, int defStyleAttr) {
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


    }


    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaint);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 4, mPaint);
    }


    private void drawAxleAndCenter(Canvas canvas) {


        for (int i = 0; i < 3; i++) {
            float x2 = (float) ((mWidth / 2.f - mPadding) * Math.cos(i * (360 / 3) * Math.PI / 180f));
            float y2 = (float) ((mWidth / 2.f - mPadding) * Math.sin(i * (360 / 3) * Math.PI / 180f));
            float x = (float) (mPaintCenterRadius * Math.cos(i * (360 / 3) * Math.PI / 180f));
            float y = (float) (mPaintCenterRadius * Math.sin(i * (360 / 3) * Math.PI / 180f));
            canvas.drawLine(mWidth / 2 - x,
                    mWidth / 2 - y,
                    mWidth / 2 - x2,
                    mWidth / 2 - y2, mPaintAxle);

        }

        canvas.drawCircle(mWidth / 2, mWidth / 2, mPaintCenterRadius, mPaintCenter);


    }

    private void drawWheelBig(Canvas canvas) {

        for (int i = 0; i < 360; i = i + mWheelBigSpace) {
            int angle = (int) (mAnimatedValue * mWheelBigSpace + i);//clockwise 顺时针
            float x = (float) ((mWidth / 2.f - mPadding + mWheelBigLength) * Math.cos(angle * Math.PI / 180f));
            float y = (float) ((mWidth / 2.f - mPadding + mWheelBigLength) * Math.sin(angle * Math.PI / 180f));
            float x2 = (float) ((mWidth / 2.f - mPadding) * Math.cos(angle * Math.PI / 180f));
            float y2 = (float) ((mWidth / 2.f - mPadding) * Math.sin(angle * Math.PI / 180f));
            canvas.drawLine(mWidth / 2.f - x,
                    mWidth / 2.f - y,
                    mWidth / 2.f - x2,
                    mWidth / 2.f - y2,
                    mPaintWheelBig);

        }

    }

    private void drawWheelSmall(Canvas canvas) {

        for (int i = 0; i < 360; i = i + mWheelSmallSpace) {
            int angle = (int) (360 - mAnimatedValue * mWheelBigSpace + i);//anticlockwise 逆时针
            float x = (float) ((mWidth / 4.f) * Math.cos(angle * Math.PI / 180f));
            float y = (float) ((mWidth / 4.f) * Math.sin(angle * Math.PI / 180f));
            float x2 = (float) ((mWidth / 4.f + mWheelSmallLength) * Math.cos(angle * Math.PI / 180f));
            float y2 = (float) ((mWidth / 4.f + mWheelSmallLength) * Math.sin(angle * Math.PI / 180f));
            canvas.drawLine(mWidth / 2.f - x,
                    mWidth / 2.f - y,
                    mWidth / 2.f - x2,
                    mWidth / 2.f - y2,
                    mPaintWheelSmall);




        }

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        drawCircle(canvas);

        drawWheelBig(canvas);
        drawWheelSmall(canvas);
        drawAxleAndCenter(canvas);

        canvas.restore();
    }

    private void initPaint() {


        mPaintCenterRadius = dip2px(2.5f) / 2;
        mPaintCenter = new Paint();
        mPaintCenter.setAntiAlias(true);
        mPaintCenter.setStyle(Paint.Style.STROKE);
        mPaintCenter.setColor(Color.WHITE);
        mPaintCenter.setStrokeWidth(dip2px(0.5f));


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(dip2px(2));


        mPaintAxle = new Paint();
        mPaintAxle.setAntiAlias(true);
        mPaintAxle.setStyle(Paint.Style.FILL);
        mPaintAxle.setColor(Color.WHITE);
        mPaintAxle.setStrokeWidth(dip2px(2f));

        mPaintWheelBig = new Paint();
        mPaintWheelBig.setAntiAlias(true);
        mPaintWheelBig.setStyle(Paint.Style.STROKE);
        mPaintWheelBig.setColor(Color.WHITE);
        mPaintWheelBig.setStrokeWidth(dip2px(1));

        mPaintWheelSmall = new Paint();
        mPaintWheelSmall.setAntiAlias(true);
        mPaintWheelSmall.setStyle(Paint.Style.STROKE);
        mPaintWheelSmall.setColor(Color.WHITE);
        mPaintWheelSmall.setStrokeWidth(dip2px(0.5f));

        mPadding = dip2px(5);
        mWheelSmallLength = dip2px(3);
        mWheelBigLength = dip2px(2.5f);


    }

    public void startAnim() {
        stopAnim();
        startViewAnim(1, 100, 300);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(int startF, final int endF, long time) {
        valueAnimator = ValueAnimator.ofInt(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatedValue = (int) valueAnimator.getAnimatedValue();
                mAnimatedValue=mAnimatedValue/100f;
                postInvalidate();
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);

            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }

        return valueAnimator;
    }


    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
