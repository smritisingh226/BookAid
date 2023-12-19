package com.example.clickshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clickshop.activities.HomeActivity;
import com.example.clickshop.activities.PlaceOrderActivity;

import java.io.Serializable;

public class MyOrderFragment extends Fragment {


    public MyOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_my_order,container,false);

        Handler handler = new Handler();

        // Delay in milliseconds
        int delayMillis = 3000; // 3 seconds

        // Post a delayed action to the handler
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the new activity
                Intent intent = new Intent(getContext(), HomeActivity.class);

                // Start the new activity
                startActivity(intent);

            }
        }, delayMillis);
        return root;
    }

    }