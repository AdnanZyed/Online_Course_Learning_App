package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        recyclerView = findViewById(R.id.recycler_view);

// تهيئة ViewModel
        My_View_Model myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        // ملاحظة LiveData لمتابعة التغييرات
        myViewModel.getBookmarkedCourses().observe(this, bookmarkedCourses -> {
            if (bookmarkedCourses != null) {
            // إعداد الـ Adapter بعد الحصول على البيانات
            CourseAdapter adapter = new CourseAdapter(this, bookmarkedCourses);
            recyclerView.setAdapter(adapter);
              }
        });


    }
}