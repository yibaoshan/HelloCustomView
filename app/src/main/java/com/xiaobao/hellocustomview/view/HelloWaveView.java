package com.xiaobao.hellocustomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;


public class HelloWaveView extends View {

    private Paint mPaint;
    private int mWidth, mHeight, offset;
    private int mWL = 100;

    private static final String TAG = "HelloWaveView";

    public HelloWaveView(Context context) {
        super(context);
        init();
    }

    public HelloWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Path path = new Path();
        path.moveTo(0 + offset, mHeight / 2);
        path.quadTo(mWidth / 4 + offset, mHeight / 2 + mWL, mWidth / 2 + offset, mHeight / 2);
        path.quadTo(mWidth - mWidth / 4 + offset, mHeight / 2 - mWL, mWidth + offset, mHeight / 2);
        path.quadTo(mWidth + mWidth / 4 + offset, mHeight / 2 + mWL, mWidth + mWidth / 2 + offset, mHeight / 2);
        path.quadTo(mWidth + mWidth - mWidth / 4 + offset, mHeight / 2 - mWL, mWidth + mWidth + offset, mHeight / 2);
        path.lineTo(mWidth,mHeight);
        path.lineTo(0,mHeight);
        path.close();
        canvas.drawPath(path, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        invalidate();
        return true;
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setTextSize(20);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, -mWidth);
        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
