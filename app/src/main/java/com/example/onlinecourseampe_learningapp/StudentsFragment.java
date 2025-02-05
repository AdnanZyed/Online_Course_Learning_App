package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinecourseampe_learningapp.R;
import com.example.onlinecourseampe_learningapp.StudentsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StudentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Student_Teacher> studentsList = new ArrayList<>();
    private List<Student> studentsList1 = new ArrayList<>();

    private StudentsAdapter adapter;

    My_View_Model myViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_students, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        String teacherUserName = getArguments().getString("TEACHER_USER_NAME1");


        myViewModel.getStudentsByTeacher(teacherUserName).observe((LifecycleOwner) requireContext(), studentTeachers -> {
            if (studentTeachers != null && !studentTeachers.isEmpty()) {
                studentsList1.clear();

                for (Student_Teacher studentTeacher : studentTeachers) {
                    myViewModel.getAllStudentByUser(studentTeacher.getStudentUserName()).observe((LifecycleOwner) requireContext(), student2 -> {
                        if (student2 != null && !student2.isEmpty()) {
                            studentsList1.addAll(student2);

                            if (studentsList1.size() == studentTeachers.size()) {
                                adapter = new StudentsAdapter(studentsList1, requireContext());
                                recyclerView.setAdapter(adapter);
                            }
                        }
                    });
                }
            } else {
                Toast.makeText(requireContext(), "No students found", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
