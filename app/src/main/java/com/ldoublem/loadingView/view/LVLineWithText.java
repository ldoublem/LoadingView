package com.ldoublem.loadingView.view;

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

    private Paint mPaint;

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
        float textlength = getFontlength(mPaint, text);
        float texthigh = getFontHeight(mPaint, text);

        if (mVlaue == 0) {
            canvas.drawText(text, mPadding, mHigh / 2 + texthigh / 2, mPaint);
            canvas.drawLine(mPadding + textlength, mHigh / 2, mWidth - mPadding, mHigh / 2, mPaint);


        } else if (mVlaue >= 100) {
            canvas.drawText(text, mWidth - mPadding - textlength, mHigh / 2 + texthigh / 2, mPaint);
            canvas.drawLine(mPadding, mHigh / 2, mWidth - mPadding - textlength, mHigh / 2, mPaint);

        } else {
            float w = mWidth - 2 * mPadding - textlength;
            canvas.drawLine(mPadding, mHigh / 2, mPadding + w * mVlaue / 100, mHigh / 2, mPaint);
            canvas.drawLine(mPadding + w * mVlaue / 100 + textlength, mHigh / 2, mWidth - mPadding, mHigh / 2, mPaint);
            canvas.drawText(text, mPadding + w * mVlaue / 100, mHigh / 2 + texthigh / 2, mPaint);


        }


    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(dip2px(10));
        mPaint.setStrokeWidth(dip2px(1));


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
