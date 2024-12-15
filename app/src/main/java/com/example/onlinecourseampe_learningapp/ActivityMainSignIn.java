package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainBinding;


public class ActivityMainSignIn extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String EUserIn;
    private String EPasswordIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        EUserIn=binding.eUserIn.getText().toString();
        EPasswordIn=binding.ePasswordIn.getText().toString();

//        if (EUserIn.length()<8){
//
//
//
//        }

















        binding.Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ActivityMainSignIn.this, Sign_up.class);
//                startActivity(intent);
            }
        });
        binding.backIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        binding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMainSignIn.this, Sign_up.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        boolean isRemembered = sharedPreferences.getBoolean("rememberMe", false);
        if (isRemembered) {
            binding.eUserIn.setText(sharedPreferences.getString("username", ""));
            binding.ePasswordIn.setText(sharedPreferences.getString("password", ""));
            binding.checkBoxIn.setChecked(true);
        }

        binding.checkBoxIn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String username = binding.eUserIn.getText().toString();
                String password = binding.ePasswordIn.getText().toString();

                if (!username.isEmpty() && !password.isEmpty()) {
                    editor.putBoolean("rememberMe", true);
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply();
                } else {
                    binding.checkBoxIn.setChecked(false);
                }
            } else {
                editor.clear();
                editor.apply();
            }
        });
    }
}




