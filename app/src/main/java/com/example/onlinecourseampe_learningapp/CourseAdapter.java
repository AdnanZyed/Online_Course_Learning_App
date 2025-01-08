package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private List<Course> courseList;

    public CourseAdapter(Context context, List<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        holder.tvCourseName.setText(course.getCourse_NAME());

        holder.tvCategorie.setText(course.getTeacher_name());
        holder.tvPrice.setText(String.format("$%d", course.getPrice()));
        // عرض صورة الكورس إذا كانت موجودة

        // تغيير لون الأيقونة بناءً على حالة isBookmarked
        holder.bookmarkIcon.setColorFilter(course.isBookmarked() ? Color.BLUE : Color.BLACK);

        // حدث الضغط على أيقونة الإشارة المرجعية
        holder.bookmarkIcon.setOnClickListener(v -> {
            course.setBookmarked(!course.isBookmarked()); // تغيير الحالة
            notifyItemChanged(position); // تحديث العنصر
            // هنا يمكنك تحديث قاعدة البيانات أيضًا
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName, tvPrice,tvCategorie;
        ImageView ivCourseImage ,cartIcon,bookmarkIcon;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCategorie = itemView.findViewById(R.id.tv_categorie);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivCourseImage = itemView.findViewById(R.id.iv_course_image);
            bookmarkIcon = itemView.findViewById(R.id.bookmarkIcon);
            cartIcon = itemView.findViewById(R.id.iv_course_image);
        }
    }
}
