package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatFragment extends Fragment {

    private My_View_Model chatViewModel;
    private ChatAdapter chatAdapter;
    String currentUsername;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        chatViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        currentUsername = getArguments().getString("USER");

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        chatAdapter = new ChatAdapter(requireContext(), currentUsername);
        recyclerView.setAdapter(chatAdapter);


        chatViewModel.getAllStudentsExceptCurrent(currentUsername).observe(getViewLifecycleOwner(), students -> {
            chatAdapter.setStudents(students);
        });

        return view;
    }
}
