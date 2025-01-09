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
        View view = LayoutInflater.from(context).inflate(R.layout.mentores_teacher_item, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        Teacher teacher = teachersMonetorsList.get(position);
        holder.tvTeacherNameAll.setText(teacher.getTeatur_name());
        holder.tvTeacherEducationAll.setText(teacher.getEducation());
        holder.chatAll.setImageResource(R.drawable.chat);

        // تحويل الصورة المخزنة في قاعدة البيانات (byte[]) إلى Bitmap
        if (teacher.getImage_teatcher() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(teacher.getImage_teatcher(), 0, teacher.getImage_teatcher().length);
            holder.imgTeacherAll.setImageBitmap(bitmap);
        } else {
            holder.imgTeacherAll.setImageResource(R.drawable.unnamed); // صورة افتراضية
        }
    }

    @Override
    public int getItemCount() {
        return teachersMonetorsList.size();
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {

        ImageView imgTeacherAll,chatAll;
        TextView tvTeacherNameAll, tvTeacherEducationAll;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTeacherAll = itemView.findViewById(R.id.iv_teacher_image_all);
            tvTeacherNameAll = itemView.findViewById(R.id.tv_teacher_name_all);
            tvTeacherEducationAll = itemView.findViewById(R.id.tv_major_all);
            chatAll = itemView.findViewById(R.id.chat_all);
        }
    }
}
