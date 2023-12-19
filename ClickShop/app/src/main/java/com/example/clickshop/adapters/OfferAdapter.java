package com.example.clickshop.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.clickshop.R;
import com.example.clickshop.activities.NavCategoryActivity;
import com.example.clickshop.activities.ViewAllActivity;
import com.example.clickshop.models.NavCategoryModel;
import com.example.clickshop.models.OfferModel;
import com.example.clickshop.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder>{

    Context context;
    List<OfferModel> offerModelList;

    public OfferAdapter(Context context, List<OfferModel> offerModelList) {
        this.context = context;
        this.offerModelList = offerModelList;
    }

    @NonNull
    @Override
    public OfferAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OfferAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(offerModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(offerModelList.get(position).getName());

        String typeposition = offerModelList.get(position).getType();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NavCategoryActivity.class);
                intent.putExtra("type", typeposition);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return offerModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.offer_img);
            name = itemView.findViewById(R.id.offer_name);
        }
    }
}
