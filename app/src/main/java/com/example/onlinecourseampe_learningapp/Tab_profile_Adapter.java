package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

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
    private final String teacherUserName;
    private final String user;
    public Tab_profile_Adapter(@NonNull FragmentActivity fragmentActivity, String teacherUserName, String user) {
        super(fragmentActivity);
        this.teacherUserName = teacherUserName;
        this.user = user;
    }






    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:

                // AboutFragment
                CoursesProfileFragment coursesProfileFragment = new CoursesProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("TEACHER_USER_NAME1", teacherUserName);
                coursesProfileFragment.setArguments(bundle);
                return  coursesProfileFragment;

            case 1:
                StudentsFragment studentsFragment = new StudentsFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("TEACHER_USER_NAME1", teacherUserName);
                studentsFragment.setArguments(bundle2);
                return  studentsFragment;
            case 2:
                ReviewsTeacherFragment reviewsTeacherFragment = new ReviewsTeacherFragment();
                Bundle bundle3 = new Bundle();
                bundle3.putString("TEACHER_USER_NAME1", teacherUserName);
                bundle3.putString("STUDENT_USER_NAME1", user);
                reviewsTeacherFragment.setArguments(bundle3);
                return  reviewsTeacherFragment;
            default:
                CoursesProfileFragment coursesProfileFragment1 = new CoursesProfileFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("TEACHER_USER_NAME1", teacherUserName);
                coursesProfileFragment1.setArguments(bundle1);
                return  coursesProfileFragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 3; // عدد التابات
    }
}


