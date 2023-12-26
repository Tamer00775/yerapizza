package com.example.yerapizza.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yerapizza.R;
import com.example.yerapizza.activity.DetailActivity1;
import com.example.yerapizza.data.Pizza;

import java.util.List;

public class BestDealAdapter extends RecyclerView.Adapter<BestDealAdapter.ViewHolder> {

    List<Pizza> pizzaArrayList;
    Context context;

    public BestDealAdapter(List<Pizza> items) {
        this.pizzaArrayList = items;
    }

    @NonNull
    @Override
    public BestDealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.best_deal,
                parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull BestDealAdapter.ViewHolder holder, int position) {
        holder.titleText.setText(pizzaArrayList.get(position).getName());
        holder.priceText.setText(pizzaArrayList.get(position).getPrice() + "$/kg");

        int drawableResourceId = holder.itemView.getResources()
                .getIdentifier(pizzaArrayList.get(position).getImagePath(),
                        "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(context)
                .load(drawableResourceId)
                .into(holder.pic);
        holder.itemView.setOnClickListener(v -> {
            Intent intent=new Intent(context, DetailActivity1.class);
            intent.putExtra("object", pizzaArrayList.get(position));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pizzaArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText, priceText;
        ImageView pic;

        public ViewHolder(@NonNull View item) {
            super(item);
            titleText = item.findViewById(R.id.titleTxt);
            priceText = item.findViewById(R.id.priceTxt);
            pic = item.findViewById(R.id.img1);
        }
    }
}
