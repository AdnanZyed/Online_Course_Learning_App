package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private Context context;
    private List<Teacher> teachersList;

    public TeacherAdapter(Context context, List<Teacher> teachersList) {
        this.context = context;
        this.teachersList = teachersList;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher, parent, false);

        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teachersList.get(position);


    }

    @Override
    public int getItemCount() {
        return teachersList.size();
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView  tvTeatur_name;

        ImageView imageView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeatur_name = itemView.findViewById(R.id.tv_teacher_name);
            imageView = itemView.findViewById(R.id.iv_teacher_image);
        }
    }
}
