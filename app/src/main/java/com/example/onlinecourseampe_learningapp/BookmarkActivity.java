package com.example.onlinecourseampe_learningapp;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    CourseAdapter adapter;
    String user;
    private List<Course> studentsList1 = new ArrayList<>();

    My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.recycler_view);
        ImageView imageView = findViewById(R.id.back_icon_enrollB);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        user = getIntent().getStringExtra("USER");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
        myViewModel.getBookmarkedCoursesByStudent(user).observe(this, bookmarkedCourses -> {
            if (bookmarkedCourses != null && !bookmarkedCourses.isEmpty()) {
                studentsList1.clear();

                for (Student_Course studentCourse : bookmarkedCourses) {
                    myViewModel.getAllCoursesById(studentCourse.getCourse_ID()).observe(this, courses -> {
                        if (courses != null && !courses.isEmpty()) {
                            studentsList1.addAll(courses);

                            runOnUiThread(() -> {
                                adapter = new CourseAdapter(this, studentsList1, user);
                                recyclerView.setAdapter(adapter);
                            });
                        }
                    });
                }

            } else {
                Toast.makeText(this, "لا توجد كورسات محفوظة!", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
