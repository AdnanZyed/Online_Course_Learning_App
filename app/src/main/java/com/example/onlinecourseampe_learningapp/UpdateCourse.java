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

public class UpdateCourse extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_A = 1;
    private static final int REQUEST_CODE_IMAGE_C = 2;

    private EditText etCourseName, courseId1, etDescription, etPrice, etTeacherUsername, etImageUrl, et_image_urlC;
    private Spinner spCategory;
    private ImageView ivCourseImage, iv_course_imageC;
    private Button btnSelectImage, btn_select_imageC, btnSaveCourse, btnLoadImageFromUrl, btn_load_image_from_urlC;
    private byte[] courseImageBytes;
    private  String NAME;
    private  byte[] Image;
    private String Price;
    private   String Categorie;
    private String Description;
    private byte[] Picture;
    private String USER_Name;
    private byte[] courseImageBytesC;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_course);

        etCourseName = findViewById(R.id.et_course_nameA1);
        etDescription = findViewById(R.id.et_course_descriptionA1);
        etPrice = findViewById(R.id.et_priceA1);
        courseId1 = findViewById(R.id.course_id);
        etTeacherUsername = findViewById(R.id.et_teacher_usernameA1);
        spCategory = findViewById(R.id.sp_categoryA1);
        btnSaveCourse = findViewById(R.id.btn_save_courseA1);

        btnLoadImageFromUrl = findViewById(R.id.btn_load_image_from_urlA1);
        etImageUrl = findViewById(R.id.et_image_urlA1);
        ivCourseImage = findViewById(R.id.iv_course_imageA1);
        btnSelectImage = findViewById(R.id.btn_select_imageA1);

        btn_load_image_from_urlC = findViewById(R.id.btn_load_image_from_urlC1);
        et_image_urlC = findViewById(R.id.et_image_urlC1);
        iv_course_imageC = findViewById(R.id.iv_course_imageC1);
        btn_select_imageC = findViewById(R.id.btn_select_imageC1);

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
                        Toast.makeText(UpdateCourse.this, "فشل تحميل الصورة", Toast.LENGTH_SHORT).show();
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

        String course_Id = courseId1.getText().toString().trim();
        myViewModel.getAllCoursesById(Integer.parseInt(course_Id)).observe(UpdateCourse.this, courses -> {


             NAME = courses.get(0).getCourse_NAME();
             Image = courses.get(0).getImage();
             Price = courses.get(0).getPrice()+"";
             Categorie = courses.get(0).getCategorie();
             Description = courses.get(0).getDescription();
             Picture = courses.get(0).getProfilePicture();
             USER_Name = courses.get(0).getTeacher_USER_Name();


        });


        String courseName = etCourseName.getText().toString().trim();

        if (courseImageBytes == null){

            courseImageBytes=Image;
        }   if (courseImageBytesC == null){

            courseImageBytesC=Picture;
        }
        if (courseName.isEmpty()) {
            courseName =NAME;

        }
        String description = etDescription.getText().toString().trim();

        if (description.isEmpty()) {
            description =Description;

        }
        String price = etPrice.getText().toString().trim();
        if (price.isEmpty()) {
            price =Price;

        }

        String category = spCategory.getSelectedItem().toString();
        if (category.isEmpty()) {
            category =Categorie;

        }
        String teacherUsername = etTeacherUsername.getText().toString().trim();
        if (teacherUsername.isEmpty()) {
            teacherUsername =USER_Name;

        }
        if (course_Id.isEmpty()) {
            Toast.makeText(this, "يرجى إدخال ID الكورس ", Toast.LENGTH_SHORT).show();
            return;
        }

        Course course = new Course(Integer.parseInt(course_Id), courseName, courseImageBytes, Integer.parseInt(price), category, description, null, courseImageBytesC, false, false, false, teacherUsername, null, 0, 0);

        myViewModel.updateCourse(course);
        Toast.makeText(this, "تم حفظ الكورس بنجاح", Toast.LENGTH_SHORT).show();
        myViewModel.addNotification("Today's Special Offers", "You get a special promo today!", R.drawable.offered);
        myViewModel.addNotification("New Category Courses!", "Now the 3D design course is available", R.drawable.offered1);


        finish();
    }
}
