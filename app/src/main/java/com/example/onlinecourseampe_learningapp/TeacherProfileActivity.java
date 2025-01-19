package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TeacherProfileActivity extends AppCompatActivity {
    String name;
    String username;
    String reviews;
    private My_View_Model myViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        TabLayout tab = findViewById(R.id.tab);
        ViewPager2 Pager = findViewById(R.id.Pager);

        ImageView imageView = findViewById(R.id.imag_profile);
        TextView Name = findViewById(R.id.teacher_name);
        TextView coursesCount = findViewById(R.id.textView1);
        TextView studentsCount = findViewById(R.id.textView2);
        TextView reviewsCount = findViewById(R.id.textView3);
        TextView Magor = findViewById(R.id.magor);
        Bundle bundle = getIntent().getExtras();
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        if (bundle != null) {


            name = bundle.getString("TEACHER_NAME_TEXT_VIEW");
            username = bundle.getString("TEACHER_USER_NAME_TEXT_VIEW");
            String education = bundle.getString("EDUCATION_TEXT_VIEW");
            Name.setText(name);
            Magor.setText(education);
            byte[] bitmapBytes = bundle.getByteArray("BITMAP");
            if (bitmapBytes != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
                imageView.setImageBitmap(bitmap);
            }

        }
        myViewModel.getStudentsByTeacher(username).observe(this, students -> {
            int studentSize = students.size();
            String studentSizeS = studentSize + "";
            studentsCount.setText(studentSizeS);
        });
        myViewModel.getAllReviewsByCourseIdT(username).observe(this, reviews -> {

            int reviewsSise = reviews.size();
            String studentSizeS = reviewsSise + "";
            reviewsCount.setText(studentSizeS);
        });

        myViewModel.getAllCoursesByTeacher_USER_Name(username).observe(this, courses -> {
            int coursesSize = courses.size();
            String coursesSizeS = coursesSize + "";
            coursesCount.setText(coursesSizeS);
        });
//        myViewModel.getAllReviewsByCourseId(username).observe(this, reviews -> {
//            int reviewsSize = reviews.size();
//            String reviewsSizeS = reviewsSize + "";
//            reviewsCount.setText(reviewsSizeS);
//        });


        // إعداد الـ ViewPager مع الـ Adapter
        Tab_profile_Adapter adapter = new Tab_profile_Adapter(this);
        Pager.setAdapter(adapter);

        // ربط TabLayout مع ViewPager2
        new TabLayoutMediator(tab, Pager, (tabL, position) -> {
            switch (position) {
                case 0:
                    tabL.setText("Courses");
                    break;
                case 1:
                    tabL.setText("Students");
                    break;
                case 2:
                    tabL.setText("Reviews");
                    break;
            }
        }).attach();


    }

}