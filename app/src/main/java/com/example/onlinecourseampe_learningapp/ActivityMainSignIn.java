package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class ActivityMainSignIn extends AppCompatActivity {
    private ActivityMainBinding binding;
    String EUserIn;
    String EPasswordIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        binding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.eUserIn.getText().toString().equals("admin") && binding.ePasswordIn.getText().toString().equals("admin")){
                    Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Admin.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Main.class);
                    startActivity(intent);

                }


            }
        });

        EUserIn = binding.eUserIn.getText().toString();
        EPasswordIn = binding.ePasswordIn.getText().toString();

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




