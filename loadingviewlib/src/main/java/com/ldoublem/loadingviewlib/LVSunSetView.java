package com.ldoublem.loadingviewlib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LVSunSetView extends View {
    public LVSunSetView(Context context) {
        super(context);
    }

    public LVSunSetView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LVSunSetView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    int sun_angle = 12;
    String SunstartTime = "2019-09-06 05:38:00";
    String SunendTime = "2019-09-06 18:16:00";


    public void setSunstartTime(String sunstartTime) {
        SunstartTime = sunstartTime;
        invalidate();
    }

    public void setSunendTime(String sunendTime) {
        SunendTime = sunendTime;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();


//        mPaint.
        initPaint();
        canvas.drawLine(0 + mWidth / 12, mHeight - mWidth / 6


                , mWidth - mWidth / 12, mHeight - mWidth / 6, mPaint);


        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextSize(35);
        mPaint.setStrokeWidth(1);
        String startTime = getTimeText(SunstartTime);
        String endTime = getTimeText(SunendTime);
        Rect rect = new Rect();
        mPaint.getTextBounds(startTime, 0, startTime.length(), rect);
        int w = rect.width();
        int h = rect.height() * 2;
        canvas.drawText(startTime, mWidth / 6 - w / 2, mHeight - mWidth / 6 + h, mPaint);
        canvas.drawText(endTime, mWidth - mWidth / 6 - w / 2, mHeight - mWidth / 6 + h, mPaint);


        mPaint.setStyle(Paint.Style.STROKE);//设置空心
        mPaint.setStrokeWidth(2.5f);
        mPaint.setPathEffect(new DashPathEffect(new float[]{14, 12}, 0));

        RectF oval1 = new RectF(mWidth / 2 - mWidth / 3, (float) ((float) (mHeight - mWidth / 6 - mWidth / 3)
                + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3)
                ,
                mWidth / 2 + mWidth / 3, (float) (mHeight - mWidth / 6 + mWidth / 3 + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3));
        canvas.drawArc(oval1, 180 + sun_angle, (180 - 2 * sun_angle), false, mPaint);//小弧形

        float intervalf = 0f;
        try {
            long TodayInterval = getTimeInterval(SunstartTime, SunendTime);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts.getTime());
            long nowInterval = getTimeInterval(SunstartTime, nowtime);
            intervalf = nowInterval * 1.0f / TodayInterval * mAnimatedValue;
            if (intervalf > 1f) {
                intervalf = 1f;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


//        float y = (float) (Math.sin(Math.toRadians(sun_angle + (180 - 2 * sun_angle) * intervalf)) * mWidth / 3);
//        float x = (float) (Math.cos(Math.toRadians(sun_angle + (180 - 2 * sun_angle) * intervalf)) * mWidth / 3);
//        mPaint.setStyle(Paint.Style.FILL);//设置空心
//        canvas.drawBitmap(getSun(intervalf), mWidth / 2 - x - mWidth / 3 / 4, (float) (mHeight - mWidth / 6 - y - mWidth / 3 / 4 + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3), mPaint);


        canvas.drawBitmap(getSunbg(intervalf), 0, 0, mPaint);


        canvas.restore();
    }

    Paint mPaint;


    public void startSunset() {

        mhandler.obtainMessage(0).sendToTarget();
        Message m = new Message();
        m.what = 1;
        mhandler.sendMessageDelayed(m, 200);

    }


    public void start() {


        stopAnim();


        float intervalf = 0f;
        try {
            long TodayInterval = getTimeInterval(SunstartTime, SunendTime);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ts.getTime());
            long nowInterval = getTimeInterval(SunstartTime, nowtime);
            intervalf = nowInterval * 1.0f / TodayInterval;
            if (intervalf > 1f) {
                intervalf = 1f;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }


        startViewAnim(0f, 1f, (long) (4000 * intervalf));

    }


    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(4);

    }

    int mWidth = 0;
    int mHeight = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHeight = getMeasuredHeight();
        mWidth = getMeasuredWidth();


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


    public long getTimeInterval(String oldTime, String newTime) throws ParseException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long NTime = df.parse(newTime).getTime();
        //从对象中拿到时间
        long OTime = df.parse(oldTime).getTime();
        long diff = (NTime - OTime) / 1000 / 60;
        return diff;
    }


    private String getTimeText(String tsStr) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
