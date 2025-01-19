package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class Tab_profile_Adapter
        extends FragmentStateAdapter {

    public Tab_profile_Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CoursesProfileFragment();
            case 1:
                return new StudentsFragment();
            case 2:
                return new ReviewsTeacherFragment();
            default:
                return new CoursesProfileFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // عدد التابات
    }
}


