package com.ldoublem.loadingviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by lumingmin on 16/6/27.
 */

public class LVRingProgress extends View {

    private Paint mPaint;
    private Bitmap mBitmapBg;
    private Paint mPaintText;
    private float MaxAngle = 359f;
    private int mPadding;
    private int mWidth;

    private RectF rectFBg = new RectF();
    private int Progress = 0;

    public int getProgress() {
        return Progress;
    }

    public void setProgress(int progress) {
        Progress = progress;
        invalidate();
    }


    public LVRingProgress(Context context) {
        this(context, null);
    }

    public LVRingProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LVRingProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();

        mPaintText = new Paint();
        mPaintText.setAntiAlias(true);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setColor(Color.WHITE);

    }


    private Bitmap getmBitmapBg(Paint paint) {
        Canvas canvas = null;

        if (mBitmapBg == null) {
            mBitmapBg = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            canvas = new Canvas(mBitmapBg);
            paint.reset();
            paint.setAntiAlias(true);
            paint.setStrokeWidth(mPadding);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.rgb(241, 241, 241));
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
                        Color.argb(100, 0, 242, 123),
                        Color.argb(100, 86, 171, 228)

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
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
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

    public float getFontlength(Paint paint, String str) {
        return paint.measureText(str);
    }

    public float getFontHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 3000);
    }

    private ValueAnimator valueAnimator;
    private float mAnimatedValue = 0f;

    public void stopAnim() {
        if (valueAnimator != null) {
            clearAnimation();
            valueAnimator.setRepeatCount(0);
            valueAnimator.cancel();
            mAnimatedValue = 0f;
            postInvalidate();
        }
    }


    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(0);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                mAnimatedValue = (float) valueAnimator.getAnimatedValue();
                setProgress((int) (mAnimatedValue * 100));


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
