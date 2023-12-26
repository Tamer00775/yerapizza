package com.example.yerapizza.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yerapizza.R;
import com.example.yerapizza.data.Pizza;

import java.io.PipedInputStream;
import java.util.ArrayList;
import java.util.List;

public class SimilarAdapter extends RecyclerView.Adapter<SimilarAdapter.viewholder> {
    List<Pizza> items;
    Context context;

    public SimilarAdapter(List<Pizza> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public SimilarAdapter.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder,parent, false);
        return new viewholder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarAdapter.viewholder holder, int position) {
        int drawableReourceId=holder.itemView.getResources()
                .getIdentifier(items.get(position).getImagePath(),
                        "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableReourceId)
                .into(holder.pic);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView pic;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            pic=itemView.findViewById(R.id.img);
        }
    }
}
