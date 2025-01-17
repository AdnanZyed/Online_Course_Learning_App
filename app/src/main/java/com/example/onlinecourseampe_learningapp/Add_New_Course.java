package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Add_New_Course extends AppCompatActivity {
    private EditText etCourseName, etDescription, etPrice, etTeacherUsername, etImageUrl; // إضافة EditText لمدخل رابط الصورة
    private Spinner spCategory;
    private ImageView ivCourseImage;
    private Button btnSelectImage, btnSaveCourse, btnLoadImageFromUrl; // إضافة زر لتحميل الصورة من الإنترنت
    private byte[] courseImageBytes;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_course);

        // ربط العناصر
        etCourseName = findViewById(R.id.et_course_nameA);
        etDescription = findViewById(R.id.et_course_descriptionA);
        etPrice = findViewById(R.id.et_priceA);
        etTeacherUsername = findViewById(R.id.et_teacher_usernameA);
        spCategory = findViewById(R.id.sp_categoryA);
        ivCourseImage = findViewById(R.id.iv_course_imageA);
        btnSelectImage = findViewById(R.id.btn_select_imageA);
        btnSaveCourse = findViewById(R.id.btn_save_courseA);
        etImageUrl = findViewById(R.id.et_image_urlA); // حقل URL
        btnLoadImageFromUrl = findViewById(R.id.btn_load_image_from_urlA); // زر تحميل الصورة من الإنترنت

        // إنشاء ViewModel
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        // اختيار الصورة من المعرض
        btnSelectImage.setOnClickListener(v -> selectImage());

        // تحميل الصورة من الإنترنت
        btnLoadImageFromUrl.setOnClickListener(v -> loadImageFromUrl());

        // حفظ الكورس
        btnSaveCourse.setOnClickListener(v -> saveCourse());
    }

    private void selectImage() {
        // فتح معرض الصور
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    // تحميل الصورة من الإنترنت
    private void loadImageFromUrl() {
        String imageUrl = etImageUrl.getText().toString().trim();
        if (imageUrl.isEmpty()) {
            Toast.makeText(this, "يرجى إدخال رابط الصورة", Toast.LENGTH_SHORT).show();
            return;
        }

        Picasso.get()
                .load(imageUrl)
                .into(ivCourseImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        // تحويل الصورة إلى byte[] عند تحميلها بنجاح
                        ivCourseImage.setDrawingCacheEnabled(true);
                        Bitmap bitmap = ivCourseImage.getDrawingCache();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        courseImageBytes = stream.toByteArray();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(Add_New_Course.this, "فشل تحميل الصورة", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            ivCourseImage.setImageURI(imageUri);

            try {
                // تحويل الصورة إلى byte[]
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                courseImageBytes = stream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCourse() {
        // جمع البيانات
        String courseName = etCourseName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int price = Integer.parseInt(etPrice.getText().toString().trim());
        String category = spCategory.getSelectedItem().toString();
        String teacherUsername = etTeacherUsername.getText().toString().trim();

        // التحقق من المدخلات
//        if (courseName.isEmpty() || description.isEmpty() || teacherUsername.isEmpty() || courseImageBytes == null) {
//            Toast.makeText(this, "يرجى إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
//            return;
//        }

        // إنشاء كائن الكورس
        Course course = new Course(0, courseName, courseImageBytes, price, category, description, null, null, false,false, teacherUsername);

        // إدخال الكورس في قاعدة البيانات
        myViewModel.insertCourse(course);
        Toast.makeText(this, "تم حفظ الكورس بنجاح", Toast.LENGTH_SHORT).show();

        // إنهاء النشاط
        finish();
    }
}
