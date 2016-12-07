package com.ldoublem.loadingviewlib;

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

/**
 * Created by lumingmin on 16/6/20.
 */

    public class LVCircularCD extends View {

    private Paint mPaint;

    private float mWidth = 0f;
    private float mPadding = 0f;

    RotateAnimation mProgerssRotateAnim;
    RectF rectF = new RectF();
    RectF rectF2 = new RectF();
    public LVCircularCD(Context context) {
        this(context, null);
    }

    public LVCircularCD(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVCircularCD(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mPadding = 5;


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaint);
        mPaint.setStrokeWidth(3);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mPadding, mPaint);

        mPaint.setStrokeWidth(2);
        rectF = new RectF(mWidth / 2 - mWidth / 3, mWidth / 2 - mWidth / 3,
                mWidth / 2 + mWidth / 3, mWidth / 2 + mWidth / 3);
        canvas.drawArc(rectF, 0, 80
                , false, mPaint);

        canvas.drawArc(rectF, 180, 80
                , false, mPaint);


        rectF2 = new RectF(mWidth / 2 - mWidth / 4, mWidth / 2 - mWidth / 4,
                mWidth / 2 + mWidth / 4, mWidth / 2 + mWidth / 4);
        canvas.drawArc(rectF2, 0, 80
                , false, mPaint);
        canvas.drawArc(rectF2, 180, 80
                , false, mPaint);
        canvas.restore();


    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mProgerssRotateAnim = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mProgerssRotateAnim.setRepeatCount(-1);
        mProgerssRotateAnim.setInterpolator(new LinearInterpolator());//不停顿
        mProgerssRotateAnim.setFillAfter(true);//停在最后


    }

    public void setViewColor(int color)
    {
        mPaint.setColor(color);
        postInvalidate();
    }



    public void startAnim() {
        stopAnim();
        mProgerssRotateAnim.setDuration(1500);
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
