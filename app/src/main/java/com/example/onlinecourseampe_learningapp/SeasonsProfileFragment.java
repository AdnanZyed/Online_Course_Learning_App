package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;


public class SeasonsProfileFragment extends Fragment {
    private My_View_Model myViewModel;
    private SeasonAdapter seasonAdapter;

    public SeasonsProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_seasons_profile, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_Seasons1);
        String expertUserName = getArguments().getString("TEACHER_USER_NAME1");


        seasonAdapter = new SeasonAdapter(requireContext(), new ArrayList<Season>(), "");
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(seasonAdapter);

        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);


        myViewModel.getAllseasonsByExpert_USER_Name(expertUserName).observe(getViewLifecycleOwner(), seasons -> {
            seasonAdapter.setSeasonList(seasons);
            seasonAdapter.setOnSeasonClickListener(season -> {

                Intent intent = new Intent(requireContext(), SeasonDetailsActivity.class);

                intent.putExtra("COURSE_ID", season.getSeason_ID());
                intent.putExtra("TEACHER_USER_NAME", season.getExpert_USER_Name());
                intent.putExtra("COURSE_NAME", season.getSeason_NAME());
                intent.putExtra("COURSE_PRICE", season.getPrice());
                intent.putExtra("COURSE_IMAGE", season.getImage());
                intent.putExtra("TEACHER_NAME", season.getExpert_name());
                intent.putExtra("COURSE_CATEGORIES", season.getCategorie());
                intent.putExtra("COURSE_DESCRIPTION", season.getDescription());


                startActivity(intent);
            });

        });


        return view;
    }
}