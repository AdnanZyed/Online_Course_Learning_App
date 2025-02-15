package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {
    private final String teacherUserName;
    private final String courseDescription;
    private final int courseId;
    private final String user;


    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity, String teacherUserName, int courseId, String user, String courseDescription) {
        super(fragmentActivity);
        this.teacherUserName = teacherUserName;
        this.courseDescription = courseDescription;
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
                bundle.putString("courseDescription", courseDescription);
                bundle.putString("STUDENT_USER", user);


                aboutFragment.setArguments(bundle);

                return aboutFragment;
            case 1:
                LessonsFragment lessonsFragment = new LessonsFragment();
                Bundle bundle4 = new Bundle();
                bundle4.putInt("COURSE_ID", courseId);
                bundle4.putString("USER", user);
                bundle4.putString("LOCK", user);

                lessonsFragment.setArguments(bundle4);
                return lessonsFragment;
            case 2:
                ReviewsFragment reviewsFragment = new ReviewsFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putInt("COURSE_ID1", courseId);
                bundle2.putString("USER_ST", user);
                reviewsFragment.setArguments(bundle2);
                return reviewsFragment;
            default:
                AboutFragment aboutFragment1 = new AboutFragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("TEACHER_USER_NAME1", teacherUserName);
                bundle1.putString("courseDescription", courseDescription);
                bundle1.putString("STUDENT_USER", user);

                aboutFragment1.setArguments(bundle1);
                return aboutFragment1;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
