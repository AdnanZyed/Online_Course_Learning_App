package com.example.onlinecourseampe_learningapp;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {
    private Context context;

    private String user;
    private List<Teacher> teachersList;

    public void setTeacherList(List<Teacher> teachers) {
        this.teachersList = teachers;
        notifyDataSetChanged();
    }
    public TeacherAdapter(Context context, List<Teacher> teachersList,String user) {
        this.context = context;
        this.teachersList = teachersList;
        this.user = user;
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


        holder.tvTeatur_name.setText(teacher.getTeatur_name().toString());
        byte[] imageBytes = teacher.getImage_teatcher();

        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.imageView.setImageBitmap(bitmap);
        } else {
            holder.imageView.setImageResource(R.drawable.unnamed1);
        }
    }

    @Override
    public int getItemCount() {
        return teachersList != null ? teachersList.size() : 0;
    }

     class TeacherViewHolder extends RecyclerView.ViewHolder {
        TextView  tvTeatur_name;

        ImageView imageView;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTeatur_name = itemView.findViewById(R.id.tv_teacher_name);
            imageView = itemView.findViewById(R.id.iv_teacher_image);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Teacher selectedTeacher = teachersList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("TEACHER_NAME_TEXT_VIEW", selectedTeacher.getTeatur_name());
                    bundle.putString("TEACHER_USER_NAME_TEXT_VIEW",selectedTeacher.getTeatur_USER_Name());
                    bundle.putString("EDUCATION_TEXT_VIEW", selectedTeacher.getEducation());
                    bundle.putString("STUDENT_USER",user);
                    bundle.putByteArray("BITMAP",selectedTeacher.getImage_teatcher());
                    Intent intent = new Intent(context, TeacherProfileActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }
}
