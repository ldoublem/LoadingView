package com.ldoublem.loadingView.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lumingmin on 16/6/28.
 */

public class LVBlock extends View {
    private Paint mPaint, mPaintShadow, mPaintLeft, mPaintRight;
    private float mWidth = 0f;
    float moveYtoCenter = 0f;
    float rhomboidsX = 0f;
    float rhomboidsY = 0f;

    public LVBlock(Context context) {
        this(context, null);
    }

    public LVBlock(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVBlock(Context context, AttributeSet attrs, int defStyleAttr) {
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
        rhomboidsX = (float) (3 * mWidth / 16 / Math.sqrt(3));
        rhomboidsY = mWidth / 16;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (!mShadow) {
            moveYtoCenter = mWidth / 4;
        } else {
            moveYtoCenter =0;
        }
        if (mAnimatedValue >= 0 && mAnimatedValue < (1.0f / 3)) {
            drawStep1(canvas, mAnimatedValue);
            if (mShadow)
                drawShadowStep1(canvas, mAnimatedValue);
        } else if (mAnimatedValue >= (1.0f / 3) && mAnimatedValue < (1.0f / 3 * 2)) {
            drawStep2(canvas, mAnimatedValue);
            if (mShadow)
                drawShadowStep2(canvas, mAnimatedValue);
        } else if (mAnimatedValue >= (1.0f / 3 * 2) && mAnimatedValue <= 1.0f) {
            drawStep3(canvas, mAnimatedValue);
            if (mShadow)
                drawShadowStep3(canvas, mAnimatedValue);
        }
        canvas.restore();

    }

    private void drawStep1(Canvas canvas, float time) {


        float moveX = rhomboidsX / 2.0f * time / (1.0f / 3);
        float moveY = rhomboidsY / 2.0f * time / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - moveX, rhomboidsY * 12 - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 11 - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - moveX, mWidth / 4 * 3 - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 13 - moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - moveX, rhomboidsY * 12 - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 13 - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 13 - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 - moveX, rhomboidsY * 12 - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + moveX, mWidth / 4 * 3 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 12 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + moveX, mWidth / 4 * 3 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 12 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 12 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX + rhomboidsX + moveX, mWidth / 4 * 3 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + moveX, mWidth / 4 * 3 + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - moveX, rhomboidsY * 11 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);


    }


    private void drawStep2(Canvas canvas, float time) {




        float moveX = rhomboidsX * (time - 1.0f / 3) / (1.0f / 3);
        float moveY = rhomboidsY * (time - 1.0f / 3) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 11 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);

        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 11 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);

        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);

    }


    private void drawStep3(Canvas canvas, float time) {




        float moveX = rhomboidsX / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3);
        float moveY = rhomboidsY / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 11 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, mWidth / 4 * 3 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 11 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);

        p.reset();
        p.moveTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintRight);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaint);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.lineTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY - mWidth / 2 + rhomboidsY * 2 + moveYtoCenter);
        p.close();
        canvas.drawPath(p, mPaintLeft);


    }


    private void drawShadowStep1(Canvas canvas, float time) {




        float moveX = rhomboidsX / 2.0f * time / (1.0f / 3);
        float moveY = rhomboidsY / 2.0f * time / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - moveX, rhomboidsY * 12 - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 11 - moveY);
        p.lineTo(mWidth / 2 - moveX, mWidth / 4 * 3 - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - moveX, rhomboidsY * 13 - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 + rhomboidsX + moveX, mWidth / 4 * 3 - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 12 + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 11 + moveY);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + moveX, mWidth / 4 * 3 + moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + moveX, rhomboidsY * 13 + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - moveX, rhomboidsY * 11 + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 + rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);
    }


    private void drawShadowStep2(Canvas canvas, float time) {

        float moveX = rhomboidsX * (time - 1.0f / 3) / (1.0f / 3);
        float moveY = rhomboidsY * (time - 1.0f / 3) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 11 - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 11 + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f);
        p.close();
        canvas.drawPath(p, mPaintShadow);
    }


    private void drawShadowStep3(Canvas canvas, float time) {



        float moveX = rhomboidsX / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3);
        float moveY = rhomboidsY / 2.0f * (time - 1.0f / 3 * 2) / (1.0f / 3);

        Path p = new Path();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 12 - rhomboidsY / 2.0f - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 11 - rhomboidsY / 2.0f - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX / 2.0f + rhomboidsX + moveX, mWidth / 4 * 3 - rhomboidsY / 2.0f - rhomboidsY + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX - rhomboidsX / 2.0f + rhomboidsX + moveX, rhomboidsY * 13 - rhomboidsY / 2.0f - rhomboidsY + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 12 - rhomboidsY + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 11 - rhomboidsY + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX / 2.0f + moveX, mWidth / 4 * 3 - rhomboidsY + rhomboidsY / 2.0f + moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX / 2.0f + moveX, rhomboidsY * 13 - rhomboidsY + rhomboidsY / 2.0f + moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 12 + rhomboidsY / 2.0f + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 11 + rhomboidsY / 2.0f + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, mWidth / 4 * 3 + rhomboidsY / 2.0f + rhomboidsY - moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX + rhomboidsX + rhomboidsX / 2.0f - rhomboidsX - moveX, rhomboidsY * 13 + rhomboidsY / 2.0f + rhomboidsY - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


        p.reset();
        p.moveTo(mWidth / 2 - rhomboidsX * 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 12 + rhomboidsY - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 - rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 11 + rhomboidsY - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 + rhomboidsX - rhomboidsX / 2.0f - moveX, mWidth / 4 * 3 + rhomboidsY - rhomboidsY / 2.0f - moveY);
        p.lineTo(mWidth / 2 + -rhomboidsX + rhomboidsX - rhomboidsX / 2.0f - moveX, rhomboidsY * 13 + rhomboidsY - rhomboidsY / 2.0f - moveY);
        p.close();
        canvas.drawPath(p, mPaintShadow);


    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.rgb(247, 202, 42));
        mPaint.setStrokeWidth(1);
        mPaintShadow = new Paint();
        mPaintShadow.setAntiAlias(true);
        mPaintShadow.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintShadow.setColor(Color.rgb(0, 0, 0));
        mPaintShadow.setStrokeWidth(1f);
        mPaintLeft = new Paint();
        mPaintLeft.setAntiAlias(true);
        mPaintLeft.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintLeft.setColor(Color.rgb(227, 144, 11));
        mPaintLeft.setStrokeWidth(1);
        mPaintRight = new Paint();
        mPaintRight.setAntiAlias(true);
        mPaintRight.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaintRight.setColor(Color.rgb(188, 91, 26));
        mPaintRight.setStrokeWidth(1);
    }


    private ValueAnimator valueAnimator;
    float mAnimatedValue = 0;

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 500);
    }


    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            mAnimatedValue = 0f;
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

    private boolean mShadow = true;

    public void isShadow(boolean show) {

        this.mShadow = show;
        invalidate();
    }


}
