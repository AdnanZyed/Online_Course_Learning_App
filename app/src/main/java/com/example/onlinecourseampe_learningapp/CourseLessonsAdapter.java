package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseLessonsAdapter extends RecyclerView.Adapter<CourseLessonsAdapter.LessonViewHolder> {

    private final My_View_Model myViewModel;
    private final List<CourseLessons> lessons;
    private final String user;
    private final String lock;
    private final Context context;


    public CourseLessonsAdapter(My_View_Model myViewModel, List<CourseLessons> lessons, String user, Context context, String lock) {
        this.myViewModel = myViewModel;
        this.lessons = lessons;
        this.lock = lock;
        this.context = context;
        this.user = user;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson, parent, false);
        return new LessonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, int position) {
        CourseLessons lesson = lessons.get(position);

        holder.mainText.setText(lesson.getL_name());
        holder.secondaryText.setText(lesson.getL_time() + "");
        holder.numberBadge.setText(String.valueOf(position + 1));
        if (lock == null || lock.isEmpty()) {

            if (lesson.isL_completed() || position == 0 || lessons.get(position - 1).isL_completed()) {


                if (position == getItemCount()) {

                }
                holder.iconPlay.setAlpha(1.0f);
                holder.iconPlay.setEnabled(true);

                holder.iconPlay.setOnClickListener(v -> {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(lesson.getL_url()));
                    holder.itemView.getContext().startActivity(intent);
                    StudentLesson studentLesson1 = new StudentLesson(user, lesson.getL_id(), true);
                    myViewModel.insertStudentLesson(studentLesson1);

                    lesson.setL_completed(true);
                    new Handler(Looper.getMainLooper()).postDelayed(() -> {


                        myViewModel.updateCourseLesson(lesson);


                        notifyItemChanged(position);
                    }, 2000);
                });
            } else {
                holder.iconPlay.setAlpha(0.5f);
                holder.iconPlay.setEnabled(false);
                holder.iconPlay.setOnClickListener(null);

                lesson.setL_completed(false);

            }

        } else {

            holder.iconPlay.setAlpha(0.5f);
            holder.iconPlay.setEnabled(false);
            holder.iconPlay.setOnClickListener(null);

            lesson.setL_completed(false);


        }
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class LessonViewHolder extends RecyclerView.ViewHolder {
        TextView mainText, secondaryText, numberBadge;
        ImageView iconPlay;

        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);

            mainText = itemView.findViewById(R.id.mainText);
            secondaryText = itemView.findViewById(R.id.secondaryText);
            numberBadge = itemView.findViewById(R.id.numberBadge);
            iconPlay = itemView.findViewById(R.id.iconPlay);
        }
    }
}
