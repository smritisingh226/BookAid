package com.example.googlemapsactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Country> countryList;



    public CountryAdapter(Context context, ArrayList<Country> countryList) {
        this.context = context;
        this.countryList = countryList;
    }
    @NonNull
    @Override
    public CountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item , parent , false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.ViewHolder holder, int position) {
        Country country = countryList.get(position);
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());
        Glide.with(context).load(country.getUrl()).into(holder.imageView);

        holder.checkInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);

                Bundle bundle = new Bundle();

                bundle.putString("name", country.getName());
                bundle.putString("capital",country.getCapital());
                bundle.putString("flags",country.getUrl());
                bundle.putString("region",country.getRegion());
                bundle.putString("population",country.getPopulation());
                bundle.putString("timezones",country.getTimezone());
                bundle.putString("currencies",country.getCurrency());
                bundle.putDouble("latitude",country.getLatitude());
                bundle.putDouble("longitude",country.getLogitude());



                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, googleApi.class);


                intent.putExtra("longitude",country.getLogitude());
                intent.putExtra("latitude",country.getLatitude());
                intent.putExtra("name", country.getName());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, capital;

        TextView checkInfo, location;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.country_flag);
            name = itemView.findViewById(R.id.country_name);
            capital = itemView.findViewById(R.id.country_capital);
            checkInfo = itemView.findViewById(R.id.country_check_info);
            location = itemView.findViewById(R.id.country_location);


        }
    }


}
