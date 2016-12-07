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
 * Created by lumingmin on 16/6/28.
 */

public class LVNews extends LVBase {

    private float mWidth = 0f;
    private float mPadding = 0f;
    private Paint mPaint;

    private float cornerRadius = 0f;

    RectF rectFTopRight = new RectF();
    RectF rectFBottomRight = new RectF();
    RectF rectFBottomLeft = new RectF();
    RectF rectFTopLeft = new RectF();
    RectF rectFSquare = new RectF();
    RectF rectFSquareBG = new RectF();

    float marggingSquareH = 0f;
    float marggingSquareV = 0f;
    float marggingLineH = 0f;
    float marggingLineV = 0f;
    private int mValue = 100;

    public LVNews(Context context) {
        super(context);
    }

    public LVNews(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVNews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setValue(int value) {
        stopAnim();
        if (value <= 100) {
            this.mValue = value;
            postInvalidate();
            if (this.mValue == 100) {
                startAnim();
            }


        } else {
            this.mValue = 100;
        }
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
        cornerRadius = dip2px(3.0f);
        mPadding = dip2px(1);

        canvas.save();


        mPaint.setStrokeWidth(dip2px(1.0f));
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.WHITE);


        rectFSquareBG.top = mPadding;
        rectFSquareBG.left = mPadding;
        rectFSquareBG.right = mWidth - mPadding;
        rectFSquareBG.bottom = mWidth - mPadding;

        drawContent(canvas, mStep);

        if (mValue <= 25) {
            if (mValue <= 5)
                mValue = 5;
            drawLineToRight(canvas, mValue);
            drawContentSquareLineToRight(canvas, mValue);
        } else if (mValue > 25 && mValue <= 50) {
            drawLineToBottom(canvas, mValue);
            drawContentSquareLineToBottom(canvas, mValue);
        } else if (mValue > 50 && mValue <= 75) {
            drawLineToLeft(canvas, mValue);
            drawContentSquareLineToLeft(canvas, mValue);
        } else if (mValue > 75) {
            if (mValue > 100)
                mValue = 100;
            drawLineToTop(canvas, mValue);
            drawContentSquareLineToTop(canvas, mValue);

        }

        if (mValue <= 16) {
            drawLine(canvas, 1, mValue);
        } else if (mValue > 16 && mValue <= 32) {
            drawLine(canvas, 2, mValue);
        } else if (mValue > 32 && mValue <= 48) {
            drawLine(canvas, 3, mValue);
        } else if (mValue > 48 && mValue <= 64) {
            drawLine(canvas, 4, mValue);
        } else if (mValue > 64 && mValue <= 80) {
            drawLine(canvas, 5, mValue);
        } else if (mValue > 80) {
            drawLine(canvas, 6, mValue);
        }


        canvas.restore();


    }

    private void drawLineToRight(Canvas canvas, int value) {

        if (value <= 20) {

            canvas.drawLine(rectFSquareBG.left + cornerRadius, rectFSquareBG.top,
                    rectFSquareBG.width() * value / 20.f - cornerRadius, rectFSquareBG.top, mPaint);


        } else {
            canvas.drawLine(rectFSquareBG.left + cornerRadius, rectFSquareBG.top,
                    rectFSquareBG.right - cornerRadius, rectFSquareBG.top, mPaint);
            rectFTopRight.top = mPadding;
            rectFTopRight.left = mWidth - mPadding - cornerRadius * 2;
            rectFTopRight.bottom = mPadding + cornerRadius * 2;
            rectFTopRight.right = mWidth - mPadding;
            canvas.drawArc(rectFTopRight, -90, 90.0f / 5 * (value - 20), false, mPaint);

        }

    }

    private void drawLineToBottom(Canvas canvas, int value) {
        drawLineToRight(canvas, 25);
        if (value <= 45) {

            canvas.drawLine(rectFSquareBG.right, rectFSquareBG.top + cornerRadius,
                    rectFSquareBG.right, rectFSquareBG.height() * (value - 25) / 20.f, mPaint);
        } else {
            canvas.drawLine(rectFSquareBG.right, rectFSquareBG.top + cornerRadius,
                    rectFSquareBG.right, rectFSquareBG.bottom - cornerRadius, mPaint);

            rectFBottomRight.top = mWidth - mPadding - cornerRadius * 2;
            rectFBottomRight.left = mWidth - mPadding - cornerRadius * 2;
            rectFBottomRight.bottom = mWidth - mPadding;
            rectFBottomRight.right = mWidth - mPadding;
            canvas.drawArc(rectFBottomRight, 0, 90.0f / 5 * (value - 45), false, mPaint);
        }
    }

