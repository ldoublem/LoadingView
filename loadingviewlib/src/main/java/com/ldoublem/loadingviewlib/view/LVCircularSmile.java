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
 * Created by lumingmin on 16/6/20.
 */

public class LVCircularSmile extends LVBase {

    private Paint mPaint;

    private float mWidth = 0f;
    private float mEyeWidth = 0f;

    private float mPadding = 0f;
    private float startAngle = 0f;
    private boolean isSmile = false;
    RectF rectF = new RectF();

    public LVCircularSmile(Context context) {
        super(context);
    }

    public LVCircularSmile(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVCircularSmile(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();
        mPadding = dip2px(10);
        mEyeWidth = dip2px(3);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        rectF = new RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
        mPaint.setStyle(Paint.Style.STROKE);

        canvas.drawArc(rectF, startAngle, 180
                , false, mPaint);//第四个参数是否显示半径

        mPaint.setStyle(Paint.Style.FILL);
        if (isSmile) {
            canvas.drawCircle(mPadding + mEyeWidth + mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - mEyeWidth - mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
        }

    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(dip2px(2));

    }

    public void setViewColor(int color)
    {
        mPaint.setColor(color);
        postInvalidate();
    }



    float mAnimatedValue = 0f;


    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        if (mAnimatedValue < 0.5) {
            isSmile = false;
            startAngle = 720 * mAnimatedValue;
        } else {
            startAngle = 720;
            isSmile = true;
        }

        invalidate();
    }

    @Override
    protected int OnStopAnim() {
        isSmile = false;
        mAnimatedValue = 0f;
        startAngle = 0f;
        return 0;
    }

    @Override
    protected void InitPaint() {
        initPaint();
    }
    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }
    @Override
    protected void AinmIsRunning() {

    }
    @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }
}
