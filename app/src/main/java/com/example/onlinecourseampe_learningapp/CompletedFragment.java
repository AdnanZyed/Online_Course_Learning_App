package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CompletedFragment extends Fragment {
    private RecyclerView recyclerView;
    private My_View_Model viewModel;
    private String user;
    private int coursId;
    private List<Course> ongoingCourses = new ArrayList<>();
    private CoursesAdapter adapter;

    public CompletedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ongoing, container, false);
        recyclerView = view.findViewById(R.id.rv_ongoing);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        if (getArguments() != null) {
            coursId = getArguments().getInt("COURSE_ID");
            user = getArguments().getString("USER");
        }

        viewModel = new ViewModelProvider(this).get(My_View_Model.class);

        adapter = new CoursesAdapter(ongoingCourses, requireContext(), user);
        recyclerView.setAdapter(adapter);

        loadOngoingCourses();

        return view;
    }

    private void loadOngoingCourses() {
        viewModel.getisRegisterCoursesByStudent1(user).observe(getViewLifecycleOwner(), studentCourses -> {
            List<Integer> courseIds = new ArrayList<>();
            for (Student_Course sc : studentCourses) {
                courseIds.add(sc.getCourse_ID());
            }

            viewModel.getAllCoursesByIds(courseIds).observe(getViewLifecycleOwner(), courses -> {
                List<Course> tempOngoingCourses = new ArrayList<>();

                for (Course course : courses) {
                    viewModel.getTotalLessonsCountByCourseId(course.getCourse_ID()).observe(getViewLifecycleOwner(), totalLessons -> {
                        viewModel.getCompletedLessonsCountByCourseId(course.getCourse_ID()).observe(getViewLifecycleOwner(), completedLessons -> {
                            if (completedLessons == totalLessons) {
                                tempOngoingCourses.add(course);
                            }

                            if (tempOngoingCourses.size() > 0) {
                                ongoingCourses.clear();
                                ongoingCourses.addAll(tempOngoingCourses);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    });
                }
            });
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadOngoingCourses();
    }
}
