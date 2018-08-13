package com.xiaobao.hellocustomview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private Context mContext;
    private List<Data> mAdapterData;
    private onItemClickListener mOnItemClickListener;

    public ActivityAdapter(Context mContext) {
        this.mContext = mContext;
        if (this.mAdapterData == null) {
            this.mAdapterData = new ArrayList<>();
        }
    }

    public void addAdapterData(Data data) {
        this.mAdapterData.add(data);
        this.notifyDataSetChanged();
    }

    public void setAdapterData(List<Data> mAdapterData) {
        this.mAdapterData = mAdapterData;
        this.notifyDataSetChanged();
    }

    public List<Data> getAdapterData() {
        return mAdapterData;
    }

    public void setOnItemClickListener(onItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_adapter_activity, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.button.setText(mAdapterData.get(position).title);
        holder.button.setOnClickListener(onClickListener);
        holder.button.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mAdapterData.size();
    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
            }
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        Button button;
        public ViewHolder(View itemView) {
            super(itemView);
            button = itemView.findViewById(R.id.button);
        }
    }

    public interface onItemClickListener {
        void onItemClick(View view, int position);
    }

    public static class Data {
        String title;
        Class aClass;

        public Data(String title, Class aClass) {
            this.title = title;
            this.aClass = aClass;
        }
    }

}
