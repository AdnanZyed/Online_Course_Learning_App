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

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    RecyclerView recyclerView;
    String students_u;

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
            students_u = args.getString("USER_NAME");
        }

        recyclerView = view.findViewById(R.id.recycler_view_Cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


        myViewModel.getisAddCartCoursesByStudent(students_u).observe((LifecycleOwner) getViewLifecycleOwner(), AddCart -> {


            List<Integer> courseIds = new ArrayList<>();
            for (Student_Course sc : AddCart) {
                if (!sc.isAddCart() || !sc.isRegister()) {
                    courseIds.add(sc.getCourse_ID());


                }
            }

            myViewModel.getAllCoursesByIds(courseIds).observe(getViewLifecycleOwner(), courses -> {

                CourseAdapter adapter = new CourseAdapter(requireContext(), courses, students_u);
                recyclerView.setAdapter(adapter);

            });
        });


        return view;
    }
}