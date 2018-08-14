package com.xiaobao.hellocustomview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiaobao.hellocustomview.view.HelloBezierView;
import com.xiaobao.hellocustomview.view.HelloPathView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ActivityAdapter mActivityAdapter;

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

        mActivityAdapter.addAdapterData(new ActivityAdapter.Data("Path之基本操作", HelloPathView.class));
        mActivityAdapter.addAdapterData(new ActivityAdapter.Data("Path之贝塞尔曲线", HelloBezierView.class));
    }

    private ActivityAdapter.onItemClickListener onItemClickListener = new ActivityAdapter.onItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(MainActivity.this, ShowViewActivity.class);
            intent.setAction(mActivityAdapter.getAdapterData().get(position).title);
            intent.putExtra(mActivityAdapter.getAdapterData().get(position).title, mActivityAdapter.getAdapterData().get(position).aClass.getSimpleName());
            startActivity(intent);
        }
    };
}
