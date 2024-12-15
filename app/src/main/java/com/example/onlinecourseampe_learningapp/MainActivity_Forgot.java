package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainForgotBinding;

public class MainActivity_Forgot extends AppCompatActivity {
    private ActivityMainForgotBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainForgotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}