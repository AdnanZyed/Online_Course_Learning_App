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

import java.util.ArrayList;


public class CoursesProfileFragment extends Fragment {
    private My_View_Model myViewModel;
    private CourseAdapter courseAdapter;

    public CoursesProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.fragment_courses_profile, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rv_courses1);
        String teacherUserName = getArguments().getString("TEACHER_USER_NAME1");


        courseAdapter = new CourseAdapter(requireContext(), new ArrayList<>(),"");
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(courseAdapter);

        // إعداد ViewModel
        myViewModel = new ViewModelProvider(requireActivity()).get(My_View_Model.class);



            // تحميل جميع الكورسات من قاعدة البيانات
            myViewModel.getAllCoursesByTeacher_USER_Name(teacherUserName).observe(getViewLifecycleOwner(), courses -> {
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


        return view;
    }
}