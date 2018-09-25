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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelloChartView extends View {

    private int mWidht, mHeight;
    private List<String> xData;
    private List<Double> yData;
    private List<PointF> pointFS;

    private Paint pointPaint;
    private Paint linePaint;
    private Paint pathPaint;

    public HelloChartView(Context context) {
        super(context);
    }

    public HelloChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mWidht = w;
        this.mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initData();
        canvas.translate(0, mHeight);
        canvas.drawColor(Color.rgb(30,180,134));
        Path path = new Path();
        path.moveTo(0,0);
        for (int i = 0; i < pointFS.size(); i++) {
            if(i==0){
                path.quadTo(0,0, pointFS.get(i).x, pointFS.get(i).y+10);
            }
            canvas.drawCircle(pointFS.get(i).x, pointFS.get(i).y, 10, pointPaint);
            if (i != pointFS.size() - 1) {
                canvas.drawLine(pointFS.get(i).x, pointFS.get(i).y, pointFS.get(i+1).x, pointFS.get(i+1).y, linePaint);
                path.quadTo(pointFS.get(i).x, pointFS.get(i).y+10, pointFS.get(i+1).x, pointFS.get(i+1).y+10);
            }else {
                path.quadTo(pointFS.get(i).x, pointFS.get(i).y, mWidht,0);
            }
        }
        path.close();
        canvas.drawPath(path,pathPaint);
    }

    private void initData() {
        float yMax = 0, xAvg, yAvg;
        xData = new ArrayList<>();
        yData = new ArrayList<>();
        pointFS = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            xData.add(i + "日");
            yData.add(new Random().nextInt(mHeight) + 0.0);
        }

        for (double d : yData) {
            if (d > yMax) {
                yMax = (float) d;
            }
        }

        xAvg = mWidht / (xData.size()-1);
        yAvg = yMax / mHeight;

        for (int i = 0; i < yData.size(); i++) {
            pointFS.add(new PointF(xAvg * i, (float) (yAvg * yData.get(i) * -1)));
        }

        pointPaint = new Paint();
        pointPaint.setColor(Color.WHITE);
        pointPaint.setStrokeWidth(50);
        pointPaint.setStyle(Paint.Style.FILL);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);

        pathPaint = new Paint();
        pathPaint.setColor(Color.argb(30,220,220,220));
        pathPaint.setStrokeWidth(5);
        pathPaint.setStyle(Paint.Style.FILL);
    }
}
