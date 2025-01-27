package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EnrollCourseActivity extends AppCompatActivity {
    private My_View_Model myViewModel;
    String userName;
    int coursed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course);
        Button btn_buy = findViewById(R.id.bt_buy2);
        Button btn_cart = findViewById(R.id.add_cart);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        userName = getIntent().getStringExtra("USER");
        coursed = getIntent().getIntExtra("COURSE_ID", -1);

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollCourseActivity.this, EnrollCodeActivity.class);
                intent.putExtra("USER", userName);
                intent.putExtra("COURSE_ID", coursed);
                startActivity(intent);

            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewModel.isStudentCourseExists(userName,coursed,false,true,false).observe((EnrollCourseActivity.this), isHad -> {
                    if (!isHad) {
                        Student_Course studentCourse = new Student_Course(0, userName, coursed, false, true, false);
                        myViewModel.insertStudentCourse(studentCourse);
                    }
                });
            }
        });
    }
}