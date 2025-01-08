package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    private List<Notification> notificationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        recyclerView = findViewById(R.id.rv_notifications);

        // إعداد قائمة الإشعارات
        notificationList = new ArrayList<>();
        adapter = new NotificationAdapter(notificationList);

        // إعداد RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);

        // إضافة إشعار عند إنشاء حساب
        addNotification("تسجيل حساب جديد", "لقد قمت بتسجيل حساب بنجاح!", R.drawable.unnamed);


    }
    private void addNotification(String title, String message, int iconResId) {
        notificationList.add(new Notification(title, message, iconResId));
        adapter.notifyItemInserted(notificationList.size() - 1);
    }
}