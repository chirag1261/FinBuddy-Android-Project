package com.example.finbuddy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.finbuddy.R;

import java.util.ArrayList;

public class sliderAdapter extends RecyclerView.Adapter<sliderAdapter.ViewHolder> {

    @SuppressWarnings("unused")
    private final Context mContext;
    private ArrayList<Integer> mProductDatas;
    ViewPager2 viewPager2;


    public sliderAdapter(Context context, ArrayList<Integer> arr , ViewPager2 viewPager2) {
        mContext = context;
        mProductDatas = arr;
        this.viewPager2 = viewPager2;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.single_view_pager, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
         Integer book = mProductDatas.get(position);
        holder.imageView.setImageDrawable(ContextCompat.getDrawable(mContext , book));
    }

    @Override
    public int getItemCount() {
        return mProductDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

          ImageView imageView ;

        private ViewHolder(View itemView) {
            super(itemView);
           imageView = itemView.findViewById(R.id.photos);
        }
    }

}
