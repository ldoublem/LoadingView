package com.ldoublem.loadingView.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lumingmin on 16/7/1.
 */

public class LVFunnyBar extends View {

    private Paint mPaintLeftTop, mPaintLeftLeft, mPaintLeftRight;
    private Paint mPaintRightTop, mPaintRightLeft, mPaintRightRight;

    private int mWidth = 0;

    private int mHeight = 0;
//    int onAnimationRepeatFlag = 0;

    public LVFunnyBar(Context context) {
        this(context, null);
    }

    public LVFunnyBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVFunnyBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        float height = MeasureSpec.getSize(heightMeasureSpec);
        float width = MeasureSpec.getSize(widthMeasureSpec);
        height = (float) (width / Math.sqrt(3));
        setMeasuredDimension((int) (width), (int) (height));
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        canvas.translate(0,mHeight/2);
        canvas.save();
        float wspace = mWidth / 8f;
        float hspace = mHeight / 8f;
        Path p = new Path();

        float leftLong = 0.1f;
        float rightLong = 0.1f;


        for (int i = 0; i < 3; i++) {


            leftLong = mAnimatedValue * (1 + i / 4f);
            if (leftLong > 1f)
                leftLong = 1f;
            float wlong = mWidth / 2 * leftLong - wspace / 2;
            float hlong = mHeight / 2 * leftLong - hspace / 2;

            if (wlong < wspace / 8 / 8 / 2) {
                wlong = wspace / 8 / 8 / 2;
            }
            if (hlong < hspace / 8 / 8 / 2) {
                hlong = wspace / 8 / 8 / 2;
            }

            p.reset();
            p.moveTo((i + 0.5f) * wspace, mHeight / 2 + (i * hspace));
            p.lineTo((i + 1f) * wspace + wlong, mHeight / 2 - hspace / 2f + (i * hspace) - hlong);
            p.lineTo((i + 1.5f) * wspace + wlong, mHeight / 2 + (i * hspace) - hlong);
            p.lineTo((i + 1f) * wspace, mHeight / 2 + hspace / 2f + (i * hspace));
            p.close();

            canvas.drawPath(p, mPaintLeftTop);
            p.reset();
            p.moveTo((i + 0.5f) * wspace, mHeight / 2 + (i * hspace));
            p.lineTo((i + 1f) * wspace, mHeight / 2 + hspace / 2f + (i * hspace));
            p.lineTo((i + 1f) * wspace, mHeight / 2 + hspace / 2f + (i * hspace) + hspace);
            p.lineTo((i + 0.5f) * wspace, mHeight / 2 + (i * hspace) + hspace);
            p.close();
            canvas.drawPath(p, mPaintLeftLeft);
            p.reset();
            p.moveTo((i + 1.5f) * wspace + wlong, mHeight / 2 + (i * hspace) - hlong);
            p.lineTo((i + 1f) * wspace, mHeight / 2 + hspace / 2f + (i * hspace));
            p.lineTo((i + 1f) * wspace, mHeight / 2 + hspace / 2f + (i * hspace) + hspace);
            p.lineTo((i + 1.5f) * wspace + wlong, mHeight / 2 + (i * hspace) + hspace - hlong);
            p.close();
            canvas.drawPath(p, mPaintLeftRight);

            int rightPosition = i;
            p.reset();
            p.moveTo(mWidth - (rightPosition + 1.5f) * wspace - wlong, mHeight / 2 + (rightPosition * hspace) - hlong);
            p.lineTo(mWidth - (rightPosition + 1f) * wspace - wlong, mHeight / 2 - hspace / 2f + (rightPosition * hspace) - hlong);
            p.lineTo(mWidth - (rightPosition + 0.5f) * wspace, mHeight / 2 + (rightPosition * hspace));
            p.lineTo(mWidth - (rightPosition + 1f) * wspace, mHeight / 2 + hspace / 2f + (rightPosition * hspace));
            p.close();
            canvas.drawPath(p, mPaintRightTop);


            p.reset();
            p.moveTo(mWidth - (rightPosition + 1.5f) * wspace - wlong, mHeight / 2 + (rightPosition * hspace) - hlong);
            p.lineTo(mWidth - (rightPosition + 1f) * wspace, mHeight / 2 + hspace / 2f + (rightPosition * hspace));
            p.lineTo(mWidth - (rightPosition + 1f) * wspace, mHeight / 2 + hspace / 2f + (rightPosition * hspace) + hspace);
            p.lineTo(mWidth - (rightPosition + 1.5f) * wspace - wlong, mHeight / 2 + (rightPosition * hspace) + hspace - hlong);
            p.close();
            canvas.drawPath(p, mPaintRightLeft);
            p.reset();
            p.moveTo(mWidth - (rightPosition + 0.5f) * wspace, mHeight / 2 + (rightPosition * hspace));
            p.lineTo(mWidth - (rightPosition + 1f) * wspace, mHeight / 2 + hspace / 2f + (rightPosition * hspace));
            p.lineTo(mWidth - (rightPosition + 1f) * wspace, mHeight / 2 + hspace / 2f + (rightPosition * hspace) + hspace);
            p.lineTo(mWidth - (rightPosition + 0.5f) * wspace, mHeight / 2 + (rightPosition * hspace) + hspace);

            p.close();
            canvas.drawPath(p, mPaintRightRight);

        }

        canvas.restore();

    }

    private void drawFire(Canvas canvas)
    {

        RectF rectFire=new RectF();








    }



    private void initPaint() {
        mPaintLeftTop = new Paint();
        mPaintLeftTop.setAntiAlias(true);
        mPaintLeftTop.setStyle(Paint.Style.FILL);
        mPaintLeftTop.setColor(Color.rgb(234, 167, 107));


        mPaintLeftLeft = new Paint();
        mPaintLeftLeft.setAntiAlias(true);
        mPaintLeftLeft.setStyle(Paint.Style.FILL);
        mPaintLeftLeft.setColor(Color.rgb(174, 113, 94));


        mPaintLeftRight = new Paint();
        mPaintLeftRight.setAntiAlias(true);
        mPaintLeftRight.setStyle(Paint.Style.FILL);
        mPaintLeftRight.setColor(Color.rgb(138, 97, 85));


        mPaintRightTop = new Paint();
        mPaintRightTop.setAntiAlias(true);
        mPaintRightTop.setStyle(Paint.Style.FILL);
//        mPaintRightTop.setColor(Color.rgb(255, 0, 69));
        mPaintRightTop.setColor(Color.rgb(234, 167, 107));


        mPaintRightLeft = new Paint();
        mPaintRightLeft.setAntiAlias(true);
        mPaintRightLeft.setStyle(Paint.Style.FILL);
//        mPaintRightLeft.setColor(Color.rgb(214, 0, 63));
        mPaintRightLeft.setColor(Color.rgb(174, 113, 94));


        mPaintRightRight = new Paint();
        mPaintRightRight.setAntiAlias(true);
        mPaintRightRight.setStyle(Paint.Style.FILL);
//        mPaintRightRight.setColor(Color.rgb(175, 4, 49));
        mPaintRightRight.setColor(Color.rgb(138, 97, 85));

    }


    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 1000);
    }

    private ValueAnimator valueAnimator;
    private float mAnimatedValue = 1f;

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();

            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            mAnimatedValue = 1f;
//            onAnimationRepeatFlag = 0;
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
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
//                onAnimationRepeatFlag = onAnimationRepeatFlag + 1;
            }
        });
        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }

        return valueAnimator;
    }


}
