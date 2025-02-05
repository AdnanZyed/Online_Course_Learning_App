package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EnrollCourseActivity extends AppCompatActivity {
    private My_View_Model myViewModel;
    String userName;
    int price;
    int coursed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_course);
        Button btn_buy = findViewById(R.id.bt_buy2);
        ImageView imageView = findViewById(R.id.back_icon_enrollCourse);
        Button btn_cart = findViewById(R.id.add_cart);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        userName = getIntent().getStringExtra("USER");
        price = getIntent().getIntExtra("PRICE", -1);
        coursed = getIntent().getIntExtra("COURSE_ID", -1);

        btn_buy.setText("Enroll Course - $" + price);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnrollCourseActivity.this, EnrollCodeActivity.class);
                intent.putExtra("USER", userName);
                intent.putExtra("COURSE_ID", coursed);
                intent.putExtra("PRICE", price);
                startActivity(intent);

            }
        });

        btn_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myViewModel.isStudentCourseExistsC(userName, coursed, true).observe((EnrollCourseActivity.this), isHadc -> {
                    myViewModel.isStudentCourseExistsB(userName, coursed, true).observe((EnrollCourseActivity.this), isHadb -> {
                        myViewModel.isStudentCourseExists(userName, coursed, true).observe((EnrollCourseActivity.this), isHad -> {

                            if (!isHadc && !isHadb && !isHad) {
                                Student_Course studentCourse = new Student_Course(userName, coursed, false, false, true, 0);
                                myViewModel.insertStudentCourse(studentCourse);
                            } else if (isHadb) {
                                Student_Course studentCourse = new Student_Course(userName, coursed, true, false, true, 0);

                                myViewModel.updateCourseStudent(studentCourse);
                            } else {

                                Log.d("MainActivity_Main", "jl hqhtji hgn hgsgm lsfrh " + price);


                            }

                        });
                    });
                });
            }
        });
    }
}