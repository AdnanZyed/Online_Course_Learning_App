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

    private String otherUser;
    private String User;
    private String name;
    private My_View_Model myViewModel;
    private ArrayList<Message> messages1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_message);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        recyclerViewMessages = findViewById(R.id.recyclerViewMessages);
        etMessageInput = findViewById(R.id.etMessageInput);
        btnSendMessage = findViewById(R.id.btnSendMessage);
        TextView textView = findViewById(R.id.tvStudentNameC);


        otherUser = getIntent().getStringExtra("studentUsername");
        name = getIntent().getStringExtra("studentName");
        User = getIntent().getStringExtra("USER");
        String call = getIntent().getStringExtra("CALL");

        textView.setText(name);
        chatAdapter = new ChatMessageAdapter(messages1, User);
        recyclerViewMessages.setAdapter(chatAdapter);
        recyclerViewMessages.setLayoutManager(new LinearLayoutManager(this));

        myViewModel.getMessagesBetweenUsers(User, otherUser).observe(this, messages -> {
            messages1.clear();
            messages1.addAll(messages);
            chatAdapter.updateMessages(messages);
            recyclerViewMessages.scrollToPosition(messages.size() - 1);
        });
        if (call != null) {
            long timestamp = System.currentTimeMillis();
            Message message = new Message(User, otherUser, "Voice call", timestamp);
            myViewModel.insertMessage(message);
        }

        btnSendMessage.setOnClickListener(v -> {
            String messageContent = etMessageInput.getText().toString().trim();
            if (!messageContent.isEmpty()) {
                long timestamp = System.currentTimeMillis();
                Message message = new Message(User, otherUser, messageContent, timestamp);
                myViewModel.insertMessage(message);
                etMessageInput.setText("");
            }
        });
    }
}
