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
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private String user;
    private List<Course> courseList;
    private final My_View_Model viewModel;
    private OnCourseClickListener listener;

    public void setOnCourseClickListener(OnCourseClickListener listener) {
        this.listener = listener;
    }

    public CourseAdapter(Context context, List<Course> courseList, String user) {
        this.context = context;
        this.courseList = courseList;
        this.user = user;
        this.viewModel = new ViewModelProvider((AppCompatActivity) context).get(My_View_Model.class);
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

        // تحميل الصورة
        byte[] imageBytes = course.getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.ivCourseImage.setImageBitmap(bitmap);
        } else {
            holder.ivCourseImage.setImageResource(R.drawable.sample_course_image);
        }

        viewModel.getisAddCartCoursesByStudent1(user, course.getCourse_ID()).observe((AppCompatActivity) context, studentCourses -> {


            if (studentCourses.isEmpty()||studentCourses==null) {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmark);
            } else {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);
            }
        });

        // تحديث حالة الإشارة المرجعية


        // التعامل مع الضغط على أيقونة الإشارة المرجعية
        holder.BookmarkIcon.setOnClickListener(v -> {
            // تحقق إذا كانت الدالة تبدأ بعملية استعلام
            viewModel.getisAddCartCoursesByStudent1(user, course.getCourse_ID()).observe((AppCompatActivity) context, studentCourses -> {
                viewModel.isStudentCourseExists(user,course.getCourse_ID(),true,false,false).observe((AppCompatActivity) context, isHad -> {
                if (!isHad) {

                    // إذا لم يتم العثور على الكورس في قاعدة البيانات (بمعنى أنه ليس مضافًا للمفضلة)
                    if (studentCourses.isEmpty()) {
                        // إضافة الكورس إلى المفضلة

                        Student_Course studentCourse = new Student_Course(0, user, course.getCourse_ID(), true, false, false);
                        viewModel.insertStudentCourse(studentCourse);
                        holder.BookmarkIcon.setImageResource(R.drawable.bookmarked); // تحديث الأيقونة

                        // تحديث حالة الكورس
                        //course.setBookmarked(true);
                        //   holder.BookmarkIcon.setImageResource(R.drawable.bookmarked); // تحديث الأيقونة
                        // خروج من الدالة بعد إضافة الكورس
                        return;
                    }
                }
                // إذا كان الكورس موجودًا بالفعل في المفضلة
                if (!studentCourses.isEmpty()) {
                    // حذف الكورس من المفضلة
                 //   viewModel.deleteStudentCourseByUserAndCourse(user, course.getCourse_ID(), true, false);

                    // تحديث حالة الكورس
                    //course.setBookmarked(false);
                    holder.BookmarkIcon.setImageResource(R.drawable.bookmark); // تحديث الأيقونة
                    // خروج من الدالة بعد حذف الكورس
                    return;
                }

                // إذا لم يتحقق أي شرط، لا شيء يحدث.
            });});
        });



        // التعامل مع الضغط على العنصر بالكامل
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCourseClick(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged(); // تأكد من تحديث الـ RecyclerView عند تعديل البيانات
    }

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvCourseName, tvPrice, tvCategorie;
        ImageView ivCourseImage, cartIcon, BookmarkIcon;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = itemView.findViewById(R.id.tv_course_name);
            tvCategorie = itemView.findViewById(R.id.tv_categorie);
            tvPrice = itemView.findViewById(R.id.tv_price);
            ivCourseImage = itemView.findViewById(R.id.iv_course_image);
            BookmarkIcon = itemView.findViewById(R.id.bookmarkIcon);
            cartIcon = itemView.findViewById(R.id.cart);
        }
    }
}
