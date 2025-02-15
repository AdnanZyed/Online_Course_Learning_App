package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlinecourseampe_learningapp.databinding.ActivitySignUpBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Sign_up extends AppCompatActivity {
    private EditText phoneEditText;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private String student_name_user;
    private Student student;
    private ArrayList<Student> students1;
    private String ePasswordIn;
    private String PhoneIn;
    private LiveData<List<Student>> studentU;
    private ActivitySignUpBinding activitySignUpBinding;
    private My_View_Model myViewModel;
    private String nameIn;
    private String eUserIn;

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());


        activitySignUpBinding.eUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                activitySignUpBinding.eUser.setBackgroundResource(R.drawable.shap_selected);
                activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.Phone.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.name.setBackgroundResource(R.drawable.shape_non_selected);

            }
        });

        activitySignUpBinding.ePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                activitySignUpBinding.eUser.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.shap_selected);
                activitySignUpBinding.Phone.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.name.setBackgroundResource(R.drawable.shape_non_selected);

            }
        });
        activitySignUpBinding.Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                activitySignUpBinding.eUser.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.Phone.setBackgroundResource(R.drawable.shap_selected);
                activitySignUpBinding.name.setBackgroundResource(R.drawable.shape_non_selected);

            }
        });
        activitySignUpBinding.name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                activitySignUpBinding.eUser.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.Phone.setBackgroundResource(R.drawable.shape_non_selected);
                activitySignUpBinding.name.setBackgroundResource(R.drawable.shap_selected);

            }
        });
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        editor = sharedPreferences.edit();
        boolean isRemembered = sharedPreferences.getBoolean("rememberMe1", false);
        if (isRemembered) {
            activitySignUpBinding.eUser.setText(sharedPreferences.getString("username1", ""));
            activitySignUpBinding.ePassword.setText(sharedPreferences.getString("password1", ""));
            activitySignUpBinding.name.setText(sharedPreferences.getString("name1", ""));
            activitySignUpBinding.Phone.setText(sharedPreferences.getString("phone1", ""));
            activitySignUpBinding.checkBox.setChecked(true);
        }

        activitySignUpBinding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eUserIn = activitySignUpBinding.eUser.getText().toString().trim();
                ePasswordIn = activitySignUpBinding.ePassword.getText().toString().trim();
                PhoneIn = activitySignUpBinding.Phone.getText().toString().trim();
                nameIn = activitySignUpBinding.name.getText().toString().trim();
                studentU = myViewModel.getAllStudentByUser(eUserIn);
//
//


                activitySignUpBinding.eUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            activitySignUpBinding.eUser.setBackgroundResource(R.drawable.shape_password);
                        } else {
                            activitySignUpBinding.eUser.setBackgroundResource(R.drawable.edittext_background);
                        }
                    }
                });
                activitySignUpBinding.ePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.shape_password);
                        } else {
                            activitySignUpBinding.ePassword.setBackgroundResource(R.drawable.edittext_background);
                        }
                    }
                });


                if (eUserIn.isEmpty()) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم مطلوب");
                    return;
                }

                if (ePasswordIn.isEmpty()) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور مطلوبة");
                    return;
                }

                if (eUserIn.length() < 3 || eUserIn.length() > 30) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم يجب أن يكون بين 3 و 20 حرفًا");
                    return;
                }

                if (!eUserIn.matches("^[a-zA-Z0-9@#$%^&+=!_]+$")) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم يجب أن يحتوي فقط على حروف وأرقام");
                    return;
                }

                if (ePasswordIn.length() < 8) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور يجب أن تكون 8 أحرف على الأقل");
                    return;
                }

                String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
                if (!ePasswordIn.matches(passwordPattern)) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور يجب أن تحتوي على حرف كبير وصغير ورقم ورمز خاص");
                    return;
                }

                if (nameIn.isEmpty()) {
                    activitySignUpBinding.name.setError("الاسم مطلوب");
                    return;
                }

                if (PhoneIn.isEmpty()) {
                    activitySignUpBinding.Phone.setError("رقم الهاتف مطلوب");
                    return;
                }


                if (!PhoneIn.matches("^[0-9]+$")) {
                    activitySignUpBinding.Phone.setError("الرجاء إدخال رقم هاتف صحيح");
                    return;
                }

                if (PhoneIn.length() != 10) {
                    activitySignUpBinding.Phone.setError("رقم الهاتف يجب أن يتكون من 10 أرقام");
                    return;
                }

                myViewModel.getAllStudentByUser(eUserIn).observe(Sign_up.this, students -> {


                    if ( students.isEmpty()) {
                        int phoneIn = Integer.parseInt(PhoneIn);
                        student = new Student(eUserIn, ePasswordIn, phoneIn, 1234, nameIn, null, "");
                        myViewModel.insertStudent(student);


                        Intent intent = new Intent(Sign_up.this, MainActivity_Main.class);
                        intent.putExtra("USER_NAME2", eUserIn);

                        startActivity(intent);
                        myViewModel.addNotification("Account Setup Successful!", "Your account has been created!", R.drawable.created);


                    }
                   else {
                        activitySignUpBinding.eUser.setError("اسم المستخدم تم استخدامه مسبقام");

                    }


                });

            }


        });

        activitySignUpBinding.bsckSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        activitySignUpBinding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_up.this, ActivityMainSignIn.class);
                startActivity(intent);
            }
        });

        activitySignUpBinding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) ->

        {
            if (isChecked) {
                String username2 = activitySignUpBinding.eUser.getText().toString();
                String password2 = activitySignUpBinding.ePassword.getText().toString();
                String phone2 = activitySignUpBinding.ePassword.getText().toString();
                String name2 = activitySignUpBinding.ePassword.getText().toString();

                if (!username2.isEmpty() && !password2.isEmpty() && !phone2.isEmpty() && !name2.isEmpty()) {
                    editor.putBoolean("rememberMe1", true);
                    editor.putString("username1", username2);
                    editor.putString("password1", password2);
                    editor.putString("phone1", phone2);
                    editor.putString("name1", name2);
                    editor.apply();
                } else {
                    activitySignUpBinding.checkBox.setChecked(false);
                }
            } else {
                editor.clear();
                editor.apply();
            }
        });

        activitySignUpBinding.icEyeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (activitySignUpBinding.ePassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    activitySignUpBinding.ePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    activitySignUpBinding.icEyeOff.setBackgroundResource(R.drawable.ic_eye);
                } else {
                    activitySignUpBinding.ePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    activitySignUpBinding.icEyeOff.setBackgroundResource(R.drawable.ic_eye_off);
                }

                activitySignUpBinding.ePassword.setSelection(activitySignUpBinding.ePassword.getText().length());
            }
        });

    }

    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber) // رقم الهاتف
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        Toast.makeText(Sign_up.this, "فشل التحقق: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
                        super.onCodeSent(verificationId, token);
                        mVerificationId = verificationId;
                        mResendToken = token;

                        Intent intent = new Intent(Sign_up.this, VerifyCodeActivity.class);
                        intent.putExtra("verificationId", verificationId);
                        startActivity(intent);
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}

