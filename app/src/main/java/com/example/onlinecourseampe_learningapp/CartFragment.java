package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    String farmers_u;

    My_View_Model myViewModel;

    public CartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        Bundle args = getArguments();
        if (args != null) {
            farmers_u = args.getString("USER_NAME");
        }

        recyclerView = view.findViewById(R.id.recycler_view_Cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        myViewModel.getisAddCartSeasonsByFarmer(farmers_u).observe((LifecycleOwner) getViewLifecycleOwner(), AddCart -> {


            List<Integer> seasonIds = new ArrayList<>();
            for (Farmer_Seasons sc : AddCart) {
                if (!sc.isAddCart() || !sc.isRegister()) {
                    seasonIds.add(sc.getSeason_ID());


                }
            }

            myViewModel.getAllSeasonsByIds(seasonIds).observe(getViewLifecycleOwner(), seasons -> {

                SeasonsAdapter adapter = new SeasonsAdapter(seasons, requireContext(), farmers_u);
                recyclerView.setAdapter(adapter);

            });
        });


        return view;
    }
}