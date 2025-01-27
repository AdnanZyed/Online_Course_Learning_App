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
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final Context context;
    private List<Student> students = new ArrayList<>();

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chat_student, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Student student = students.get(position);

        holder.studentName.setText(student.getS_name());
        if (student.getS_Image() != null) {
            // تحويل الصورة من byte[] إلى Bitmap
            Bitmap bitmap = BitmapFactory.decodeByteArray(student.getS_Image(), 0, student.getS_Image().length);
            holder.studentImage.setImageBitmap(bitmap);
        }
        // مستمع الضغط على العنصر
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatMessageActivity.class);
            intent.putExtra("studentUsername", student.getStudent_user_name());
            intent.putExtra("studentName", student.getS_name());
            context.startActivity(intent);
        });

        // هنا يمكنك جلب الرسالة الأخيرة للطالب
        // يمكن استخدام ViewModel للحصول على آخر رسالة عبر LiveData
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        ImageView studentImage;
        TextView studentName, lastMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);
            studentImage = itemView.findViewById(R.id.ivStudentImage);
            studentName = itemView.findViewById(R.id.tvStudentNameItemC);
            lastMessage = itemView.findViewById(R.id.tvLastMessage);
        }
    }
}
