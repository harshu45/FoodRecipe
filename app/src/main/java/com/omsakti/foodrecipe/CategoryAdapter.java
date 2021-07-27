package com.omsakti.foodrecipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<model_category> arrayList_category;
    public Click_main click_main;

    public CategoryAdapter(Context context, ArrayList<model_category> arrayList_category, Click_main click_main) {
        this.context = context;
        this.arrayList_category = arrayList_category;
        this.click_main = click_main;
    }

    public interface Click_main {
        void itemclick(CardView v, int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(arrayList_category.get(position).getStr_image()).into(holder.img_category);
        holder.txt_category.setText(arrayList_category.get(position).getStr_name());
    }

    @Override
    public int getItemCount() {
        return arrayList_category.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_category;
        TextView txt_category;
        CardView card_main;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_category = itemView.findViewById(R.id.img_category);
            txt_category = itemView.findViewById(R.id.txt_category);
            card_main = itemView.findViewById(R.id.card_main);

            card_main.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click_main.itemclick(card_main, getAdapterPosition());
                }
            });

        }
    }
}
