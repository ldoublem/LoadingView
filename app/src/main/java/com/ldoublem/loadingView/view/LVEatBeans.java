package com.ldoublem.loadingView.view;

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
import android.view.animation.LinearInterpolator;

/**
 * Created by lumingmin on 16/6/20.
 */

public class LVEatBeans extends View {

    private Paint mPaint, mPaintEye;

    private float mWidth = 0f;
    private float mHigh = 0f;
    private float mPadding = 5f;

    private float eatErWidth = 60f;
    private float eatErPositonX = 0f;
    int eatSpeed = 5;
    private float beansWidth = 10f;


    private float mAngle = 34;
    private float eatErStrtAngle = mAngle;
    private float eatErEndAngle = 360 - 2 * eatErStrtAngle;


    public LVEatBeans(Context context) {
        this(context, null);
    }

    public LVEatBeans(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVEatBeans(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHigh = getMeasuredHeight();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float eatRightX = mPadding + eatErWidth + eatErPositonX;
        RectF rectF = new RectF(mPadding + eatErPositonX, mHigh / 2 - eatErWidth / 2, eatRightX, mHigh / 2 + eatErWidth / 2);
        canvas.drawArc(rectF, eatErStrtAngle, eatErEndAngle
                , true, mPaint);
        canvas.drawCircle(mPadding + eatErPositonX + eatErWidth / 2,
                mHigh / 2 - eatErWidth / 4,
                beansWidth / 2, mPaintEye);

        int beansCount = (int) ((mWidth - mPadding * 2 - eatErWidth) / beansWidth / 2);
        for (int i = 0; i < beansCount; i++) {

            float x = beansCount * i + beansWidth / 2 + mPadding + eatErWidth;
            if (x > eatRightX) {
                canvas.drawCircle(x,
                        mHigh / 2, beansWidth / 2, mPaint);
            }
        }


    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);

        mPaintEye = new Paint();
        mPaintEye.setAntiAlias(true);
        mPaintEye.setStyle(Paint.Style.FILL);
        mPaintEye.setColor(Color.BLACK);

    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 3500);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            eatErPositonX = 0;
            postInvalidate();
        }
    }

    ValueAnimator valueAnimator = null;

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                float mAnimatedValue = (float) valueAnimator.getAnimatedValue();
                eatErPositonX = (mWidth - 2 * mPadding - eatErWidth) * mAnimatedValue;
                eatErStrtAngle = mAngle * (1 - (mAnimatedValue * eatSpeed - (int) (mAnimatedValue * eatSpeed)));
                eatErEndAngle = 360 - eatErStrtAngle * 2;
                invalidate();
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


}
