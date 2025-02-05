package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class CallFragment extends Fragment {


    private My_View_Model chatViewModel;
    private CallAdapter chatAdapter;
    String currentUsername;

    public CallFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_call, container, false);


        chatViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewCall);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        currentUsername = getArguments().getString("USER");

        chatAdapter = new CallAdapter(requireContext(), currentUsername);
        recyclerView.setAdapter(chatAdapter);


        chatViewModel.getAllStudentsExceptCurrent(currentUsername).observe(getViewLifecycleOwner(), students -> {
            chatAdapter.setStudents(students);

        });
        return view;
    }
}