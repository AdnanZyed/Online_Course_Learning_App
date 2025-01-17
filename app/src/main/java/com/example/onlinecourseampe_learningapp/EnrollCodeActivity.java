package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EnrollCodeActivity extends AppCompatActivity {
    EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    int course_Id = 3;
    String userName = "aDNAN@123";
    int card_num;
    String teacher_USER_Name;
//    int courseId;
    int otpCodeInt;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_code);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        otpDigit1 = findViewById(R.id.otp_digit_1);
        otpDigit2 = findViewById(R.id.otp_digit_2);
        otpDigit3 = findViewById(R.id.otp_digit_3);
        otpDigit4 = findViewById(R.id.otp_digit_4);

        otpDigit1.addTextChangedListener(new OTPTextWatcher(otpDigit1, otpDigit2));
        otpDigit2.addTextChangedListener(new OTPTextWatcher(otpDigit2, otpDigit3));
        otpDigit3.addTextChangedListener(new OTPTextWatcher(otpDigit3, otpDigit4));
        otpDigit4.addTextChangedListener(new OTPTextWatcher(otpDigit4, null));

        Button continueButton = findViewById(R.id.bt_buy1);


//        Intent intent = getIntent();
//        userName = intent.getStringExtra("USER_NAME1");
//        Log.d("EnrollCodeActivity", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM " + userName);

//        Intent intent2 = getIntent();
//        courseId = intent2.getIntExtra("COURSE_ID1", -1);
//        Log.d("EnrollCodeActivity", "MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM " + courseId);

        // إعداد حدث النقر على الزر
        continueButton.setOnClickListener(v -> {
            // جلب النصوص من الحقول
            String digit1 = otpDigit1.getText().toString().trim();
            String digit2 = otpDigit2.getText().toString().trim();
            String digit3 = otpDigit3.getText().toString().trim();
            String digit4 = otpDigit4.getText().toString().trim();


            // تحقق من أن جميع الحقول ممتلئة
            if (digit1.isEmpty() || digit2.isEmpty() || digit3.isEmpty() || digit4.isEmpty()) {
                Toast.makeText(this, "Please enter all 4 digits of the OTP.", Toast.LENGTH_SHORT).show();
                return;
            }

            // تكوين النص الكامل
            String otpCode = digit1 + digit2 + digit3 + digit4;
            otpCodeInt = Integer.parseInt(otpCode);

            // تحقق من طول الكود
            if (otpCode.length() != 4) {
                Toast.makeText(this, "OTP Code must be 4 digits.", Toast.LENGTH_SHORT).show();
                return;
            }
            myViewModel.getAllStudentByUser(userName).observe(this, students -> {
                if (students != null && !students.isEmpty()) {
                    card_num = students.get(0).getCard_Number();
                    Log.d("ActivityMainSignIn", "IIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII " + otpCodeInt + card_num);

                    // الآن يمكنك مقارنة الرقم المخزن مع الكود المدخل بعد التأكد من تحميل البيانات
                    if (otpCodeInt == card_num) {
                        showCustomDialog();
                    } else {
                        Toast.makeText(this, "الرقم لا يتطابق " + otpCode, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "لم يتم العثور على بيانات الطالب.", Toast.LENGTH_SHORT).show();
                }
            });


        });
    }

    private void showCustomDialog() {
        // إنشاء الـ Dialog
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);

        // إعداد عناصر الـ Dialog
        ImageView dialogImage = dialog.findViewById(R.id.dialog_image);
        TextView mainText = dialog.findViewById(R.id.dialog_main_text);
        TextView secondaryText = dialog.findViewById(R.id.dialog_secondary_text);
        Button viewCourseButton = dialog.findViewById(R.id.btn_view_course);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);

        // تخصيص النصوص والصورة
        mainText.setText("Enroll in the Course");
        secondaryText.setText("Would you like to enroll in this course?");
        dialogImage.setImageResource(R.drawable.unnamed1);

        myViewModel.getAllCoursesById(1).observe(this, courses -> {
            teacher_USER_Name = courses.get(0).getTeacher_USER_Name();


        });
        // إعداد الأزرار
        viewCourseButton.setOnClickListener(v -> {
            // الانتقال إلى شاشة الدورة
            Intent intent = new Intent(EnrollCodeActivity.this, MainActivity_Main.class);
            intent.putExtra("SHOW_CUSTOM_NAVIGATION", true);  // أضف هذا المتغير ليتم الكشف عنه في الـ MainActivity

            Student_Course studentCourse = new Student_Course(0, userName, course_Id);
            myViewModel.insertStudentCourse(studentCourse);
            Student_Teacher studentTeacher = new Student_Teacher(0, userName, teacher_USER_Name);
            myViewModel.insertStudentTeacher(studentTeacher);

            startActivity(intent);

            dialog.dismiss();
        });

        cancelButton.setOnClickListener(v -> {
            // إغلاق الـ Dialog
            dialog.dismiss();
        });

        // عرض الـ Dialog
        dialog.show();
    }

    private class OTPTextWatcher implements TextWatcher {
        private final EditText currentEditText;
        private final EditText nextEditText;

        public OTPTextWatcher(EditText currentEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    }
}
