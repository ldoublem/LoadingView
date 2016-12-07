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

public class LVCircularRing extends LVBase {

    private Paint mPaint;
    private Paint mPaintPro;

    private float mWidth = 0f;
    private float mPadding = 0f;
    private float startAngle = 0f;
    RectF rectF = new RectF();

    public LVCircularRing(Context context) {
        super(context);
    }

    public LVCircularRing(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVCircularRing(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaintPro);
        rectF = new RectF(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
        canvas.drawArc(rectF, startAngle, 100
                , false, mPaint);//第四个参数是否显示半径

    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(8);

        mPaintPro=new Paint();
        mPaintPro.setAntiAlias(true);
        mPaintPro.setStyle(Paint.Style.STROKE);
        mPaintPro.setColor(Color.argb(100, 255, 255, 255));
        mPaintPro.setStrokeWidth(8);

    }


    public void setViewColor(int color)
    {
        mPaintPro.setColor(color);
        postInvalidate();
    }

    public void setBarColor(int color)
    {
        mPaint.setColor(color);
        postInvalidate();
    }




    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        float value = (float) valueAnimator.getAnimatedValue();
        startAngle = 360 * value;

        invalidate();
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected int OnStopAnim() {
        return 0;
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
