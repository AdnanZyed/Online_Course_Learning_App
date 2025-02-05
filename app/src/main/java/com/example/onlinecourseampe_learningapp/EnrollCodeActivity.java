package com.example.onlinecourseampe_learningapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class EnrollCodeActivity extends AppCompatActivity {
    EditText otpDigit1, otpDigit2, otpDigit3, otpDigit4;
    String userName;
    int card_num;
    String teacher_USER_Name;
    int courseId;
    int otpCodeInt;
    int price;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_code);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        otpDigit1 = findViewById(R.id.otp_digit_1);
        otpDigit2 = findViewById(R.id.otp_digit_2);
        ImageView imageView = findViewById(R.id.back_icon_enrollCoude);
        otpDigit3 = findViewById(R.id.otp_digit_3);
        otpDigit4 = findViewById(R.id.otp_digit_4);

        otpDigit1.addTextChangedListener(new OTPTextWatcher(otpDigit1, otpDigit2));
        otpDigit2.addTextChangedListener(new OTPTextWatcher(otpDigit2, otpDigit3));
        otpDigit3.addTextChangedListener(new OTPTextWatcher(otpDigit3, otpDigit4));
        otpDigit4.addTextChangedListener(new OTPTextWatcher(otpDigit4, null));

        Button continueButton = findViewById(R.id.bt_buy1);
        userName = getIntent().getStringExtra("USER");
        courseId = getIntent().getIntExtra("COURSE_ID", -1);
        price = getIntent().getIntExtra("PRICE", -1);

        continueButton.setText("Enroll Course - $" + price);
//

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        continueButton.setOnClickListener(v -> {
            String digit1 = otpDigit1.getText().toString().trim();
            String digit2 = otpDigit2.getText().toString().trim();
            String digit3 = otpDigit3.getText().toString().trim();
            String digit4 = otpDigit4.getText().toString().trim();


            if (digit1.isEmpty() || digit2.isEmpty() || digit3.isEmpty() || digit4.isEmpty()) {
                Toast.makeText(this, "Please enter all 4 digits of the OTP.", Toast.LENGTH_SHORT).show();
                return;
            }

            String otpCode = digit1 + digit2 + digit3 + digit4;
            otpCodeInt = Integer.parseInt(otpCode);

            if (otpCode.length() != 4) {
                Toast.makeText(this, "OTP Code must be 4 digits.", Toast.LENGTH_SHORT).show();
                return;
            }
            myViewModel.getAllStudentByUser(userName).observe(this, students -> {
                if (students != null && !students.isEmpty()) {
                    card_num = students.get(0).getCard_Number();

                    if (otpCodeInt == card_num) {
                        new Handler(Looper.getMainLooper()).postDelayed(() -> {

                            showCustomDialog();
                        }, 2000);

                        myViewModel.addNotification("Credit Card Connected!", "Credit Card has been linked!", R.drawable.connected_card);

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
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);


        ImageView dialogImage = dialog.findViewById(R.id.dialog_image);
        TextView mainText = dialog.findViewById(R.id.dialog_main_text);
        TextView secondaryText = dialog.findViewById(R.id.dialog_secondary_text);
        Button viewCourseButton = dialog.findViewById(R.id.btn_view_course);
        Button cancelButton = dialog.findViewById(R.id.btn_cancel);

        mainText.setText("Enroll Course Successful!");
        secondaryText.setText("You have successfully made payment and enrolled the course");
        dialogImage.setImageResource(R.drawable.img_2);


        myViewModel.getAllCoursesById(courseId).observe(this, courses -> {
            teacher_USER_Name = courses.get(0).getTeacher_USER_Name();


        });
        viewCourseButton.setOnClickListener(v -> {

            myViewModel.isStudentCourseExists(userName, courseId, true).observe((this), isHad -> {
                myViewModel.isStudentCourseExistsC(userName, courseId, true).observe((this), isHadC -> {
                    myViewModel.isStudentCourseExistsB(userName, courseId, true).observe((this), isHadb -> {
                        if (!isHadC && !isHadb) {
                            Student_Course studentCourse = new Student_Course(userName, courseId, isHadb, true, false, 0);
                            myViewModel.insertStudentCourse(studentCourse);
                            Student_Teacher studentTeacher = new Student_Teacher(0, userName, teacher_USER_Name);
                            myViewModel.insertStudentTeacher(studentTeacher);
                            Toast.makeText(this, "تم الشراء بنجاح .", Toast.LENGTH_SHORT).show();
                            myViewModel.addNotification("Payment Successful!", "You have made a course payment", R.drawable.connected_card);


                        } else if (isHadC || isHadb) {
                            Student_Course studentCourse = new Student_Course(userName, courseId, isHadb, true, false, 0);
                            myViewModel.updateCourseStudent(studentCourse);
                            myViewModel.addNotification("Payment Successful!", "You have made a course payment", R.drawable.connected_card);

                        }

                    });


                    otpDigit1.setText("");
                    otpDigit2.setText("");
                    otpDigit3.setText("");
                    otpDigit4.setText("");
                    dialog.dismiss();
                });
            });
        });

        cancelButton.setOnClickListener(v -> {
            dialog.dismiss();
        });
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            window.setAttributes(params);
            window.setBackgroundDrawableResource(android.R.color.transparent); // إزالة الخلفية الافتراضية
            window.setBackgroundDrawableResource(R.drawable.rounded_dialog_background);

        }
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
