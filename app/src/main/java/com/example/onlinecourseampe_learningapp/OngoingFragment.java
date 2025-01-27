package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class OngoingFragment extends Fragment {
    RecyclerView recyclerView;
    private My_View_Model viewModel;
    String user;
    int coursId;
    private My_Database myDatabase;

    public OngoingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        recyclerView = view.findViewById(R.id.rv_ongoing);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        if (getArguments() != null) {

             coursId = getArguments().getInt("COURSE_ID");
            user = getArguments().getString("USER");
        }
        viewModel = new ViewModelProvider(this).get(My_View_Model.class);

        viewModel.getisRegisterCoursesByStudent1(user).observe(getViewLifecycleOwner(), studentCourses -> {

            List<Integer> courseIds = new ArrayList<>();
            for (Student_Course sc : studentCourses) {
                    courseIds.add(sc.getCourse_ID());

            }

            viewModel.getAllCoursesByIds(courseIds).observe(getViewLifecycleOwner(), courses -> {
                CoursesAdapter adapter = new CoursesAdapter(courses, requireContext());
                recyclerView.setAdapter(adapter);

            });
        });


        return view;
    }
}