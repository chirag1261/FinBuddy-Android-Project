package com.example.finbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finbuddy.R;


import java.util.List;

public class LoanAdapter extends RecyclerView.Adapter<LoanAdapter.ViewHolder>{
    List<String> list;
    Context ctx;
    public LoanAdapter(List<String> list, Context ctx) {
        this.list=list;
        this.ctx=ctx;
    }

    @NonNull
    @Override
    public LoanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listviewtext,parent,false);
        return new LoanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanAdapter.ViewHolder holder, int position) {
        String s=list.get(position);
        holder.tV.setText(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tV=itemView.findViewById(R.id.textView);
        }
    }
}
