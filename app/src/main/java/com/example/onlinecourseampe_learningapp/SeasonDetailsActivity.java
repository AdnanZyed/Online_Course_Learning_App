package com.example.onlinecourseampe_learningapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.onlineSeasonampe_learningapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class SeasonDetailsActivity extends AppCompatActivity {
    private String userName;
    private TabPagerAdapter adapter;
    private int seasonId;
    private ImageView seasonImageView;
    private TextView Price_dep;

    private Button bt_Buy;
    private TextView seasonNameTextView;
    private TextView seasonNameTextView1;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private String seasonName;
    private TextView priceTextView;
    private String seasonDescription;
    private String catigories;
    private String seasonUserName;
    private String seasonName1;
    private My_View_Model myViewModel;
    int seasonPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_season_details);

        Price_dep = findViewById(R.id.price_dep);
        bt_Buy = findViewById(R.id.bt_buy);
        seasonNameTextView = findViewById(R.id.Season_name);
        priceTextView = findViewById(R.id.price);
        seasonNameTextView1 = findViewById(R.id.Season_name1);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager1);
        Price_dep.setPaintFlags(Price_dep.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        myViewModel = new ViewModelProvider(this).get(My_View_Model.class);


        seasonId = getIntent().getIntExtra("COURSE_ID", -1);
        seasonUserName = getIntent().getStringExtra("TEACHER_USER_NAME");
        seasonName = getIntent().getStringExtra("COURSE_NAME");
        catigories = getIntent().getStringExtra("COURSE_CATEGORIES");
        seasonDescription = getIntent().getStringExtra("COURSE_DESCRIPTION");
        userName = getIntent().getStringExtra("USER");
        seasonName1 = getIntent().getStringExtra("COURSE_NAME1");
        seasonPrice = getIntent().getIntExtra("COURSE_PRICE", 0);
        byte[] seasonImage = getIntent().getByteArrayExtra("COURSE_IMAGE");
        String expertName = getIntent().getStringExtra("TEACHER_NAME");


        Bundle bundle = new Bundle();
        bundle.putInt("COURSE_ID1", seasonId);


        bt_Buy.setText("Enroll Season - $" + seasonPrice);
        myViewModel.isFarmerSeasonExists(userName, seasonId, true).observe((this), isHad -> {
            if (isHad) {
                bt_Buy.setVisibility(View.GONE);

            }
        });


        Fragment targetFragment = new ReviewsFragment();
        targetFragment.setArguments(bundle);
        seasonNameTextView.setText(seasonName);
        seasonNameTextView1.setText(catigories);


        adapter = new TabPagerAdapter(this, seasonUserName, seasonId, userName, seasonDescription);
        viewPager.setAdapter(adapter);
        seasonImageView = findViewById(R.id.img_Season);


        int rival = seasonPrice + seasonPrice / 4;
        priceTextView.setText(String.format("$%d", seasonPrice));
        Price_dep.setText(String.format("$%d", rival));


        if (seasonImage != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(seasonImage, 0, seasonImage.length);
            seasonImageView.setImageBitmap(bitmap);
        }


        bt_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SeasonDetailsActivity.this, EnrollSeasonActivity.class);
                intent.putExtra("USER", userName);
                intent.putExtra("COURSE_ID", seasonId);
                intent.putExtra("PRICE", seasonPrice);
                startActivity(intent);
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("About");
                    break;
                case 1:
                    tab.setText("Steps");
                    break;
                case 2:
                    tab.setText("Reviews");
                    break;
            }
        }).attach();

    }
}