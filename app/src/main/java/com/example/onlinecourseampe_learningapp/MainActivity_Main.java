package com.example.onlinecourseampe_learningapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_Main extends AppCompatActivity

        implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    Prophile_Fragment prophileFragment = new Prophile_Fragment();
    CoursesFragment coursesFragment = new CoursesFragment();
    CartFragment cartFragment = new CartFragment();
    CourseFragment fragment = (CourseFragment) getSupportFragmentManager()
            .findFragmentById(R.id.flFragment);
    Fragment activeFragment = homeFragment; // الفراجمنت النشط افتراضيًا هو HomeFragment

    InboxFragment inboxFragment = new InboxFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.home);

        // تعيين الفراجمنت الافتراضي عند البداية
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, homeFragment) // عرض HomeFragment بشكل افتراضي
                    .commit();
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        // تحديد الفراجمنت الذي سيتم عرضه بناءً على العنصر المختار

        switch (item.getItemId()) {
            case R.id.prophile:

                selectedFragment = prophileFragment;

                break;

            case R.id.home:
                selectedFragment = homeFragment;

                break;

            case R.id.courses:
                selectedFragment = coursesFragment;

                break;
            case R.id.cart:
                selectedFragment = cartFragment;

                break;
            case R.id.inbox:
                selectedFragment = inboxFragment;

                break;
        }

        // استبدال الفراجمنت الحالي بالفراجمنت المختار
        if (selectedFragment != activeFragment) {

            getSupportFragmentManager().beginTransaction()
                .replace(R.id.flFragment, selectedFragment)
                .commit();
            activeFragment = selectedFragment;  // تحديث الفراجمنت النشط
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        // إذا كان الفراجمنت النشط هو غير HomeFragment، عد إلى HomeFragment
        if (!(activeFragment instanceof HomeFragment)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            activeFragment = homeFragment;  // تحديث الفراجمنت النشط إلى Home
            // تحديث التحديد في BottomNavigationView إلى زر الهوم
            bottomNavigationView.setSelectedItemId(R.id.home);

        } else {
            super.onBackPressed(); // إذا كان بالفعل في HomeFragment، تابع تنفيذ الزر الرجوع المعتاد
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}
