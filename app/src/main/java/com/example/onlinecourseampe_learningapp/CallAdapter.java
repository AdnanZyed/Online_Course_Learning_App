package com.example.onlinecourseampe_learningapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ChatViewHolder> {

    private final Context context;
    private final String user;
    private List<Student> students = new ArrayList<>();

    public CallAdapter(Context context, String user) {
        this.context = context;
        this.user = user;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_call_student, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Student student = students.get(position);

        holder.studentName.setText(student.getS_name());
        if (student.getS_Image() != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(student.getS_Image(), 0, student.getS_Image().length);
            holder.studentImage.setImageBitmap(bitmap);
        } else {

            holder.studentImage.setImageResource(R.drawable.profile);


        }
        holder.callImage.setOnClickListener(v -> {
            Intent intent = new Intent(context, CallActivity.class);
            intent.putExtra("studentUsername", student.getStudent_user_name());
            intent.putExtra("studentName", student.getS_name());
            intent.putExtra("USER", user);
            intent.putExtra("studentImage", student.getS_Image());

            context.startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView studentImage, callImage;
        TextView studentName, lastMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            studentImage = itemView.findViewById(R.id.ivStudentImage1);
            studentName = itemView.findViewById(R.id.tvStudentNameItemC1);
            lastMessage = itemView.findViewById(R.id.tvLastMessage1);
            callImage = itemView.findViewById(R.id.callImage);
        }
    }
}
