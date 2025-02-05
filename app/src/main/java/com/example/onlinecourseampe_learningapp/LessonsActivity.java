package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        String user = getIntent().getStringExtra("USER");


        TabLayout tabLayout = findViewById(R.id.tabLayout_lessons);
        ViewPager2 viewPager = findViewById(R.id.viewPager_lessons);
        ImageView imageView = findViewById(R.id.back_icon_lesson);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ViewPagerAdapterLessons adapter = new ViewPagerAdapterLessons(this, courseId, user);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Lessone");
                    break;
                case 1:
                    tab.setText("Certificate");
                    break;
            }
        }).attach();

    }
}