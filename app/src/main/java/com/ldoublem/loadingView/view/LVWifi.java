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
 * Created by lumingmin on 16/6/27.
 */

public class LVWifi extends View {
    private float mWidth = 0f;
    private float mPadding = 0f;
    private Paint mPaint;
    private int signalSize = 4;

    public LVWifi(Context context) {
        this(context, null);
    }

    public LVWifi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVWifi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        canvas.translate(0, mWidth / signalSize);
        mPaint.setStrokeWidth(mWidth / signalSize / 2 / 2 / 2);
        int scale = (int) ((mAnimatedValue * signalSize - (int) (mAnimatedValue * signalSize)) * signalSize)+1;
        RectF rect = null;
        float signalRadius = mWidth / 2 / signalSize;
        for (int i = 0; i < signalSize; i++) {

            if (i>=signalSize-scale) {
                float radius = signalRadius * i;
                rect = new RectF(
                        radius,
                        radius,
                        mWidth - radius,
                        mWidth - radius);
                if (i < signalSize - 1) {
                    mPaint.setStyle(Paint.Style.STROKE);
                    canvas.drawArc(rect, -135, 90
                            , false, mPaint);
                } else {
                    mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawArc(rect, -135, 90
                            , true, mPaint);
                }
            }

        }
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();
        mPadding = dip2px(1);


    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 6000);
    }

    private ValueAnimator valueAnimator;
    private float mAnimatedValue = 0.9f;

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();

            valueAnimator.setRepeatCount(1);
            valueAnimator.cancel();
            valueAnimator.end();
            mAnimatedValue = 0.9f;
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatedValue = (float) valueAnimator.getAnimatedValue();
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

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


}
