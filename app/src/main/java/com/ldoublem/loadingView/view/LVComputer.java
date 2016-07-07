package com.ldoublem.loadingView.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.ldoublem.loadingView.R;

/**
 * Created by lumingmin on 16/7/7.
 */

public class LVComputer extends View {

    Paint mPaint;
    float mHigth = 0f;
    float mWidth = 0f;

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


    float mPadding = 2;


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
        rectSreeenWithin.top = rectSreeen.top + 4;
        rectSreeenWithin.bottom = rectSreeen.bottom - 4;
        rectSreeenWithin.left = rectSreeen.left + 4;
        rectSreeenWithin.right = rectSreeen.right - 4;
        mPaint.setColor(Color.rgb(65, 64, 66));
        canvas.drawRoundRect(rectSreeenWithin, rectBg.width() / 6 / 5f, rectBg.width() / 6 / 5f, mPaint);

    }

    private void drawScreenShow(Canvas canvas) {
        rectSreeenWithin.top = rectSreeen.top + 4;
        rectSreeenWithin.bottom = rectSreeen.bottom - 4;
        rectSreeenWithin.left = rectSreeen.left + 4;
        rectSreeenWithin.right = rectSreeen.right - 4;
        mPaint.setColor(Color.rgb(65, 64, 66));
        canvas.drawRoundRect(rectSreeenWithin, rectBg.width() / 6 / 5f, rectBg.width() / 6 / 5f, mPaint);
        rectSreeenShow.top = rectSreeenWithin.top + rectBg.width() / 6 / 5f * 1.2f;
        rectSreeenShow.bottom = rectSreeenWithin.bottom - rectBg.width() / 6f / 5f * 1.8f;
        rectSreeenShow.left = rectSreeenWithin.left + rectBg.width() / 6 / 5f;
        rectSreeenShow.right = rectSreeenWithin.right - rectBg.width() / 6 / 5f;

        mPaint.setColor(Color.rgb(88, 89, 91));
        canvas.drawRect(rectSreeenShow, mPaint);
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2 + 4, 4, mPaint);
        mPaint.setColor(Color.rgb(65, 64, 66));
        canvas.drawCircle(rectSreeen.centerX(), rectSreeenShow.top / 2 + 4, 2, mPaint);


        Bitmap ios = setBitmapSize(R.mipmap.apple, (int) (rectSreeen.width() / 10));
        canvas.drawBitmap(ios, rectSreeen.centerX() - ios.getWidth()-5 ,
                rectSreeen.centerY() - ios.getHeight() / 2, mPaint);
        Bitmap android = setBitmapSize(R.mipmap.android, (int) (rectSreeen.width()/12));
        canvas.drawBitmap(android, rectSreeen.centerX() +5,
                rectSreeen.centerY() - android.getHeight() / 2, mPaint);
    }

    private void drawScreenReflective(Canvas canvas) {

        pathScreenReflective.moveTo(rectSreeen.left, rectSreeen.top);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.bottom);
        pathScreenReflective.lineTo(rectSreeen.right, rectSreeen.top);
        pathScreenReflective.close();

        mPaint.setColor(Color.argb(10, 255, 255, 255));
        canvas.drawPath(pathScreenReflective, mPaint);

    }


    private void drawKeyboard(Canvas canvas) {
        rectKeyboard.top = rectSreeenWithin.bottom - rectBg.width() / 6f / 5f + 5;
        rectKeyboard.bottom = rectSreeen.bottom;
        rectKeyboard.left = rectBg.left + rectBg.width() / 6f / 3f;
        rectKeyboard.right = rectBg.right - rectBg.width() / 6f / 3f;

        mPaint.setColor(Color.rgb(209, 211, 212));
        canvas.drawRect(rectKeyboard, mPaint);
    }


    private void drawKeyboardShadow(Canvas canvas) {
        rectKeyboardShadow.top = rectKeyboard.top + rectKeyboard.height() / 3;
        rectKeyboardShadow.bottom = rectKeyboard.bottom;
        rectKeyboardShadow.left = rectKeyboard.left + rectKeyboard.height() / 3 * 2;
        rectKeyboardShadow.right = rectKeyboard.right - rectKeyboard.height() / 3 * 2;

        mPaint.setColor(Color.rgb(188, 190, 192));
        canvas.drawRect(rectKeyboardShadow, mPaint);
    }


    private void drawKeyboardTouch(Canvas canvas) {
        pathKeyboardTouch.reset();
        pathKeyboardTouch.moveTo((rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 2f),
                rectKeyboard.top);
        pathKeyboardTouch.quadTo((rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 2f),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f
                , (rectKeyboard.centerX() - rectBg.width() / 6f / 3f * 2f) + rectKeyboard.height()
                , rectKeyboard.top + rectKeyboard.height() / 3f * 2f


        );
        pathKeyboardTouch.lineTo((rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 2f) - rectKeyboard.height(),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f);


        pathKeyboardTouch.quadTo(
                (rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 2f),
                rectKeyboard.top + rectKeyboard.height() / 3f * 2f,


                (rectKeyboard.centerX() + rectBg.width() / 6f / 3f * 2f),
                rectKeyboard.top);


        pathKeyboardTouch.close();
        mPaint.setColor(Color.rgb(165, 165, 165));
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


        mPaint.setColor(Color.rgb(229, 230, 231));
        canvas.drawPath(pathComputerShadow, mPaint);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        drawScreen(canvas);
        drawScreenWithin(canvas);
        drawScreenShow(canvas);
        drawScreenReflective(canvas);
        drawKeyboard(canvas);
        drawKeyboardShadow(canvas);
        drawKeyboardTouch(canvas);
        drawKeyboardBottom(canvas);
        drawComputerShadow(canvas);

        canvas.restore();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private Bitmap setBitmapSize(int iconId, int w) {


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
}
