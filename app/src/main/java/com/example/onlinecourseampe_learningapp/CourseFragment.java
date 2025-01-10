package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseFragment extends Fragment {

    private My_View_Model myViewModel;
    private CourseAdapter courseAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_courses);

        // إعداد RecyclerView
        courseAdapter = new CourseAdapter(requireContext(), new ArrayList<>());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(courseAdapter);

        // إعداد ViewModel
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);

        return view;
    }

    public void loadCourses() {


        // تحميل جميع الكورسات من قاعدة البيانات
        myViewModel.getAllCourse().observe(getViewLifecycleOwner(), courses -> {
            courseAdapter.setCourseList(courses);
            courseAdapter.setOnCourseClickListener(course -> {

                Intent intent = new Intent(requireContext(), CourseDetailsActivity.class);

                intent.putExtra("COURSE_ID", course.getCourse_ID());
                intent.putExtra("TEACHER_USER_NAME", course.getTeacher_USER_Name());
                intent.putExtra("COURSE_NAME", course.getCourse_NAME());
                intent.putExtra("COURSE_PRICE", course.getPrice());
                intent.putExtra("COURSE_IMAGE", course.getImage());
                intent.putExtra("TEACHER_NAME", course.getTeacher_name());


                startActivity(intent);
            });

        });
    }
}
