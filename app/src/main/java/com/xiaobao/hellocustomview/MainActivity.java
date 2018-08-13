package com.xiaobao.hellocustomview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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

        for (int i = 0; i < 10; i++) {
            mActivityAdapter.addAdapterData(new ActivityAdapter.Data("自定义View基础-坐标系", null));
        }
    }

    private ActivityAdapter.onItemClickListener onItemClickListener = new ActivityAdapter.onItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Toast.makeText(MainActivity.this,mActivityAdapter.getAdapterData().get(position).title+","+position,Toast.LENGTH_SHORT).show();
        }
    };
}
