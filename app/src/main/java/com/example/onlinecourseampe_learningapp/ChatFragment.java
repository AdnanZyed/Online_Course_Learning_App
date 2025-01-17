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
import java.util.List;

public class ChatFragment extends Fragment {

    private My_View_Model chatViewModel;
    private ChatAdapter chatAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewChat);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        chatAdapter = new ChatAdapter(requireContext());
        recyclerView.setAdapter(chatAdapter);

        chatViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        String currentUsername = "aDNAN@1234"; // استبدل باسم المستخدم الحالي
        chatViewModel.getAllStudentsExceptCurrent(currentUsername).observe(getViewLifecycleOwner(), students -> {
            chatAdapter.setStudents(students);
        });

        return view;
    }
}
