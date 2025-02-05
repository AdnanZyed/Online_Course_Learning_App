package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder> {

    public Course course1;
    boolean isCompleted;

    private final List<Course> courseList;
    private final Context context;
    private final My_View_Model viewModel;
    private final String user;

    public CoursesAdapter(List<Course> courseList, Context context, String user) {
        this.courseList = courseList;
        this.context = context;
        this.user = user;
        this.viewModel = new ViewModelProvider((AppCompatActivity) context).get(My_View_Model.class);

    }


    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_regestered, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);

        holder.bind(course);

        course1 = course;

    }

    @Override
    public int getItemCount() {
        return courseList != null ? courseList.size() : 0;
    }

    public boolean completed() {

        viewModel.getTotalLessonsCountByCourseId(course1.getCourse_ID()).observe((AppCompatActivity) context, totalLessons1 -> {
            int total = totalLessons1;


            viewModel.getCompletedLessonsCountByCourseId(course1.getCourse_ID()).observe((AppCompatActivity) context, completedLessons1 -> {
                int complete = completedLessons1;


                if (total == complete) {


                    isCompleted = true;

                } else {
                    isCompleted = false;


                }


            });
        });
        return isCompleted;

    }

    class CourseViewHolder extends RecyclerView.ViewHolder {

        private final ImageView courseImage;
        private final TextView courseName;
        private final TextView courseTime;
        private final ProgressBar progressBar;
        private final TextView lessonsNumR;
        private final TextView numLessonR;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);

            courseImage = itemView.findViewById(R.id.iv_course_imageR);
            courseName = itemView.findViewById(R.id.tv_course_nameR);
            courseTime = itemView.findViewById(R.id.time_courseR);
            progressBar = itemView.findViewById(R.id.progressBarR);
            lessonsNumR = itemView.findViewById(R.id.lessons_numR);
            numLessonR = itemView.findViewById(R.id.numlessonR);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Course selectedCourse = courseList.get(position);
                    Intent intent = new Intent(context, LessonsActivity.class);
                    intent.putExtra("COURSE_ID", selectedCourse.getCourse_ID());
                    intent.putExtra("USER", user);
                    context.startActivity(intent);

                }

            });

        }


        public void bind(Course course) {


            courseName.setText(course.getCourse_NAME());
            if (course.getImage() != null) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(course.getImage(), 0, course.getImage().length);
                courseImage.setImageBitmap(bitmap);
            } else {
                courseImage.setImageResource(R.drawable.unnamed);
            }
            viewModel.getTotalLessonsCountByCourseId(course.getCourse_ID()).observe((AppCompatActivity) context, totalLessons -> {
                numLessonR.setText(String.valueOf(totalLessons));


            });

            viewModel.getCompletedLessonsCountByCourseId(course.getCourse_ID()).observe((AppCompatActivity) context, completedLessons -> {
                lessonsNumR.setText(String.valueOf(completedLessons));

            });

            viewModel.getTotalLessonsTimeByCourseId(course.getCourse_ID()).observe((AppCompatActivity) context, totalTime -> {
                courseTime.setText(String.format("%d min", totalTime));
            });

            viewModel.getTotalLessonsCountByCourseId(course.getCourse_ID()).observe((AppCompatActivity) context, totalLessons -> {
                viewModel.getCompletedLessonsCountByCourseId(course.getCourse_ID()).observe((AppCompatActivity) context, completedLessons -> {
                    int progress = (totalLessons > 0) ? (completedLessons * 100 / totalLessons) : 0;
                    progressBar.setProgress(progress);

                    if (totalLessons == completedLessons) {
                        course.setCompleted(true);


                    }
                });
            });


        }
    }
}

