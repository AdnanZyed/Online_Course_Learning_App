package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CompletedFragment extends Fragment {

RecyclerView recyclerView;

    public CompletedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view=inflater.inflate(R.layout.fragment_completed, container, false);

     recyclerView=view.findViewById(R.id.rv_completed);

         return view;
    }

}