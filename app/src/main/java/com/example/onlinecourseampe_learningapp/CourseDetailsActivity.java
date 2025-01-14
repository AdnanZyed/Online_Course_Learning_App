package com.example.onlinecourseampe_learningapp;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CourseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);


        TextView Price_dep = findViewById(R.id.price_dep);
        TextView Dollar_dep = findViewById(R.id.dollar_dep);
        Price_dep.setPaintFlags(Price_dep.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        Dollar_dep.setPaintFlags(Dollar_dep.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager1);


        // استرجاع البيانات
        int courseId = getIntent().getIntExtra("COURSE_ID", -1);
        String courseUserName = getIntent().getStringExtra("TEACHER_USER_NAME");

        String courseName = getIntent().getStringExtra("COURSE_NAME");
        String courseName1 = getIntent().getStringExtra("COURSE_NAME1");
        int coursePrice = getIntent().getIntExtra("COURSE_PRICE", 0);
        byte[] courseImage = getIntent().getByteArrayExtra("COURSE_IMAGE");
        String teacherName = getIntent().getStringExtra("TEACHER_NAME");

        Bundle bundle = new Bundle();
        bundle.putInt("COURSE_ID1", courseId);


// إنشاؤ Fragment الجديد
        Fragment targetFragment = new ReviewsFragment();

// ضبط البيانات المرسلة على الـ Fragment
        targetFragment.setArguments(bundle);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME12");


//        String userName = getIntent().getStringExtra("USER_NAME12");
        Log.d("MainActivity_Main", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM " + userName);

        // إعداد الـ ViewPager مع الـ Adapter
        TabPagerAdapter adapter = new TabPagerAdapter(this, courseUserName, courseId,userName );
        viewPager.setAdapter(adapter);



        Log.d("CourseDetailsActivity", "VVVVVVVVVVVVVVVVVVVVVVVVVVV" + courseId + userName);


        // تعيين البيانات في الواجهة
        TextView courseNameTextView = findViewById(R.id.course_name);
        TextView courseNameTextView1 = findViewById(R.id.course_name1);
        courseNameTextView.setText(courseName);
        courseNameTextView1.setText(courseName1);

        TextView priceTextView = findViewById(R.id.price);
        priceTextView.setText(String.format("$%d", coursePrice));

        ImageView courseImageView = findViewById(R.id.img_course);
        if (courseImage != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(courseImage, 0, courseImage.length);
            courseImageView.setImageBitmap(bitmap);
        }


        // ربط TabLayout مع ViewPager2
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