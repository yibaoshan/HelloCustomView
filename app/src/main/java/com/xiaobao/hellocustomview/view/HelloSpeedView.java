package com.xiaobao.hellocustomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class HelloSpeedView extends View {

    private int mWidth;
    private int mHeight;
    private int mViewWidth;//圆的直径
    private int mOuterWidth;//外环宽度
    private int mScaleWidth;//刻度宽度
    private int mScaleSelectWidth;//刻度宽度
    private int mMiddleWidth;//中间白色环的宽度
    private int mOuterDegrees;
    private int mMaxValue = 60;//码表最大值
    private int mIntervalValue = 10;//间隔
    private int mBlurMaskFilterRadius = -1;//阴影范围

    private int mScaleColor;//刻度颜色
    private int mScaleSelectColor;//刻度选中颜色
    private int mOuterStartColor;//外环起始颜色
    private int mOuterEndColor;//外环结束颜色
    private int mCircleStartColor;//内圆起始颜色
    private int mCircleEndColor;//内圆结束颜色

    private BlurMaskFilter mOuterBlurMaskFilter;//外环的阴影程度
    private LinearGradient mOuterLinearGradient;//外环的渐变颜色
    private LinearGradient mMiddleLinearGradient;//中间环的渐变颜色
    private LinearGradient mCircleLinearGradient;//里面圆的渐变颜色

    private Paint mOuterPaint;//外环画笔
    private Paint mMiddlePaint;//中间环画笔
    private Paint mCirclePaint;//中心圆
    private Paint mScalePaint;//刻度画笔

    private Paint mPaint;

    public HelloSpeedView(Context context) {
        super(context);
    }

    public HelloSpeedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {

        mScaleWidth = (int) (mViewWidth/2/8.0);
        mScaleSelectWidth = (int) (mViewWidth/2/10.0);
        mOuterWidth = (int) (mViewWidth / 2 / 100.0 * 12);
        mMiddleWidth = (int) (mViewWidth / 2 / 100.0);

        if (mScaleColor == 0) {
            mScaleColor = Color.GRAY;
        }
        if (mScaleSelectColor == 0) {
            mScaleSelectColor = Color.WHITE;
        }
        if (mOuterStartColor == 0) {
            mOuterStartColor = Color.rgb(75, 140, 200);
        }
        if (mOuterEndColor == 0) {
            mOuterEndColor = Color.rgb(26, 40, 60);
        }
        if (mCircleStartColor == 0) {
            mCircleStartColor = Color.rgb(69, 124, 185);
        }
        if (mCircleEndColor == 0) {
            mCircleEndColor = Color.rgb(64, 114, 170);
        }
        if (mBlurMaskFilterRadius == -1) {
            mBlurMaskFilterRadius = 50;
        }

        mOuterBlurMaskFilter = new BlurMaskFilter(mBlurMaskFilterRadius, BlurMaskFilter.Blur.SOLID);
        mOuterLinearGradient = new LinearGradient(0, 0, 0, (float) (mViewWidth / 2.5), new int[]{mOuterStartColor, mOuterEndColor}, null, LinearGradient.TileMode.CLAMP);
        mMiddleLinearGradient = new LinearGradient(0, 0, 0, mViewWidth / 3, new int[]{mOuterStartColor, Color.WHITE}, null, LinearGradient.TileMode.CLAMP);
        mCircleLinearGradient = new LinearGradient(0, mViewWidth / 2, 0, -mViewWidth / 2, new int[]{mCircleStartColor, mCircleEndColor}, null, LinearGradient.TileMode.CLAMP);

        mOuterPaint = new Paint();
        mOuterPaint.setStrokeWidth(mOuterWidth);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setMaskFilter(mOuterBlurMaskFilter);
        mOuterPaint.setShader(mOuterLinearGradient);
        setLayerType(LAYER_TYPE_SOFTWARE, mOuterPaint);

        mMiddlePaint = new Paint();
        mMiddlePaint.setStrokeWidth(mMiddleWidth);
        mMiddlePaint.setStyle(Paint.Style.STROKE);
        mMiddlePaint.setAntiAlias(true);
        mMiddlePaint.setMaskFilter(mOuterBlurMaskFilter);
        mMiddlePaint.setShader(mMiddleLinearGradient);
        setLayerType(LAYER_TYPE_SOFTWARE, mMiddlePaint);

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setMaskFilter(mOuterBlurMaskFilter);
        mCirclePaint.setAntiAlias(true);
        mMiddlePaint.setShader(mCircleLinearGradient);
        setLayerType(LAYER_TYPE_SOFTWARE, mCirclePaint);

        mScalePaint = new Paint();
        mScalePaint.setAntiAlias(true);
        mScalePaint.setColor(mScaleColor);
        mScalePaint.setStyle(Paint.Style.FILL);
        mScalePaint.setStrokeWidth(mMiddleWidth);

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);

        start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidth = w;
        this.mHeight = h;
        this.mViewWidth = w > h ? h : w;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWidth / 2, mHeight / 2);//把坐标移到中间

//        canvas.drawCircle(0, 0, (float) (mViewWidth / 2 - (mOuterWidth * 1.5) - mMiddleWidth), mMiddlePaint);//画中间白色的圆
        drawCircle(canvas);//画内圆
        drawOuter(canvas);
        drawScale(canvas);
    }

    //画外圆
    private void drawOuter(Canvas canvas) {
        canvas.save();
        canvas.rotate(mOuterDegrees);
        canvas.drawCircle(0, 0, mViewWidth / 2 - mOuterWidth, mOuterPaint);
        canvas.restore();
    }

    //画内圆
    private void drawCircle(Canvas canvas) {
        canvas.drawCircle(0, 0, mViewWidth / 2 - mOuterWidth, mCirclePaint);
    }

    //画刻度
    private void drawScale(Canvas canvas) {
        PointF p1 = new PointF((float) (-mViewWidth/2+mOuterWidth*2.5),0);
        PointF p2 = new PointF(p1.x+mScaleWidth,0);
        canvas.drawLine(p1.x, p1.y, p2.x, p2.y, mScalePaint);
    }


    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 360);
        valueAnimator.setDuration(9000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOuterDegrees = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }

}
