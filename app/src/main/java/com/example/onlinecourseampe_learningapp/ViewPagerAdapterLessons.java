package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterLessons extends FragmentStateAdapter {

    private final int courseId;
    private final String user;

    public ViewPagerAdapterLessons(@NonNull FragmentActivity fragmentActivity, int courseId, String user) {
        super(fragmentActivity);
        this.courseId = courseId;
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                LessonsFragment lessonsFragment = new LessonsFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("COURSE_ID", courseId);
                bundle1.putString("USER", user);
                lessonsFragment.setArguments(bundle1);
                return lessonsFragment;
            case 1:
                CertificatesFragment certificatesFragment = new CertificatesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("COURSE_ID", courseId);
                certificatesFragment.setArguments(bundle);
                return certificatesFragment;
            default:
                return new LessonsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
