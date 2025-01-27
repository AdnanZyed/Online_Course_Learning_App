package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final int courseId; // تعريف متغير courseId
    private final String user; // تعريف متغير courseId

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int courseId,String user) {
        super(fragmentActivity);
        this.courseId = courseId; // تخزين courseId في المتغير
        this.user = user; // تخزين courseId في المتغير

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                OngoingFragment ongoingFragment = new OngoingFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putInt("COURSE_ID", courseId); // إضافة الـ courseId إلى الـ Bundle
                bundle1.putString("USER", user); // إضافة الـ courseId إلى الـ Bundle
                ongoingFragment.setArguments(bundle1); // تمرير الـ Bundle إلى الفراجمنت
                return ongoingFragment;
            case 1:
                CompletedFragment completedFragment = new CompletedFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("COURSE_ID", courseId);
                bundle2.putString("USER", user); // إضافة الـ courseId إلى الـ Bundle
// إضافة الـ courseId إلى الـ Bundle
                completedFragment.setArguments(bundle2); // تمرير الـ Bundle إلى الفراجمنت
                return completedFragment;
            default:
                OngoingFragment ongoingFragment1 = new OngoingFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("USER", user); // إضافة الـ courseId إلى الـ Bundle

                bundle3.putInt("COURSE_ID", courseId); // إضافة الـ courseId إلى الـ Bundle
                ongoingFragment1.setArguments(bundle3); // تمرير الـ Bundle إلى الفراجمنت
                return ongoingFragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 2; // عدد التبويبات
    }
}
