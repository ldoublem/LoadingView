package com.ldoublem.loadingviewlib.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.ldoublem.loadingviewlib.view.base.LVBase;

/**
 * Created by lumingmin on 16/6/27.
 */

public class LVBattery extends LVBase {
    private float mWidth = 0f;
    private float mhigh = 0f;

    private float mBatteryWidth;
    private float mBatteryHigh;
    private float mPadding = 0f;
    private float mBodyCorner = 0f;

    private float mBatterySpace = 0f;

    private Paint mPaint, mPaintHead, mPaintValue;


    private BatteryOrientation mBatteryOrientation = BatteryOrientation.HORIZONTAL;


    RectF rectFBody = null;
    RectF rectHead = null;

    public LVBattery(Context context) {
        super(context);
    }

    public LVBattery(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVBattery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public enum BatteryOrientation {
        VERTICAL, HORIZONTAL
    }

    private boolean mShowNum = false;


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


        if (getMeasuredWidth() > getHeight()) {
            mWidth = getMeasuredHeight();
            mhigh = getMeasuredHeight() * 0.8f;

        } else {
            mWidth = getMeasuredWidth();
            mhigh = getMeasuredWidth() * 0.8f;

        }


    }

    private void drawHead(Canvas canvas) {
        float mHeadwidth = mhigh / 6;
        rectHead = new RectF(
                mWidth - mPadding - mHeadwidth,
                mWidth / 2 - mHeadwidth / 2,
                mWidth - mPadding,
                mWidth / 2 + mHeadwidth / 2);
        canvas.drawArc(rectHead, -70, 140
                , false, mPaintHead);
    }


    private void drawBody(Canvas canvas) {
        float mHeadwidth = mhigh / 6;
        float x = (float) ((mHeadwidth / 2) * Math.cos(-70 * Math.PI / 180f));
        rectFBody = new RectF();
        rectFBody.top = mWidth / 2 - mhigh / 4 + mPadding;
        rectFBody.bottom = mWidth / 2 + mhigh / 4 - mPadding;
        rectFBody.left = mPadding;
        rectFBody.right = mWidth - mPadding - x - x - mBatterySpace;
        canvas.drawRoundRect(rectFBody, mBodyCorner, mBodyCorner, mPaint);
    }

    private void drawValue(Canvas canvas) {
        RectF rectFBatteryValueFill = new RectF();
        rectFBatteryValueFill.top = rectFBody.top + mBatterySpace;
        rectFBatteryValueFill.bottom = rectFBody.bottom - mBatterySpace;
        rectFBatteryValueFill.left = mPadding + mBatterySpace;
        rectFBatteryValueFill.right = rectFBody.right - mBatterySpace;

        RectF rectFBatteryValue = new RectF();
        rectFBatteryValue.top = rectFBatteryValueFill.top;
        rectFBatteryValue.bottom = rectFBatteryValueFill.bottom;
        rectFBatteryValue.left = rectFBatteryValueFill.left;
        rectFBatteryValue.right = rectFBatteryValueFill.right * mAnimatedValue;
        canvas.drawRoundRect(rectFBatteryValue, 1, 1, mPaintValue);
    }

    private void drawLogo(Canvas canvas) {

        mPaintHead.setTextSize(mhigh / 6);
        if (!mShowNum) {
            Path path = new Path();
            path.moveTo(mWidth / 2 - mhigh / 6, mWidth / 2 - dip2px(1.5f));
            path.lineTo(mWidth / 2 + dip2px(2f), mWidth / 2 + mhigh / 12);
            path.lineTo(mWidth / 2 + dip2px(1f), mWidth / 2);
            path.close();
            canvas.drawPath(path, mPaintHead);
            Path path2 = new Path();
            path2.moveTo(mWidth / 2 - dip2px(2f), mWidth / 2 - mhigh / 12);
            path2.lineTo(mWidth / 2 + mhigh / 6, mWidth / 2 + dip2px(1.5f));
            path2.lineTo(mWidth / 2 - dip2px(1f), mWidth / 2);
            path2.close();
            canvas.drawPath(path2, mPaintHead);
        } else {
            String text = String.valueOf((int) (mAnimatedValue * 100)) + "%";


            if (mBatteryOrientation == BatteryOrientation.VERTICAL) {
                Path p = new Path();
                p.moveTo(mWidth / 2, 0);
                p.lineTo(mWidth / 2, mWidth);
                canvas.drawTextOnPath(text, p,
                        mWidth / 2 - getFontlength(mPaintHead, text) / 2,
                        mWidth / 2 - mhigh / 2 - getFontHeight(mPaintHead, text) / 2,
                        mPaintHead);
            } else {
                canvas.drawText(text,
                        mWidth / 2 - getFontlength(mPaintHead, text) / 2,
                        mWidth / 2 + getFontHeight(mPaintHead, text) / 2,
                        mPaintHead);
            }

        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBatteryOrientation == BatteryOrientation.VERTICAL)
            canvas.rotate(270, mWidth / 2, mWidth / 2);
        else
            canvas.rotate(0, mWidth / 2, mWidth / 2);
        canvas.save();

        drawHead(canvas);
        drawBody(canvas);
        drawValue(canvas);
        drawLogo(canvas);

        canvas.restore();

    }

    private void initPaint() {
        mBatteryHigh = dip2px(20);
        mPadding = dip2px(2);
        mBodyCorner = dip2px(1);
        mBatterySpace = dip2px(1);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.WHITE);
        mPaintHead = new Paint();
        mPaintHead.setAntiAlias(true);
        mPaintHead.setStyle(Paint.Style.FILL);
        mPaintHead.setColor(Color.WHITE);
        mPaintValue = new Paint();
        mPaintValue.setAntiAlias(true);
        mPaintValue.setStyle(Paint.Style.FILL);
        mPaintValue.setColor(Color.rgb(67, 213, 81));
    }



    public void setViewColor(int color)
    {
        mPaint.setColor(color);
        mPaintHead.setColor(color);
        postInvalidate();
    }

    public void setCellColor(int color)
    {
        mPaintValue.setColor(color);
        postInvalidate();
    }




    public void setValue(int value)//0-100
    {
        this.mAnimatedValue = value * 1.f / 100;
        invalidate();
    }

    public void setShowNum(boolean show) {
        this.mShowNum = show;
        invalidate();
    }

    public void setBatteryOrientation(BatteryOrientation batteryOrientation) {
        this.mBatteryOrientation = batteryOrientation;
        invalidate();
    }


    private float mAnimatedValue = 0f;


    @Override
    protected void InitPaint() {
        initPaint();
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
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }

    @Override
    protected int OnStopAnim() {
        mAnimatedValue = 0f;
        postInvalidate();
        return 1;
    }

    @Override
    protected void AinmIsRunning() {

    }

    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }


}
