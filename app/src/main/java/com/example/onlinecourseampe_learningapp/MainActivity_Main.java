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
    private My_View_Model myViewModel;
    //StudentsProfileFragment fragment1;
    String userName;

        StudentsProfileFragment prophileFragment = new StudentsProfileFragment();
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
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        showCustomNav = getIntent().getBooleanExtra("SHOW_CUSTOM_NAVIGATION", false);

        Intent intent = getIntent();
        userName = intent.getStringExtra("USER_NAME2");
        Log.d("MainActivity_Main", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM " + userName);

        HomeFragment homeFragment = new HomeFragment();
        CoursesFragment coursesFragment1 = new CoursesFragment();

        Bundle bundle = new Bundle();

        //   fragment.setArguments(bundle);
        bundle.putString("USER_NAME", userName);

//        myViewModel.getAllStudentByUser(userName).observe(this, students -> {
//
//            fragment1 = StudentsProfileFragment.newInstance(
//                    students.get(0).getS_name(),
//                    students.get(0).getBio(),
//                    students.get(0).getS_Image()
//            );
//
//        });


// إنشاء كائن Fragment

// تمرير البيانات إلى الـ Fragment

        // إذا كانت الرسالة المرسلة تطلب إخفاء الـ Bottom Navigation الافتراضي
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
                Bundle bundle4 = new Bundle();

                bundle4.putString("USER_NAME", userName);
                prophileFragment.setArguments(bundle4);
                selectedFragment = prophileFragment;

                break;

            case R.id.home1:
                Bundle bundle = new Bundle();

                bundle.putString("USER_NAME", userName);
                homeFragment.setArguments(bundle);

                selectedFragment = homeFragment;

                break;

            case R.id.courses1:
                Bundle bundle1 = new Bundle();

                bundle1.putString("USER_NAME", userName);
                coursesFragment.setArguments(bundle1);
                selectedFragment = coursesFragment;

                break;
            case R.id.cart:
                Bundle bundle2 = new Bundle();

                bundle2.putString("USER_NAME", userName);
                cartFragment.setArguments(bundle2);
                selectedFragment = cartFragment;

                break;
            case R.id.inbox:
                Bundle bundle3 = new Bundle();

                bundle3.putString("USER_NAME", userName);
                inboxFragment.setArguments(bundle3);
                selectedFragment = inboxFragment;

                break;
        }

        // استبدال الفراجمنت الحالي بالفراجمنت المختار
        if (selectedFragment != activeFragment) {

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, selectedFragment)
                    .commit();

            activeFragment = selectedFragment;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!(activeFragment instanceof HomeFragment)) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.flFragment, homeFragment)
                    .commit();
            activeFragment = homeFragment;
            if (showCustomNav) {
                bottomNavigationView.setSelectedItemId(R.id.courses1);
            } else {

                bottomNavigationView.setSelectedItemId(R.id.home1);

            }
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


}
