package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

public class EnrollCourseActivity extends AppCompatActivity {
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course);
        Button btn_buy = findViewById(R.id.bt_buy2);
        Button btn_cart = findViewById(R.id.add_cart);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EnrollCourseActivity.this, EnrollCodeActivity.class);
                startActivity(intent);

            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.getAllTeacherByUser("aDNAN@123").observe(EnrollCourseActivity.this, students -> {


                });
                }
        });
    }}