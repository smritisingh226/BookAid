package com.example.clickshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.clickshop.R;
import com.example.clickshop.adapters.NavCategoryAdapter;
import com.example.clickshop.adapters.NavCategoryDetailedAdapter;
import com.example.clickshop.models.HomeCategory;
import com.example.clickshop.models.NavCategoryDetailedModel;
import com.example.clickshop.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class NavCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<NavCategoryDetailedModel> navCategoryDetailedModelList;
    NavCategoryDetailedAdapter adapter;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_category);

        db = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.nav_cat_det_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        navCategoryDetailedModelList = new ArrayList<>();
        adapter = new NavCategoryDetailedAdapter(this, navCategoryDetailedModelList);

        recyclerView.setAdapter(adapter);


        //Getting Makeup
        if(type != null && type.equalsIgnoreCase("Jewellery")){
            db.collection("NavCategoryDetailed").whereEqualTo("type",
                    "Jewellery").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()){
                        NavCategoryDetailedModel viewAllModel = documentSnapshot.toObject(NavCategoryDetailedModel.class);
                        navCategoryDetailedModelList.add(viewAllModel);
                        adapter.notifyDataSetChanged();
                    }
                }
            });
        }
    }
}