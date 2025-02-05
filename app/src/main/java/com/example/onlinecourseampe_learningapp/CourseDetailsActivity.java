package com.example.onlinecourseampe_learningapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CourseDetailsActivity extends AppCompatActivity {
    private String userName;
    private TabPagerAdapter adapter;
    private int courseId;
    private ImageView courseImageView;
    private TextView Price_dep;

    private Button bt_Buy;
    private TextView courseNameTextView;
    private TextView courseNameTextView1;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String courseName;
    private TextView priceTextView;
    private String courseDescription;
    private String catigories;
    private String courseUserName;
    private String courseName1;
    private My_View_Model myViewModel;
    int coursePrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        Price_dep = findViewById(R.id.price_dep);
        bt_Buy = findViewById(R.id.bt_buy);
        courseNameTextView = findViewById(R.id.course_name);
        priceTextView = findViewById(R.id.price);
        courseNameTextView1 = findViewById(R.id.course_name1);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager1);
        Price_dep.setPaintFlags(Price_dep.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        courseId = getIntent().getIntExtra("COURSE_ID", -1);
        courseUserName = getIntent().getStringExtra("TEACHER_USER_NAME");
        courseName = getIntent().getStringExtra("COURSE_NAME");
        catigories = getIntent().getStringExtra("COURSE_CATEGORIES");
        courseDescription = getIntent().getStringExtra("COURSE_DESCRIPTION");
        userName = getIntent().getStringExtra("USER");
        courseName1 = getIntent().getStringExtra("COURSE_NAME1");
        coursePrice = getIntent().getIntExtra("COURSE_PRICE", 0);
        byte[] courseImage = getIntent().getByteArrayExtra("COURSE_IMAGE");
        String teacherName = getIntent().getStringExtra("TEACHER_NAME");


        Bundle bundle = new Bundle();
        bundle.putInt("COURSE_ID1", courseId);


        bt_Buy.setText("Enroll Course - $" + coursePrice);
        myViewModel.isStudentCourseExists(userName, courseId, true).observe((this), isHad -> {
            if (isHad) {
                bt_Buy.setVisibility(View.GONE);

            }
        });


        Fragment targetFragment = new ReviewsFragment();
        targetFragment.setArguments(bundle);
        courseNameTextView.setText(courseName);
        courseNameTextView1.setText(catigories);


        adapter = new TabPagerAdapter(this, courseUserName, courseId, userName, courseDescription);
        viewPager.setAdapter(adapter);
        courseImageView = findViewById(R.id.img_course);


        int rival = coursePrice + coursePrice / 4;
        priceTextView.setText(String.format("$%d", coursePrice));
        Price_dep.setText(String.format("$%d", rival));


        if (courseImage != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(courseImage, 0, courseImage.length);
            courseImageView.setImageBitmap(bitmap);
        }


        bt_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(CourseDetailsActivity.this, EnrollCourseActivity.class);
                intent.putExtra("USER", userName);
                intent.putExtra("COURSE_ID", courseId);
                intent.putExtra("PRICE", coursePrice);
                startActivity(intent);
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("About");
                    break;
                case 1:
                    tab.setText("Lessons");
                    break;
                case 2:
                    tab.setText("Reviews");
                    break;
            }
        }).attach();

    }
}