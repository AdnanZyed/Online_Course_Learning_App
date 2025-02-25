package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class CoursesFragment extends Fragment {


    public CoursesFragment() {
    }

    private RecyclerView recyclerView;
    private My_View_Model viewModel;

    private My_Database myDatabase;
    String students_u;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        ImageView imageView = view.findViewById(R.id.back_icon_enrollC);
        Bundle args = getArguments();
        if (args != null) {
            students_u = args.getString("USER_NAME");


        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(requireActivity(), 1, students_u);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Ongoing");
                    break;
                case 1:
                    tab.setText("Completed");
                    break;
            }
        }).attach();
        return view;
    }
}