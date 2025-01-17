package com.example.onlinecourseampe_learningapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatMessageActivity extends AppCompatActivity {

    private RecyclerView recyclerViewMessages;
    private EditText etMessageInput;
    private ImageView btnSendMessage;
    private ChatMessageAdapter chatAdapter;

    private String currentUser = "aDNAN@1234"; // اسم المستخدم الحالي
    private String otherUser = "aDNAN@123"; // اسم المستخدم الآخر
    private My_View_Model myViewModel;
    private ArrayList<Message> messages1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        // تهيئة ViewModel
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        // الربط مع عناصر الواجهة
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        etMessageInput = findViewById(R.id.etMessageInput);
        btnSendMessage = findViewById(R.id.btnSendMessage);

        otherUser = getIntent().getStringExtra("otherUser"); // اسم المستخدم الآخر
        ((TextView) findViewById(R.id.tvStudentName)).setText("aDNAN@123");

        // إعداد RecyclerView و Adapter
        chatAdapter = new ChatMessageAdapter(messages1, "aDNAN@1234");
        recyclerViewMessages.setAdapter(chatAdapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));

        // مراقبة الرسائل من ViewModel
        myViewModel.getMessagesBetweenUsers("aDNAN@1234", "aDNAN@123").observe(this, messages -> {
            messages1.clear();
            messages1.addAll(messages);
            chatAdapter.updateMessages(messages);
            recyclerViewMessages.scrollToPosition(messages.size() - 1); // تمرير لآخر رسالة
        });

        // إرسال رسالة جديدة
        btnSendMessage.setOnClickListener(v -> {
            String messageContent = etMessageInput.getText().toString().trim();
            if (!messageContent.isEmpty()) {
                long timestamp = System.currentTimeMillis();
                Message message = new Message("aDNAN@1234", "aDNAN@123", messageContent, timestamp);
                myViewModel.insertMessage(message); // إضافة الرسالة إلى قاعدة البيانات
                etMessageInput.setText(""); // إفراغ حقل الإدخال
            }
        });
    }
}
