package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinecourseampe_learningapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class ActivityMainSignIn extends AppCompatActivity {
    private ActivityMainBinding binding;
    String EUserIn;
    String EPasswordIn;
    My_View_Model myViewModel;
    String student_name_user;
    boolean isRemembered;
    String student_password;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);
        // تهيئة SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // استرجاع حالة "تذكرني" إذا كانت مفعلة
        boolean isRemembered = sharedPreferences.getBoolean("rememberMe", false);
        if (isRemembered) {
            binding.eUserIn.setText(sharedPreferences.getString("username", ""));
            binding.ePasswordIn.setText(sharedPreferences.getString("password", ""));
            binding.checkBoxIn.setChecked(true);
        }

        binding.SignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // الحصول على اسم المستخدم وكلمة المرور من الإدخالات
                EUserIn = binding.eUserIn.getText().toString().trim();
                EPasswordIn = binding.ePasswordIn.getText().toString().trim();




                // التحقق من الإدخالات
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

                // التحقق من كون المستخدم أدمن
                if (EUserIn.equals("admin") && EPasswordIn.equals("admin")) {
                    Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Admin.class);
                    startActivity(intent);

                }
                myViewModel.getAllStudentByUser(EUserIn).observe(ActivityMainSignIn.this, students -> {
                    // استخدام LinearLayoutManager مع التمرير الأفقي
                    if (students != null && !students.isEmpty()) {
                        Student student = students.get(0);
                        student_name_user = student.getStudent_user_name().toString();
                        student_password = student.getStudent_Password().toString();


                        if (EUserIn.equals(student_name_user)&& EPasswordIn.equals(student_password)) {
                            Log.d("ActivityMainSignIn", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII " + EUserIn);

                            // الانتقال إلى شاشة المستخدم
//                            Intent intent1 = new Intent(ActivityMainSignIn.this, EnrollCodeActivity.class);
//                            intent1.putExtra("USER_NAME1", EUserIn);

                            Log.d("CourseDetailsActivity", "لقد حصلت على اشتراك جديد");

                            Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Main.class);
                            intent.putExtra("USER_NAME2", EUserIn);

                            startActivity(intent);
                        } else {
                            // التحقق من اسم المستخدم وكلمة المرور في قاعدة البيانات
                            //   new Thread(() -> {
                            //   Student student = myViewModel.getStudentByUsernameAndPassword(EUserIn, EPasswordIn);

                            // runOnUiThread(() -> {

                            // عرض رسالة خطأ إذا لم يتم العثور على المستخدم
                            Toast.makeText(ActivityMainSignIn.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();

                            //        });
                            // }).start();
                        }
                    }
                        else {
                            // التحقق من اسم المستخدم وكلمة المرور في قاعدة البيانات
                            //   new Thread(() -> {
                            //   Student student = myViewModel.getStudentByUsernameAndPassword(EUserIn, EPasswordIn);

                            // runOnUiThread(() -> {

                            // عرض رسالة خطأ إذا لم يتم العثور على المستخدم
                            Toast.makeText(ActivityMainSignIn.this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();

                            //        });
                            // }).start();
                        }

                });
            }

        });


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




//    private void handleSignIn() {
//        String enteredUsername = binding.eUserIn.getText().toString().trim();
//        String enteredPassword = binding.ePasswordIn.getText().toString().trim();
//
//        // التحقق من الإدخالات
//        if (enteredUsername.isEmpty()) {
//            binding.eUserIn.setError("الرجاء إدخال اسم المستخدم");
//            binding.eUserIn.requestFocus();
//            return;
//        }
//
//        if (enteredPassword.isEmpty()) {
//            binding.ePasswordIn.setError("الرجاء إدخال كلمة المرور");
//            binding.ePasswordIn.requestFocus();
//            return;
//        }
//
//        // التحقق إذا كان المستخدم أدمن
//        if ("admin".equals(enteredUsername) && "admin".equals(enteredPassword)) {
//            Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Admin.class);
//            startActivity(intent);
//            return;
//        }
//
//        // التحقق من المستخدم العادي
//        myViewModel.getAllStudentByUser(enteredUsername).observe(this, students -> {
//            if (students != null && !students.isEmpty()) {
//                Student student = students.get(0);
//                studentNameUser = student.getStudent_user_name();
//                studentPassword = student.getStudent_Password();
//
//                if (studentNameUser.equals(enteredUsername) && studentPassword.equals(enteredPassword)) {
//                    Intent intent = new Intent(ActivityMainSignIn.this, MainActivity_Main.class);
//                    intent.putExtra("USER_NAME1", enteredUsername);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                Toast.makeText(this, "اسم المستخدم أو كلمة المرور غير صحيحة", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void handleRememberMe(boolean isChecked) {
//        if (isChecked) {
//            String username = binding.eUserIn.getText().toString();
//            String password = binding.ePasswordIn.getText().toString();
//
//            if (!username.isEmpty() && !password.isEmpty()) {
//                editor.putBoolean("rememberMe", true);
//                editor.putString("username", username);
//                editor.putString("password", password);
//                editor.apply();
//            } else {
//                binding.checkBoxIn.setChecked(false);
//                Toast.makeText(this, "الرجاء إدخال اسم المستخدم وكلمة المرور أولاً", Toast.LENGTH_SHORT).show();
//            }
//        } else {
//            editor.clear();
//            editor.apply();
//        }
//    }
}



