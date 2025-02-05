package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity_Main extends AppCompatActivity

        implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    boolean showCustomNav;
    String userName;

    StudentsProfileFragment prophileFragment = new StudentsProfileFragment();
    CoursesFragment coursesFragment = new CoursesFragment();
    CartFragment cartFragment = new CartFragment();
    CourseFragment fragment = (CourseFragment) getSupportFragmentManager()
            .findFragmentById(R.id.flFragment);
    Fragment activeFragment = homeFragment;

    InboxFragment inboxFragment = new InboxFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);

        showCustomNav = getIntent().getBooleanExtra("SHOW_CUSTOM_NAVIGATION", false);

        Intent intent = getIntent();
        userName = intent.getStringExtra("USER_NAME2");

        HomeFragment homeFragment = new HomeFragment();
        CoursesFragment coursesFragment1 = new CoursesFragment();

        Bundle bundle = new Bundle();

        bundle.putString("USER_NAME", userName);

//


        if (showCustomNav) {
            coursesFragment.setArguments(bundle);

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


        Intent intent2 = new Intent(MainActivity_Main.this, CourseDetailsActivity.class);
        intent2.putExtra("USER_NAME14", userName);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;


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
