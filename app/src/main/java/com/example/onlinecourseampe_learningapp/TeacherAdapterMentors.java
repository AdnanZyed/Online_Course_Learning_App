package com.example.onlinecourseampe_learningapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private List<Teacher> teachersMonetorsList;

    public TeacherAdapterMentors(Context context, List<Teacher> teacherList) {
        this.context = context;
        this.teachersMonetorsList = teacherList;
    }
    public void setTeacher_MonetorsList(List<Teacher> teachers) {
        this.teachersMonetorsList = teachers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_teacher, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teachersMonetorsList.get(position);
        holder.tvTeacherName.setText(teacher.getTeatur_name());
        holder.tvTeacherEducation.setText(teacher.getEducation());

        // تحويل الصورة المخزنة في قاعدة البيانات (byte[]) إلى Bitmap
        if (teacher.getImage_teatcher() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(teacher.getImage_teatcher(), 0, teacher.getImage_teatcher().length);
            holder.imgTeacher.setImageBitmap(bitmap);
        } else {
            holder.imgTeacher.setImageResource(R.drawable.unnamed); // صورة افتراضية
        }
    }

    @Override
    public int getItemCount() {
        return teachersMonetorsList.size();
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeacher;
        TextView tvTeacherName, tvTeacherEducation;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTeacher = itemView.findViewById(R.id.img_teacher);
            tvTeacherName = itemView.findViewById(R.id.tv_teacher_name);
            tvTeacherEducation = itemView.findViewById(R.id.tv_teacher_education);
        }
    }
}
