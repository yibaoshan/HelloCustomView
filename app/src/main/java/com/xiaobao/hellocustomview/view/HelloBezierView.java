package com.xiaobao.hellocustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class HelloBezierView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;
    private PointF startPointF, endPointF, controlPointF;

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

        startPointF.x = w / 2-200;
        startPointF.y = h / 2;

        endPointF.x = w / 2-200;
        endPointF.y = h / 2;

        controlPointF.x = w / 2-200;
        controlPointF.y = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);

        startPointF = new PointF(0, 0);
        endPointF = new PointF(0, 0);
        controlPointF = new PointF(0, 0);
    }
}
