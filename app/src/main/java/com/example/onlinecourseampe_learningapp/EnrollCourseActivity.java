package com.example.onlinecourseampe_learningapp;

import android.annotation.SuppressLint;
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
    private boolean is;
    private boolean is2;
    int coursed;

    @SuppressLint("ResourceAsColor")
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
//        myViewModel.isStudentCourseExists(userName, coursed, true).observe((EnrollCourseActivity.this), isHad -> {
//            is = isHad;
//        });
        myViewModel.isStudentCourseExistsC(userName, coursed, true).observe((EnrollCourseActivity.this), isHadc -> {
            if (isHadc) {
                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                btn_cart.setTextColor(R.color.white);
                btn_cart.setText("Delete Card");
            }


        });
//        if (is) {
//            btn_cart.setVisibility(View.GONE);
//            btn_buy.setVisibility(View.GONE);
//
//        }


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
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                myViewModel.isStudentCourseExistsC(userName, coursed, true).observe((EnrollCourseActivity.this), isHadc -> {
                    myViewModel.isStudentCourseExistsB(userName, coursed, true).observe((EnrollCourseActivity.this), isHadb -> {
                        myViewModel.isStudentCourseExists(userName, coursed, true).observe((EnrollCourseActivity.this), isHad -> {

                            if (!isHadc && !isHadb && !isHad) {
                                Student_Course studentCourse = new Student_Course(userName, coursed, false, false, true, 0);
                                myViewModel.insertStudentCourse(studentCourse);
                                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                                btn_cart.setTextColor(R.color.white);
                                btn_cart.setText("Delete Card");
                            } else if (isHadb && !isHadc) {
                                Student_Course studentCourse = new Student_Course(userName, coursed, true, false, true, 0);
                                btn_cart.setBackgroundResource(R.drawable.delete_btn);
                                btn_cart.setTextColor(R.color.white);
                                btn_cart.setText("Delete Card");
                                myViewModel.updateCourseStudent(studentCourse);
                            } else if (isHadc&&!isHadb&&!isHad) {
                                myViewModel.deleteStudentCourseByUserAndCourse(userName,coursed);
                                btn_cart.setBackgroundResource(R.drawable.btn_cart);
                                btn_cart.setTextColor(R.color.blue);
                                btn_cart.setText("Add New Card");

                            }

                        });
                    });
                });
            }
        });
    }
}