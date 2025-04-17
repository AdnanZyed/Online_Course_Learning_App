package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterSteps extends FragmentStateAdapter {

    private final int seasonId;
    private final String user;

    public ViewPagerAdapterSteps(@NonNull FragmentActivity fragmentActivity, int seasonId, String user) {
        super(fragmentActivity);
        this.seasonId = seasonId;
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                StepsFragment stepsFragment = new StepsFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("COURSE_ID", seasonId);
                bundle1.putString("USER", user);
                stepsFragment.setArguments(bundle1);
                return stepsFragment;
            case 1:
                CertificatesFragment certificatesFragment = new CertificatesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("COURSE_ID", seasonId);
                certificatesFragment.setArguments(bundle);
                return certificatesFragment;
            default:
                return new StepsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