//        String tsStr = "2011-05-09 11:49:45";
        String time = "00:00";
        try {
            ts = Timestamp.valueOf(tsStr);

            time = new SimpleDateFormat("HH:mm").format(ts.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }

    private Bitmap getSunbg(float intervalf) {
        Bitmap b = Bitmap.createBitmap(mWidth, mHeight - mWidth / 6, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.save();

        mPaint.setStyle(Paint.Style.STROKE);//设置空心

        mPaint.setColor(Color.argb(255, 254, 219, 57));

        if (intervalf < 1f) {
            RectF oval2 = new RectF(mWidth / 2 - mWidth / 3, (float) ((float) (mHeight - mWidth / 6 - mWidth / 3)
                    + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3)
                    ,
                    mWidth / 2 + mWidth / 3, (float) (mHeight - mWidth / 6 + mWidth / 3 + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3));
            c.drawArc(oval2, 180 + sun_angle, (180 - 2 * sun_angle) * intervalf, false, mPaint);//小弧形
        }

        mPaint.setStyle(Paint.Style.FILL);//设置空心
        //如果从地下开始日出这个 sun_angle用0使用

        float y = (float) (Math.sin(Math.toRadians(sun_angle + (180 - 2 * sun_angle) * intervalf)) * mWidth / 3);
        float x = (float) (Math.cos(Math.toRadians(sun_angle + (180 - 2 * sun_angle) * intervalf)) * mWidth / 3);

        c.drawBitmap(getSun(intervalf), mWidth / 2 - x - mWidth / 3 / 4, (float) (mHeight - mWidth / 6 - y - mWidth / 3 / 4 + Math.sin(Math.toRadians(sun_angle)) * mWidth / 3), mPaint);

        c.restore();

        return b;


    }


    private Bitmap getSun(float intervalf) {
//        intervalf = 0;
        Bitmap b = Bitmap.createBitmap(mWidth / 3 / 2, mWidth / 3 / 2, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        c.save();
        mPaint.setStyle(Paint.Style.FILL);//设置空心
        c.rotate(intervalf * 180, mWidth / 3 / 4, mWidth / 3 / 4);


        if (intervalf >= 0.3f || intervalf <= 0.7f) {
            mPaint.setColor(Color.argb(255, 254, 219, 57));

        }
        if (intervalf < 0.3f) {

            float colorap = (float) (intervalf / 0.3f);
            int suncolor = (int) (230 + (25 * (colorap)));


            mPaint.setColor(Color.argb(suncolor, 254, 219, 57));


        } else if (intervalf > 0.7f) {


            float colorap = (float) ((1f - intervalf) / 0.3f);
            int suncolor = (int) (230 + (25 * (colorap)));


            mPaint.setColor(Color.argb(suncolor, 254, 219, 57));


        }


        if (intervalf > 0 && intervalf < 1) {
            c.drawCircle(mWidth / 3 / 4, mWidth / 3 / 4, mWidth / 3 / 10, mPaint);

            mPaint.setStrokeWidth(5);
            c.drawLine(mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5) - (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );

            c.drawLine(mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );


            c.drawLine(mWidth / 3 / 4, mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5)
                    , mWidth / 3 / 4,
                    mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5) - (mWidth / 3 / 10 / 2)
                    , mPaint


            );


            c.drawLine(mWidth / 3 / 4, mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)
                    , mWidth / 3 / 4,
                    mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)
                    , mPaint


            );


            c.drawLine((float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );


            c.drawLine((float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );


            c.drawLine((float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );

            c.drawLine((float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );


//            Log.e("eeeee", (Math.sqrt(2) + ""));


        } else if (intervalf == 0) {


            RectF oval1 = new RectF();
            oval1.set(mWidth / 3 / 4 - mWidth / 3 / 10, mWidth / 3 / 4 - mWidth / 3 / 10,
                    mWidth / 3 / 4 + mWidth / 3 / 10, mWidth / 3 / 4 + mWidth / 3 / 10);

            c.drawArc(oval1, 180, 180, true, mPaint);//小弧形

            mPaint.setStrokeWidth(5);


            c.drawLine(mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5) - (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );

            c.drawLine(mWidth / 3 / 4, mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5)
                    , mWidth / 3 / 4,
                    mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5) - (mWidth / 3 / 10 / 2)
                    , mPaint


            );

            c.drawLine(mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );


            c.drawLine((float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );

            c.drawLine((float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );


        } else if (intervalf == 1) {


            mPaint.setStrokeWidth(5);


            RectF oval1 = new RectF();
            oval1.set(mWidth / 3 / 4 - mWidth / 3 / 10, mWidth / 3 / 4 - mWidth / 3 / 10,
                    mWidth / 3 / 4 + mWidth / 3 / 10, mWidth / 3 / 4 + mWidth / 3 / 10);

            c.drawArc(oval1, 180, -180, true, mPaint);//小弧形


            c.drawLine(mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 - mWidth / 3 / 10 - (mWidth / 3 / 10 / 5) - (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );

            c.drawLine(mWidth / 3 / 4, mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)
                    , mWidth / 3 / 4,
                    mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)
                    , mPaint


            );

            c.drawLine(mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5),
                    mWidth / 3 / 4,
                    mWidth / 3 / 4 + mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)
                    , mWidth / 3 / 4, mPaint


            );


            c.drawLine((float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 - ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );

            c.drawLine((float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5)) / Math.sqrt(2))),
                    (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , (float) (mWidth / 3 / 4 + ((float) (mWidth / 3 / 10 + (mWidth / 3 / 10 / 5) + (mWidth / 3 / 10 / 2)) / Math.sqrt(2)))
                    , mPaint


            );


        }
        c.restore();

        return b;
    }


    private android.os.Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);


            if (msg.what == 0) {
                mAnimatedValue = 0f;
                invalidate();


            } else if (msg.what == 1) {
                start();
            }


        }
    };


}
