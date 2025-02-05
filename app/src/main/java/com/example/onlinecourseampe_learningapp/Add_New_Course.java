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

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Add_New_Course extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_A = 1;
    private static final int REQUEST_CODE_IMAGE_C = 2;

    private EditText etCourseName, etDescription, etPrice, etTeacherUsername, etImageUrl, et_image_urlC;
    private Spinner spCategory;
    private ImageView ivCourseImage, iv_course_imageC;
    private Button btnSelectImage, btn_select_imageC, btnSaveCourse, btnLoadImageFromUrl, btn_load_image_from_urlC;
    private byte[] courseImageBytes;
    private byte[] courseImageBytesC;
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
        btnSaveCourse = findViewById(R.id.btn_save_courseA);

        btnLoadImageFromUrl = findViewById(R.id.btn_load_image_from_urlA);
        etImageUrl = findViewById(R.id.et_image_urlA);
        ivCourseImage = findViewById(R.id.iv_course_imageA);
        btnSelectImage = findViewById(R.id.btn_select_imageA);

        btn_load_image_from_urlC = findViewById(R.id.btn_load_image_from_urlC);
        et_image_urlC = findViewById(R.id.et_image_urlC);
        iv_course_imageC = findViewById(R.id.iv_course_imageC);
        btn_select_imageC = findViewById(R.id.btn_select_imageC);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        btnSelectImage.setOnClickListener(v -> selectImage(REQUEST_CODE_IMAGE_A));
        btn_select_imageC.setOnClickListener(v -> selectImage(REQUEST_CODE_IMAGE_C));
        btnLoadImageFromUrl.setOnClickListener(v -> loadImageFromUrl(etImageUrl, ivCourseImage, true));
        btn_load_image_from_urlC.setOnClickListener(v -> loadImageFromUrl(et_image_urlC, iv_course_imageC, false));
        btnSaveCourse.setOnClickListener(v -> saveCourse());
    }

    private void selectImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    private void loadImageFromUrl(EditText urlField, ImageView imageView, boolean isMainImage) {
        String imageUrl = urlField.getText().toString().trim();
        if (imageUrl.isEmpty()) {
            Toast.makeText(this, "يرجى إدخال رابط الصورة", Toast.LENGTH_SHORT).show();
            return;
        }

        Picasso.get()
                .load(imageUrl)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setDrawingCacheEnabled(true);
                        Bitmap bitmap = imageView.getDrawingCache();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                        if (isMainImage) {
                            courseImageBytes = stream.toByteArray();
                        } else {
                            courseImageBytesC = stream.toByteArray();
                        }
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
        if (resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                if (requestCode == REQUEST_CODE_IMAGE_A) {
                    ivCourseImage.setImageURI(imageUri);
                    courseImageBytes = stream.toByteArray();
                } else if (requestCode == REQUEST_CODE_IMAGE_C) {
                    iv_course_imageC.setImageURI(imageUri);
                    courseImageBytesC = stream.toByteArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCourse() {
        String courseName = etCourseName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int price = Integer.parseInt(etPrice.getText().toString().trim());
        String category = spCategory.getSelectedItem().toString();
        String teacherUsername = etTeacherUsername.getText().toString().trim();

        if (courseName.isEmpty() || description.isEmpty() || teacherUsername.isEmpty() || courseImageBytes == null) {
            Toast.makeText(this, "يرجى إدخال جميع الحقول", Toast.LENGTH_SHORT).show();
            return;
        }

        Course course = new Course(0, courseName, courseImageBytes, price, category, description, null, courseImageBytesC, false, false, false, teacherUsername, null, 0, 0);

        myViewModel.insertCourse(course);
        Toast.makeText(this, "تم حفظ الكورس بنجاح", Toast.LENGTH_SHORT).show();
        myViewModel.addNotification("Today's Special Offers", "You get a special promo today!", R.drawable.offered);
        myViewModel.addNotification("New Category Courses!", "Now the 3D design course is available", R.drawable.offered1);


        finish();
    }
}
