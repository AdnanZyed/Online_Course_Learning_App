package com.example.onlinecourseampe_learningapp;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseLessonsAdapter extends RecyclerView.Adapter<CourseLessonsAdapter.LessonViewHolder> {

    private final My_View_Model myViewModel;  // تعريف ViewModel
    private final List<CourseLessons> lessons;

    // إضافة ViewModel كمُدخل في المُنشئ
    public CourseLessonsAdapter(My_View_Model myViewModel, List<CourseLessons> lessons) {
        this.myViewModel = myViewModel;
        this.lessons = lessons;
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

        // تعيين النصوص
        holder.mainText.setText(lesson.getL_name());
        holder.secondaryText.setText(lesson.getL_time() + "");
        holder.numberBadge.setText(String.valueOf(lesson.getL_id()));

        // التحكم في تفعيل أيقونة Play بناءً على حالة الدرس
        if (lesson.isL_completed() || position == 0 || lessons.get(position - 1).isL_completed()) {
            holder.iconPlay.setAlpha(1.0f); // تمكين الأيقونة
            holder.iconPlay.setEnabled(true);

            holder.iconPlay.setOnClickListener(v -> {
                // التعامل مع الضغط
                // تحديث حالة الدرس إلى مكتمل

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(lesson.getL_url()));

                holder.itemView.getContext().startActivity(intent);
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                lesson.setL_completed(true);
                myViewModel.updateCourseLesson(lesson); // تحديث الحالة في قاعدة البيانات
                notifyItemChanged(position); // تحديث العنصر الحالي في القائمة
                }, 2000); // تأخير 2 ثانية
            });
        } else {
            holder.iconPlay.setAlpha(0.5f); // تعطيل الأيقونة
            holder.iconPlay.setEnabled(false);
            holder.iconPlay.setOnClickListener(null);
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
