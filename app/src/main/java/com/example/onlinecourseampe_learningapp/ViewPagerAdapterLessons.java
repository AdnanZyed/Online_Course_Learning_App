package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapterLessons extends FragmentStateAdapter {

    private final int courseId; // تعريف متغير courseId

    // تعديل المُنشئ لقبول courseId
    public ViewPagerAdapterLessons(@NonNull FragmentActivity fragmentActivity, int courseId) {
        super(fragmentActivity);
        this.courseId = courseId; // تخزين courseId في المتغير
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new LessonsFragment(); // الصفحة الأولى
            case 1:
                // الصفحة الثانية مع إرسال courseId
                CertificatesFragment certificatesFragment = new CertificatesFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("COURSE_ID", courseId); // إضافة الـ courseId إلى الـ Bundle
                certificatesFragment.setArguments(bundle); // تمرير الـ Bundle إلى الفراجمنت
                return certificatesFragment;
            default:
                return new LessonsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // عدد الصفحات
    }
}
