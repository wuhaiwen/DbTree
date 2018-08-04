package com.whw.dbtree.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whw.dbtree.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuhaiwen
 * @date 2018/8/1 0001
 */
public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.MyViewHolder>{

    Context context;
    List<String> data;
    LayoutInflater inflater;

    public MainRecycleViewAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_blog,parent,false);
        MainRecycleViewAdapter.MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }



    @Override
    public int getItemCount() {
        return data.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv)
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
