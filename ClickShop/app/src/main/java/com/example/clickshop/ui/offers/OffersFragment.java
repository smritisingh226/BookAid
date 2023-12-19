package com.example.clickshop.ui.offers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.clickshop.R;
import com.example.clickshop.adapters.NavCategoryAdapter;
import com.example.clickshop.adapters.NavCategoryDetailedAdapter;
import com.example.clickshop.adapters.OfferAdapter;
import com.example.clickshop.models.NavCategoryDetailedModel;
import com.example.clickshop.models.NavCategoryModel;
import com.example.clickshop.models.OfferModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class OffersFragment extends Fragment {

    FirebaseFirestore db;
    RecyclerView recyclerView;
    List<OfferModel> offerModelList;
    OfferAdapter offerAdapter;



    public OffersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_offers, container, false);

        recyclerView = root.findViewById(R.id.offer_rec);

        //Nav Fragment Category
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        db = FirebaseFirestore.getInstance();
        offerModelList = new ArrayList<>();
        offerAdapter = new OfferAdapter(getActivity(), offerModelList);
        recyclerView.setAdapter(offerAdapter);

        db.collection("OfferCategory")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                OfferModel offerModel = document.toObject(OfferModel.class);
                                offerModelList.add(offerModel);
                                offerAdapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error" +task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;

    }

}