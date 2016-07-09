package com.ldoublem.loadingView.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ldoublem.loadingView.R;

/**
 * Created by lumingmin on 16/7/9.
 */

public class LVComputerIpad extends LVComputer {

    int colorHome = Color.rgb(125, 130, 135);

    public LVComputerIpad(Context context) {
        this(context, null);
    }

    public LVComputerIpad(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVComputerIpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void drawScreen(Canvas canvas) {
        rectBg.top = 1;
        rectBg.left = 1;
        rectBg.right = mWidth - 1;
        rectBg.bottom = mHigth - 1;
        rectSreeen.top = rectBg.top;
        rectSreeen.left = rectBg.left;
        rectSreeen.right = rectBg.right;
        rectSreeen.bottom = rectBg.bottom;
        mPaint.setColor(Color.rgb(165, 165, 165));
        canvas.drawRoundRect(rectSreeen, rectSreeen.width() / 12f, rectSreeen.width() / 12f, mPaint);

    }

    private void drawScreenWithin(Canvas canvas) {

        rectSreeenWithin.top = rectSreeen.top + 1;
        rectSreeenWithin.left = rectSreeen.left + 1;
        rectSreeenWithin.right = rectSreeen.right - 1;
        rectSreeenWithin.bottom = rectSreeen.bottom - 1;
        mPaint.setColor(colorScreenWithin);
        canvas.drawRoundRect(rectSreeenWithin, rectSreeen.width() / 12f - 1, rectSreeen.width() / 12f - 1, mPaint);


    }

    private void drawContent(Canvas canvas) {
        mPaint.setColor(Color.WHITE);
        ios = setBitmapSize(R.mipmap.apple, (int) (rectSreeen.width() / 5));
        canvas.drawBitmap(ios, rectSreeenShow.centerX() - ios.getWidth() - 5,
                rectSreeenShow.centerY() - ios.getHeight() / 2, mPaint);
        android = setBitmapSize(R.mipmap.android, (int) (rectSreeenShow.width() / 5));
        canvas.drawBitmap(android, rectSreeenShow.centerX() + 5,
                rectSreeenShow.centerY() - android.getHeight() / 2, mPaint);

    }

    private void drawCamera(Canvas canvas) {
        mPaint.setColor(colorCamera);
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2, 2f, mPaint);
        mPaint.setColor(colorCameraCenter);
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2, 1f, mPaint);
        mPaint.setColor(colorHome);
        canvas.drawCircle(rectSreeen.centerX(),
                rectSreeenShow.bottom + (rectSreeen.width() / 12f - 1) * 1.5f / 2f
                , (rectSreeen.width() / 12f - 1) / 2.5f, mPaint);
        mPaint.setColor(colorScreenWithin);
        canvas.drawCircle(rectSreeen.centerX(),
                rectSreeenShow.bottom + (rectSreeen.width() / 12f - 1) * 1.5f / 2f
                , (rectSreeen.width() / 12f - 1) / 2.5f - 0.6f, mPaint);

    }


    private void drawScreenShow(Canvas canvas) {

        rectSreeenShow.top = rectSreeenWithin.top + (rectSreeen.width() / 12f - 1) * 1.5f;
        rectSreeenShow.bottom = rectSreeenWithin.bottom - (rectSreeen.width() / 12f - 1) * 1.5f;
        rectSreeenShow.right = rectSreeenWithin.right - (rectSreeen.width() / 12f - 1) * 1f;
        rectSreeenShow.left = rectSreeenWithin.left + (rectSreeen.width() / 12f - 1) * 1f;

        mPaint.setColor(colorScreenShow);
        canvas.drawRect(rectSreeenShow, mPaint);


    }

    private void drawScreenReflective(Canvas canvas) {
        pathScreenReflective.reset();
        pathScreenReflective.moveTo(rectSreeen.left + rectSreeen.width() / 10f * 5f, rectSreeen.top);
        pathScreenReflective.lineTo(rectSreeen.right - rectSreeen.width() / 10f * 2.5f, rectSreeen.bottom);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.bottom);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.top);
        pathScreenReflective.close();
        mPaint.setColor(colorScreenReflective);
        canvas.drawPath(pathScreenReflective, mPaint);


    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.save();
        if (mAnimatedValue >= 0) {
            drawScreen(canvas);
        }
        if (mAnimatedValue >= 1.0f / 6 * 1) {
            drawScreenWithin(canvas);
        }
        if (mAnimatedValue >= 1.0f / 6 * 2) {

            drawScreenShow(canvas);
        }
        if (mAnimatedValue >= 1.0f / 6 * 3) {

            drawCamera(canvas);
        }

        if (mAnimatedValue >= 1.0f / 6 * 4) {

            drawScreenReflective(canvas);
        }
        if (mAnimatedValue >= 1.0f / 6 * 5 && mAnimatedValue <= 1.0f) {

            drawContent(canvas);
        }

        canvas.restore();


    }

    @Override
    protected void initPaint() {
        super.initPaint();
    }
}