package com.xiaobao.hellocustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class HelloBezierView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private PointF startPointF, endPointF, controlPointF, control2PointF;
    private int currentMode = -1;

    public HelloBezierView(Context context) {
        super(context);
        init();
    }

    public HelloBezierView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        startPointF.x = 100;
        startPointF.y = h / 2;

        endPointF.x = w - 100;
        endPointF.y = h / 2;

        controlPointF.x = w / 2;
        controlPointF.y = h / 2 - 100;

        control2PointF.x = w / 2;
        control2PointF.y = h / 2 + 100;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStrokeWidth(5);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(startPointF.x, startPointF.y, controlPointF.x, controlPointF.y, mPaint);//起始点到控制点的直线
        canvas.drawLine(endPointF.x, endPointF.y, controlPointF.x, controlPointF.y, mPaint);//结束点到控制点的直线
        canvas.drawLine(startPointF.x, startPointF.y, control2PointF.x, control2PointF.y, mPaint);//起始点到控制点2的直线
        canvas.drawLine(endPointF.x, endPointF.y, control2PointF.x, control2PointF.y, mPaint);//结束点到控制点2的直线

        mPaint.setStrokeWidth(8);
        mPaint.setColor(Color.GREEN);
        Path path = new Path();
        path.moveTo(startPointF.x, startPointF.y);
//        path.quadTo(controlPointF.x, controlPointF.y, endPointF.x, endPointF.y);//二阶贝塞尔
        path.cubicTo(controlPointF.x, controlPointF.y, control2PointF.x, control2PointF.y, endPointF.x, endPointF.y);//三阶贝塞尔
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(10);
        canvas.drawPoint(startPointF.x, startPointF.y, mPaint);//起始点
        canvas.drawPoint(endPointF.x, endPointF.y, mPaint);//结束点
        canvas.drawText("1", controlPointF.x, controlPointF.y, mPaint);
        canvas.drawText("2", control2PointF.x, control2PointF.y, mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentMode = getMode(event);
                break;
            case MotionEvent.ACTION_MOVE:
                if (currentMode == 1) {
                    controlPointF.x = event.getX();
                    controlPointF.y = event.getY();
                } else if (currentMode == 2) {
                    control2PointF.x = event.getX();
                    control2PointF.y = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    private int getMode(MotionEvent event) {
        double c1x = Math.pow(controlPointF.x - event.getX(), 2);
        double c1y = Math.pow(controlPointF.y - event.getY(), 2);
        double c1 = Math.sqrt(c1x + c1y);
        double c2x = Math.pow(control2PointF.x - event.getX(), 2);
        double c2y = Math.pow(control2PointF.y - event.getY(), 2);
        double c2 = Math.sqrt(c2x + c2y);
        Log.e("onTouchEvent", "c1 = " + c1 + ",c2 = " + c2);
        if (c1 > 50 && c2 > 50) {
            return -1;
        } else if (c1 < c2) {
            return 1;
        }
        return 2;
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setTextSize(50);

        startPointF = new PointF(0, 0);
        endPointF = new PointF(0, 0);
        controlPointF = new PointF(0, 0);
        control2PointF = new PointF(0, 0);
    }
}
