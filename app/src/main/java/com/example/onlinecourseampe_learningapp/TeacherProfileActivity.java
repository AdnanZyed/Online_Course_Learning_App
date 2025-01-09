package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TeacherProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        TabLayout tab = findViewById(R.id.tab);
        ViewPager2 Pager = findViewById(R.id.Pager);
        // إعداد الـ ViewPager مع الـ Adapter
        TabPagerAdapter adapter = new TabPagerAdapter(this);
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