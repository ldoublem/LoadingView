package com.ldoublem.loadingviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by lumingmin on 16/6/20.
 */

public class LVLineWithText extends View {

    private Paint mPaintBar;
    private Paint mPaintText;


    private float mWidth = 0f;
    private float mHigh = 0f;
    private int mVlaue = 0;

    private float mPadding = 5f;

    public LVLineWithText(Context context) {
        this(context, null);
    }

    public LVLineWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVLineWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mWidth = getMeasuredWidth();
        mHigh = getMeasuredHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        String text = mVlaue + "%";
        float textlength = getFontlength(mPaintText, text);
        float texthigh = getFontHeight(mPaintText, text);

        if (mVlaue == 0) {
            canvas.drawText(text, mPadding, mHigh / 2 + texthigh / 2, mPaintText);
            canvas.drawLine(mPadding + textlength, mHigh / 2, mWidth - mPadding, mHigh / 2, mPaintBar);


        } else if (mVlaue >= 100) {
            canvas.drawText(text, mWidth - mPadding - textlength, mHigh / 2 + texthigh / 2, mPaintText);
            canvas.drawLine(mPadding, mHigh / 2, mWidth - mPadding - textlength, mHigh / 2, mPaintBar);

        } else {
            float w = mWidth - 2 * mPadding - textlength;
            canvas.drawLine(mPadding, mHigh / 2, mPadding + w * mVlaue / 100, mHigh / 2, mPaintBar);
            canvas.drawLine(mPadding + w * mVlaue / 100 + textlength, mHigh / 2, mWidth - mPadding, mHigh / 2, mPaintBar);
            canvas.drawText(text, mPadding + w * mVlaue / 100, mHigh / 2 + texthigh / 2, mPaintText);


        }


    }


    private void initPaint() {
        mPaintBar = new Paint();
        mPaintBar.setAntiAlias(true);
        mPaintBar.setStyle(Paint.Style.FILL);
        mPaintBar.setColor(Color.WHITE);
        mPaintBar.setTextSize(dip2px(10));
        mPaintBar.setStrokeWidth(dip2px(1));


        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(dip2px(10));
        mPaintText.setStrokeWidth(dip2px(1));


    }

    public void setTextColor(int color) {
        mPaintText.setColor(color);
        postInvalidate();
    }

    public void setViewColor(int color) {
        mPaintBar.setColor(color);
        postInvalidate();
    }


    public float getFontlength(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public float getFontHeight(Paint paint, String str) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();

    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setValue(int value) {
        this.mVlaue = value;
        invalidate();
    }

}
