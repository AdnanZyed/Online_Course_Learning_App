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
import android.widget.Toast;

public class LessonsFragment extends Fragment {


    public LessonsFragment() {
        // Required empty public constructor
    }

    My_View_Model myViewModel;
    int coursId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        if (getArguments() != null) {

             coursId = getArguments().getInt("COURSE_ID");
        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_lessons);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        // قم بجلب البيانات من ViewModel
        myViewModel.getLessonsByCourseId(coursId).observe(getViewLifecycleOwner(), lessons -> {

            CourseLessonsAdapter adapter = new CourseLessonsAdapter(myViewModel,lessons);
            recyclerView.setAdapter(adapter);

        });


        return view;
    }
}