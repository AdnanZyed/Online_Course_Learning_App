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

import com.example.onlineSeasonampe_learningapp.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Add_New_Season extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_A = 1;
    private static final int REQUEST_CODE_IMAGE_C = 2;

    private EditText etSeasonName, etDescription, etPrice, etExpertUsername, etImageUrl, et_image_urlC;
    private Spinner spCategory;
    private ImageView ivSeasonImage, iv_season_imageC;
    private Button btnSelectImage, btn_select_imageC, btnSaveSeason, btnLoadImageFromUrl, btn_load_image_from_urlC;
    private byte[] seasonImageBytes;
    private byte[] seasonImageBytesC;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_season);

        etSeasonName = findViewById(R.id.et_Season_nameA);
        etDescription = findViewById(R.id.et_Season_descriptionA);
        etPrice = findViewById(R.id.et_priceA);
        etExpertUsername = findViewById(R.id.et_expert_usernameA);
        spCategory = findViewById(R.id.sp_categoryA);
        btnSaveSeason = findViewById(R.id.btn_save_SeasonA);

        btnLoadImageFromUrl = findViewById(R.id.btn_load_image_from_urlA);
        etImageUrl = findViewById(R.id.et_image_urlA);
        ivSeasonImage = findViewById(R.id.iv_Season_imageA);
        btnSelectImage = findViewById(R.id.btn_select_imageA);

        btn_load_image_from_urlC = findViewById(R.id.btn_load_image_from_urlC);
        et_image_urlC = findViewById(R.id.et_image_urlC);
        iv_season_imageC = findViewById(R.id.iv_Season_imageC);
        btn_select_imageC = findViewById(R.id.btn_select_imageC);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        btnSelectImage.setOnClickListener(v -> selectImage(REQUEST_CODE_IMAGE_A));
        btn_select_imageC.setOnClickListener(v -> selectImage(REQUEST_CODE_IMAGE_C));
        btnLoadImageFromUrl.setOnClickListener(v -> loadImageFromUrl(etImageUrl, ivSeasonImage, true));
        btn_load_image_from_urlC.setOnClickListener(v -> loadImageFromUrl(et_image_urlC, iv_season_imageC, false));
        btnSaveSeason.setOnClickListener(v -> saveSeason());
    }

    private void selectImage(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, requestCode);
    }

    private void loadImageFromUrl(EditText urlField, ImageView imageView, boolean isMainImage) {
        String imageUrl = urlField.getText().toString().trim();
        if (imageUrl.isEmpty()) {
            Toast.makeText(this, "Please enter the image link", Toast.LENGTH_SHORT).show();
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
                            seasonImageBytes = stream.toByteArray();
                        } else {
                            seasonImageBytesC = stream.toByteArray();
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(Add_New_Season.this, "Image failed to load", Toast.LENGTH_SHORT).show();
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
                    ivSeasonImage.setImageURI(imageUri);
                    seasonImageBytes = stream.toByteArray();
                } else if (requestCode == REQUEST_CODE_IMAGE_C) {
                    iv_season_imageC.setImageURI(imageUri);
                    seasonImageBytesC = stream.toByteArray();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSeason() {
        String seasonName = etSeasonName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        int price = Integer.parseInt(etPrice.getText().toString().trim());
        String category = spCategory.getSelectedItem().toString();
        String expertUsername = etExpertUsername.getText().toString().trim();

        if (seasonName.isEmpty() || description.isEmpty() || expertUsername.isEmpty() || seasonImageBytes == null) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Season z = new Season(0, seasonName, seasonImageBytes, price, category, description, null, seasonImageBytesC, false, false, false, expertUsername, null, 0, 0);

        myViewModel.insertSeason(z);
        Toast.makeText(this, "The season has been saved successfully", Toast.LENGTH_SHORT).show();
        myViewModel.addNotification("Today's Special Offers", "You get a special promo today!", R.drawable.offered);
        myViewModel.addNotification("New Category Seasons!", "Now the 3D design season is available", R.drawable.offered1);


        finish();
    }
}
