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

public class EnrollCourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course);
        Button button = findViewById(R.id.bt_buy1);
        Button button1 = findViewById(R.id.bt_buy);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollCourseActivity.this, EnrollCodeActivity.class);
                startActivity(intent);
            }
        });

    }
}