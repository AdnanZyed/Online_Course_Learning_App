package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    boolean showCustomNav;
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

        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME2");
        Log.d("MainActivity_Main", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM " + userName);

        HomeFragment homeFragment = new HomeFragment();
        CoursesFragment coursesFragment1 = new CoursesFragment();

        Bundle bundle = new Bundle();

        bundle.putString("USER_NAME", userName);

// إنشاء كائن Fragment

// تمرير البيانات إلى الـ Fragment

        // إذا كانت الرسالة المرسلة تطلب إخفاء الـ Bottom Navigation الافتراضي
         showCustomNav = getIntent().getBooleanExtra("SHOW_CUSTOM_NAVIGATION", false);
        if (showCustomNav) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, coursesFragment1)
                    .commit();
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.courses1);
        } else {
            homeFragment.setArguments(bundle);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();

            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.home1);

        }

        Intent intent1 = new Intent(MainActivity_Main.this, CourseDetailsActivity.class);
        intent1.putExtra("USER_NAME12", userName);

        //  ReviewsFragment reviewsFragment = new ReviewsFragment();

//        Bundle bundle1 = new Bundle();
//
//        bundle1.putString("USER_NAME12", userName);
//        CourseDetailsActivity courseDetailsActivity=new CourseDetailsActivity();
//        courseDetailsActivity.setArguments(bundle1);

// إنشاء كائن Fragment

// تمرير البيانات إلى الـ Fragment
        //reviewsFragment.setArguments(bundle1);

        Intent intent2 = new Intent(MainActivity_Main.this, CourseDetailsActivity.class);
        intent2.putExtra("USER_NAME14", userName);

        Log.d("AAAAA", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUMain" + userName);

        // استبدال أو إضافة الـ Fragment إلى الـ Layout
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.flFragment, fragment) // تأكد أن ID الحاوية صحيح
//                .commit();

// استبدال أو إضافة الـ Fragment إلى الـ Layout
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.flFragment, fragment) // استبدل `fragment_container` بـ ID الحاوية
//                .commit();


//        Log.d("AAAAA", "USER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAMEUSER_NAME" + userName);


//        // تعيين الفراجمنت الافتراضي عند البداية
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.flFragment, homeFragment) // عرض HomeFragment بشكل افتراضي
//                    .commit();
//        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        // تحديد الفراجمنت الذي سيتم عرضه بناءً على العنصر المختار

        switch (item.getItemId()) {
            case R.id.prophile:

                selectedFragment = prophileFragment;

                break;

            case R.id.home1:
                selectedFragment = homeFragment;

                break;

            case R.id.courses1:
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
            if (showCustomNav) {
                bottomNavigationView.setSelectedItemId(R.id.courses1);
            }else {

                bottomNavigationView.setSelectedItemId(R.id.home1);

            }
        } else {
            super.onBackPressed(); // إذا كان بالفعل في HomeFragment، تابع تنفيذ الزر الرجوع المعتاد
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}
