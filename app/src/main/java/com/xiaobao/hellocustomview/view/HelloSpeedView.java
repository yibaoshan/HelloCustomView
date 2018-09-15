package com.xiaobao.hellocustomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class HelloSpeedView extends View {

    private int mWidth;
    private int mHeight;
    private int mViewWidth;
    private int mOuterWidth;//外环宽度
    private int mOuterDegrees;
    private int mMiddleWidth;//中间白色环的宽度
    private int mMaxValue = 60;//码表最大值
    private int mIntervalValue = 10;//间隔

    private int mOuterStartColor;//外环起始颜色
    private int mOuterEndColor;//外环结束颜色
    private int mCircleColor;//圆盘颜色

    private BlurMaskFilter mOuterBlurMaskFilter;//外环的阴影程度
    private LinearGradient mOuterLinearGradient;//外环的渐变颜色
    private LinearGradient mMiddleLinearGradient;//中间环的渐变颜色
    private LinearGradient mCircleLinearGradient;//里面的渐变颜色

    private Paint mOuterPaint;//外环画笔
    private Paint mMiddlePaint;//中间环画笔
    private Paint mCirclePaint;//中心圆

    private Paint mPaint;

    public HelloSpeedView(Context context) {
        super(context);
    }

    public HelloSpeedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {

        mOuterWidth = (int) (mViewWidth / 2 / 100.0 * 12);
        mMiddleWidth = (int) (mViewWidth / 2 / 100.0);

        mOuterStartColor = Color.rgb(75, 140, 200);
        mOuterEndColor = Color.rgb(26, 40, 60);
        mCircleColor = Color.rgb(69, 124, 185);

        mOuterBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
        mOuterLinearGradient = new LinearGradient(0, 0, 0, (float) (mViewWidth / 2.5), new int[]{mOuterStartColor, mOuterEndColor}, null, LinearGradient.TileMode.CLAMP);
        mMiddleLinearGradient = new LinearGradient(0, 0, 0, mViewWidth / 3, new int[]{mOuterStartColor, Color.WHITE}, null, LinearGradient.TileMode.CLAMP);
        mCircleLinearGradient = new LinearGradient(0, mViewWidth / 2, 0, -mViewWidth / 2, new int[]{Color.rgb(69, 124, 185), Color.rgb(64, 114, 170)}, null, LinearGradient.TileMode.CLAMP);

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
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setMaskFilter(mOuterBlurMaskFilter);
        mCirclePaint.setAntiAlias(true);
        mMiddlePaint.setShader(mCircleLinearGradient);
        setLayerType(LAYER_TYPE_SOFTWARE, mCirclePaint);

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
        drawCircle(canvas);//画内圆
//        drawOuter(canvas);
    }

    private void drawOuter(Canvas canvas) {
        canvas.save();
        canvas.rotate(mOuterDegrees);
        canvas.drawCircle(0, 0, mViewWidth / 2 - mOuterWidth, mOuterPaint);
        canvas.drawCircle(0, 0, (float) (mViewWidth / 2 - (mOuterWidth * 1.5)), mMiddlePaint);
        canvas.restore();
    }

    private void drawCircle(Canvas canvas) {
        mOuterBlurMaskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
        canvas.drawCircle(0, 0, (float) (mViewWidth / 2 - (mOuterWidth*1.5)), mCirclePaint);
//        canvas.drawCircle(0, 0, (float) (mViewWidth / 2 - (mOuterWidth * 1.5) -mMiddleWidth/2.0), mCirclePaint);
//        mOuterBlurMaskFilter = new BlurMaskFilter(100, BlurMaskFilter.Blur.NORMAL);
//        mCirclePaint.setStrokeWidth(mMiddleWidth);
//        mCirclePaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(0, 0, (float) (mViewWidth / 2 - (mOuterWidth*1.5)), mCirclePaint);
//        mCirclePaint.setStyle(Paint.Style.FILL);
//        mOuterBlurMaskFilter = new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID);
//        mCircleLinearGradient = new LinearGradient(0, mViewWidth / 2, 0, -mViewWidth / 2, new int[]{Color.rgb(69, 124, 185), Color.rgb(64, 114, 170)}, null, LinearGradient.TileMode.CLAMP);
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
