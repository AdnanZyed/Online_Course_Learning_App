package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainAdminBinding;
import com.example.onlinecourseampe_learningapp.databinding.ActivityMainBinding;

public class MainActivity_Admin extends AppCompatActivity {
    private ActivityMainAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Admin.this, Add_New_Course.class);
                startActivity(intent);
            }
        });

        binding.Card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Admin.this, Delete_Course.class);
                startActivity(intent);
            }
        });
        binding.Card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Admin.this, UpdateCourse1.class);
                startActivity(intent);
            }
        });


    }
}