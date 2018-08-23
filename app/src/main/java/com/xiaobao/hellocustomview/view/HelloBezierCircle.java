package com.xiaobao.hellocustomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class HelloBezierCircle extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private PointF p1_1, p1_2, p2_1, p2_2, p3_1, p3_2, p4_1, p4_2;
    private final double C = 0.551915024494f;

    public HelloBezierCircle(Context context) {
        super(context);
    }

    public HelloBezierCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path path = new Path();
        path.moveTo(mWidth / 2, mHeight / 2 - mWidth / 4);
        path.cubicTo(p1_1.x, p1_1.y, p1_2.x, p1_2.y, p1_2.x, mHeight / 2);
        path.cubicTo(p2_1.x, p2_1.y, p2_2.x, p2_2.y, mWidth / 2, p2_2.y);
        path.cubicTo(p3_1.x, p3_1.y, p3_2.x, p3_2.y, p3_2.x, mHeight / 2);
        path.cubicTo(p4_1.x, p4_1.y, p4_2.x, p4_2.y, mWidth / 2, p4_2.y);
        canvas.drawPath(path, mPaint);

        canvas.drawPoint(p1_1.x, p1_1.y, mPaint);
        canvas.drawPoint(p1_2.x, p1_2.y, mPaint);
        canvas.drawPoint(p2_1.x, p2_1.y, mPaint);
        canvas.drawPoint(p2_2.x, p2_2.y, mPaint);
        canvas.drawPoint(p3_1.x, p3_1.y, mPaint);
        canvas.drawPoint(p3_2.x, p3_2.y, mPaint);
        canvas.drawPoint(p4_1.x, p4_1.y, mPaint);
        canvas.drawPoint(p4_2.x, p4_2.y, mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(2);

        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mPaint);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        p1_1 = new PointF((float) (mWidth / 2 + mWidth / 4 * C), mHeight / 2 - mWidth / 4);
        p1_2 = new PointF(mWidth / 4 * 3, (float) (mHeight / 2 - mWidth / 4 * C));

        p2_1 = new PointF(mWidth / 4 * 3, (float) (mHeight / 2 + mWidth / 4 * C));
        p2_2 = new PointF((float) (mWidth / 2 + mWidth / 4 * C), mHeight / 2 + mWidth / 4);

        p3_1 = new PointF((float) (mWidth / 2 - mWidth / 4 * C), mHeight / 2 + mWidth / 4);
        p3_2 = new PointF(mWidth / 4, (float) (mHeight / 2 + mWidth / 4 * C));

        p4_1 = new PointF(mWidth / 4, (float) (mHeight / 2 - mWidth / 4 * C));
        p4_2 = new PointF((float) (mWidth / 2 - mWidth / 4 * C), mHeight / 2 - mWidth / 4);

        start();
    }

    private void start() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(p1_2.x, mWidth);
        valueAnimator.setDuration(10000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                p1_2.x = p1_2.x + ((float) animation.getAnimatedValue());
                p2_1.x = p2_1.x + ((float) animation.getAnimatedValue());
                postInvalidate();
            }
        });
        valueAnimator.start();
    }
}