    private void drawLineToLeft(Canvas canvas, int value) {
        drawLineToBottom(canvas, 50);

        if (value <= 70) {

            canvas.drawLine(rectFSquareBG.right - cornerRadius,
                    rectFSquareBG.bottom,
                    rectFSquareBG.left + rectFSquareBG.width() - rectFSquareBG.width() * (value - 50) / 20.f,
                    rectFSquareBG.bottom, mPaint);

        } else {
            canvas.drawLine(rectFSquareBG.right - cornerRadius,
                    rectFSquareBG.bottom,
                    rectFSquareBG.left + cornerRadius,
                    rectFSquareBG.bottom, mPaint);


            rectFBottomLeft.top = mWidth - mPadding - cornerRadius * 2;
            rectFBottomLeft.left = mPadding;
            rectFBottomLeft.bottom = mWidth - mPadding;
            rectFBottomLeft.right = mPadding + cornerRadius * 2;
            canvas.drawArc(rectFBottomLeft, 90, 90.f / 5 * (value - 70), false, mPaint);
        }
    }

    private void drawLineToTop(Canvas canvas, int value) {
        drawLineToLeft(canvas, 75);
        if (value <= 95) {
            canvas.drawLine(rectFSquareBG.left, rectFSquareBG.bottom - cornerRadius,
                    rectFSquareBG.left,
                    rectFSquareBG.top +
                            rectFSquareBG.height() - rectFSquareBG.height() * (value - 75) / 20.f,
                    mPaint);
        } else {

            canvas.drawLine(rectFSquareBG.left, rectFSquareBG.bottom - cornerRadius,
                    rectFSquareBG.left,
                    rectFSquareBG.top + cornerRadius,
                    mPaint);


            rectFTopLeft.top = mPadding;
            rectFTopLeft.left = mPadding;
            rectFTopLeft.bottom = mPadding + cornerRadius * 2;
            rectFTopLeft.right = mPadding + cornerRadius * 2;
            canvas.drawArc(rectFTopLeft, 180, 90.f / 5 * (value - 95), false, mPaint);
        }
    }


    private void drawContentSquareLineToRight(Canvas canvas, int value) {
        canvas.drawLine(rectFSquare.left, rectFSquare.top,
                rectFSquare.left + rectFSquare.width() * (value) / 25.f, rectFSquare.top, mPaint);
    }

    private void drawContentSquareLineToBottom(Canvas canvas, int value) {
        drawContentSquareLineToRight(canvas, 25);
        canvas.drawLine(rectFSquare.right,
                rectFSquare.top,
                rectFSquare.right,
                rectFSquare.top + rectFSquare.height() * (value - 25) / 25.f, mPaint);
    }

    private void drawContentSquareLineToLeft(Canvas canvas, int value) {
        drawContentSquareLineToBottom(canvas, 50);
        canvas.drawLine(rectFSquare.right,
                rectFSquare.bottom,
                rectFSquare.left + rectFSquare.width() - rectFSquare.width() * (value - 50) / 25.f,
                rectFSquare.bottom, mPaint);
    }

    private void drawContentSquareLineToTop(Canvas canvas, int value) {
        drawContentSquareLineToLeft(canvas, 75);
        canvas.drawLine(rectFSquare.left, rectFSquare.bottom,
                rectFSquare.left,
                rectFSquare.top +
                        rectFSquare.height() - rectFSquare.height() * (value - 75) / 25.f,
                mPaint);

    }


