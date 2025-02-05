package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        holder.tvCategorie.setText(course.getCategorie());
        holder.tvPrice.setText(String.format("$%d", course.getPrice()));

        byte[] imageBytes = course.getImage();
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            holder.ivCourseImage.setImageBitmap(bitmap);
        } else {
            holder.ivCourseImage.setImageResource(R.drawable.sample_course_image);
        }

        viewModel.getBookmarkedCoursesByStudent1(user, course.getCourse_ID()).observe((AppCompatActivity) context, studentCourses -> {


            if (studentCourses.isEmpty() || studentCourses == null) {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmark);
            } else {
                holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);
            }
        });


        holder.BookmarkIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                showCustomDialog(course, course.getImage(), course.getCourse_NAME(), course.getCategorie(), course.getPrice());


                return true;
            }
        });
        holder.BookmarkIcon.setOnClickListener(v -> {
            viewModel.isStudentCourseExistsC(user, course.getCourse_ID(), true).observe((AppCompatActivity) context, isHadc -> {
                viewModel.isStudentCourseExistsB(user, course.getCourse_ID(), true).observe((AppCompatActivity) context, isHadb -> {
                    viewModel.isStudentCourseExists(user, course.getCourse_ID(), true).observe((AppCompatActivity) context, isHad -> {
                        if (!isHadb && !isHad && !isHadc) {


                            Student_Course studentCourse = new Student_Course(user, course.getCourse_ID(), true, false, false, 0);
                            viewModel.insertStudentCourse(studentCourse);
                            holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);


                        } else if (isHad || isHadc) {
                            Student_Course studentCourse = new Student_Course(user, course.getCourse_ID(), true, isHad, isHadc, 0);

                            viewModel.updateCourseStudent(studentCourse);

                            holder.BookmarkIcon.setImageResource(R.drawable.bookmarked);


                        } else if (isHadb) {
                            Student_Course studentCourse = new Student_Course(user, course.getCourse_ID(), false, isHad, isHadc, 0);

                            viewModel.updateCourseStudent(studentCourse);

                            holder.BookmarkIcon.setImageResource(R.drawable.bookmark);


                        }


                    });
                });
            });
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

    public Course getCourseAt(int position) {
        return courseList.get(position);
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
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

    private void showCustomDialog(Course course, byte[] image, String name, String catigories, int prise) {
        // إنشاء الـ Dialog
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setCancelable(true);


        // إعداد عناصر الـ Dialog
        TextView mainText = dialog.findViewById(R.id.dialog_main_text1);
        Button viewCourseButton = dialog.findViewById(R.id.btn_view_course1);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel1);
        ImageView iv_course_image = dialog.findViewById(R.id.iv_course_imageD);
        TextView tv_categorie = dialog.findViewById(R.id.tv_categorieD);
        TextView tv_course_name = dialog.findViewById(R.id.tv_course_nameD);
        TextView tv_price = dialog.findViewById(R.id.tv_priceD);

        byte[] imageBytes = image;
        if (imageBytes != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            iv_course_image.setImageBitmap(bitmap);
            tv_categorie.setText(catigories);
            tv_course_name.setText(name);
            tv_price.setText(prise + "");
            // تخصيص النصوص والصورة
            mainText.setText("Remove from Bookmark?");


            viewCourseButton.setOnClickListener(v -> {

                viewModel.deleteStudentCourseByUserAndCourse(user, course.getCourse_ID());

                dialog.dismiss();
            });

            Window window = dialog.getWindow();
            if (window != null) {
                WindowManager.LayoutParams params = window.getAttributes();
                params.gravity = Gravity.BOTTOM;
                params.width = WindowManager.LayoutParams.MATCH_PARENT;
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
                window.setAttributes(params);
                window.setBackgroundDrawableResource(android.R.color.transparent);
                window.setBackgroundDrawableResource(R.drawable.rounded_dialog_background);

            }


            cancelButton.setOnClickListener(v -> {
                dialog.dismiss();
            });

            dialog.show();
        }

    }


}
