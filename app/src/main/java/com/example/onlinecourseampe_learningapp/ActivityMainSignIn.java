package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainBinding;

public class ActivityMainSignIn extends AppCompatActivity {
    private ActivityMainBinding binding;
    String EUserIn;
    String EPasswordIn;
    My_View_Model myViewModel;
    String student_name_user;
    String student_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean isRemembered = sharedPreferences.getBoolean("rememberMe", false);
        if (isRemembered) {
            binding.eUserIn.setText(sharedPreferences.getString("username", ""));
            binding.ePasswordIn.setText(sharedPreferences.getString("password", ""));
            binding.checkBoxIn.setChecked(true);
        }


        binding.Forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewModel.getAllStudentByUser(binding.eUserIn.getText().toString()).observe(ActivityMainSignIn.this, students -> {
                    if (students != null && !students.isEmpty() && !binding.eUserIn.getText().toString().isEmpty() && binding.eUserIn.getText().toString() != null && binding.eUserIn.getText().toString() != "") {

                        Intent intent = new Intent(ActivityMainSignIn.this, ForgetPassword1.class);
                        intent.putExtra("USER", binding.eUserIn.getText().toString());
                        startActivity(intent);


                    }
                });
            }
        });


        binding.SignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EUserIn = binding.eUserIn.getText().toString().trim();
                EPasswordIn = binding.ePasswordIn.getText().toString().trim();


                if (EUserIn.isEmpty()) {
                    binding.eUserIn.setError("الرجاء إدخال اسم المستخدم");
                    binding.eUserIn.requestFocus();
                    return;
                }

                if (EPasswordIn.isEmpty()) {
                    binding.ePasswordIn.setError("الرجاء إدخال كلمة المرور");
                    binding.ePasswordIn.requestFocus();
                    return;
                }

                if (EUserIn.equals("admin") && EPasswordIn.equals("admin")) {
                    Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Admin.class);
                    startActivity(intent);

                }
                myViewModel.getAllStudentByUser(EUserIn).observe(ActivityMainSignIn.this, students -> {
                    if (students != null && !students.isEmpty()) {
                        Student student = students.get(0);
                        student_name_user = student.getStudent_user_name().toString();
                        student_password = student.getStudent_Password().toString();


                        if (EUserIn.equals(student_name_user) && EPasswordIn.equals(student_password)) {
                            Toast.makeText(ActivityMainSignIn.this, "لقد حصلت على اشتراك جديد", Toast.LENGTH_SHORT).show();


                            Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Main.class);
                            intent.putExtra("USER_NAME2", EUserIn);

                            startActivity(intent);
                        } else {

                            Toast.makeText(ActivityMainSignIn.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();


                        }
                    } else {

                        Toast.makeText(ActivityMainSignIn.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();


                    }

                });
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


        binding.checkBoxIn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String username1 = binding.eUserIn.getText().toString();
                String password1 = binding.ePasswordIn.getText().toString();

                if (!username1.isEmpty() && !password1.isEmpty()) {
                    editor.putBoolean("rememberMe", true);
                    editor.putString("username", username1);
                    editor.putString("password", password1);
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



