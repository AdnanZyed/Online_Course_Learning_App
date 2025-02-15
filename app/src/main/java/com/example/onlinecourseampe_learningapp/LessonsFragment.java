package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LessonsFragment extends Fragment {


    public LessonsFragment() {
    }

    My_View_Model myViewModel;
    List<ImageView> stars = new ArrayList<>();

    int coursId;
    int currentRating = 0;

    String user;
    String lock;
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lessons, container, false);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        if (getArguments() != null) {

            coursId = getArguments().getInt("COURSE_ID");
            user = getArguments().getString("USER");
            lock = getArguments().getString("LOCK");

        }
        RecyclerView recyclerView = view.findViewById(R.id.rv_lessons);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        myViewModel.getLessonsByCourseId(coursId).observe(getViewLifecycleOwner(), lessons -> {
            for (i = 0; i < lessons.size(); i++) {

                myViewModel.getCompletedLessonsForStudent(user).observe((LifecycleOwner) requireContext(), lessonsc -> {
                    for (StudentLesson cl : lessonsc) {
                        if (cl.getLessonId() == lessons.get(i - 1).getL_id()) {
                            //lessons.get(i-1).setL_completed(true);
                            CourseLessons courseLessons = new CourseLessons();
                            courseLessons.setCourse_ID(lessons.get(i - 1).getL_id());
                            courseLessons.setL_completed(true);
                            myViewModel.updateCourseLesson(courseLessons);


                        } else {
                            CourseLessons courseLessons = new CourseLessons();
                            courseLessons.setCourse_ID(lessons.get(i - 1).getL_id());
                            courseLessons.setL_completed(false);
                            myViewModel.updateCourseLesson(courseLessons);

                        }
                    }
                });

            }


            if (lessons.get(lessons.size() - 1).isL_completed()) {
                myViewModel.getisRatingCoursesByStudent1(user, coursId).observe(getViewLifecycleOwner(), lessons1 -> {


                    if (lessons1 == null || lessons1.isEmpty()) {
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {

                            showCustomDialog();
                        }, 1000);
                    }
                });
            }
        });
        myViewModel.getLessonsByCourseId(coursId).observe(getViewLifecycleOwner(), lessons -> {


            CourseLessonsAdapter adapter = new CourseLessonsAdapter(myViewModel, lessons, user, requireContext(),lock);
            recyclerView.setAdapter(adapter);

        });
        return view;
    }

    public void showCustomDialog() {

        Dialog dialog = new Dialog(requireContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.complete_course_dialog);
        dialog.setCancelable(true);


        Button viewCourseButton = dialog.findViewById(R.id.btn_view_course);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);


        stars.add(dialog.findViewById(R.id.star1));
        stars.add(dialog.findViewById(R.id.star2));
        stars.add(dialog.findViewById(R.id.star3));
        stars.add(dialog.findViewById(R.id.star4));
        stars.add(dialog.findViewById(R.id.star5));

        for (int i = 0; i < stars.size(); i++) {
            final int index = i;
            stars.get(i).setOnClickListener(v -> setRating(index + 1));
        }

        viewCourseButton.setOnClickListener(v -> {
            Student_Course studentCourse = new Student_Course(user, coursId, false, true, false, currentRating);
            myViewModel.updateCourseStudent(studentCourse);

            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            window.setAttributes(params);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setBackgroundDrawableResource(R.drawable.rounded_dialog_background);
        }
        dialog.show();
    }

    private void setRating(int rating) {
        currentRating = rating;
        for (int i = 0; i < stars.size(); i++) {
            if (i < rating) {
                stars.get(i).setImageResource(R.drawable.star1);
            } else {
                stars.get(i).setImageResource(R.drawable.starn);
            }
        }
    }
}