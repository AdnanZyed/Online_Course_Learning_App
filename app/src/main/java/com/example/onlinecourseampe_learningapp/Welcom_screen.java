
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

import com.example.onlineSeasonampe_learningapp.R;

public class Welcom_screen extends AppCompatActivity {
    private int loadingStep = 0;
    private TextView loadingText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom_screen);

        loadingText = findViewById(R.id.loading_text);
        ImageView logo = findViewById(R.id.logo);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.welcome_sound);
        mediaPlayer.start();

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateLoadingText();
            }
        }, 500);
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(Welcom_screen.this, Main_Activity_part.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

    private void updateLoadingText() {
        if (loadingStep < 3) {
            loadingStep++;
            loadingText.setText("Loading" + ".".repeat(loadingStep));
            new Handler().postDelayed(this::updateLoadingText, 500);
        }
    }


}
