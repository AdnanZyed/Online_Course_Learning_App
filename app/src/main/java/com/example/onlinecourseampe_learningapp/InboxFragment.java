package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class InboxFragment extends Fragment {


    String students_u;

    public InboxFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout_chat);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager_chat);
        Bundle args = getArguments();
        if (args != null) {
            students_u = args.getString("USER_NAME");


        }

        ViewPagerAdapterChat adapter = new ViewPagerAdapterChat(requireActivity(), students_u);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Chat");
                    break;
                case 1:
                    tab.setText("Call");
                    break;
            }
        }).attach();
        return view;
    }
}