package com.xiaobao.hellocustomview;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SpeedView extends View {

    private Paint mPaint;
    private int mWidth, mHeight;

    public SpeedView(Context context) {
        super(context);
    }

    public SpeedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


}
