package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.onlineSeasonampe_learningapp.R;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateSeason extends AppCompatActivity {
    private static final int REQUEST_CODE_IMAGE_A = 1;
    private static final int REQUEST_CODE_IMAGE_C = 2;

    private EditText etSeasonName, etDescription, etPrice, etExpertUsername, etImageUrl, et_image_urlC;
    private Spinner spCategory;
    private ImageView ivSeasonImage, iv_season_imageC;
    private Button btnSelectImage, btn_select_imageC, btnSaveSeason, btnLoadImageFromUrl, btn_load_image_from_urlC;
    private byte[] seasonImageBytes;
    private String NAME;
    private byte[] Image;
    private TextView seasonId1;
    private String Price;
    private String Categorie;
    private String Description;
    private byte[] Picture;
    private String USER_Name;
    private byte[] seasonImageBytesC;
    private My_View_Model myViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_season);

        etSeasonName = findViewById(R.id.et_Season_nameA1);
        etDescription = findViewById(R.id.et_Season_descriptionA1);
        etPrice = findViewById(R.id.et_priceA1);
        seasonId1 = findViewById(R.id.Season_id);
        etExpertUsername = findViewById(R.id.et_expert_usernameA1);
        spCategory = findViewById(R.id.sp_categoryA1);
        btnSaveSeason = findViewById(R.id.btn_save_SeasonA1);

        btnLoadImageFromUrl = findViewById(R.id.btn_load_image_from_urlA1);
        etImageUrl = findViewById(R.id.et_image_urlA1);
        ivSeasonImage = findViewById(R.id.iv_Season_imageA1);
        btnSelectImage = findViewById(R.id.btn_select_imageA1);

        btn_load_image_from_urlC = findViewById(R.id.btn_load_image_from_urlC1);
        et_image_urlC = findViewById(R.id.et_image_urlC1);
        iv_season_imageC = findViewById(R.id.iv_Season_imageC1);
        btn_select_imageC = findViewById(R.id.btn_select_imageC1);

        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);

        Intent intent = getIntent();
        seasonId1.setText(intent.getIntExtra("ID", -1) + "");
        etPrice.setText(intent.getIntExtra("Price", -1) + "");
       // spCategory.setTextAlignment(Integer.parseInt(intent.getStringExtra("Categorie")));


        etSeasonName.setText(intent.getStringExtra("NAME"));
        etExpertUsername.setText(intent.getStringExtra("USER_Name"));
        etDescription.setText(intent.getStringExtra("Description"));


        iv_season_imageC.setImageResource(R.drawable.certificate);


        byte[] byteArray1 =  intent.getByteArrayExtra("Image");


        if (byteArray1 != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray1, 0, byteArray1.length);

            ivSeasonImage.setImageBitmap(bitmap);
        }




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
                        Toast.makeText(UpdateSeason.this, "Image failed to load", Toast.LENGTH_SHORT).show();
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

        String season_Id = seasonId1.getText().toString().trim();
        myViewModel.getAllSeasonsById(Integer.parseInt(season_Id)).observe(UpdateSeason.this, seasons -> {


            NAME = seasons.get(0).getSeason_NAME();
            Image = seasons.get(0).getImage();
            Price = seasons.get(0).getPrice() + "";
            Categorie = seasons.get(0).getCategorie();
            Description = seasons.get(0).getDescription();
            Picture = seasons.get(0).getProfilePicture();
            USER_Name = seasons.get(0).getExpert_USER_Name();


        });


        String seasonName = etSeasonName.getText().toString().trim();

        if (seasonImageBytes == null) {

            seasonImageBytes = Image;
        }
        if (seasonImageBytesC == null) {

            seasonImageBytesC = Picture;
        }
        if (seasonName.isEmpty()) {
            seasonName = NAME;

        }
        String description = etDescription.getText().toString().trim();

        if (description.isEmpty()) {
            description = Description;

        }
        String price = etPrice.getText().toString().trim();
        if (price.isEmpty()) {
            price = Price;

        }

        String category = spCategory.getSelectedItem().toString();
        if (category.isEmpty()) {
            category = Categorie;

        }
        String expertUsername = etExpertUsername.getText().toString().trim();
        if (expertUsername.isEmpty()) {
            expertUsername = USER_Name;

        }
        if (season_Id.isEmpty()) {
            Toast.makeText(this, "Please enter your season ID ", Toast.LENGTH_SHORT).show();
            return;
        }

        Season season = new Season(Integer.parseInt(season_Id), seasonName, seasonImageBytes, Integer.parseInt(price), category, description, null, seasonImageBytesC, false, false, false, expertUsername, null, 0, 0);

        myViewModel.updateSeason(season);
        Toast.makeText(this, "The season has been saved successfully", Toast.LENGTH_SHORT).show();
        myViewModel.addNotification("Today's Special Offers", "You get a special promo today!", R.drawable.offered);
        myViewModel.addNotification("New Category Seasons!", "Now the 3D design season is available", R.drawable.offered1);


        finish();
    }
}
