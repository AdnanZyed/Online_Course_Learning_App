package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentViewHolder> {

    private final List<Student> studentList;
    private final Context context;

    public StudentsAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    @NonNull
    @Override
    public StudentsAdapter.StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentList != null ? studentList.size() : 0;
    }


    class StudentViewHolder extends RecyclerView.ViewHolder {
        private final ImageView studentImage;
        private final TextView studentName;
        private final TextView lastMessage;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            studentImage = itemView.findViewById(R.id.ivStudentImageS);
            studentName = itemView.findViewById(R.id.tvStudentNameS);
            lastMessage = itemView.findViewById(R.id.tvLastMessageS);


        }

        public void bind(Student student) {
            Bitmap bitmap = ImageConverter.byteArrayToBitmap(student.getS_Image());
            if (bitmap != null) {
                studentImage.setImageBitmap(bitmap);
            } else {
                studentImage.setImageResource(R.drawable.profile);
            }

            studentName.setText(student.getS_name());
            lastMessage.setText(student.getBio());
        }

    }
}
