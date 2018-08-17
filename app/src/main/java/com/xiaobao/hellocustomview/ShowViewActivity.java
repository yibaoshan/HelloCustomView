package com.xiaobao.hellocustomview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.widget.LinearLayout;


public class ShowViewActivity extends Activity {

    private LinearLayout linearLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearLayout = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_show_view, null);
        linearLayout.addView(MainActivity.viewList.get(getIntent().getIntExtra(getIntent().getAction(), 0)));
        setContentView(linearLayout);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        linearLayout.removeAllViews();
    }
}
