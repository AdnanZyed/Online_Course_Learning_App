package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Welcom_screen extends AppCompatActivity {
    private int loadingStep = 0;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_screen);
        loadingText = findViewById(R.id.loading_text);

        ImageView logo = findViewById(R.id.logo);

//        // تحميل الأنيميشن
//
//        // تشغيل الصوت
       MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.welcome_sound); // تأكد من أن ملف الصوت في res/raw
      mediaPlayer.start();
//
//
      Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);
        // بدأ التحميل تدريجياً (تحريك النص)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateLoadingText();
            }
        }, 500);  // التأخير الأولي
        // تأخير لمدة 3 ثوانٍ قبل الانتقال إلى الشاشة الرئيسية
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Welcom_screen.this, Main_Activity_part.class);
            startActivity(intent);
            finish(); // إنهاء شاشة الترحيب
        }, 3000); // المدة بالميلي ثانية
    }
        private void updateLoadingText() {
            // تحديث النص لعرض تحميل النقاط
            if (loadingStep < 3) {
                loadingStep++;
                loadingText.setText("Loading" + ".".repeat(loadingStep));
                new Handler().postDelayed(this::updateLoadingText, 500); // تكرار التحديث بعد نصف ثانية
            }
        }




    }
