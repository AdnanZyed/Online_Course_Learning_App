package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CallActivity extends AppCompatActivity {


    private String otherUser;
    private String User;
    private String name;
    private byte[] image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        ImageView imageView = findViewById(R.id.otherImage);
        ImageView imageView1 = findViewById(R.id.back_icon2);
        TextView studentName = findViewById(R.id.studentName);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        otherUser = getIntent().getStringExtra("studentUsername");
        name = getIntent().getStringExtra("studentName");
        User = getIntent().getStringExtra("USER");
        image = getIntent().getByteArrayExtra("studentImage");

        if (image != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
            imageView.setImageBitmap(bitmap);

        } else {
            imageView.setImageResource(R.drawable.profile);
        }
        studentName.setText(name);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CallActivity.this, ChatMessageActivity.class);
                intent.putExtra("studentUsername", otherUser);
                intent.putExtra("studentName", name);
                intent.putExtra("USER", User);
                intent.putExtra("CALL", "CALL");

                startActivity(intent);
            }
        });
    }

}