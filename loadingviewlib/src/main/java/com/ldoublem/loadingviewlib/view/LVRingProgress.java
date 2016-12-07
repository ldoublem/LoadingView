package com.ldoublem.loadingviewlib.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.ldoublem.loadingviewlib.view.base.LVBase;

/**
 * Created by lumingmin on 16/6/27.
 */

public class LVRingProgress extends LVBase {

    private Paint mPaint;
    private Bitmap mBitmapBg;
    private Paint mPaintText;
    private float MaxAngle = 359f;
    private int mPadding;
    private int mWidth;

    private RectF rectFBg = new RectF();
    private int Progress = 0;

    public LVRingProgress(Context context) {
        super(context);
    }

    public LVRingProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVRingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
        invalidate();
    }


    private void initPaint() {
        mPaint = new Paint();

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.WHITE);

    }


    public void setViewColor(int color) {
        mPaint.setColor(color);
        postInvalidate();
    }

    public void setTextColor(int color) {
        mPaintText.setColor(color);
        postInvalidate();
    }


    public void setPorBarStartColor(int color) {
        ProStartColor = color;
    }

    public void setPorBarEndColor(int color) {
        ProEndColor = color;
    }

    private Bitmap getmBitmapBg(Paint paint) {
        Canvas canvas = null;

        if (mBitmapBg == null) {
            mBitmapBg = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(mBitmapBg);
//            paint.reset();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(mPadding);
            paint.setStyle(Paint.Style.STROKE);
//            paint.setColor(Color.rgb(241, 241, 241));
            Path pathBg = new Path();
            pathBg.addArc(rectFBg, 0, 360);
            paint.setShadowLayer(mPadding / 3,
                    0,
                    mPadding / 4, Color.argb(100, 0, 0, 0));
            canvas.drawPath(pathBg, paint);


        }

        return mBitmapBg;

    }


    private void drawBg(Canvas canvas, Paint paint) {
        canvas.drawBitmap(getmBitmapBg(paint), 0
                , 0, paint);


    }


    int ProStartColor = Color.argb(100, 0, 242, 123);
    int ProEndColor = Color.argb(100, 86, 171, 228);

    private void drawProgress(Canvas canvas, Paint paint, int sweepAngle) {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(mPadding);
        paint.setStyle(Paint.Style.STROKE);

        Path pathProgress = new Path();
        pathProgress.addArc(rectFBg, -90, sweepAngle);

        Shader mShader = new LinearGradient(rectFBg.left, rectFBg.top,
                rectFBg.left, rectFBg.bottom,
                new int[]{
                        ProStartColor,
                        ProEndColor


                }, new float[]{0f, 1f},
                Shader.TileMode.CLAMP);

        paint.setShader(mShader);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(pathProgress, paint);
        paint.setShader(null);


        mPaintText.setTextSize(mPaint.getStrokeWidth() / 2);
        String text = String.valueOf((int) (sweepAngle / MaxAngle * 100)) + "%";
        canvas.drawTextOnPath(text
                ,
                pathProgress,
                (float) (Math.PI * rectFBg.width()
                        * (sweepAngle / MaxAngle)
                        - getFontlength(mPaintText, text) * 1.5f),
                getFontHeight(mPaintText) / 3,
                mPaintText

        );


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        canvas.save();
        mPadding = mWidth / 10;//dip2px(12);

        rectFBg = new RectF(getMeasuredWidth() / 2 - mWidth / 2 + mPadding
                , getMeasuredHeight() / 2 - mWidth / 2 + mPadding
                , getMeasuredWidth() / 2 + mWidth / 2 - mPadding
                , getMeasuredHeight() / 2 + mWidth / 2 - mPadding);

        drawBg(canvas, mPaint);
        drawProgress(canvas, mPaint, (int) (MaxAngle / 100f * getProgress()));
        canvas.restore();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();


    }


    private float mAnimatedValue = 0f;


    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        setProgress((int) (mAnimatedValue * 100));
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected int OnStopAnim() {
        mAnimatedValue = 0f;
        postInvalidate();
        return 1;
    }

    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }

    @Override
    protected int SetAnimRepeatCount() {
        return 0;
    }


    @Override
    protected void AinmIsRunning() {

    }


}
