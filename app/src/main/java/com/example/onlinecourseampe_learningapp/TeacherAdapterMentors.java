package com.example.onlinecourseampe_learningapp;


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

import java.util.List;

public class TeacherAdapterMentors extends RecyclerView.Adapter<TeacherAdapterMentors.TeacherViewHolder> {

    private Context context;
    private String user;
    private List<Teacher> teachersMonetorsList;

    public TeacherAdapterMentors(Context context, List<Teacher> teacherList, String user) {
        this.context = context;
        this.user = user;
        this.teachersMonetorsList = teacherList;
    }

    public void setTeacher_MonetorsList(List<Teacher> teachers) {
        this.teachersMonetorsList = teachers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mentores_teacher_item, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teachersMonetorsList.get(position);
        holder.tvTeacherNameAll.setText(teacher.getTeatur_name());
        holder.tvTeacherEducationAll.setText(teacher.getEducation());
        holder.chatAll.setImageResource(R.drawable.chat);

        if (teacher.getImage_teatcher() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(teacher.getImage_teatcher(), 0, teacher.getImage_teatcher().length);
            holder.imgTeacherAll.setImageBitmap(bitmap);
        } else {
            holder.imgTeacherAll.setImageResource(R.drawable.unnamed);
        }
    }

    @Override
    public int getItemCount() {
        return teachersMonetorsList.size();
    }

    class TeacherViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeacherAll, chatAll;
        TextView tvTeacherNameAll, tvTeacherEducationAll;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Teacher selectedTeacher = teachersMonetorsList.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("TEACHER_NAME_TEXT_VIEW", selectedTeacher.getTeatur_name());
                    bundle.putString("TEACHER_USER_NAME_TEXT_VIEW", selectedTeacher.getTeatur_USER_Name());
                    bundle.putString("EDUCATION_TEXT_VIEW", selectedTeacher.getEducation());
                    bundle.putString("STUDENT_USER", user);
                    bundle.putByteArray("BITMAP", selectedTeacher.getImage_teatcher());
                    Intent intent = new Intent(context, TeacherProfileActivity.class);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
            imgTeacherAll = itemView.findViewById(R.id.iv_teacher_image_all);
            tvTeacherNameAll = itemView.findViewById(R.id.tv_teacher_name_all);
            tvTeacherEducationAll = itemView.findViewById(R.id.tv_major_all);
            chatAll = itemView.findViewById(R.id.chat_all);
        }
    }
}
