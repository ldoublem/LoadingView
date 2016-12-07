package com.ldoublem.loadingviewlib.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.ldoublem.loadingviewlib.view.base.LVBase;

/**
 * Created by lumingmin on 16/6/27.
 */

public class LVWifi extends LVBase {
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
    public void setViewColor(int color)
    {
        mPaint.setColor(color);

        postInvalidate();
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



    private float mAnimatedValue = 0.9f;






    @Override
    protected void InitPaint() {

    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        invalidate();
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected int OnStopAnim() {
        mAnimatedValue = 0.9f;
        postInvalidate();
        return 1;
    }

    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }
    @Override
    protected void AinmIsRunning() {

    } @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }


}
