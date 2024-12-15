package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class VerifyCodeActivity extends AppCompatActivity {

    private EditText verificationCodeEditText;
    private Button btnVerify;
    private FirebaseAuth mAuth;
    private String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_phone);

        // Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();
        verificationCodeEditText = findViewById(R.id.verificationCodeEditText);
        btnVerify = findViewById(R.id.btnVerify);

        // Get the verification ID from the intent
        mVerificationId = getIntent().getStringExtra("verificationId");

        btnVerify.setOnClickListener(v -> {
            String code = verificationCodeEditText.getText().toString().trim();
            if (TextUtils.isEmpty(code)) {
                verificationCodeEditText.setError("يرجى إدخال رمز التحقق");
                return;
            }
            verifyCode(code);
        });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Verification successful, navigate to the next screen
                        Intent intent = new Intent(VerifyCodeActivity.this, ActivityMainSignIn.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // Verification failed
                        Toast.makeText(VerifyCodeActivity.this, "رمز التحقق غير صحيح", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
