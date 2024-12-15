package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.onlinecourseampe_learningapp.databinding.ActivitySignUpBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Sign_up extends AppCompatActivity {
    private EditText phoneEditText;
    private Button btnSignUp;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private String mVerificationId;
    private ActivitySignUpBinding activitySignUpBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activitySignUpBinding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(activitySignUpBinding.getRoot());



        mAuth = FirebaseAuth.getInstance();
        phoneEditText = findViewById(R.id.Phone);
        btnSignUp = findViewById(R.id.Sign_up);





        My_Database db = My_Database.getDatabase(this);
        Student_Dao student_dao = db.studentDao();

        // زر التسجيل
        activitySignUpBinding.SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eUserIn = activitySignUpBinding.eUser.getText().toString().trim();
                String ePasswordIn = activitySignUpBinding.ePassword.getText().toString().trim();
                String PhoneIn = activitySignUpBinding.Phone.getText().toString().trim();
                String nameIn = activitySignUpBinding.name.getText().toString().trim();

                // التحقق من الحقول الفارغة
                if (eUserIn.isEmpty()) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم مطلوب");
                    return;
                }

                if (ePasswordIn.isEmpty()) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور مطلوبة");
                    return;
                }

                // التحقق من طول اسم المستخدم
                if (eUserIn.length() < 3 || eUserIn.length() > 30) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم يجب أن يكون بين 3 و 20 حرفًا");
                    return;
                }

                // التحقق من الحروف المسموح بها في اسم المستخدم
                if (!eUserIn.matches("^[a-zA-Z0-9@#$%^&+=!_]+$")) {
                    activitySignUpBinding.eUser.setError("اسم المستخدم يجب أن يحتوي فقط على حروف وأرقام");
                    return;
                }

                // التحقق من طول كلمة المرور
                if (ePasswordIn.length() < 8) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور يجب أن تكون 8 أحرف على الأقل");
                    return;
                }

                // التحقق من قوة كلمة المرور
                String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
                if (!ePasswordIn.matches(passwordPattern)) {
                    activitySignUpBinding.ePassword.setError("كلمة المرور يجب أن تحتوي على حرف كبير وصغير ورقم ورمز خاص");
                    return;
                }

                // التحقق من الحقل الفارغ
                if (nameIn.isEmpty()) {
                    activitySignUpBinding.name.setError("الاسم مطلوب");
                    return;
                }

                // التحقق إذا كان الحقل فارغًا
                if (PhoneIn.isEmpty()) {
                    activitySignUpBinding.Phone.setError("رقم الهاتف مطلوب");
                    return;
                }

                // التحقق من أن الرقم يحتوي على أرقام فقط




                if (!PhoneIn.matches("^[0-9]+$")) {
                    activitySignUpBinding.Phone.setError("الرجاء إدخال رقم هاتف صحيح");
                    return;
                }

                // التحقق من طول الرقم (مثال: يجب أن يكون الرقم مكونًا من 10 أرقام)
                if (PhoneIn.length() != 10) {
                    activitySignUpBinding.Phone.setError("رقم الهاتف يجب أن يتكون من 10 أرقام");
                    return;
                }

                // إضافة الطالب إلى قاعدة البيانات
                new Thread(() -> {
                    // إنشاء كائن طالب باستخدام الـ Setters
                    Student student = new Student();
                    student.setS_name(nameIn);
                    student.setStudent_user_name(eUserIn);
                    student.setStudent_Password(ePasswordIn);
                    student.setPhone_nomber(Integer.parseInt(PhoneIn));

                    // إدخال الطالب إلى قاعدة البيانات
                    student_dao.insertStudent(student);
                }).start();
                sendVerificationCode(PhoneIn);

            }
        });

        // زر الرجوع
        activitySignUpBinding.bsckSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // الانتقال إلى تسجيل الدخول
        activitySignUpBinding.SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Sign_up.this, ActivityMainSignIn.class);
                startActivity(intent);
            }
        });

        activitySignUpBinding.icEyeOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // التحقق إذا كانت كلمة المرور مرئية أم مخفية
                if (activitySignUpBinding.ePassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())) {
                    // إظهار كلمة المرور
                    activitySignUpBinding.ePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    activitySignUpBinding.icEyeOff.setBackgroundResource(R.drawable.ic_eye); // تغيير الأيقونة إلى "إظهار"
                } else {
                    // إخفاء كلمة المرور
                    activitySignUpBinding.ePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    activitySignUpBinding.icEyeOff.setBackgroundResource(R.drawable.ic_eye_off); // تغيير الأيقونة إلى "إخفاء"
                }

                // إعادة تعيين المؤشر إلى آخر مكان في النص
                activitySignUpBinding.ePassword.setSelection(activitySignUpBinding.ePassword.getText().length());
            }
        });

//
//        private void sendVerificationCode (String phoneNumber){
//            PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
//                    .setPhoneNumber(phoneNumber) // رقم الهاتف
//                    .setTimeout(60L, TimeUnit.SECONDS)
//                    .setActivity(this)
//                    .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                        @Override
//                        public void onVerificationCompleted(PhoneAuthCredential credential) {
//                            // عندما يتم التحقق تلقائيًا
//                        }
//
//                        @Override
//                        public void onVerificationFailed(FirebaseException e) {
//                            Toast.makeText(Sign_up.this, "فشل التحقق: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//
//                        @Override
//                        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token) {
//                            super.onCodeSent(verificationId, token);
//                            mVerificationId = verificationId;
//                            mResendToken = token;
//
//                            // الانتقال إلى واجهة إدخال الرمز
//                            Intent intent = new Intent(Sign_up.this, ActivityMainSignIn.class);
//                            intent.putExtra("verificationId", verificationId);
//                            startActivity(intent);
//                        }
//                    }).build();
//            PhoneAuthProvider.verifyPhoneNumber(options);
//
//        }
    }
    private void sendVerificationCode(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber) // رقم الهاتف
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential credential) {
                        // عندما يتم التحقق تلقائيًا
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

                        // الانتقال إلى واجهة إدخال الرمز
                        Intent intent = new Intent(Sign_up.this,VerifyCodeActivity.class);
                        intent.putExtra("verificationId", verificationId);
                        startActivity(intent);
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}

