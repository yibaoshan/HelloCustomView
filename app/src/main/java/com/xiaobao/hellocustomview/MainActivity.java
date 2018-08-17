package com.xiaobao.hellocustomview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaobao.hellocustomview.view.HelloBezierView;
import com.xiaobao.hellocustomview.view.HelloPathView;
import com.xiaobao.hellocustomview.view.HelloWaveView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ActivityAdapter mActivityAdapter;

    public static List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mActivityAdapter = new ActivityAdapter(this);
        mActivityAdapter.setOnItemClickListener(onItemClickListener);
        mRecyclerView.setAdapter(mActivityAdapter);

        addView("Path之基本操作", new HelloPathView(this));
        addView("Path之贝塞尔曲线", new HelloBezierView(this));
        addView("Path之贝塞尔曲线-波浪", new HelloWaveView(this));
    }

    private void addView(String title, View view) {
        if(viewList==null){
            viewList = new ArrayList<>();
        }
        viewList.add(view);
        mActivityAdapter.addAdapterData(title);
    }

    private ActivityAdapter.onItemClickListener onItemClickListener = new ActivityAdapter.onItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(MainActivity.this, ShowViewActivity.class);
            intent.setAction(mActivityAdapter.getAdapterData().get(position));
            intent.putExtra(mActivityAdapter.getAdapterData().get(position), position);
            startActivity(intent);
        }
    };
}
