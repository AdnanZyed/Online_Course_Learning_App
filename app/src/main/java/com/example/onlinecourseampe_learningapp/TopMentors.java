package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TopMentors extends AppCompatActivity {
    private RecyclerView recyclerView;
    private My_View_Model myViewModel;
    private ImageView searchIcon;
    private ImageView backIcon;
    private ImageView imageFound;
    private TextView t_sory;
    private TextView t_found;
    private TextView textMentor;
    private EditText eSearsh;
    private String searchQuery;
    private TeacherAdapterMentors teacherAdapter;
    private List<Teacher> teacherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_mentors);

        recyclerView = findViewById(R.id.rv_teachers);
        t_sory = findViewById(R.id.sorry);
        t_found = findViewById(R.id.found);
        imageFound = findViewById(R.id.imageView8);
        searchIcon = findViewById(R.id.search_icon);
        backIcon = findViewById(R.id.back_icon);
        eSearsh = findViewById(R.id.e_searsh);
        textMentor = findViewById(R.id.text_mentor);

        String user = getIntent().getStringExtra("STUDENT_USER");

        eSearsh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchQuery = eSearsh.getText().toString().trim();
                if (searchQuery != null && !searchQuery.isEmpty()) {
                    searchTeachers(searchQuery);
                } else {
                    loadTeacher1();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        teacherAdapter = new TeacherAdapterMentors(this, new ArrayList<>(), user);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(teacherAdapter);

        loadTeacher1();

        searchIcon.setOnClickListener(v -> {
            searchIcon.setVisibility(View.GONE);
            textMentor.setVisibility(View.GONE);
            backIcon.setVisibility(View.VISIBLE);
            eSearsh.setVisibility(View.VISIBLE);
        });

        backIcon.setOnClickListener(v -> {
            eSearsh.setVisibility(View.GONE);
            searchIcon.setVisibility(View.VISIBLE);
            textMentor.setVisibility(View.VISIBLE);
            loadTeacher1();
        });
    }

    private void searchTeachers(String query) {
        myViewModel.searchTeachers(query).observe(this, teachers -> {
            if (teachers != null && !teachers.isEmpty()) {
                teacherAdapter.setTeacher_MonetorsList(teachers);
                recyclerView.setVisibility(View.VISIBLE);
                t_sory.setVisibility(View.GONE);
                t_found.setVisibility(View.GONE);
                imageFound.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                t_sory.setVisibility(View.VISIBLE);
                t_found.setVisibility(View.VISIBLE);
                imageFound.setVisibility(View.VISIBLE);
            }
        });
    }

    public void loadTeacher1() {
        myViewModel.getAllTeacher().observe(this, teachers -> {
            if (teachers != null && !teachers.isEmpty()) {
                teacherAdapter.setTeacher_MonetorsList(teachers);
                recyclerView.setVisibility(View.VISIBLE);
                t_sory.setVisibility(View.GONE);
                t_found.setVisibility(View.GONE);
                imageFound.setVisibility(View.GONE);
            } else {
                recyclerView.setVisibility(View.GONE);
                t_sory.setVisibility(View.VISIBLE);
                t_found.setVisibility(View.VISIBLE);
                imageFound.setVisibility(View.VISIBLE);
            }
        });
    }
}