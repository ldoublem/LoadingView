package com.ldoublem.loadingviewlib.view;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;

import com.ldoublem.loadingviewlib.view.base.LVBase;

/**
 * Created by lumingmin on 16/6/24.
 */

public class LVBlazeWood extends LVBase {


    private int mWidth;
    private Paint mPaintBg;

    private Paint mPaintWood;

    private Paint mPaintFire;

    private RectF rectFBg;
    private RectF rectFWood;
    private int mPadding;
    private int woodWidth;
    private int woodLength;
    private Bitmap wood;

    public LVBlazeWood(Context context) {
        super(context);
    }

    public LVBlazeWood(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVBlazeWood(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void initPaint() {

        evaluator = new ArgbEvaluator();
        mPadding = dip2px(1);
        mPaintBg = new Paint();
        mPaintBg.setAntiAlias(true);
        mPaintBg.setStyle(Paint.Style.FILL);
        mPaintBg.setColor(Color.BLACK);

        mPaintWood = new Paint();
        mPaintWood.setAntiAlias(true);
        mPaintWood.setStyle(Paint.Style.FILL);
        mPaintWood.setColor(Color.rgb(122, 57, 47));


        mPaintFire = new Paint();
        mPaintFire.setAntiAlias(true);
        mPaintFire.setStyle(Paint.Style.FILL);
        mPaintFire.setColor(Color.rgb(232, 132, 40));

    }


    private Bitmap getWood() {
        if (wood != null)
            return wood;
        wood = Bitmap.createBitmap((int) getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = null;
        canvas = new Canvas(wood);

        canvas.rotate(-18, rectFWood.centerX(), rectFWood.centerY());
        mPaintWood.setColor(Color.rgb(97, 46, 37));

        canvas.drawRoundRect(rectFWood, woodWidth / 5f, woodWidth / 5f, mPaintWood);
        canvas.rotate(36, rectFWood.centerX(), rectFWood.centerY());
        mPaintWood.setColor(Color.rgb(102, 46, 37));


        canvas.drawRoundRect(rectFWood, woodWidth / 5f, woodWidth / 5f, mPaintWood);


        return wood;

    }

    RectF rectFire0 = new RectF();

    RectF rectFire1 = new RectF();
    RectF rectFire2 = new RectF();
    RectF rectFire3 = new RectF();
    ArgbEvaluator evaluator;


    private void drawFire0(Canvas canvas) {


        int color = (Integer) evaluator.evaluate(mAnimatedValue, Color.rgb(255, 220, 1), Color.rgb(240, 169, 47));

        mPaintFire.setColor(color);


        Path pathFire = new Path();

        RectF rectFire0 = new RectF();

        rectFire0.top = this.rectFire0.centerY() - (this.rectFire1.height() / 2 - this.rectFire0.height() / 2) * mAnimatedValue
                - (this.rectFire0.centerY() - this.rectFire1.centerY()) * mAnimatedValue

        ;
        rectFire0.bottom = this.rectFire0.centerY() + (this.rectFire1.height() / 2 - this.rectFire0.height() / 2) * mAnimatedValue
                - (this.rectFire0.centerY() - this.rectFire1.centerY()) * mAnimatedValue
        ;


        rectFire0.left = this.rectFire0.centerX() - (this.rectFire1.width() / 2 - this.rectFire0.width() / 2) * mAnimatedValue
                - rectFire1.width() / 5 * mAnimatedValue;
        rectFire0.right = this.rectFire0.centerX() + (this.rectFire1.width() / 2 - this.rectFire0.width() / 2) * mAnimatedValue
                - rectFire1.width() / 5 * mAnimatedValue;


        pathFire.moveTo(rectFire0.centerX(), rectFire0.top);
        pathFire.lineTo(rectFire0.right, rectFire0.centerY());
        pathFire.lineTo(rectFire0.centerX(), rectFire0.bottom);
        pathFire.lineTo(rectFire0.left, rectFire0.centerY());
        pathFire.close();

        canvas.drawPath(pathFire, mPaintFire);

    }


    private void drawFire1(Canvas canvas) {


        int color = (Integer) evaluator.evaluate(mAnimatedValue, Color.rgb(240, 169, 47), Color.rgb(232, 132, 40));

        mPaintFire.setColor(color);


        Path pathFire = new Path();

        RectF rectFire1 = new RectF();

        rectFire1.top = this.rectFire1.centerY() - this.rectFire1.height() / 2


                - (this.rectFire2.height() / 2 - this.rectFire1.height() / 2) * mAnimatedValue


                - (this.rectFire1.centerY() - this.rectFire2.centerY()) * mAnimatedValue
        ;

        rectFire1.bottom = this.rectFire1.centerY() + this.rectFire1.height() / 2
                + (this.rectFire2.height() / 2 - this.rectFire1.height() / 2) * mAnimatedValue
                - (this.rectFire1.centerY() - this.rectFire2.centerY()) * mAnimatedValue

        ;


        rectFire1.left = this.rectFire1.centerX() - this.rectFire1.width() / 2


                - (this.rectFire2.width() / 2 - this.rectFire1.width() / 2) * mAnimatedValue

                + this.rectFire1.width() / 5 * mAnimatedValue
        ;


        rectFire1.right = this.rectFire1.centerX() + this.rectFire1.width() / 2 +
                +(this.rectFire2.width() / 2 - this.rectFire1.width() / 2) * mAnimatedValue
                + this.rectFire1.width() / 5 * mAnimatedValue;


        pathFire.moveTo(rectFire1.centerX(), rectFire1.top);
        pathFire.lineTo(rectFire1.right, rectFire1.centerY());
        pathFire.lineTo(rectFire1.centerX(), rectFire1.bottom);
        pathFire.lineTo(rectFire1.left, rectFire1.centerY());
        pathFire.close();

        canvas.drawPath(pathFire, mPaintFire);


    }


    private void drawFire2(Canvas canvas) {

        int color = (Integer) evaluator.evaluate(mAnimatedValue, Color.rgb(232, 132, 40), Color.rgb(223, 86, 33));

        mPaintFire.setColor(color);

        RectF rectFire2 = new RectF();
        rectFire2.bottom = this.rectFire2.centerY() +
                rectFire3.height() / 2 +
                +(this.rectFire2.height() / 2 - rectFire3.height() / 2) * (1 - mAnimatedValue)


                - (this.rectFire2.centerY() - this.rectFire3.centerY()) * mAnimatedValue


        ;


        rectFire2.top = this.rectFire2.centerY() -

                rectFire3.height() / 2 +

                -(this.rectFire2.height() / 2 - rectFire3.height() / 2) * (1 - mAnimatedValue)

                - (this.rectFire2.centerY() - this.rectFire3.centerY()) * mAnimatedValue

        ;
        rectFire2.left = this.rectFire2.centerX()
                - rectFire3.width() / 2 +


                -(this.rectFire2.height() / 2 - rectFire3.width() / 2) * (1 - mAnimatedValue)
                + (this.rectFire3.width() / 3 * mAnimatedValue)


        ;
        rectFire2.right = this.rectFire2.centerX()
                + rectFire3.width() / 2 +

                +(this.rectFire2.height() / 2 - rectFire3.width() / 2) * (1 - mAnimatedValue)

                + (this.rectFire3.width() / 3 * mAnimatedValue)
        ;


        Path pathFire = new Path();
        pathFire.moveTo(rectFire2.centerX(), rectFire2.top);
        pathFire.lineTo(rectFire2.right, rectFire2.centerY());
        pathFire.lineTo(rectFire2.centerX(), rectFire2.bottom);
        pathFire.lineTo(rectFire2.left, rectFire2.centerY());
        pathFire.close();

        canvas.drawPath(pathFire, mPaintFire);


    }


    private void drawFire3(Canvas canvas) {


        mPaintFire.setColor(Color.rgb(223, 86, 33));


        RectF rectFire3 = new RectF();
        rectFire3.bottom = this.rectFire3.centerY() + this.rectFire3.height() / 2 * (1 - mAnimatedValue)
                - (this.rectFire3.height()*0.75f) * mAnimatedValue;

        rectFire3.top = this.rectFire3.centerY() - this.rectFire3.height() / 2 * (1 - mAnimatedValue)
                - (this.rectFire3.height()*0.75f) * mAnimatedValue;
        rectFire3.left = this.rectFire3.centerX() - this.rectFire3.height() / 2 * (1 - mAnimatedValue)

                - this.rectFire3.width() / 3 * mAnimatedValue;


        rectFire3.right = this.rectFire3.centerX() + this.rectFire3.height() / 2 * (1 - mAnimatedValue)
                - this.rectFire3.width() / 3 * mAnimatedValue;
        ;


        Path pathFire = new Path();
        pathFire.moveTo(rectFire3.centerX(), rectFire3.top);
        pathFire.lineTo(rectFire3.right, rectFire3.centerY());
        pathFire.lineTo(rectFire3.centerX(), rectFire3.bottom);
        pathFire.lineTo(rectFire3.left, rectFire3.centerY());
        pathFire.close();

        canvas.drawPath(pathFire, mPaintFire);


    }


    private void initFire() {
        rectFire3.bottom = rectFBg.centerY() + woodLength / 5 - woodLength / 4;
        rectFire3.top = rectFBg.centerY() - woodLength / 5 - woodLength / 4;
        rectFire3.left = rectFBg.centerX() - woodLength / 5;
        rectFire3.right = rectFBg.centerX() + woodLength / 5;

        rectFire3.left = rectFire3.left + rectFire3.width() / 3;
        rectFire3.right = rectFire3.right + rectFire3.width() / 3;


        rectFire2.bottom = rectFBg.centerY() + woodLength / 3;
        rectFire2.top = rectFBg.centerY() - woodLength / 3;
        rectFire2.left = rectFBg.centerX() - woodLength / 3;
        rectFire2.right = rectFBg.centerX() + woodLength / 3;


        rectFire1.bottom = rectFBg.centerY() + woodLength / 4 + woodLength / 4;
        rectFire1.top = rectFBg.centerY() - woodLength / 4 + woodLength / 4;
        rectFire1.left = rectFBg.centerX() - woodLength / 4;
        rectFire1.right = rectFBg.centerX() + woodLength / 4;


        rectFire1.left = rectFire1.left - rectFire1.width() / 5;
        rectFire1.right = rectFire1.right - rectFire1.width() / 5;


        rectFire0.bottom = rectFWood.centerY() + rectFWood.height() / 2;
        rectFire0.top = rectFWood.centerY() - rectFWood.height() / 2;
        rectFire0.left = rectFWood.centerX() - rectFWood.height() / 2;
        rectFire0.right = rectFWood.centerX() + rectFWood.height() / 2;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.save();


        rectFBg = new RectF(getMeasuredWidth() / 2 - mWidth / 2 + mPadding
                , getMeasuredHeight() / 2 - mWidth / 2 + mPadding
                , getMeasuredWidth() / 2 + mWidth / 2 - mPadding
                , getMeasuredHeight() / 2 + mWidth / 2 - mPadding);
        woodWidth = (int) (rectFBg.height() / 12f);
        woodLength = (int) (rectFBg.width() / 3 * 2);
        rectFWood = new RectF();
        rectFWood.bottom = rectFBg.bottom - woodWidth * 2;
        rectFWood.top = rectFBg.bottom - woodWidth * 3;
        rectFWood.left = rectFBg.centerX() - woodLength / 2f;
        rectFWood.right = rectFBg.centerX() + woodLength / 2f;
//        canvas.drawRect(rectFBg, mPaintBg);

        initFire();
        if(valueAnimator!=null) {
            drawFire3(canvas);
            drawFire2(canvas);
            drawFire1(canvas);
            drawFire0(canvas);
        }
        canvas.drawBitmap(getWood(), 0, 0, mPaintBg);
        canvas.restore();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST
                && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(dip2px(30), dip2px(30));
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(heightSpecSize, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, widthSpecSize);
        }
        if (getMeasuredWidth() > getHeight())
            mWidth = getMeasuredHeight();
        else
            mWidth = getMeasuredWidth();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > h)
            mWidth = h;
        else
            mWidth = w;


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



    private float mAnimatedValue = 0.5f;






    @Override
    protected void InitPaint() {
        initPaint();
    }

    @Override
    protected void OnAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimatedValue = (float) valueAnimator.getAnimatedValue();
        invalidate();
    }

    @Override
    protected void OnAnimationRepeat(Animator animation) {

    }

    @Override
    protected int OnStopAnim() {
        mAnimatedValue = 0.25f;
        valueAnimator=null;
        postInvalidate();
        return 1;
    }

    @Override
    protected int SetAnimRepeatMode() {
        return ValueAnimator.RESTART;
    }


    public boolean isAnimatorRunning() {

        if (valueAnimator == null) {
            return false;
        } else {
            if (valueAnimator.isRunning()) {
                return true;
            }

        }
        return false;
    }
    @Override
    protected void AinmIsRunning() {

    }
    @Override
    protected int SetAnimRepeatCount() {
        return ValueAnimator.INFINITE;
    }
}
