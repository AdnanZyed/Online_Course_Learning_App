package com.example.onlinecourseampe_learningapp;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // إعداد الـ ViewPager مع الـ Adapter
        TabPagerAdapter adapter = new TabPagerAdapter(this);
        viewPager.setAdapter(adapter);

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