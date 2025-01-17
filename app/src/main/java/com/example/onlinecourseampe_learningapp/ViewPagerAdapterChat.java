package com.example.onlinecourseampe_learningapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterChat extends FragmentStateAdapter {

    public ViewPagerAdapterChat(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // التحكم في الفراقمنت بناءً على التبويب
        switch (position) {
            case 0:
                return new ChatFragment(); // فراقمنت الشات
            case 1:
                return new CallFragment(); // فراقمنت الكال
            default:
                return new ChatFragment(); // افتراضيًا
        }
    }

    @Override
    public int getItemCount() {
        return 2; // عدد التبويبات
    }
}
