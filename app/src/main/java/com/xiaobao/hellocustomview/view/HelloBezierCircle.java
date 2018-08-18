package com.xiaobao.hellocustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HelloBezierCircle extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private PointF p1, p2, p3, p4;
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
        path.moveTo(mWidth/2,p1.y);
        path.quadTo(p1.x,p1.y,p1.x,mHeight/2);
        path.quadTo(p2.x,p2.y,mWidth/2,p2.y);
        path.quadTo(p3.x,p3.y,p3.x,mHeight/2);
        path.quadTo(p4.x,p4.y,mWidth/2,p1.y);
        canvas.drawPath(path,mPaint);

        mPaint.setColor(Color.BLACK);
        canvas.drawLine(0, mHeight / 2, mWidth, mHeight / 2, mPaint);
        canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, mPaint);

        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(2);
        canvas.drawPoint(p1.x, p1.y, mPaint);
        canvas.drawPoint(p2.x, p2.y, mPaint);
        canvas.drawPoint(p3.x, p3.y, mPaint);
        canvas.drawPoint(p4.x, p4.y, mPaint);
//        canvas.drawLine(p1.x,p1.y,p2.x,p2.y,mPaint);
//        canvas.drawLine(p2.x,p2.y,p3.x,p3.y,mPaint);
//        canvas.drawLine(p3.x,p3.y,p4.x,p4.y,mPaint);
//        canvas.drawLine(p4.x,p4.y,p1.x,p1.y,mPaint);

    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);

        p1 = new PointF(mWidth / 4 * 3, mHeight / 2 - mWidth / 4);
        p2 = new PointF(mWidth / 4 * 3, mHeight / 2 + mWidth / 4);
        p3 = new PointF(mWidth / 4, mHeight / 2 + mWidth / 4);
        p4 = new PointF(mWidth / 4, mHeight / 2 - mWidth / 4);
    }
}
