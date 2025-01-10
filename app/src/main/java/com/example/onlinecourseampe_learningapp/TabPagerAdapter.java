package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {
    private final String teacherUserName;

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity, String teacherUserName) {
        super(fragmentActivity);
        this.teacherUserName = teacherUserName;

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                // AboutFragment
                AboutFragment aboutFragment = new AboutFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TEACHER_USER_NAME1", teacherUserName);
                aboutFragment.setArguments(bundle);
                return aboutFragment;
            case 1:
                return new LessonsFragment();
            case 2:
                return new ReviewsFragment();
            default:
                return new AboutFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // عدد التابات
    }
}
