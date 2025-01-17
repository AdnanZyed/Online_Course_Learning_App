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

public class LessonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);
        int courseId = getIntent().getIntExtra("COURSE_ID", -1);


        // استخدم courseId لجلب البيانات من قاعدة البيانات أو عرضها مباشرة
        // الربط بين TabLayout و ViewPager2
        TabLayout tabLayout = findViewById(R.id.tabLayout_lessons);
        ViewPager2 viewPager = findViewById(R.id.viewPager_lessons);

        // إعداد Adapter لـ ViewPager
        ViewPagerAdapterLessons adapter = new ViewPagerAdapterLessons(this, courseId);
        viewPager.setAdapter(adapter);

        // الربط بين الـ Tabs والـ ViewPager
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("الدروس");
                    break;
                case 1:
                    tab.setText("الشهادات");
                    break;
            }
        }).attach();

    }
}