package com.ldoublem.loadingView.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.ldoublem.loadingView.R;

/**
 * Created by lumingmin on 16/7/7.
 */

public class LVComputer extends View {

    Paint mPaint;
    float mHigth = 0f;
    float mWidth = 0f;
    Bitmap ios;
    Bitmap android;
    Shader mShader;
    RectF rectBg = new RectF();
    RectF rectSreeen = new RectF();
    RectF rectSreeenWithin = new RectF();
    RectF rectSreeenShow = new RectF();

    RectF rectKeyboard = new RectF();
    RectF rectKeyboardShadow = new RectF();
    Path pathKeyboardTouch = new Path();
    Path pathKeyboardBottom = new Path();
    Path pathComputerShadow = new Path();
    Path pathScreenReflective = new Path();


    int colorScreenWithin = Color.rgb(0, 0, 0);
    int colorScreenShow = Color.rgb(15, 15, 15);
    int colorCamera = Color.rgb(80, 81, 82);
    int colorCameraCenter = Color.rgb(15, 15, 15);
    int colorScreenReflective = Color.argb(10, 255, 255, 255);
    int colorKeyboard = Color.rgb(209, 211, 212);
    int colorKeyboardShadow = Color.rgb(188, 190, 192);
    int coloKeyboardTouch = Color.rgb(165, 165, 165);


    public LVComputer(Context context) {
        this(context, null);
    }

