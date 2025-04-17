package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlineSeasonampe_learningapp.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Activity_part extends AppCompatActivity {

    private ViewPager2 viewPager;
    private Part_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_h);

        viewPager = findViewById(R.id.viewPager);
        Button btnNext = findViewById(R.id.B_Next);
        LinearLayout dotsLayout = findViewById(R.id.dots);

        List<Part_Item> items = new ArrayList<>();
        items.add(new Part_Item(R.drawable.f, " اختيار المحاصيل الأنسب لبيئتك وموسمك، مع تقليل التكاليف والجهد باستخدام بيانات حية وخبرات مزارعين آخرين."));
        items.add(new Part_Item(R.drawable.f, "تجربة زراعية مبتكرة مع تطبيق يتتبع تقدم المحاصيل ويوفر لك أفضل الأوقات للزراعة والحصاد لتحسين إنتاجك بسهولة.\""));
        items.add(new Part_Item(R.drawable.f, "تطبيق ذكي يساعدك في اختيار المحاصيل المثالية بناءً على البيئة والموسم، مع واجهة سهلة وتحديثات مستمرة من خبراء الزراعة"));
        adapter = new Part_Adapter(items);
        viewPager.setAdapter(adapter);

        setupDotsIndicator(items.size(), dotsLayout);

        btnNext.setOnClickListener(v -> {
            if (viewPager.getCurrentItem() < items.size() - 1) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            } else {
                Intent intent = new Intent(this, MainActivity_sign.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setupDotsIndicator(int size, LinearLayout dotsLayout) {
        for (int i = 0; i < size; i++) {
            View dot = new View(this);
            dot.setBackgroundResource(R.drawable.dot_selector);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(16, 16);
            params.setMargins(8, 0, 8, 0);

            dot.setSelected(i == 0);
            dotsLayout.addView(dot, params);

            viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    for (int j = 0; j < dotsLayout.getChildCount(); j++) {
                        dotsLayout.getChildAt(j).setSelected(j == position);
                    }
                }
            });
        }
    }
}