    private void drawContent(Canvas canvas, int step) {


        if (step == 1) {

            marggingSquareH = 0f + mAnimatedValue * (mWidth / 2 - cornerRadius) / 0.25f;
            marggingSquareV = 0f;
            marggingLineH = 0f + mAnimatedValue * (mWidth / 2 - cornerRadius) / 0.25f;
            marggingLineV = 0f;


        } else if (step == 2) {

            marggingSquareH = mWidth / 2 - cornerRadius;
            marggingSquareV = 0 + (mWidth / 2 - cornerRadius) / 0.25f * (mAnimatedValue - 0.25f);
            marggingLineH = mWidth / 2 - cornerRadius;
            marggingLineV = 0 + (-mWidth / 2 + cornerRadius) / 0.25f * (mAnimatedValue - 0.25f);


        } else if (step == 3) {
            marggingSquareH = (mWidth / 2 - cornerRadius) - (mWidth / 2 - cornerRadius) / 0.25f * (mAnimatedValue - 0.5f);
            marggingSquareV = mWidth / 2 - cornerRadius;
            marggingLineH = (mWidth / 2 - cornerRadius) - (mWidth / 2 - cornerRadius) / 0.25f * (mAnimatedValue - 0.5f);
            marggingLineV = -mWidth / 2 + cornerRadius;


        } else if (step == 4) {
            marggingSquareH = 0;
            marggingSquareV = (mWidth / 2 - cornerRadius) - (mWidth / 2 - cornerRadius) / 0.25f * (mAnimatedValue - 0.75f);
            marggingLineH = 0;
            marggingLineV = (-mWidth / 2 + cornerRadius) - (-mWidth / 2 + cornerRadius) / 0.25f * (mAnimatedValue - 0.75f);
        }
        if (mValue == 100) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setAlpha(100);
            rectFSquare.top = mPadding + cornerRadius + marggingSquareV;
            rectFSquare.left = mPadding + cornerRadius + marggingSquareH;
            rectFSquare.bottom = mWidth / 2 - mPadding + marggingSquareV;
            rectFSquare.right = mWidth / 2 - mPadding + marggingSquareH;
            canvas.drawRect(rectFSquare, mPaint);
        }
        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setColor(Color.WHITE);
        mPaint.setAlpha(255);
    }


    private void drawShortLine(Canvas canvas, float line_width_short, int value, int positon) {

        positon = positon - 1;


        canvas.drawLine(mWidth / 2 + mPadding + cornerRadius / 2 - marggingLineH,
                mPadding + cornerRadius + cornerRadius - marggingLineV + rectFSquare.height() / 3 * positon,
                mWidth / 2 + mPadding + cornerRadius / 2 - marggingLineH + line_width_short / 16.0f * value,
                mPadding + cornerRadius + cornerRadius - marggingLineV + rectFSquare.height() / 3 * positon, mPaint);


    }


    private void drawlongLine(Canvas canvas, float line_width_long, int value, int positon) {
        positon = positon - 4;
        canvas.drawLine(mPadding + cornerRadius,
                mPadding + cornerRadius + rectFSquare.height() / 3 * positon + mWidth / 2 + marggingLineV,
                mPadding + cornerRadius + line_width_long / 16.0f * (value),
                mPadding + cornerRadius + rectFSquare.height() / 3 * positon + mWidth / 2 + marggingLineV, mPaint);


    }


    private void drawLine(Canvas canvas, int count, int mValue) {


        float line_width_short = (mWidth - mPadding - cornerRadius - marggingLineH) -
                (mWidth / 2 + mPadding + cornerRadius / 2 - marggingLineH);
        float line_width_long = (mWidth - mPadding - cornerRadius) -
                (mPadding + cornerRadius);

        if (count == 1) {
            drawShortLine(canvas, line_width_short, mValue, 1);
        } else if (count == 2) {
            drawShortLine(canvas, line_width_short, 16, 1);
            drawShortLine(canvas, line_width_short, mValue - 16, 2);


        } else if (count == 3) {
            drawShortLine(canvas, line_width_short, 16, 1);
            drawShortLine(canvas, line_width_short, 16, 2);
            drawShortLine(canvas, line_width_short, mValue - 32, 3);


        } else if (count == 4) {


            drawShortLine(canvas, line_width_short, 16, 1);
            drawShortLine(canvas, line_width_short, 16, 2);
            drawShortLine(canvas, line_width_short, 16, 3);
            drawlongLine(canvas, line_width_long, mValue - 48, 4);

        } else if (count == 5) {


            drawShortLine(canvas, line_width_short, 16, 1);
            drawShortLine(canvas, line_width_short, 16, 2);
            drawShortLine(canvas, line_width_short, 16, 3);
            drawlongLine(canvas, line_width_long, 16, 4);
            drawlongLine(canvas, line_width_long, mValue - 64, 5);


        } else if (count == 6) {
            drawShortLine(canvas, line_width_short, 16, 1);
            drawShortLine(canvas, line_width_short, 16, 2);
            drawShortLine(canvas, line_width_short, 16, 3);
            drawlongLine(canvas, line_width_long, 16, 4);
            drawlongLine(canvas, line_width_long, 16, 5);
            canvas.drawLine(mPadding + cornerRadius,
                    mPadding + cornerRadius + rectFSquare.height() / 3 * 2 + mWidth / 2 + marggingLineV,
                    mPadding + cornerRadius + line_width_long / 20.0f * (mValue - 80),
                    mPadding + cornerRadius + rectFSquare.height() / 3 * 2 + mWidth / 2 + marggingLineV, mPaint);

        }


    }




    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();


    }


    private int mStep = 1;
    float mAnimatedValue = 0f;


    @Override
    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            mAnimatedValue = 0f;
            mStep = 1;
            invalidate();
        } else {
            mAnimatedValue = 0f;
            mStep = 1;
            mValue = 100;
            invalidate();
        }
    }


    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        if (mAnimatedValue > 0 && mAnimatedValue <= 0.25f) {
            mStep = 1;
        } else if (mAnimatedValue > 0.25f && mAnimatedValue <= 0.5f) {
            mStep = 2;
        } else if (mAnimatedValue > 0.5f && mAnimatedValue <= 0.75f) {
            mStep = 3;
        } else if (mAnimatedValue > 0.75f && mAnimatedValue <= 1.0f) {
            mStep = 4;
        }

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

    } @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }



}