    public LVComputer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVComputer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHigth = getMeasuredHeight();
        mWidth = getMeasuredWidth();

    }


    float mPadding = 2;

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private void drawScreen(Canvas canvas) {
        rectBg.top = mPadding;
        rectBg.left = mPadding;
        rectBg.right = mWidth - mPadding;
        rectBg.bottom = mHigth - mPadding;
        rectSreeen.top = rectBg.top;
        rectSreeen.left = rectBg.left + rectBg.width() / 6;
        rectSreeen.right = rectBg.right - rectBg.width() / 6;
        rectSreeen.bottom = rectBg.bottom - 20;
        mPaint.setColor(Color.rgb(165, 165, 165));
        canvas.drawRoundRect(rectSreeen, rectBg.width() / 6 / 5f, rectBg.width() / 6 / 5f, mPaint);
    }


    private void drawScreenWithin(Canvas canvas) {
        rectSreeenWithin.top = rectSreeen.top + 2;
        rectSreeenWithin.bottom = rectSreeen.bottom - 2;
        rectSreeenWithin.left = rectSreeen.left + 2;
        rectSreeenWithin.right = rectSreeen.right - 2;
        mPaint.setColor(colorScreenWithin);
        canvas.drawRoundRect(rectSreeenWithin, rectBg.width() / 6 / 5f - 2, rectBg.width() / 6 / 5f - 2, mPaint);

    }

    private void drawScreenShow(Canvas canvas) {

        rectSreeenShow.top = rectSreeenWithin.top + rectBg.width() / 6 / 6f * 1.1f;
        rectSreeenShow.bottom = rectSreeenWithin.bottom - rectBg.width() / 6f / 6f - 15;
        rectSreeenShow.left = rectSreeenWithin.left + rectBg.width() / 6 / 6f;
        rectSreeenShow.right = rectSreeenWithin.right - rectBg.width() / 6 / 6f;

        mPaint.setColor(colorScreenShow);

        canvas.drawRect(rectSreeenShow, mPaint);


    }

    private void drawCamera(Canvas canvas) {
        mPaint.setColor(colorCamera);
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2 + 4, 3, mPaint);
        mPaint.setColor(colorCameraCenter);
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2 + 4, 1.5f, mPaint);

    }


    private void drawContent(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        ios = setBitmapSize(R.mipmap.apple, (int) (rectSreeen.width() / 10));
        canvas.drawBitmap(ios, rectSreeenShow.centerX() - ios.getWidth() - 5,
                rectSreeenShow.centerY() - ios.getHeight() / 2, mPaint);
        android = setBitmapSize(R.mipmap.android, (int) (rectSreeenShow.width() / 11));
        canvas.drawBitmap(android, rectSreeenShow.centerX() + 5,
                rectSreeenShow.centerY() - android.getHeight() / 2, mPaint);

    }


    private void drawScreenReflective(Canvas canvas) {
        pathScreenReflective.reset();
        pathScreenReflective.moveTo(rectSreeen.left + rectSreeen.width() / 10f * 6f, rectSreeen.top);
        pathScreenReflective.lineTo(rectSreeen.right - rectSreeen.width() / 10f, rectSreeen.bottom);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.bottom);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.top);
        pathScreenReflective.close();
        mPaint.setColor(colorScreenReflective);
        canvas.drawPath(pathScreenReflective, mPaint);

    }


    private void drawKeyboard(Canvas canvas) {
        rectKeyboard.top = rectSreeenWithin.bottom - rectBg.width() / 6f / 8f;
        rectKeyboard.bottom = rectSreeen.bottom;
        rectKeyboard.left = rectBg.left + rectBg.width() / 6f / 3f;
        rectKeyboard.right = rectBg.right - rectBg.width() / 6f / 3f;
        mPaint.setColor(colorKeyboard);
        canvas.drawRect(rectKeyboard, mPaint);
    }


    private void drawKeyboardShadow(Canvas canvas) {
        rectKeyboardShadow.top = rectKeyboard.top + rectKeyboard.height() / 3;
        rectKeyboardShadow.bottom = rectKeyboard.bottom;
        rectKeyboardShadow.left = rectKeyboard.left + rectKeyboard.height() / 3 * 2;
        rectKeyboardShadow.right = rectKeyboard.right - rectKeyboard.height() / 3 * 2;
        mPaint.setColor(colorKeyboardShadow);
        canvas.drawRect(rectKeyboardShadow, mPaint);
    }


    private void drawKeyboardTouch(Canvas canvas) {
        pathKeyboardTouch.reset();
        pathKeyboardTouch.moveTo((rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 1.2f),
                rectKeyboard.top);
        pathKeyboardTouch.quadTo((rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 1.2f),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f
                , (rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 1.2f) + rectKeyboard.height()
                , rectKeyboard.top + rectKeyboard.height() / 3f * 2f


        );
        pathKeyboardTouch.lineTo((rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 1.2f) - rectKeyboard.height(),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f);


        pathKeyboardTouch.quadTo(
                (rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 1.2f),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f,


                (rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 1.2f),
                rectKeyboard.top);


        pathKeyboardTouch.close();
        mPaint.setColor(coloKeyboardTouch);
        canvas.drawPath(pathKeyboardTouch, mPaint);
    }


    private void drawKeyboardBottom(Canvas canvas) {
        pathKeyboardBottom.reset();

        pathKeyboardBottom.moveTo(rectKeyboard.left, rectKeyboard.bottom);
        pathKeyboardBottom.quadTo(
                rectKeyboard.left, rectKeyboard.bottom + rectKeyboard.height() / 2f,
                rectSreeen.left, rectKeyboard.bottom + rectKeyboard.height() / 2f
        );
        pathKeyboardBottom.lineTo(rectSreeen.right, rectKeyboard.bottom + rectKeyboard.height() / 2f);
        pathKeyboardBottom.quadTo(
                rectKeyboard.right, rectKeyboard.bottom + rectKeyboard.height() / 2f,
                rectKeyboard.right, rectKeyboard.bottom
        );
        pathKeyboardBottom.close();

        canvas.drawPath(pathKeyboardBottom, mPaint);
    }


    private void drawComputerShadow(Canvas canvas) {
        pathComputerShadow.reset();

        pathComputerShadow.moveTo(rectSreeen.left, rectKeyboard.bottom + rectKeyboard.height() / 2f);
        pathComputerShadow.quadTo(
                rectKeyboard.left, rectKeyboard.bottom + rectKeyboard.height() / 2f
                , rectKeyboard.left, rectBg.bottom + mPadding
        );
        pathComputerShadow.lineTo(rectKeyboard.right, rectBg.bottom + mPadding);
        pathComputerShadow.quadTo(
                rectKeyboard.right, rectKeyboard.bottom + rectKeyboard.height() / 2f
                ,
                rectSreeen.right, rectKeyboard.bottom + rectKeyboard.height() / 2f
        );
        pathComputerShadow.close();


        mShader = new LinearGradient(rectKeyboard.centerX(), rectKeyboard.bottom + rectKeyboard.height() / 2f,
                rectKeyboard.centerX(), rectBg.bottom + mPadding,
                new int[]{
                        Color.rgb(229, 230, 231),
                        Color.rgb(245, 245, 245)
                }, new float[]{0f, 1f},
                Shader.TileMode.CLAMP);
        mPaint.setShader(mShader);
        mPaint.setColor(Color.rgb(229, 230, 231));
        canvas.drawPath(pathComputerShadow, mPaint);
        mPaint.setShader(null);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        if (mAnimatedValue >= 0) {
            drawScreen(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11) {
            drawScreenWithin(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 2) {

            drawScreenShow(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 3) {

            drawCamera(canvas);
        }
        if (mAnimatedValue >= 1.0f / 11 * 4) {


            drawScreenReflective(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 5) {

            drawKeyboard(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 6) {

            drawKeyboardShadow(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 7) {

            drawKeyboardTouch(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 8) {

            drawKeyboardBottom(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 9) {

            drawComputerShadow(canvas);

        }
        if (mAnimatedValue >= 1.0f / 11 * 10 && mAnimatedValue <= 1.0f / 11 * 11) {
            drawContent(canvas);
        }
        canvas.restore();
    }

    protected void initPaint() {
        mPadding = dip2px(1f);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    protected Bitmap setBitmapSize(int iconId, int w) {


        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(), iconId);
        float s = w * 1.0f / bitmap.getWidth();
        bitmap = Bitmap.createScaledBitmap(bitmap, (int) (bitmap.getWidth() * s), (int) (bitmap.getHeight() * s), true);
        return bitmap;

    }

    private Bitmap setBitmapRotation(Bitmap bm, final int orientationDegree) {

        Matrix m = new Matrix();
        m.setRotate(orientationDegree, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);

        try {
            Bitmap bm1 = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), m, true);
            return bm1;
        } catch (OutOfMemoryError ex) {
        }
        return null;
    }


    public void startAnim(int time) {
        stopAnim();
        startViewAnim(0f, 1f, time);
    }

    private ValueAnimator valueAnimator;
    float mAnimatedValue = 1.0f;

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            valueAnimator.end();
            mAnimatedValue = 1.0f;
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);//ValueAnimator.INFINITE);//无限循环
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
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
}
