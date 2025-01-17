package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class InboxFragment extends Fragment {

    private My_View_Model chatViewModel;
    private ChatAdapter chatAdapter;

    public InboxFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_inbox, container, false);

        // ربط العناصر
        TabLayout tabLayout = view.findViewById(R.id.tabLayout_chat);
        ViewPager2 viewPager = view.findViewById(R.id.viewPager_chat);

        // إعداد الـAdapter
        ViewPagerAdapterChat adapter = new ViewPagerAdapterChat(requireActivity());
        viewPager.setAdapter(adapter);

        // إعداد التبويبات مع TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            // تحديد عناوين التبويبات
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