package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {
    private final String teacherUserName;
    private final int courseId;
    private final String user;




    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity, String teacherUserName, int courseId, String user) {
        super(fragmentActivity);
        this.teacherUserName = teacherUserName;
        this.courseId = courseId;
        this.user = user;

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
                // إنشاء ReviewsFragment وتمرير البيانات
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("COURSE_ID1", courseId); // تمرير معرف الدورة
                bundle2.putString("USER_ST", user); // تمرير معرف الدورة
                reviewsFragment.setArguments(bundle2);
                return reviewsFragment;
            default:
                // AboutFragment
                AboutFragment aboutFragment1 = new AboutFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("TEACHER_USER_NAME1", teacherUserName);
                aboutFragment1.setArguments(bundle1);
                return aboutFragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // عدد التابات
    }
}
